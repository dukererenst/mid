/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.indexgenesys.mid.security.RSAQRUtil;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author ernest
 */
public class GenerateSample {

    public static void main(String[] args) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stickerNumber", "STK2025001234");
        parameters.put("serialNumber", "SN12345678");
        parameters.put("registrationNo", "GR 1234-23");
        parameters.put("chassisNo", "GR 1234-23");
        parameters.put("vehicle_color", "GR 1234-23");
        parameters.put("insuranceCompany", "2");
        parameters.put("vehicle_usage", "PRIVATE");
        parameters.put("validFrom", new Date());
        parameters.put("validTo", oneYearLaterDate());
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(parameters);
        parameters.put("qrCodeImage", generateQrCodeImage(RSAQRUtil.encrypt(jsonString))); 

        JasperReport jasperReport = JasperCompilerUtil.compileReport("/reports/sticker.jrxml");
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfFile(print, "sticker.pdf");

    }

    public static BufferedImage generateQrCodeImage(String text) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 150, 150);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static Date oneYearLaterDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTime();
    }

}
