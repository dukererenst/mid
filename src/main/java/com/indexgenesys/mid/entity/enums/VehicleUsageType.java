package com.indexgenesys.mid.entity.enums;

public enum VehicleUsageType {
    PRIVATE("Private"),
    COMMERCIAL("Commercial");

    private final String label;

    VehicleUsageType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

