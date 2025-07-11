/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.sticker;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.enums.StickerStatus;
import com.indexgenesys.mid.entity.setting.CompanyInformation;
import com.indexgenesys.mid.entity.sticker.StickerInformation;
import com.indexgenesys.mid.report.StickerQrCode;
import com.indexgenesys.mid.report.VehicleInformation;
import com.indexgenesys.mid.security.RSAQRUtil;
import com.indexgenesys.mid.service.IdGenerator;
import com.indexgenesys.mid.service.MidService;
import com.indexgenesys.mid.util.Function;
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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
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
    
    private CompanyInformation companyInformation = null;
    
    private StickerStatus stickerStatus = null;

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
       stickerInformationsList = stickerInformationFacade.findAll(StickerInformation.class, "stickerNumber", "companyInformation", companyInformation, "stickerStatus", stickerStatus);
    }

    public void prepareNew() {
        stickerInformation = new StickerInformation();

    }
    
    public void viewQRCode(StickerInformation sticker) {
    try {
        // Set issued & expiry date
        sticker.setIssuedDate(LocalDateTime.now());
        sticker.setExpiryDate(LocalDateTime.now());
        sticker.setStickerStatus(StickerStatus.ISSUED);

        // Generate encrypted QR code content
        StickerQrCode qrCode = generateQRCodeData(sticker);
        Gson gson = new Gson();
        String qdata = RSAQRUtil.encrypt(gson.toJson(qrCode));

        // Generate QR code image
        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix matrix = qrWriter.encode(qdata, BarcodeFormat.QR_CODE, 100, 100);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix);

        ByteArrayOutputStream qrOut = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "PNG", qrOut);

        // Load and compile JRXML template
        InputStream jrxml = StickerInformationController.class.getResourceAsStream("/reports/current_sticker.jrxml");
        if (jrxml == null) {
            throw new IllegalStateException("Report template not found on classpath!");
        }
        JasperDesign design = JRXmlLoader.load(jrxml);
        JasperReport jasperReport = JasperCompileManager.compileReport(design);

        // Load logo as BufferedImage (not InputStream)
        InputStream logoStream = StickerInformationController.class.getResourceAsStream("/reports/image/nic_bg.png");
        if (logoStream == null) {
            throw new IllegalStateException("Logo image not found at /reports/image/nic_bg.png");
        }
        //BufferedImage logoImage = ImageIO.read(logoStream);

        // Report parameters
        Map<String, Object> params = new HashMap<>();
        params.put("companyCode", sticker.getCompanyInformation().getCompanyCode());
        params.put("nic_logo", logoStream);

        // Data record for fields
        Map<String, Object> record = new HashMap<>();
        record.put("stickerNo", sticker.getSerialNumber());
        record.put("make", qrCode.getVehicleInformation().getMake());
        record.put("model", qrCode.getVehicleInformation().getModel());
        record.put("color", qrCode.getVehicleInformation().getColor());
        record.put("usage", qrCode.getVehicleInformation().getUsage());
        record.put("registrationNo", qrCode.getVehicleInformation().getRegistrationNo()); // fixed
        record.put("inception", java.sql.Date.valueOf(LocalDate.now())); // change if dynamic
        record.put("expiry", java.sql.Date.valueOf(LocalDate.now().plusYears(1))); // change if dynamic
        InputStream  inputStream = new ByteArrayInputStream(qrOut.toByteArray());
        record.put("qrCode", inputStream);

        // Wrap in a datasource
        List<Map<String, ?>> list = Collections.singletonList(record);
        JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(list);

        // Fill and export the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
        byte[] pdfBytes;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            pdfBytes = baos.toByteArray();
        }

        // Convert to Base64 for embedding
        this.base64QRCode = "data:application/pdf;base64," + Base64.getEncoder().encodeToString(pdfBytes);

    } catch (Exception e) {
        this.base64QRCode = null;
        e.printStackTrace(); // optionally replace with a logger
    }
}



    
      public StickerQrCode generateQRCodeData(StickerInformation information) {
        StickerQrCode qrCode = new StickerQrCode();
        //qrCode.setExpiryDate(information.getExpiryDate());
//        qrCode.setInsuranceCompany(information.getInsuranceCompany().getCompanyName());
        qrCode.setInsuranceCompany(information.getCompanyInformation().getCompanyCode());
        //qrCode.setIssuedDate(information.getIssuedDate());
        qrCode.setSerialNumber(information.getSerialNumber());
        qrCode.setStickerNumber(information.getStickerNumber());
        qrCode.setCheckSum(information.getCheckSum());
        VehicleInformation vi = new VehicleInformation();
        vi.setChassisNo(Function.generate(13));
        vi.setColor(Function.randomColor());
        vi.setMake(Function.randomMake());
        vi.setModel(Function.randomModel(vi.getMake()));
        vi.setRegistrationNo(Function.generateRegNo());
        vi.setUsage("PRIVATE");
        qrCode.setVehicleInformation(vi);
        return qrCode;
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

    public CompanyInformation getCompanyInformation() {
        return companyInformation;
    }

    public void setCompanyInformation(CompanyInformation companyInformation) {
        this.companyInformation = companyInformation;
    }

    public StickerStatus getStickerStatus() {
        return stickerStatus;
    }

    public void setStickerStatus(StickerStatus stickerStatus) {
        this.stickerStatus = stickerStatus;
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
