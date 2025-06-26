/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.mid;

import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.enums.CompanyType;
import com.indexgenesys.mid.entity.enums.CustomerType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ernest
 */
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "CUSTOMER_INFORMATION_")
@ToString
public class CustomerInformation1 extends EntityModel {

//    // Identification Numbers
//    @Column(name = "GHANA_CARD_NO", unique = true)
//    private String ghanaCardNumber;
//
//    @Column(name = "COMPANY_REG_NO", unique = true)
//    private String companyRegNo;
//
//    @Column(name = "TAX_ID", unique = true)
//    private String taxId;
//
//    @Column(name = "REGISTRATION_NUMBER", unique = true)
//    private String registrationNumber;
//
//    // Personal & Company Names
//    @Column(name = "FIRST_NAME")
//    private String firstName;
//
//    @Column(name = "LASTNAME")
//    private String lastName;
//
//    @Column(name = "COMPANY_NAME")
//    private String companyName;
//
//    @Column(name = "CEO_FULL_NAME")
//    private String ceoFullName;
//    
//    @Column(name = "CEO_GHANA_CARD_PIN")
//    private String ceoGhanaCardPin;
//
//    // Contact Info
//    @Column(name = "EMAIL_ADDRESS", nullable = false, unique = true)
//    private String emailAddress;
//
//    @Column(name = "PRIMARY_TELEPHONE", nullable = false)
//    private String primaryTelephone;
//
//    @Column(name = "SECONDARY_TELEPHONE", nullable = false)
//    private String secondaryTelephone;
//
//    @Column(name = "COMPANY_PHONE")
//    private String companyPhone;
//
//    @Column(name = "CEO_EMAIL_ADDRESS")
//    private String ceoEmailAddress;
//
//    @Column(name = "CEO_TELEPHONE")
//    private String ceoTelePhone;
//
//    @Column(name = "WEBSITE")
//    private String website;
//
//    // Location Info
//    @Column(name = "GPS_CODE", nullable = false)
//    private String gpsCode;
//
//    @Column(name = "COMPANY_ADDRESS")
//    private String companyAddress;
//
//    // Status & Classification
//    @Column(name = "CUSTOMER_TYPE", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private CustomerType customerType;
//
//    @Column(name = "CLASS_OF_BUSINESS")
//    private String classOfBusiness;
//
//
//    @Column(name = "LOGO")
//    private String logo;
//
//
//    @Column(name = "STATUS", nullable = false)
//    private boolean status = true;
//
//    @Column(name = "ACTIVE", nullable = false)
//    private boolean active = true;
//    
//
//    @Transient
//    public String getDisplayName() {
//        if (customerType == CustomerType.INDIVIDUAL) {
//            return (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
//        } else {
//            return companyRegNo != null ? companyRegNo : "";
//        }
//    }


}
