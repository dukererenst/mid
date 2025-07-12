/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.util;

import java.security.MessageDigest;
import java.util.UUID;

/**
 *
 * @author ernest
 */
public class SerialNumberGenerator {

    public static String generateFromHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            UUID uuid = UUID.randomUUID();
            byte[] hash = digest.digest(uuid.toString().getBytes());
            return toBase62(hash).substring(0, 10).toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toBase62(byte[] bytes) {
        final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        java.math.BigInteger bigInt = new java.math.BigInteger(1, bytes);
        StringBuilder sb = new StringBuilder();
        while (bigInt.compareTo(java.math.BigInteger.ZERO) > 0) {
            sb.append(BASE62[bigInt.mod(java.math.BigInteger.valueOf(62)).intValue()]);
            bigInt = bigInt.divide(java.math.BigInteger.valueOf(62));
        }
        return sb.reverse().toString();
    }
}
