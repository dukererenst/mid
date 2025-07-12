/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.util;

import java.security.SecureRandom;
import java.time.Year;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ernest
 */
public class Function {

    private static final int DEFAULT_LENGTH = 17;
    private static final String CHARSET = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String[] REGION_CODES = {
        "AR", // Ashanti
        "AF", // Ahafo
        "BE", // Bono East
        "BR", // Bono
        "CR", // Central
        "ER", // Eastern
        "GR", // Greater Accra
        "NE", // North East
        "NR", // Northern
        "OT", // Oti
        "SV", // Savannah
        "UE", // Upper East
        "UW", // Upper West
        "VR", // Volta
        "WN", // Western North
        "WR" // Western
    };
    private static final List<String> COLORS = List.of(
            "Black", "White", "Silver", "Blue", "Red", "Green", "Yellow", "Orange", "Brown", "Gray"
    );

    // Map each make to its list of models
    private static final Map<String, List<String>> MAKE_MODELS = Map.of(
            "Toyota", List.of("Corolla", "Camry", "RAV4", "Highlander", "Prius"),
            "Honda", List.of("Civic", "Accord", "CR-V", "Pilot", "Fit"),
            "Ford", List.of("Fiesta", "Focus", "Mustang", "Explorer", "Escape"),
            "BMW", List.of("3 Series", "5 Series", "X3", "X5", "i3"),
            "Mercedes-Benz", List.of("C-Class", "E-Class", "GLA", "GLC", "S-Class"),
            "Nissan", List.of("Sentra", "Altima", "Rogue", "Murano", "Leaf"),
            "Hyundai", List.of("Elantra", "Sonata", "Tucson", "Santa Fe", "Accent"),
            "Kia", List.of("Rio", "Forte", "Sportage", "Sorento", "Optima"),
            "Chevrolet", List.of("Spark", "Malibu", "Equinox", "Traverse", "Impala"),
            "Volkswagen", List.of("Golf", "Passat", "Tiguan", "Jetta", "Atlas")
    );

    public static String randomColor() {
        return COLORS.get(RANDOM.nextInt(COLORS.size()));
    }

    /**
     * Chooses a random make.
     */
    public static String randomMake() {
        List<String> makes = List.copyOf(MAKE_MODELS.keySet());
        return makes.get(RANDOM.nextInt(makes.size()));
    }

    /**
     * For a given make, pick one of its models at random. If the make isn’t in
     * the map, returns “Unknown Model”.
     */
    public static String randomModel(String make) {
        List<String> models = MAKE_MODELS.get(make);
        if (models == null || models.isEmpty()) {
            return "Unknown Model";
        }
        return models.get(RANDOM.nextInt(models.size()));
    }

    public static String generate(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int idx = RANDOM.nextInt(CHARSET.length());
            sb.append(CHARSET.charAt(idx));
        }
        return sb.toString();
    }

    public static String generateRegNo() {
        // 1) Pick a random region
        String region = REGION_CODES[RANDOM.nextInt(REGION_CODES.length)];

        // 2) Generate a 1–9999 serial, formatted to 4 digits
        int serial = RANDOM.nextInt(9_999) + 1;
        String serialStr = String.format("%04d", serial);

        // 3) Year suffix (last two digits)
        int year = Year.now().getValue() % 100;
        String yearStr = String.format("%02d", year);

        return region + " " + serialStr + "-" + yearStr;
    }
}
