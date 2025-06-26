/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.indexgenesys.mid.converter;


import com.indexgenesys.mid.entity.mid.VehicleMake;
import com.indexgenesys.mid.entity.setting.Region;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.FacesConverter;
import org.omnifaces.converter.SelectItemsConverter;

/**
 *
 * @author Darlington
 */
@FacesConverter(forClass=VehicleMake.class)
public class VehicleMakeConverter extends SelectItemsConverter {
    
    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value){
        VehicleMake agency   = (VehicleMake) value;
        if (agency != null){
            if (component.getId().toLowerCase().contains("name")){
                return agency.getVehicleMakeName();
            }
            return agency.getId();
        }
        return null;
    }
}
