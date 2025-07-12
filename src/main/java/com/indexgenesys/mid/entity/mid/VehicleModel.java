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
import java.util.Objects;

/**
 *
 * @author ernest
 */


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

    public VehicleModel() {
    }

    
    
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
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.vehicleModelName);
        hash = 67 * hash + Objects.hashCode(this.vehicleModelCode);
        hash = 67 * hash + Objects.hashCode(this.vehicleMake);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VehicleModel other = (VehicleModel) obj;
        if (!Objects.equals(this.vehicleModelName, other.vehicleModelName)) {
            return false;
        }
        if (!Objects.equals(this.vehicleModelCode, other.vehicleModelCode)) {
            return false;
        }
        return Objects.equals(this.vehicleMake, other.vehicleMake);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VehicleModel{");
        sb.append("vehicleModelName=").append(vehicleModelName);
        sb.append(", vehicleModelCode=").append(vehicleModelCode);
        sb.append(", vehicleMake=").append(vehicleMake);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
