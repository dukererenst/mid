/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.mid;

import com.indexgenesys.mid.entity.EntityModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "VEHICLE_BODY_TYPE")
public class VehicleBodyType extends EntityModel{
  
     @Column(name = "VEHICLE_BODY_TYPE_NAME")
    private String vehicleBodyTypeName;

    @Column(name = "VEHICLE_BODY_TYPE_CODE")
    private String vehicleBodyTypeCode;

    public String getVehicleBodyTypeName() {
        return vehicleBodyTypeName;
    }

    public void setVehicleBodyTypeName(String vehicleBodyTypeName) {
        this.vehicleBodyTypeName = vehicleBodyTypeName;
    }

    public String getVehicleBodyTypeCode() {
        return vehicleBodyTypeCode;
    }

    public void setVehicleBodyTypeCode(String vehicleBodyTypeCode) {
        this.vehicleBodyTypeCode = vehicleBodyTypeCode;
    }

    @Override
    public String toString() {
        return  vehicleBodyTypeName;
    }

  
    
}
