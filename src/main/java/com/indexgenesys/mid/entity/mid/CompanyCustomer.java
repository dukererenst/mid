/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.mid;

/**
 *
 * @author ernest
 */
import com.indexgenesys.mid.entity.EntityModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMPANY_CUSTOMER")
public class CompanyCustomer extends EntityModel{

    @Column(name = "COMPANY_REG_NO", unique = true)
    private String companyRegNo;

    @Column(name = "TAX_ID", unique = true)
    private String taxId;

    @Column(name = "REGISTRATION_NUMBER", unique = true)
    private String registrationNumber;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "COMPANY_ADDRESS")
    private String companyAddress;

    @Column(name = "COMPANY_PHONE")
    private String companyPhone;

    @Column(name = "CEO_FULL_NAME")
    private String ceoFullName;

    @Column(name = "CEO_GHANA_CARD_PIN")
    private String ceoGhanaCardPin;

    @Column(name = "CEO_EMAIL_ADDRESS")
    private String ceoEmailAddress;

    @Column(name = "CEO_TELEPHONE")
    private String ceoTelePhone;

    @Column(name = "WEBSITE")
    private String website;

    @Column(name = "CLASS_OF_BUSINESS")
    private String classOfBusiness;

    @Column(name = "LOGO")
    private String logo;

    @Column(name = "EMAIL_ADDRESS", nullable = false, unique = true)
    private String emailAddress;


    @Column(name = "SECONDARY_TELEPHONE")
    private String secondaryTelephone;

    @Column(name = "GPS_CODE", nullable = false)
    private String gpsCode;

    @Column(name = "STATUS", nullable = false)
    private boolean status = true;

    @Column(name = "ACTIVE", nullable = false)
    private boolean active = true;

    public CompanyCustomer() {
    }

    
    
    public String getCompanyRegNo() {
        return companyRegNo;
    }

    public void setCompanyRegNo(String companyRegNo) {
        this.companyRegNo = companyRegNo;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCeoFullName() {
        return ceoFullName;
    }

    public void setCeoFullName(String ceoFullName) {
        this.ceoFullName = ceoFullName;
    }

    public String getCeoGhanaCardPin() {
        return ceoGhanaCardPin;
    }

    public void setCeoGhanaCardPin(String ceoGhanaCardPin) {
        this.ceoGhanaCardPin = ceoGhanaCardPin;
    }

    public String getCeoEmailAddress() {
        return ceoEmailAddress;
    }

    public void setCeoEmailAddress(String ceoEmailAddress) {
        this.ceoEmailAddress = ceoEmailAddress;
    }

    public String getCeoTelePhone() {
        return ceoTelePhone;
    }

    public void setCeoTelePhone(String ceoTelePhone) {
        this.ceoTelePhone = ceoTelePhone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getClassOfBusiness() {
        return classOfBusiness;
    }

    public void setClassOfBusiness(String classOfBusiness) {
        this.classOfBusiness = classOfBusiness;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSecondaryTelephone() {
        return secondaryTelephone;
    }

    public void setSecondaryTelephone(String secondaryTelephone) {
        this.secondaryTelephone = secondaryTelephone;
    }

    public String getGpsCode() {
        return gpsCode;
    }

    public void setGpsCode(String gpsCode) {
        this.gpsCode = gpsCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}
