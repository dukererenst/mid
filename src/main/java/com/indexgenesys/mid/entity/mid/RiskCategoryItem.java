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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ernest
 */
@Entity
@Table(name = "RISK_CATEGORY_ITEM")
public class RiskCategoryItem extends EntityModel {

    @JoinColumn(name = "RISK_CATEGORY")
    private RiskCategory riskCategory;

    @JoinColumn(name = "ITEM")
    private Items items;

    @Column(name = "PRICE_TYPE")
    @Enumerated(EnumType.STRING)
    private PriceType priceType;

    @Column(name = "PRICE_VALUE")
    private double priceValue;

    @JoinColumn(name = "COMPANY_INFORMATION")
    private CompanyInformation companyInformation;

    public RiskCategoryItem() {
    }

    public RiskCategory getRiskCategory() {
        return riskCategory;
    }

    public void setRiskCategory(RiskCategory riskCategory) {
        this.riskCategory = riskCategory;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public double getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(double priceValue) {
        this.priceValue = priceValue;
    }

}
