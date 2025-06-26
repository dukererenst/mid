/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.indexgenesys.mid.converter;


import com.indexgenesys.mid.entity.mid.ProductType;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.FacesConverter;
import org.omnifaces.converter.SelectItemsConverter;

/**
 *
 * @author Darlington
 */
@FacesConverter(forClass=ProductType.class)
public class ProductTypeConverter extends SelectItemsConverter {
    
    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value){
        ProductType agency   = (ProductType) value;
        if (agency != null){
            if (component.getId().toLowerCase().contains("name")){
                return agency.getProductTypeName();
            }
            return agency.getId();
        }
        return null;
    }
}
