/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.nia.response;

import java.io.Serializable;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ernest
 */

public class Person implements Serializable{
    public String nationalId;
    public String cardValidTo;
    public String surname;
    public String forenames;
    public String nationality;
    public String birthDate;
    public ArrayList<Address> addresses;
    public BiometricFeed biometricFeed; 

    public Person() {
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getCardValidTo() {
        return cardValidTo;
    }

    public void setCardValidTo(String cardValidTo) {
        this.cardValidTo = cardValidTo;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForenames() {
        return forenames;
    }

    public void setForenames(String forenames) {
        this.forenames = forenames;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    public BiometricFeed getBiometricFeed() {
        return biometricFeed;
    }

    public void setBiometricFeed(BiometricFeed biometricFeed) {
        this.biometricFeed = biometricFeed;
    }
    
    
}
