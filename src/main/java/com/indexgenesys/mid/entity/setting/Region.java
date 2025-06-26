/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.setting;


import com.indexgenesys.mid.entity.EntityModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "REGION")
public class Region extends EntityModel {

    @Column(name = "REGION_NAME")
    private String regionName;

    @Column(name = "REGION_CODE")
    private String regionCode;
    
    

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    @Override
    public String toString() {
        return  regionName ;
    }

    
}
