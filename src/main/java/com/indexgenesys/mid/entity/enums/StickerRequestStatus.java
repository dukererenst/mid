/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.indexgenesys.mid.entity.enums;

/**
 *
 * @author ernest
 */
public enum StickerRequestStatus {
    REQUESTED("Requested"),
    GENERATED("Generated"),
    CANCELLED("Cancelled"),
    APPROVED("Approved");

    private final String label;

    StickerRequestStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

