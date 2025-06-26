/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.sticker;


import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.enums.StickerStatus;
import com.indexgenesys.mid.entity.mid.Policy;
import com.indexgenesys.mid.entity.setting.CompanyInformation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@Table(name = "STICKER_INFORMATION")
@ToString
public class StickerInformation extends EntityModel {

    @Column(name = "STICKER_NUMBER")
    private String stickerNumber;

    @Column(name = "SERIAL_NUMBER")
    private String serialNumber;

    @Column(name = "CHECK_SUM")
    private String checkSum;

    @ManyToOne
    @JoinColumn(name = "STICKER_BATCH")
    private StickerBatch stickerBatch;
    
    @ManyToOne
    @JoinColumn(name = "COMPANY_INFORMATION")
    private CompanyInformation companyInformation;
    
    @ManyToOne
    @JoinColumn(name = "STICKER_REQUEST")
    private StickerRequest stickerRequest;
    

    @JoinColumn(name = "POLICY_ASSIGNED")
    private Policy policy;

    @Column(name = "STICKER_STATUS")
    @Enumerated(EnumType.STRING)
    private StickerStatus stickerStatus;
    
    @Column(name = "ISSUED_DATE")
    private LocalDateTime issuedDate;

    @Column(name = "EXPIRY_DATE")
    private LocalDateTime expiryDate;
    
     @Column(name = "QR_CODE_DATA")
    private String qrCodeData;

     
     
    public String getStickerNumber() {
        return stickerNumber;
    }

    public void setStickerNumber(String stickerNumber) {
        this.stickerNumber = stickerNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public StickerBatch getStickerBatch() {
        return stickerBatch;
    }

    public void setStickerBatch(StickerBatch stickerBatch) {
        this.stickerBatch = stickerBatch;
    }

    public CompanyInformation getCompanyInformation() {
        return companyInformation;
    }

    public void setCompanyInformation(CompanyInformation companyInformation) {
        this.companyInformation = companyInformation;
    }

    public StickerRequest getStickerRequest() {
        return stickerRequest;
    }

    public void setStickerRequest(StickerRequest stickerRequest) {
        this.stickerRequest = stickerRequest;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public StickerStatus getStickerStatus() {
        return stickerStatus;
    }

    public void setStickerStatus(StickerStatus stickerStatus) {
        this.stickerStatus = stickerStatus;
    }

    public LocalDateTime getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDateTime issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getQrCodeData() {
        return qrCodeData;
    }

    public void setQrCodeData(String qrCodeData) {
        this.qrCodeData = qrCodeData;
    }

   
    
}
