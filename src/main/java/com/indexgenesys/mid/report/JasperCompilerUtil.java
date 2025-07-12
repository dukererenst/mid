/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.report;

/**
 *
 * @author ernest
 */
import net.sf.jasperreports.engine.*;

import java.io.InputStream;

public class JasperCompilerUtil {

    public static JasperReport compileReport(String jrxmlPath) throws JRException {
        // Load .jrxml file from classpath (e.g., src/main/resources/reports/sticker.jrxml)
        InputStream inputStream = JasperCompilerUtil.class.getResourceAsStream(jrxmlPath);
        if (inputStream == null) {
            throw new IllegalArgumentException("JRXML file not found at path: " + jrxmlPath);
        }

        // Compile to JasperReport object
        return JasperCompileManager.compileReport(inputStream);
    }
}

