/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.indexgenesys.mid.entity.enums;

/**
 *
 * @author ernest
 */
public enum StickerStatus {
    GENERATED("Generated"),
    DISTRIBUTED("Distributed"),
    ISSUED("Issued");

    private final String label;

    StickerStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    
    public static StickerStatus fromString(String value) {
    for (StickerStatus status : StickerStatus.values()) {
        if (status.name().equalsIgnoreCase(value)) {
            return status;
        }
    }
    throw new IllegalArgumentException("Invalid StickerStatus: " + value);
}

}

