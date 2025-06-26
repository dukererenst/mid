/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.mid;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.mid.VehicleBodyType;
import com.indexgenesys.mid.entity.mid.VehicleBodyType;
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
@Named(value = "vehicleBodyTypeController")
@SessionScoped
public class VehicleBodyTypeController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private VehicleBodyType vehicleBodyType = new VehicleBodyType();
    private List<VehicleBodyType> vehicleBodyTypesList = new ArrayList<>();
    @Inject
    MidService vehicleBodyTypeFacade;
    @Inject
    IdGenerator idGenerator;
    private VehicleBodyType vehicleBodyTypeToDelete;
    private VehicleBodyType selectvehicleBodyType;

    public VehicleBodyTypeController() {
    }

    @PostConstruct
    public void init() {
        findAll();
    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(vehicleBodyType);
        if (vehicleBodyTypeFacade.save(vehicleBodyType) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        vehicleBodyType = new VehicleBodyType();
        vehicleBodyTypesList = new ArrayList<>();
        findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
        vehicleBodyType = (VehicleBodyType) em;
    }

    @Override
    public void deleteMethod(EntityModel em) {
        VehicleBodyType ins = (VehicleBodyType) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now());
        if (vehicleBodyTypeFacade.save(ins) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.deletedSuccess);
        } else {
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        vehicleBodyTypesList = vehicleBodyTypeFacade.findAll(VehicleBodyType.class, "vehicleBodyTypeName");
    }

    public VehicleBodyType getVehicleBodyType() {
        return vehicleBodyType;
    }

    public void setVehicleBodyType(VehicleBodyType vehicleBodyType) {
        this.vehicleBodyType = vehicleBodyType;
    }

    public List<VehicleBodyType> getVehicleBodyTypesList() {
        return vehicleBodyTypesList;
    }

    public void setVehicleBodyTypesList(List<VehicleBodyType> vehicleBodyTypesList) {
        this.vehicleBodyTypesList = vehicleBodyTypesList;
    }

    public VehicleBodyType getVehicleBodyTypeToDelete() {
        return vehicleBodyTypeToDelete;
    }

    public void setVehicleBodyTypeToDelete(VehicleBodyType vehicleBodyTypeToDelete) {
        this.vehicleBodyTypeToDelete = vehicleBodyTypeToDelete;
    }

    public VehicleBodyType getSelectvehicleBodyType() {
        return selectvehicleBodyType;
    }

    public void setSelectvehicleBodyType(VehicleBodyType selectvehicleBodyType) {
        this.selectvehicleBodyType = selectvehicleBodyType;
    }

}
