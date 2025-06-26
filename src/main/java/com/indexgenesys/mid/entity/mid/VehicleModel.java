/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.mid;


import com.indexgenesys.mid.entity.EntityModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "VEHICLE_MODEL")
public class VehicleModel extends EntityModel{
  
     @Column(name = "MODEL_NAME")
    private String vehicleModelName;

    @Column(name = "MODEL_CODE")
    private String vehicleModelCode;
    
    @ManyToOne
    @JoinColumn(name = "VEHICLE_MAKE")
    private VehicleMake vehicleMake;

    public String getVehicleModelName() {
        return vehicleModelName;
    }

    public void setVehicleModelName(String vehicleModelName) {
        this.vehicleModelName = vehicleModelName;
    }

    public String getVehicleModelCode() {
        return vehicleModelCode;
    }

    public void setVehicleModelCode(String vehicleModelCode) {
        this.vehicleModelCode = vehicleModelCode;
    }

    public VehicleMake getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(VehicleMake vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    @Override
    public String toString() {
        return  vehicleModelName ;
    }
    
    
    
}
