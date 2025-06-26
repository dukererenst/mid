/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.nia;

import com.indexgenesys.mid.entity.nia.response.VerificationResultData;
import com.indexgenesys.mid.util.JSF;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import static javax.print.attribute.ResolutionSyntax.DPI;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CaptureEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DialogFrameworkOptions;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ernest
 */
@Named(value = "cameraVerificationController")
@SessionScoped
public class CameraVerificationController implements Serializable {

    private CroppedImage croppedImage;

    BufferedImage bufferedImage;

    BufferedImage bufferedCroppedImage;

    private String ghanaCardPin = null;

//    @Inject
//    private LoginController loginController;
//
//    @Getter
//    @Setter
//    private Verification savedverification = null;
//
//    private GhanaCardHolder savedGhanaCardHolder = null;
//
//    private CardHolderImage savedCardHolderImage = null;

    VerificationResultData callBack = new VerificationResultData();

   
    private boolean verified = false;

    private static final double INCH_2_CM = 2.54;

//    @Inject
//    HyperServiceFacade hyperService;
//    @Inject
//    ReportExporter reportExporter;
//    @Inject
//    CardHolderImageFacade cardHolderImageFacade;
    
    private StreamedContent streamedContent;

    private String shortId = null;
    private String transId = null;
    private String message = null;
    private String encodedPdf = null;
    StreamedContent pdfFile;

    static {
        System.setProperty("java.awt.headless", "true");
    }

    public CameraVerificationController() {
    }

    public void oncapture(CaptureEvent captureEvent) {
        bufferedImage = null;
        byte[] data = captureEvent.getData();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        try {
            bufferedImage = ImageIO.read(bais);
            getImage();
            PrimeFaces.current().executeScript("PF('photoEditDialog').hide()");
            PrimeFaces.current().executeScript("PF('photoCropperDialog').show()");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void crop() {
        try {
            bufferedCroppedImage = null;
            if (croppedImage != null) {
                ByteArrayInputStream bais = new ByteArrayInputStream(croppedImage.getBytes());
                bufferedCroppedImage = ImageIO.read(bais);
                getCroppedImage();
                PrimeFaces.current().executeScript("PF('photoCropperDialog').hide()");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public StreamedContent getImage() {
        return DefaultStreamedContent.builder()
                .contentType("image/png")
                .stream(() -> {
                    try {

                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        ImageIO.write(bufferedImage, "png", outputStream);
                        return new ByteArrayInputStream(outputStream.toByteArray());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .build();
    }

    public StreamedContent getFinalCroppedImage() {
        return DefaultStreamedContent.builder()
                .contentType("image/png")
                .stream(() -> {
                    try {

                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        ImageIO.write(bufferedCroppedImage, "png", outputStream);
                        return new ByteArrayInputStream(outputStream.toByteArray());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .build();
    }

    public void clearMethod() {
        croppedImage = null;
        bufferedCroppedImage = null;
        croppedImage = null;

    }

    public String imageDpi(String img) {
        try {
            byte[] image = Base64.getDecoder().decode(img);
            InputStream is = new ByteArrayInputStream(image);
            BufferedImage newBi = ImageIO.read(is);
            byte[] d = processData(newBi);
            String encodedImage = Base64.getEncoder().encodeToString(d);
            return encodedImage;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static byte[] processData(BufferedImage image) throws IOException {

        for (Iterator<ImageWriter> iw = ImageIO.getImageWritersByFormatName("png"); iw.hasNext();) {
            ImageWriter writer = iw.next();
            ImageWriteParam writeParam = writer.getDefaultWriteParam();
            ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);
            IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, writeParam);
            if (metadata.isReadOnly() || !metadata.isStandardMetadataFormatSupported()) {
                continue;
            }
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageOutputStream stream = null;
            try {
                setDPI(metadata);
                stream = ImageIO.createImageOutputStream(output);
                writer.setOutput(stream);
                writer.write(metadata, new IIOImage(image, null, metadata), writeParam);
            } finally {
                stream.close();
            }
            return output.toByteArray();
        }

        return null;
    }

    private static void setDPI(IIOMetadata metadata) throws IIOInvalidTreeException {

        // for PMG, it's dots per millimeter
        double dotsPerMilli = 5.0 * DPI / 10 / INCH_2_CM;

        IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
        horiz.setAttribute("value", Double.toString(dotsPerMilli));

        IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
        vert.setAttribute("value", Double.toString(dotsPerMilli));

        IIOMetadataNode dim = new IIOMetadataNode("Dimension");
        dim.appendChild(horiz);
        dim.appendChild(vert);

        IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
        root.appendChild(dim);

        metadata.mergeTree("javax_imageio_1.0", root);
    }

//    private void saveRequestLog(String pin, String status, String message, CompanyInformation companyInfo, String img) {
//        RequestLog log = Function.convertRequest(pin);
//        log.setUserAccount(loginController.getUserAccount());
//        log.setCompany(companyInfo);
//        log.setId(UUID.randomUUID().toString());
//        log.setSource("WEB_PORTAL");
//        log.setApiHttpResponse(status);
//        log.setApiResponse(message);
//        log.setCreatedAt(new Date());
//        log.setCreatedBy(loginController.getUserAccount().getEmailAddress());
//        hyperService.save(log);
//
//        if (img != null) {
//            RequestImage image = Function.convertRequestImage(Base64.getDecoder().decode(img), log.getId());
//            hyperService.save(image);
//        }
//    }

    private void showDialog(String dialogId, String msg, boolean success) {
        if (success) {
            JSF.addSuccessMessage(msg);
        } else {
            JSF.addErrorMessage(msg);
        }
        PrimeFaces.current().executeScript("PF('" + dialogId + "').show()");
    }

//    private void handleException(Exception e, String pin, String img) {
//        e.printStackTrace();
//        CompanyInformation companyInfo = loginController.getUserAccount().getCompanyInformation();
//        saveRequestLog(pin, "EXCEPTION", "CONNECTION ERROR", companyInfo, img);
//        showDialog("errorVarDialog", "CONNECTION ERROR", false);
//    }

//    private void handleFailedVerification(JSONObject data, String img, String pin, CompanyInformation companyInfo, String message) {
//        shortId = data.optString("shortGUID", null);
//        transId = data.optString("transactionId", null);
//        this.message = data.optString("msg", "NOT VERIFIED");
//
//        saveRequestLog(pin, "200", message, companyInfo, img);
//        showDialog("errorVarDialog", "NOT VERIFIED", false);
//    }

    
    public void sendForVerification() {
//        CompanyInformation companyInformation = loginController.getUserAccount().getCompanyInformation();
//        String img = null;
//        try {
//
//            if (ghanaCardPin != null) {
//                callBack = new VerificationResultData();
//                verified = false;
//                InputStream is = new ByteArrayInputStream(croppedImage.getBytes());
//                BufferedImage newBi = ImageIO.read(is);
//                img = Base64.getEncoder().encodeToString(Function.toByteArray(newBi, "png"));
//                VerificationRequest request = new VerificationRequest();
//                request.setDataType("PNG");
//                request.setImage(imageDpi(img));
//                request.setPinNumber(ghanaCardPin);
//                request.setMerchantKey(loginController.getUserAccount().getBranch().getCompanyInformation().getNiaApiKey());
//                TokenRefreshRequest refreshRequest = new TokenRefreshRequest();
//                refreshRequest.setRefreshToken(request.getMerchantKey());
//
//                HttpResponse<String> response = Unirest.post("https://selfie.imsgh.org:2035/skyface/api/v1/third-party/verification/base_64/verification/kyc/face")
//                        .body(request)
//                        .header("Content-Type", "application/json")
//                        .asString();
//                String res = response.getBody();
//                System.out.println("RESP >>>>>>>>>>>>>>>>" + res);
//                JSONObject objectData = new JSONObject(res);
//                if (response.getStatus() == 200) {
//                    Gson g = new Gson();
//                    callBack = g.fromJson(response.getBody(), VerificationResultData.class);
//                    if (callBack != null) {
//                        if (callBack.isSuccess() && callBack.getData().getVerified().equals("TRUE")) {
//                            verified = true;
//
//                            GhanaCardHolder cardHolder = Function.convertGhanaCardHolder(callBack);
//                            cardHolder.setCompany(companyInformation);
//                            savedGhanaCardHolder = (GhanaCardHolder) hyperService.save(cardHolder);
//                            CardHolderImage holderImage = Function.convertGhanaCardHolderImage(callBack, cardHolder);
//                            holderImage.setCompany(companyInformation);
//                            savedCardHolderImage = (CardHolderImage) hyperService.save(holderImage);
//                            Verification verification = Function.convertResponseToVerification(callBack, savedGhanaCardHolder);
//                            verification.setCompany(companyInformation);
//                            if (loginController.getUserAccount() != null) {
//                                verification.setUserId(loginController.getUserAccount());
//                            }
//                            verification.setSource("WEBCAM");
//                            verification.setId(callBack.getData().getTransactionGuid());
//                            savedverification = (Verification) hyperService.save(verification);
//                            JSF.addSuccessMessage("VERIFIED SUCCESSFULLY");
//                            RequestLog requestLog = Function.convertRequest(ghanaCardPin);
//                            requestLog.setUserAccount(loginController.getUserAccount());
//                            requestLog.setApiResponse("SUCCESSFUL VERIFICATION");
//                            requestLog.setId(UUID.randomUUID().toString());
//                            requestLog.setApiHttpResponse(String.valueOf(response.getStatus()));
//                            requestLog.setCreatedAt(new Date());
//                            requestLog.setCreatedBy(loginController.getUserAccount().getEmailAddress());
//                            requestLog.setSource("WEB_PORTAL");
//                            requestLog.setCompany(companyInformation);
//                            hyperService.save(requestLog);
//                            RequestImage requestImage = Function.convertRequestImage(Base64.getDecoder().decode(img), requestLog.getId());
//                            hyperService.save(requestImage);
//                            printCardToPdf();
//
//                            PrimeFaces.current().executeScript("PF('successVarDialog').show()");
//
//                        } else if (callBack.getData().getVerified().equals("FALSE")) {
//                            RequestLog requestLog = Function.convertRequest(ghanaCardPin);
//                            requestLog.setUserAccount(loginController.getUserAccount());
//                            requestLog.setApiResponse(res);
//                            requestLog.setCompany(companyInformation);
//                            requestLog.setId(UUID.randomUUID().toString());
//                            requestLog.setSource("WEB_PORTAL");
//                            requestLog.setApiResponse("FAILED VERIFICATION");
//                            requestLog.setApiHttpResponse(String.valueOf(response.getStatus()));
//                            requestLog.setCreatedAt(new Date());
//                            requestLog.setCreatedBy(loginController.getUserAccount().getEmailAddress());
//                            hyperService.save(requestLog);
//                            RequestImage requestImage = Function.convertRequestImage(Base64.getDecoder().decode(img), requestLog.getId());
//                            hyperService.save(requestImage);
//                            verified = false;
//                            message = objectData.optString("msg", "NOT VERIFIED");
//                            shortId = objectData.optString("shortGUID");
//                            transId = objectData.optString("transactionId");
//                            JSF.addErrorMessage("NOT VERIFIED");
//                            PrimeFaces.current().executeScript("PF('errorVarDialog').show()");
//                        } else {
//                            RequestLog requestLog = Function.convertRequest(ghanaCardPin);
//                            requestLog.setUserAccount(loginController.getUserAccount());
//                            requestLog.setApiResponse(res);
//                            requestLog.setCompany(companyInformation);
//                            requestLog.setId(UUID.randomUUID().toString());
//                            requestLog.setSource("WEB_PORTAL");
//                            requestLog.setApiResponse("UNSUCCESSFUL VERIFICATION");
//                            requestLog.setCreatedAt(new Date());
//                            requestLog.setApiHttpResponse(String.valueOf(response.getStatus()));
//                            requestLog.setCreatedBy(loginController.getUserAccount().getEmailAddress());
//                            hyperService.save(requestLog);
//                            RequestImage requestImage = Function.convertRequestImage(Base64.getDecoder().decode(img), requestLog.getId());
//                            hyperService.save(requestImage);
//                            verified = false;
//                            //showMainForm = false;
//                            if (objectData.has("msg")) {
//                                message = objectData.optString("msg", "NOT VERIFIED");
//                            }
//                            if (objectData.has("shortGuid")) {
//                                shortId = objectData.optString("shortGuid");
//                            }
//                            if (objectData.has("shortGuid")) {
//                                transId = objectData.optString("transactionId");
//                            }
//                            JSF.addErrorMessage("NOT VERIFIED");
//                            PrimeFaces.current().executeScript("PF('errorVarDialog').show()");
//                        }
//                    } else {
//                        RequestLog requestLog = Function.convertRequest(ghanaCardPin);
//                        requestLog.setApiResponse(res);
//                        requestLog.setUserAccount(loginController.getUserAccount());
//                        requestLog.setCompany(companyInformation);
//                        requestLog.setId(UUID.randomUUID().toString());
//                        requestLog.setSource("WEB_PORTAL");
//                        requestLog.setApiHttpResponse("INVALID RESPONSE");
//                        requestLog.setApiResponse("INVALID RESPONSE");
//                        requestLog.setCreatedAt(new Date());
//                        requestLog.setCreatedBy(loginController.getUserAccount().getEmailAddress());
//                        hyperService.save(requestLog);
//                        RequestImage requestImage = Function.convertRequestImage(Base64.getDecoder().decode(img), requestLog.getId());
//                        hyperService.save(requestImage);
//                        verified = false;
//                        //showMainForm = false;
//                        JSF.addErrorMessage("NOT VERIFIED");
//                        PrimeFaces.current().executeScript("PF('errorVarDialog').show()");
//                    }
//                } else {
//                    verified = false;
//
//                    JSF.addErrorMessage("DATA EXCEPTION");
//                    RequestLog requestLog = Function.convertRequest(ghanaCardPin);
//                    requestLog.setUserAccount(loginController.getUserAccount());
//                    requestLog.setApiResponse(res);
//                    requestLog.setId(UUID.randomUUID().toString());
//                    requestLog.setCompany(companyInformation);
//                    requestLog.setSource("WEB_PORTAL");
//                    requestLog.setApiHttpResponse("DATA EXCEPTION");
//                    requestLog.setApiResponse("DATA EXCEPTION");
//                    requestLog.setCreatedAt(new Date());
//                    requestLog.setCreatedBy(loginController.getUserAccount().getEmailAddress());
//                    hyperService.save(requestLog);
//                    RequestImage requestImage = Function.convertRequestImage(Base64.getDecoder().decode(img), requestLog.getId());
//                    hyperService.save(requestImage);
//                    PrimeFaces.current().executeScript("PF('errorVarDialog').show()");
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JSF.addErrorMessage("CONNECTION ERROR");
//            RequestLog requestLog = Function.convertRequest(ghanaCardPin);
//            requestLog.setUserAccount(loginController.getUserAccount());
//            requestLog.setApiResponse(e.getMessage());
//            requestLog.setId(UUID.randomUUID().toString());
//            requestLog.setCompany(companyInformation);
//            requestLog.setSource("WEB_PORTAL");
//            requestLog.setApiHttpResponse("CONNECTION ERROR");
//            requestLog.setApiResponse("CONNECTION ERROR");
//            requestLog.setCreatedAt(new Date());
//            requestLog.setCreatedBy(loginController.getUserAccount().getEmailAddress());
//            hyperService.save(requestLog);
//            RequestImage requestImage = Function.convertRequestImage(Base64.getDecoder().decode(img), requestLog.getId());
//            hyperService.save(requestImage);
//            message = "CONNECTION ERROR";
//            shortId = null;
//            transId = null;
//            PrimeFaces.current().executeScript("PF('errorVarDialog').show()");
//
//        }
    }

    public void reset() {
        ghanaCardPin = null;
        croppedImage = null;
//        savedCardHolderImage = new CardHolderImage();
//        savedGhanaCardHolder = new GhanaCardHolder();
//        savedverification = new Verification();
        verified = false;
        bufferedCroppedImage = null;

    }

    public void printCard() {
//        List<CardDetail> cardDetails = new ArrayList<>();
//        CardDetail detail = new CardDetail();
//        CardDetail convertCardData = detail.convertCardData(savedverification, savedverification.getCardHolder(), (CardHolderImage) cardHolderImageFacade.findById(savedGhanaCardHolder.getNationalId()));
//        cardDetails.add(convertCardData);
//        reportExporter.generateReport(cardDetails, loginController.getUserAccount().getBranch().getCompanyInformation());

//        List<CardDetail> cardDetails = new ArrayList<>();
//        CardDetail detail = new CardDetail();
//        CardDetail convertCardData = detail.convertCardData(v, v.getCardHolder(), (CardHolderImage) cardHolderImageFacade.findById(v.getCardHolder().getNationalId()));
//        cardDetails.add(convertCardData);
//        reportExporter.generateReport(cardDetails, loginController.getUserAccount().getBranch().getCompanyInformation());
//        PrimeFaces.current().executeScript("PF('successVarDialog').hide()");
//        PrimeFaces.current().executeScript("PF('errorVarDialog').hide()");
//        File print = print(savedverification);
//        if (print != null) {
//            streamedContent = createStream(print);
//             PrimeFaces.current().executeScript("PF('printVarDialog').show()");
//        } else {
//
//        }
    }

    public void printCardToPdf() {
//        try {
//            List<CardDetail> cardDetails = new ArrayList<>();
//            CardDetail detail = new CardDetail();
//            CardDetail convertCardData = detail.convertCardData(savedverification, savedverification.getCardHolder(), (CardHolderImage) cardHolderImageFacade.findById(savedGhanaCardHolder.getNationalId()));
//            cardDetails.add(convertCardData);
//            File generateReportAndSave = reportExporter.generateReportAndSave(cardDetails, loginController.getUserAccount().getBranch().getCompanyInformation());
//            byte[] pdfBytes = Files.toByteArray(generateReportAndSave);
//            encodedPdf = "data:application/pdf;base64," + Base64.getEncoder().encodeToString(pdfBytes);
//
//            pdfFile = DefaultStreamedContent.builder()
//                    .contentType("application/pdf")
//                    .name(savedverification.getCardHolder().getNationalId() + ".pdf")
//                    .stream(() -> {
//                        try {
//                            return new FileInputStream(generateReportAndSave);
//
//                        } catch (FileNotFoundException ex) {
//                            Logger.getLogger(CameraVerificationController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        return null;
//                    })
//                    .build();
//            //viewResponsive();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

//    public File print(Verification v) {
////        List<CardDetail> cardDetails = new ArrayList<>();
////        CardDetail detail = new CardDetail();
////        CardDetail convertCardData = detail.convertCardData(v, v.getCardHolder(), (CardHolderImage) cardHolderImageFacade.findById(v.getCardHolder().getNationalId()));
////        cardDetails.add(convertCardData);
////        File generateReportAndSave = reportExporter.generateReportAndSave(cardDetails, loginController.getUserAccount().getCompanyInformation());
////        return generateReportAndSave;
//
//    }

    private StreamedContent createStream(File fileName) {
        return DefaultStreamedContent.builder()
                .contentType("application/pdf")
                .name(ghanaCardPin)
                .stream(() -> {
                    try {
                        byte[] byteArray = FileUtils.readFileToByteArray(fileName);
                        return new ByteArrayInputStream(byteArray);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .build();

    }

    private InputStream getData(File f) {
        InputStream is = null;
        try {
            is = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return is;

    }

    public void viewResponsive() {
        DialogFrameworkOptions options = DialogFrameworkOptions.builder()
                .modal(true)
                .fitViewport(true)
                .responsive(true)
                .width("900px")
                .contentWidth("100%")
                .resizeObserver(true)
                .resizeObserverCenter(true)
                .resizable(false)
                .styleClass("max-w-screen")
                .iframeStyleClass("max-w-screen")
                .build();

        PrimeFaces.current().dialog().openDynamic("verification/viewResponsive", options, null);
    }

    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VerificationResultData getCallBack() {
        return callBack;
    }

    public void setCallBack(VerificationResultData callBack) {
        this.callBack = callBack;
    }

    public CroppedImage getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getBufferedCroppedImage() {
        return bufferedCroppedImage;
    }

    public void setBufferedCroppedImage(BufferedImage bufferedCroppedImage) {
        this.bufferedCroppedImage = bufferedCroppedImage;
    }

    public String getGhanaCardPin() {
        return ghanaCardPin;
    }

    public void setGhanaCardPin(String ghanaCardPin) {
        this.ghanaCardPin = ghanaCardPin;
    }

   

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    public String getEncodedPdf() {
        return encodedPdf;
    }

    public void setEncodedPdf(String encodedPdf) {
        this.encodedPdf = encodedPdf;
    }

    public StreamedContent getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(StreamedContent pdfFile) {
        this.pdfFile = pdfFile;
    }

}
