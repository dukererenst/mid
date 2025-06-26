/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.mid;

import com.indexgenesys.mid.entity.EntityModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author ernest
 */


@Entity
@Table(name = "RISK_CATEGORY")
public class RiskCategory extends EntityModel {

    @Column(name = "RISK_TYPE_NAME")
    private String riskTypeName;

    @Column(name = "RISK_TYPE_CODE")
    private String riskTypeCode;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "ACTIVE", nullable = false)
    private boolean active = true;
    
    
    @Column(name = "CURRENT_YEAR")
    private int currentYear;

    @Column(name = "BASIC_PREMIUM")
    private Double basicPremium;

    @Column(name = "TPPDL")
    private Double tppdl;

    @Column(name = "TPI")
    private Double tpi;

    @Column(name = "OPEX")
    private Double opEx;

    @Column(name = "MOTOR_CONTRIBUTION")
    private Double motorContribution;

    @Column(name = "ADMIN_CHARGES")
    private Double adminCharges;

    @Column(name = "BROWN_CARD")
    private Double brownCard;

    @Column(name = "OWN_DAMAGE_RATE")
    private Double ownDamageRate;

    @Column(name = "COMPREHENSIVE_RATE")
    private Double comprehensiveRate;

    @Column(name = "THIRD_PARTY_FIRE_AND_THEFT_RATE")
    private Double thirdPartyFireAndTheftRate;

    @Column(name = "TPPDL_RATE")
    private Double tppdlRate;

    @Column(name = "UMBRELLA_LIMIT_RATE")
    private Double umbrellaLimitRate;

    @Column(name = "TPPDL_AMOUNT")
    private Double tppdlAmount;

    @Column(name = "EXCESS_RATE")
    private Double excessRate;

    @Column(name = "DEATH_LIMIT")
    private Double deathLimit;

    @Column(name = "BODILY_INJURY")
    private Double bodilyInjury;

    @Column(name = "MINIMUM_SEAT")
    private Integer minimumSeat;

    @Column(name = "EXTRA_SEAT_CHARGE")
    private Double extraSeatCharge;

    @Column(name = "ADDITIONAL_PERIL_CHARGE")
    private Double additionalPerilCharge;

    @Column(name = "ECOWAS_PERIL_CHARGE")
    private Double ecowasPerilCharge;

    @Column(name = "PERSONAL_ACCIDENT_CHARGE")
    private Double personalAccidentCharge;

    @Column(name = "OTHER_CHARGES")
    private Double otherCharges;

    // Private vehicle categories
    @Column(name = "PRIVATE_0")
    private Double private0;

    @Column(name = "PRIVATE_1")
    private Double private1;

    @Column(name = "PRIVATE_2")
    private Double private2;

    @Column(name = "PRIVATE_3")
    private Double private3;

    @Column(name = "PRIVATE_4")
    private Double private4;

    @Column(name = "PRIVATE_5_MORE")
    private Double private5More;

    // Commercial vehicle categories
    @Column(name = "COMMERCIAL_0")
    private Double commercial0;

    @Column(name = "COMMERCIAL_1")
    private Double commercial1;

    @Column(name = "COMMERCIAL_2")
    private Double commercial2;

    @Column(name = "COMMERCIAL_3")
    private Double commercial3;

    @Column(name = "COMMERCIAL_4")
    private Double commercial4;

    @Column(name = "COMMERCIAL_5_MORE")
    private Double commercial5More;

    public RiskCategory() {
    }

    public String getRiskTypeName() {
        return riskTypeName;
    }

    public void setRiskTypeName(String riskTypeName) {
        this.riskTypeName = riskTypeName;
    }

    public String getRiskTypeCode() {
        return riskTypeCode;
    }

    public void setRiskTypeCode(String riskTypeCode) {
        this.riskTypeCode = riskTypeCode;
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

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public Double getBasicPremium() {
        return basicPremium;
    }

    public void setBasicPremium(Double basicPremium) {
        this.basicPremium = basicPremium;
    }

    public Double getTppdl() {
        return tppdl;
    }

    public void setTppdl(Double tppdl) {
        this.tppdl = tppdl;
    }

    public Double getTpi() {
        return tpi;
    }

    public void setTpi(Double tpi) {
        this.tpi = tpi;
    }

    public Double getOpEx() {
        return opEx;
    }

    public void setOpEx(Double opEx) {
        this.opEx = opEx;
    }

    public Double getMotorContribution() {
        return motorContribution;
    }

    public void setMotorContribution(Double motorContribution) {
        this.motorContribution = motorContribution;
    }

    public Double getAdminCharges() {
        return adminCharges;
    }

    public void setAdminCharges(Double adminCharges) {
        this.adminCharges = adminCharges;
    }

    public Double getBrownCard() {
        return brownCard;
    }

    public void setBrownCard(Double brownCard) {
        this.brownCard = brownCard;
    }

    public Double getOwnDamageRate() {
        return ownDamageRate;
    }

    public void setOwnDamageRate(Double ownDamageRate) {
        this.ownDamageRate = ownDamageRate;
    }

    public Double getComprehensiveRate() {
        return comprehensiveRate;
    }

    public void setComprehensiveRate(Double comprehensiveRate) {
        this.comprehensiveRate = comprehensiveRate;
    }

    public Double getThirdPartyFireAndTheftRate() {
        return thirdPartyFireAndTheftRate;
    }

    public void setThirdPartyFireAndTheftRate(Double thirdPartyFireAndTheftRate) {
        this.thirdPartyFireAndTheftRate = thirdPartyFireAndTheftRate;
    }

    public Double getTppdlRate() {
        return tppdlRate;
    }

    public void setTppdlRate(Double tppdlRate) {
        this.tppdlRate = tppdlRate;
    }

    public Double getUmbrellaLimitRate() {
        return umbrellaLimitRate;
    }

    public void setUmbrellaLimitRate(Double umbrellaLimitRate) {
        this.umbrellaLimitRate = umbrellaLimitRate;
    }

    public Double getTppdlAmount() {
        return tppdlAmount;
    }

    public void setTppdlAmount(Double tppdlAmount) {
        this.tppdlAmount = tppdlAmount;
    }

    public Double getExcessRate() {
        return excessRate;
    }

    public void setExcessRate(Double excessRate) {
        this.excessRate = excessRate;
    }

    public Double getDeathLimit() {
        return deathLimit;
    }

    public void setDeathLimit(Double deathLimit) {
        this.deathLimit = deathLimit;
    }

    public Double getBodilyInjury() {
        return bodilyInjury;
    }

    public void setBodilyInjury(Double bodilyInjury) {
        this.bodilyInjury = bodilyInjury;
    }

    public Integer getMinimumSeat() {
        return minimumSeat;
    }

    public void setMinimumSeat(Integer minimumSeat) {
        this.minimumSeat = minimumSeat;
    }

    public Double getExtraSeatCharge() {
        return extraSeatCharge;
    }

    public void setExtraSeatCharge(Double extraSeatCharge) {
        this.extraSeatCharge = extraSeatCharge;
    }

    public Double getAdditionalPerilCharge() {
        return additionalPerilCharge;
    }

    public void setAdditionalPerilCharge(Double additionalPerilCharge) {
        this.additionalPerilCharge = additionalPerilCharge;
    }

    public Double getEcowasPerilCharge() {
        return ecowasPerilCharge;
    }

    public void setEcowasPerilCharge(Double ecowasPerilCharge) {
        this.ecowasPerilCharge = ecowasPerilCharge;
    }

    public Double getPersonalAccidentCharge() {
        return personalAccidentCharge;
    }

    public void setPersonalAccidentCharge(Double personalAccidentCharge) {
        this.personalAccidentCharge = personalAccidentCharge;
    }

    public Double getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(Double otherCharges) {
        this.otherCharges = otherCharges;
    }

    public Double getPrivate0() {
        return private0;
    }

    public void setPrivate0(Double private0) {
        this.private0 = private0;
    }

    public Double getPrivate1() {
        return private1;
    }

    public void setPrivate1(Double private1) {
        this.private1 = private1;
    }

    public Double getPrivate2() {
        return private2;
    }

    public void setPrivate2(Double private2) {
        this.private2 = private2;
    }

    public Double getPrivate3() {
        return private3;
    }

    public void setPrivate3(Double private3) {
        this.private3 = private3;
    }

    public Double getPrivate4() {
        return private4;
    }

    public void setPrivate4(Double private4) {
        this.private4 = private4;
    }

    public Double getPrivate5More() {
        return private5More;
    }

    public void setPrivate5More(Double private5More) {
        this.private5More = private5More;
    }

    public Double getCommercial0() {
        return commercial0;
    }

    public void setCommercial0(Double commercial0) {
        this.commercial0 = commercial0;
    }

    public Double getCommercial1() {
        return commercial1;
    }

    public void setCommercial1(Double commercial1) {
        this.commercial1 = commercial1;
    }

    public Double getCommercial2() {
        return commercial2;
    }

    public void setCommercial2(Double commercial2) {
        this.commercial2 = commercial2;
    }

    public Double getCommercial3() {
        return commercial3;
    }

    public void setCommercial3(Double commercial3) {
        this.commercial3 = commercial3;
    }

    public Double getCommercial4() {
        return commercial4;
    }

    public void setCommercial4(Double commercial4) {
        this.commercial4 = commercial4;
    }

    public Double getCommercial5More() {
        return commercial5More;
    }

    public void setCommercial5More(Double commercial5More) {
        this.commercial5More = commercial5More;
    }
    
    

   
    
    
    
}

