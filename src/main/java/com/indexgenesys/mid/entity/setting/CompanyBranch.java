/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.setting;


import com.indexgenesys.mid.entity.EntityModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "INSURANCE_COMPANY_BRANCH")
@ToString
public class CompanyBranch extends EntityModel {

    @Column(name = "BRANCH_NAME")
    private String branchName;
    
    @ManyToOne
    @JoinColumn(name = "COMPANY")
    private CompanyInformation insuranceCompany;

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "CITY")
    private String city;

    @JoinColumn(name = "REGION")
    private Region region;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "MANAGER_NAME", nullable = false)
    private String managerName;

    @Column(name = "ACTIVE", nullable = false)
    private boolean active = true;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public CompanyInformation getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(CompanyInformation insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }
    
    
}
