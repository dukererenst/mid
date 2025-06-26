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
import lombok.NoArgsConstructor;

/**
 *
 * @author ernest
 */

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VEHICLE_MAKE")
public class VehicleMake extends EntityModel{
  
     @Column(name = "MAKE_NAME")
    private String vehicleMakeName;

    @Column(name = "MAKE_CODE")
    private String vehicleMakeCode;

    @Override
    public String toString() {
        return  vehicleMakeName ;
    }
    
    

    public String getVehicleMakeName() {
        return vehicleMakeName;
    }

    public void setVehicleMakeName(String vehicleMakeName) {
        this.vehicleMakeName = vehicleMakeName;
    }

    public String getVehicleMakeCode() {
        return vehicleMakeCode;
    }

    public void setVehicleMakeCode(String vehicleMakeCode) {
        this.vehicleMakeCode = vehicleMakeCode;
    }
    
    
    
}
