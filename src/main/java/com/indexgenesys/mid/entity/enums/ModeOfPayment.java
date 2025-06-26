/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.indexgenesys.mid.entity.enums;

/**
 *
 * @author ernest
 */
public enum ModeOfPayment {
    BANK_TRANSFER("Bank Transfer"),
    CASH("Cash"),
    MOBILE_MONEY("Mobile Money"),
    CHEQUE("Cheque"),
    VISA_CARD("Visa Card"),
    MASTER_CARD("Master Card");

    private final String label;

    ModeOfPayment(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public String getLabel() {
        return label;
    }
    
    
}

