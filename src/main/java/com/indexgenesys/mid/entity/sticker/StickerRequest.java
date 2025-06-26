/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.sticker;

import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.enums.ModeOfPayment;
import com.indexgenesys.mid.entity.enums.StickerRequestStatus;
import com.indexgenesys.mid.entity.setting.CompanyInformation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Builder;
import lombok.ToString;

/**
 *
 * @author ernest
 */
@Entity
@Table(name = "STICKER_REQUEST")
@ToString(onlyExplicitlyIncluded = true)
public class StickerRequest extends EntityModel {

    
    @ManyToOne
    @JoinColumn(name = "STICKER_BATCH")
    private StickerBatch stickerBatch;
    
    @Column(name = "AMOUNT_PAID")
    private Double amountPaid;
    
    @Column(name = "QUANTITY")
    private int quantity;
    
    @Column(name = "REQUEST_BY")
    private String requestedBy;
    
    @Column(name = "APPROVED_BY")
    private String approvedBy;
    
    @Column(name = "REQUEST_STATUS")
    @Enumerated(EnumType.STRING)
    private StickerRequestStatus requestStatus;
    
    @Column(name = "MODE_OF_PAYMENT")
    @Enumerated(EnumType.STRING)
    private ModeOfPayment modeOfPayment;
    
     @Column(name = "PAYMENT_REF_NO")
    private String paymentRefNo;
     
     @Column(name = "DATE_OF_PAYMENT")
     @Temporal(TemporalType.DATE)
    private Date dateOfPayment;
    
    @ManyToOne
    @JoinColumn(name = "COMPANY_INFORMATION")
    private CompanyInformation companyInformation;

    public CompanyInformation getCompanyInformation() {
        return companyInformation;
    }

    public void setCompanyInformation(CompanyInformation companyInformation) {
        this.companyInformation = companyInformation;
    }

    public StickerRequest() {
    }
    

    public StickerBatch getStickerBatch() {
        return stickerBatch;
    }

    public void setStickerBatch(StickerBatch stickerBatch) {
        this.stickerBatch = stickerBatch;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public StickerRequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(StickerRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public ModeOfPayment getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(ModeOfPayment modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getPaymentRefNo() {
        return paymentRefNo;
    }

    public void setPaymentRefNo(String paymentRefNo) {
        this.paymentRefNo = paymentRefNo;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }
    
    
    
}
