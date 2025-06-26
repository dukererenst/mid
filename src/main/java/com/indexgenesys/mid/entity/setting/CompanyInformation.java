/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.setting;


import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.enums.CompanyType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author ernest
 */

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COMPANY_INFORMATION")
public class CompanyInformation extends EntityModel {

    @Column(name = "COMPANY_NAME")
    private String companyName;
    
    @Column(name = "COMPANY_CODE")
    private String companyCode;
    
    @Column(name = "TELEPHONE")
    private String telephone;
    
    @Column(name = "REGISTRATION_NO")
    private String registrationNo;
    
    @Column(name = "LOCATION")
    private String location;
    
    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;
    
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    
    @Column(name = "COMPANY_TYPE")
    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return  companyName ;
    }
    
    
    
}
