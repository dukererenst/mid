/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.util;

/**
 *
 * @author ernest
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

public class StickerNumberGenerator {

    private static final AtomicLong counter = new AtomicLong(1); // Replace with DB-based sequence for production

    public static String generateStickerNumber(String companyCode, String batchCode) {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMM"));
        long sequence = counter.getAndIncrement(); // should be fetched from DB for multi-instance
        return String.format("%s-%s-%s-%06d", companyCode, datePart, batchCode, sequence);
    }

    public static String generateSerialNumber() {
        return String.format("%012d", counter.getAndIncrement());
    }
    
    
    
    
}

