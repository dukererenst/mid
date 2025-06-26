/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.indexgenesys.mid.entity.enums;

/**
 *
 * @author ernest
 */
public enum BatchStatus {
    ACTIVE("Active"),
    IN_ACTIVE("Inactive"),
    SUSPENDED("Suspended");

    private final String label;

    BatchStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

