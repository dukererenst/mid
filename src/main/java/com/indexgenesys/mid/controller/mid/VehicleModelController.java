/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.mid;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.mid.VehicleModel;
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
@Named(value = "vehicleModelController")
@SessionScoped
public class VehicleModelController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private VehicleModel vehicleModel = new VehicleModel();
    private List<VehicleModel> vehicleModelsList = new ArrayList<>();
    @Inject
    MidService vehicleModelFacade;
    @Inject
    IdGenerator idGenerator;
    private VehicleModel vehicleModelToDelete;

    public VehicleModelController() {
    }

    @PostConstruct
    public void init() {
        findAll();
    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(vehicleModel);
        if (vehicleModelFacade.save(vehicleModel) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        vehicleModel = new VehicleModel();
        vehicleModelsList = new ArrayList<>();
        vehicleModelToDelete = null;
        findAll();

    }

    @Override
    public void editMethod(EntityModel em) {
        vehicleModel = (VehicleModel) em;
    }

    @Override
    public void deleteMethod(EntityModel em) {
        VehicleModel ins = (VehicleModel) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now());
        if (vehicleModelFacade.save(ins) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.deletedSuccess);
        } else {
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        vehicleModelsList = vehicleModelFacade.findAll(VehicleModel.class, "vehicleModelName");
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public List<VehicleModel> getVehicleModelsList() {
        return vehicleModelsList;
    }

    public void setVehicleModelsList(List<VehicleModel> vehicleModelsList) {
        this.vehicleModelsList = vehicleModelsList;
    }

    public VehicleModel getVehicleModelToDelete() {
        return vehicleModelToDelete;
    }

    public void setVehicleModelToDelete(VehicleModel vehicleModelToDelete) {
        this.vehicleModelToDelete = vehicleModelToDelete;
    }

}
