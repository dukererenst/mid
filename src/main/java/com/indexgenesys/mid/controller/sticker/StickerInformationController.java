/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.sticker;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.enums.StickerStatus;
import com.indexgenesys.mid.entity.sticker.StickerInformation;
import com.indexgenesys.mid.service.IdGenerator;
import com.indexgenesys.mid.service.MidService;
import com.indexgenesys.mid.util.JSF;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;

import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;


/**
 *
 * @author ernest
 */
@Named(value = "stickerInformationController")
@SessionScoped
public class StickerInformationController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private StickerInformation stickerInformation = new StickerInformation();
    private StickerInformation filteredRequests;
    private List<StickerInformation> stickerInformationsList = new ArrayList<>();
    private List<StickerInformation> filteredList;
    @Inject
    MidService stickerInformationFacade;
    @Inject
    IdGenerator idGenerator;
    private StickerInformation stickerInformationToDelete;
    private StickerInformation selectedRequest;
    private LazyDataModel<StickerInformation> lazyRequests;
    private String base64QRCode;

    public StickerInformationController() {
    }

    @PostConstruct
    public void init() {
        lazyRequests = new LazyDataModel<>() {

            
            @Override
            public int count(Map<String, FilterMeta> filters) {
                return stickerInformationFacade.countStatusFiltered(filters, StickerStatus.GENERATED);
            }

            @Override
            public List<StickerInformation> load(int first, int pageSize,
                    Map<String, SortMeta> sortBy,
                    Map<String, FilterMeta> filters) {
                List<StickerInformation> results = stickerInformationFacade
                        .loadStickerByStatusLazy(first, pageSize, sortBy, filters, StickerStatus.GENERATED);
                setRowCount(stickerInformationFacade.countStatusFiltered(filters, StickerStatus.GENERATED));
                return results;
            }
        };
    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(stickerInformation);

        if (stickerInformationFacade.save(stickerInformation) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        stickerInformation = new StickerInformation();
        stickerInformationsList = new ArrayList<>();
        findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
        stickerInformation = (StickerInformation) em;
    }

    @Override
    public void deleteMethod(EntityModel em) {
        StickerInformation ins = (StickerInformation) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now());
        if (stickerInformationFacade.save(ins) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.deletedSuccess);
        } else {
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        lazyRequests = new LazyDataModel<>() {

            @Override
            public String getRowKey(StickerInformation request) {
                return request.getId() != null ? request.getId() : null;
            }

            @Override
            public StickerInformation getRowData(String rowKey) {
                try {
                    UUID uuid = UUID.fromString(rowKey);
                    return stickerInformationFacade.find(StickerInformation.class, uuid);
                } catch (IllegalArgumentException e) {
                    return null;
                }
            }

            @Override
            public int count(Map<String, FilterMeta> filters) {
                return stickerInformationFacade.countStatusFiltered(filters, StickerStatus.GENERATED);
            }

            @Override
            public List<StickerInformation> load(int first, int pageSize,
                    Map<String, SortMeta> sortBy,
                    Map<String, FilterMeta> filters) {
                List<StickerInformation> results = stickerInformationFacade
                        .loadStickerByStatusLazy(first, pageSize, sortBy, filters, StickerStatus.GENERATED);
                setRowCount(stickerInformationFacade.countStatusFiltered(filters, StickerStatus.GENERATED));
                return results;
            }
        };
    }

    public void prepareNew() {
        stickerInformation = new StickerInformation();

    }

    public void viewQRCode(StickerInformation sticker) {

        try {
            stickerInformation = sticker;
            String qrData = "NIC SAMPLE DATA WITH MID";
//            String qrData = sticker.getQrCodeData();
            int size = 200;
            BitMatrix matrix = new MultiFormatWriter().encode(qrData, BarcodeFormat.QR_CODE, size, size);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);

            this.base64QRCode = "data:image/png;base64," + Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (Exception e) {
            this.base64QRCode = null;
            e.printStackTrace();
        }
    }
    
    public void exportQRCodeToPdf() {
//    if (stickerInformation == null || stickerInformation.getQrCodeData() == null) return;

    try {
        // Generate QR Code
        BitMatrix matrix = new MultiFormatWriter().encode(
            stickerInformation.getQrCodeData(), BarcodeFormat.QR_CODE, 200, 200);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);

        // Convert to byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageBytes = baos.toByteArray();

        // PDF Response
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=qr_code.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Add title
        document.add(new Paragraph("Sticker QR Code"));
        document.add(new Paragraph("Sticker Number: " + stickerInformation.getStickerNumber()));
        document.add(new Paragraph("Serial: " + stickerInformation.getSerialNumber()));
        document.add(new Paragraph(" "));

        // Add QR Image
        Image qr = Image.getInstance(imageBytes);
        qr.scaleToFit(150, 150);
        qr.setAlignment(Element.ALIGN_CENTER);
        document.add(qr);

        document.close();
        facesContext.responseComplete();

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    public StickerInformation getStickerInformation() {
        return stickerInformation;
    }

    public void setStickerInformation(StickerInformation stickerInformation) {
        this.stickerInformation = stickerInformation;
    }

    public List<StickerInformation> getStickerInformationsList() {
        return stickerInformationsList;
    }

    public void setStickerInformationsList(List<StickerInformation> stickerInformationsList) {
        this.stickerInformationsList = stickerInformationsList;
    }

    public StickerInformation getStickerInformationToDelete() {
        return stickerInformationToDelete;
    }

    public void setStickerInformationToDelete(StickerInformation stickerInformationToDelete) {
        this.stickerInformationToDelete = stickerInformationToDelete;
    }

    public StickerInformation getFilteredRequests() {
        return filteredRequests;
    }

    public void setFilteredRequests(StickerInformation filteredRequests) {
        this.filteredRequests = filteredRequests;
    }

    public StickerInformation getSelectedRequest() {
        return selectedRequest;
    }

    public void setSelectedRequest(StickerInformation selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

    public LazyDataModel<StickerInformation> getLazyRequests() {
        return lazyRequests;
    }

    public void setLazyRequests(LazyDataModel<StickerInformation> lazyRequests) {
        this.lazyRequests = lazyRequests;
    }

    public List<StickerInformation> getFilteredList() {
        return filteredList;
    }

    public void setFilteredList(List<StickerInformation> filteredList) {
        this.filteredList = filteredList;
    }

    public String getBase64QRCode() {
        return base64QRCode;
    }

    public void setBase64QRCode(String base64QRCode) {
        this.base64QRCode = base64QRCode;
    }

}
