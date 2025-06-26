/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.mid;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.mid.VehicleType;
import com.indexgenesys.mid.service.IdGenerator;
import com.indexgenesys.mid.service.MidService;
import com.indexgenesys.mid.util.JSF;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ernest
 */
@Named(value = "vehicleTypeController")
@SessionScoped
public class VehicleTypeController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private VehicleType vehicleType = new VehicleType();
    private List<VehicleType> vehicleTypesList = new ArrayList<>();
    @Inject
    MidService vehicleTypeFacade;
    @Inject
    IdGenerator idGenerator;
    private VehicleType vehicleTypeToDelete;

    public VehicleTypeController() {
    }

    @PostConstruct
    public void init() {
        clearMethod();

    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(vehicleType);
        if (vehicleTypeFacade.save(vehicleType) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        vehicleType = new VehicleType();
        vehicleTypesList = new ArrayList<>();
        vehicleTypeToDelete = null;
        findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
        vehicleType = (VehicleType) em;

    }

    @Override
    public void deleteMethod(EntityModel em) {
        VehicleType ins = (VehicleType) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now());
        if (vehicleTypeFacade.save(ins) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.deletedSuccess);
        } else {
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        vehicleTypesList = vehicleTypeFacade.findAll(VehicleType.class, "vehicleTypeName");
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<VehicleType> getVehicleTypesList() {
        return vehicleTypesList;
    }

    public void setVehicleTypesList(List<VehicleType> vehicleTypesList) {
        this.vehicleTypesList = vehicleTypesList;
    }

    public VehicleType getVehicleTypeToDelete() {
        return vehicleTypeToDelete;
    }

    public void setVehicleTypeToDelete(VehicleType vehicleTypeToDelete) {
        this.vehicleTypeToDelete = vehicleTypeToDelete;
    }

}
