/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.security;

/**
 *
 * @author ernest
 */
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class ChecksumUtil {

    public static String generateSHA256ShortChecksum(String serialNumber) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(serialNumber.getBytes(StandardCharsets.UTF_8));
            String hexChecksum = String.format("%02X", hash[0]); // first byte = 2 hex chars
            return serialNumber + "-" + hexChecksum;
        } catch (Exception e) {
            throw new RuntimeException("Checksum generation failed", e);
        }
    }
}

