/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.security;

/**
 *
 * @author ernest
 */
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import java.io.FileOutputStream;

public class RSAKeyGeneratorService {

    private static final String PUBLIC_KEY_FILE = "keys/public_key.pem";
    private static final String PRIVATE_KEY_FILE = "keys/private_key.pem";

    public void generateAndSaveKeys() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);

        KeyPair keyPair = generator.generateKeyPair();

        // Save the public key (Base64 encoded)
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        String publicKeyPem = "-----BEGIN PUBLIC KEY-----\n"
                + java.util.Base64.getMimeEncoder().encodeToString(publicKeyBytes)
                + "\n-----END PUBLIC KEY-----";
        saveToFile(PUBLIC_KEY_FILE, publicKeyPem);

        // Save the private key (Base64 encoded)
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        String privateKeyPem = "-----BEGIN PRIVATE KEY-----\n"
                + java.util.Base64.getMimeEncoder().encodeToString(privateKeyBytes)
                + "\n-----END PRIVATE KEY-----";
        saveToFile(PRIVATE_KEY_FILE, privateKeyPem);
    }

    private void saveToFile(String path, String content) throws Exception {
        Files.createDirectories(Paths.get("keys"));
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(content.getBytes());
        }
    }

//    public static void main(String[] args) throws Exception {
//        RSAKeyGeneratorService service = new RSAKeyGeneratorService();
//        service.generateAndSaveKeys();
//        System.out.println("Keys saved to /keys/public_key.pem and private_key.pem");
//    }

}
