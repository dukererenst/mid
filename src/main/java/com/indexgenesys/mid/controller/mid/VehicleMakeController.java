/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.mid;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.mid.VehicleMake;
import com.indexgenesys.mid.service.IdGenerator;
import com.indexgenesys.mid.service.MidService;
import com.indexgenesys.mid.util.JSF;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;

import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ernest
 */
@Named(value = "vehicleMakeController")
@SessionScoped
public class VehicleMakeController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private VehicleMake vehicleMake = new VehicleMake();
    private List<VehicleMake> vehicleMakesList = new ArrayList<>();
    @Inject MidService vehicleMakeFacade;
    @Inject IdGenerator idGenerator;
     private VehicleMake vehicleMakeToDelete;

    public VehicleMakeController() {
    }
    
    @PostConstruct
    public void init() {
        findAll();
    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(vehicleMake);
        if(vehicleMakeFacade.save(vehicleMake)!=null)
        {
            clearMethod();
            JSF.addSuccessMessage(Variable.saveSuccess);
        }
        else{
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
       vehicleMake = new VehicleMake();
       vehicleMakesList = new ArrayList<>();
       vehicleMakeToDelete = null;
       findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
       vehicleMake = (VehicleMake) em;
    }

    @Override
    public void deleteMethod(EntityModel em) {
       VehicleMake ins = (VehicleMake) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now()); 
         if(vehicleMakeFacade.save(ins)!=null)
        {
            clearMethod();
            JSF.addSuccessMessage(Variable.deletedSuccess);
        }
        else{
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        vehicleMakesList = vehicleMakeFacade.findAll(VehicleMake.class, "vehicleMakeName"); 
    }

    public VehicleMake getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(VehicleMake vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public List<VehicleMake> getVehicleMakesList() {
        return vehicleMakesList;
    }

    public void setVehicleMakesList(List<VehicleMake> vehicleMakesList) {
        this.vehicleMakesList = vehicleMakesList;
    }

    public VehicleMake getVehicleMakeToDelete() {
        return vehicleMakeToDelete;
    }

    public void setVehicleMakeToDelete(VehicleMake vehicleMakeToDelete) {
        this.vehicleMakeToDelete = vehicleMakeToDelete;
    }
    
    

}
