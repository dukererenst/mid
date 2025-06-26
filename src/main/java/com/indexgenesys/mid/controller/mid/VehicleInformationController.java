/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.mid;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.mid.VehicleInformation;
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
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author ernest
 */
@Named(value = "vehicleInformationController")
@SessionScoped
public class VehicleInformationController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private VehicleInformation vehicle = new VehicleInformation();
    private List<VehicleInformation> vehiclesList = new ArrayList<>();
    private List<VehicleInformation> filteredVehicleList;
    private LazyDataModel<VehicleInformation> vehiclesLazyModel;
    private List<VehicleModel> filteredModels = new ArrayList<>();
    @Inject
    MidService vehicleFacade;
    @Inject
    IdGenerator idGenerator;
    private VehicleInformation vehicleToDelete;

    public VehicleInformationController() {
    }

    @PostConstruct
    public void init() {
        vehiclesLazyModel = new LazyDataModel<VehicleInformation>() {
            @Override
            public int count(Map<String, FilterMeta> map) {
                return vehicleFacade.count(map);
            }

            @Override
            public List<VehicleInformation> load(int first, int pageSize, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                String sortField = null;
                boolean asc = true;

                if (map != null && !map.isEmpty()) {
                    SortMeta sortMeta = map.values().iterator().next();
                    sortField = sortMeta.getField();
                    asc = sortMeta.getOrder() == SortOrder.ASCENDING;
                }

                return vehicleFacade.findPaginated(first, pageSize, sortField, asc, map1);
            }
        };

    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(vehicle);
        if (vehicleFacade.save(vehicle) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        vehicle = new VehicleInformation();
        vehiclesList = new ArrayList<>();
        vehicleToDelete = null;
        findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
        vehicle = (VehicleInformation) em;
    }

    @Override
    public void deleteMethod(EntityModel em) {
        VehicleInformation ins = (VehicleInformation) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now());
        if (vehicleFacade.save(ins) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.deletedSuccess);
        } else {
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        vehiclesLazyModel = new LazyDataModel<VehicleInformation>() {
            @Override
            public int count(Map<String, FilterMeta> map) {
                return vehicleFacade.count(map);
            }

            @Override
            public List<VehicleInformation> load(int first, int pageSize, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                String sortField = null;
                boolean asc = true;

                if (map != null && !map.isEmpty()) {
                    SortMeta sortMeta = map.values().iterator().next();
                    sortField = sortMeta.getField();
                    asc = sortMeta.getOrder() == SortOrder.ASCENDING;
                }

                return vehicleFacade.findPaginated(first, pageSize, sortField, asc, map1);
            }
        };
    }

    // Call this method when make is changed
    public void onMakeChange() {

        if (vehicle.getVehicleMake() != null) {

            filteredModels = vehicleFacade.findAll(VehicleModel.class, "vehicleModelName", "vehicleMake", vehicle.getVehicleMake());

        } else {
            filteredModels = new ArrayList<>();
        }
    }

    public List<VehicleModel> getFilteredModels() {
        return filteredModels;
    }

    public void prepareNewVehicle() {
        this.vehicle = new VehicleInformation(); // resets the form to blank
    }

    public VehicleInformation getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleInformation vehicle) {
        this.vehicle = vehicle;
    }

    public List<VehicleInformation> getVehiclesList() {
        return vehiclesList;
    }

    public void setVehiclesList(List<VehicleInformation> vehiclesList) {
        this.vehiclesList = vehiclesList;
    }

    public VehicleInformation getVehicleToDelete() {
        return vehicleToDelete;
    }

    public void setVehicleToDelete(VehicleInformation vehicleToDelete) {
        this.vehicleToDelete = vehicleToDelete;
    }

    public List<VehicleInformation> getVehicleInformationsList() {
        return vehiclesList;
    }

    public void setVehicleInformationsList(List<VehicleInformation> vehiclesList) {
        this.vehiclesList = vehiclesList;
    }

    public VehicleInformation getVehicleInformationToDelete() {
        return vehicleToDelete;
    }

    public void setVehicleInformationToDelete(VehicleInformation vehicleToDelete) {
        this.vehicleToDelete = vehicleToDelete;
    }

    public List<VehicleInformation> getFilteredVehicleList() {
        return filteredVehicleList;
    }

    public void setFilteredVehicleList(List<VehicleInformation> filteredVehicleList) {
        this.filteredVehicleList = filteredVehicleList;
    }

    public LazyDataModel<VehicleInformation> getVehiclesLazyModel() {
        return vehiclesLazyModel;
    }

    public void setVehiclesLazyModel(LazyDataModel<VehicleInformation> vehiclesLazyModel) {
        this.vehiclesLazyModel = vehiclesLazyModel;
    }

}
