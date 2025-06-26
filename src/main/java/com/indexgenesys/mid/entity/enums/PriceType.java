package com.indexgenesys.mid.entity.enums;

public enum PriceType {
    FLAT("Flat", "₵"),         // or "$", or any currency/unit
    PERCENTAGE("Percentage", "%");

    private final String label;
    private final String symbol;

    PriceType(String label, String symbol) {
        this.label = label;
        this.symbol = symbol;
    }

    public String getLabel() {
        return label;
    }

    public String getSymbol() {
        return symbol;
    }
}

