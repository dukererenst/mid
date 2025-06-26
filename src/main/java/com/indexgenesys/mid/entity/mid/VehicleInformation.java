package com.indexgenesys.mid.entity.mid;


import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.enums.VehicleUsageType;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import lombok.AllArgsConstructor;

/**
 *
 * @author ernest
 */

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VEHICLE_INFORMATION")
@ToString
public class VehicleInformation extends EntityModel {

    @Column(name = "REGISTRATION_NO", nullable = false, unique = true)
    private String registrationNumber;

    @Column(name = "CHASSIS_NO", nullable = false, unique = true)
    private String chassisNumber;

    @ManyToOne
    @JoinColumn(name = "VEHICLE_MAKE", nullable = false)
    private VehicleMake vehicleMake;

    @ManyToOne
    @JoinColumn(name = "VEHICLE_MODEL", nullable = false)
    private VehicleModel vehicleModel;

    @Column(name = "YEAR_OF_MANUFACTURE", nullable = false)
    private int yearOfManufacture;

    @Column(name = "VEHICLE_COLOR")
    private String color;

    @ManyToOne
    @JoinColumn(name = "VEHICLE_TYPE", nullable = false)
    private VehicleType vehicleType;
    
    @ManyToOne
    @JoinColumn(name = "VEHICLE_BODY_TYPE", nullable = false)
    private VehicleBodyType vehicleBodyType;

    @Column(name = "ENGINE_NO")
    private String engineNumber;

    @Column(name = "ENGINE_CAPACITY")
    private Double engineCapacity;

    @Column(name = "NUMBER_OF_SEATS")
    private Integer numberOfSeats;

    @Column(name = "VEHICLE_VALUE", nullable = false)
    private Double vehicleValue;

   

    @Column(name = "COMMERCIAL")
    private Boolean isCommercial;

    @Column(name = "PURPOSE_OF_USE")
    private String purposeOfUse;
    
    @Column(name = "VEHICLE_USAGE")
    @Enumerated(EnumType.STRING)
    private VehicleUsageType vehicleUsageType;

  
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public VehicleMake getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(VehicleMake vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleBodyType getVehicleBodyType() {
        return vehicleBodyType;
    }

    public void setVehicleBodyType(VehicleBodyType vehicleBodyType) {
        this.vehicleBodyType = vehicleBodyType;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public Double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Double getVehicleValue() {
        return vehicleValue;
    }

    public void setVehicleValue(Double vehicleValue) {
        this.vehicleValue = vehicleValue;
    }


    public Boolean getIsCommercial() {
        return isCommercial;
    }

    public void setIsCommercial(Boolean isCommercial) {
        this.isCommercial = isCommercial;
    }

    public String getPurposeOfUse() {
        return purposeOfUse;
    }

    public void setPurposeOfUse(String purposeOfUse) {
        this.purposeOfUse = purposeOfUse;
    }

    public VehicleUsageType getVehicleUsageType() {
        return vehicleUsageType;
    }

    public void setVehicleUsageType(VehicleUsageType vehicleUsageType) {
        this.vehicleUsageType = vehicleUsageType;
    }

    
    
}
