/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.mid;

import com.indexgenesys.mid.entity.EntityModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author ernest
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SHORT_RATES")
public class ShortRates extends EntityModel {

    @Column(name = "UPPER_BAND", nullable = false)
    private int upperBand;

    @Column(name = "LOWER_BAND", nullable = false)
    private int lowerBand;
    
    @Positive
    @Column(name = "RATES")
    private double rate;

    @Column(name = "ACTION")
    private String action;
    
    

    public int getUpperBand() {
        return upperBand;
    }

    public void setUpperBand(int upperBand) {
        this.upperBand = upperBand;
    }

    public int getLowerBand() {
        return lowerBand;
    }

    public void setLowerBand(int lowerBand) {
        this.lowerBand = lowerBand;
    }

   

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    

}
