/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.security;

/**
 *
 * @author ernest
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class RSAQRUtil {

    private static final String PUBLIC_KEY_FILE = "keys/public_key.pem";
    private static final String PRIVATE_KEY_FILE = "keys/private_key.pem";

    public static String encrypt(String data) throws Exception {
        // 1. Generate AES key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey aesKey = keyGen.generateKey();

        // 2. Encrypt data using AES
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] iv = aesCipher.getIV();
        byte[] encryptedData = aesCipher.doFinal(data.getBytes());

        // 3. Encrypt AES key using RSA
        PublicKey publicKey = loadPublicKey();
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedAesKey = rsaCipher.doFinal(aesKey.getEncoded());

        // 4. Combine and encode
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(encryptedAesKey.length); // store length of AES key
        outputStream.write(encryptedAesKey);
        outputStream.write(iv);
        outputStream.write(encryptedData);
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    public static String decrypt(String encrypted) throws Exception {
        byte[] input = Base64.getDecoder().decode(encrypted);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input);

        // 1. Extract AES key length and AES key
        int keyLength = inputStream.read();
        byte[] encryptedAesKey = inputStream.readNBytes(keyLength);

        // 2. Extract IV
        byte[] iv = inputStream.readNBytes(16); // 16 bytes for AES IV

        // 3. Extract encrypted data
        byte[] encryptedData = inputStream.readAllBytes();

        // 4. Decrypt AES key using RSA
        PrivateKey privateKey = loadPrivateKey();
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] aesKeyBytes = rsaCipher.doFinal(encryptedAesKey);
        SecretKey aesKey = new SecretKeySpec(aesKeyBytes, "AES");

        // 5. Decrypt data using AES
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(iv));
        byte[] decrypted = aesCipher.doFinal(encryptedData);

        return new String(decrypted);
    }

    // Load public key from PEM
    private static PublicKey loadPublicKey() throws Exception {
        String key = new String(Files.readAllBytes(Paths.get(PUBLIC_KEY_FILE)))
                .replaceAll("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");
        byte[] decoded = Base64.getDecoder().decode(key);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }

    // Load private key from PEM
    private static PrivateKey loadPrivateKey() throws Exception {
        String key = new String(Files.readAllBytes(Paths.get(PRIVATE_KEY_FILE)))
                .replaceAll("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");
        byte[] decoded = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }

   

//    public static void main(String[] args) {
//        RSAQRUtil  aQRUtil = new RSAQRUtil();
//        aQRUtil.
//    }
}
