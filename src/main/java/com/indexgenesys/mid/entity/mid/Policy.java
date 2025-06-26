/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.mid;

import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.setting.CompanyInformation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ernest
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "POLICY")
@ToString
public class Policy extends EntityModel {

    @Column(name = "POLICY_NUMBER", nullable = false, unique = true)
    private String policyNumber;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private IndividualCustomer customer;

    @ManyToOne
    @JoinColumn(name = "VEHICLE_ID", nullable = false)
    private VehicleInformation vehicle;

    @ManyToOne
    @JoinColumn(name = "POLICY_TYPE_ID", nullable = false)
    private ProductType policyType;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDate endDate;

    @Column(name = "PREMIUM_AMOUNT", nullable = false)
    private Double premiumAmount;

    @Column(name = "STAMP_DUTY")
    private Double stampDuty;

    @Column(name = "LEVIES")
    private Double levies;

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private Double totalAmount;

    @Column(name = "ACTIVE", nullable = false)
    private boolean active;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSURANCE_COMPANY_ID", nullable = false)
    private CompanyInformation insuranceCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COVER_TYPE_ID", nullable = false)
    private CoverType coverType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VEHICLE_INFORMATION_ID", nullable = false)
    private VehicleInformation vehicleInformation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RISK_CATEGORY_ID", nullable = false)
    private RiskCategory riskCategory;
}

