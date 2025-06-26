/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.mid;

import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.enums.PriceType;
import com.indexgenesys.mid.entity.setting.CompanyInformation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ernest
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product extends EntityModel {

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_CODE")
    private String productCode;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_TYPE")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "COMPANY_INFORMATION")
    private CompanyInformation companyInformation;

    @ManyToOne
    @JoinColumn(name = "RISK_CATEGORY")
    private RiskCategory riskCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "PREMIUM_PRICE_TYPE")
    private PriceType premiumPriceType;

    @Column(name = "PREMIUM_VALUE")
    private Double premiumValue;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ACTIVE", nullable = false)
    private boolean active = false;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public PriceType getPremiumPriceType() {
        return premiumPriceType;
    }

    public void setPremiumPriceType(PriceType premiumPriceType) {
        this.premiumPriceType = premiumPriceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public CompanyInformation getCompanyInformation() {
        return companyInformation;
    }

    public void setCompanyInformation(CompanyInformation companyInformation) {
        this.companyInformation = companyInformation;
    }

    public RiskCategory getRiskCategory() {
        return riskCategory;
    }

    public void setRiskCategory(RiskCategory riskCategory) {
        this.riskCategory = riskCategory;
    }

    public Double getPremiumValue() {
        return premiumValue;
    }

    public void setPremiumValue(Double premiumValue) {
        this.premiumValue = premiumValue;
    }

}
