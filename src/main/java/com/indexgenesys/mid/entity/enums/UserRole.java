/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.enums;

/**
 *
 * @author ernest
 */


public enum UserRole {
    ADMINISTRATOR("Administrator"),
    UNDERWRITER("Underwriter"),
    SUPERVISION("Supervision"),
    REPORT_VIEWER("Report Viewer"),
    UNDERWRITING_OFFICER("Underwriting Officer"),
    UNDERWRITING_MANAGER("Underwriting Manager"),
    FINANCE_OFFICER("Finance Officer"),
    FINANCE_MANAGER("Finance Manager"),
    COMPANY_ADMIN("Company Admin"),
    CLAIMS_OFFICER("Claims Officer"),
    CLAIMS_MANAGER("Claims Manager"),
    REGULATOR("Regulator");

    private final String label;

    UserRole(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

