/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.setting;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.setting.Region;
import com.indexgenesys.mid.service.IdGenerator;
import com.indexgenesys.mid.service.MidService;
import com.indexgenesys.mid.util.JSF;

/**
 *
 * @author ernest
 */
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class RegionController implements Serializable, MidMethods {

    private Region region = new Region();
    private List<Region> regions;
    private Region selectedRegion;
    private Region regionToDelete;

    @Inject
    private MidService regionService;
    @Inject
    private IdGenerator idGenerator;

//    @PostConstruct
//    public void init() {
//        regions = regionService.findAll();
//    }
    public void save() {
        idGenerator.uniqueEntityId(region);
        if (regionService.save(region) != null) {
            region = new Region();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    public void delete() {
        if (selectedRegion != null) {
            //regionService.delete(selectedRegion.getId());
            //regions = regionService.findAll();
        }
    }

    // Getters and Setters
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Region> getRegions() {
        regions = regionService.findAll(Region.class, "regionName");
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public Region getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(Region selectedRegion) {
        this.selectedRegion = selectedRegion;
    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(region);
        if (regionService.save(region) != null) {
            region = new Region();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        region = new Region();
        regionToDelete = null;
    }

    @Override
    public void editMethod(EntityModel em) {
        region = (Region) em;
    }

    @Override
    public void deleteMethod(EntityModel em) {
        region = (Region) em;
        region.setDeleted(true);
        if (regionService.save(region) != null) {
            
            JSF.addSuccessMessage(Variable.deletedSuccess);
        } else {
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Region getRegionToDelete() {
        return regionToDelete;
    }

    public void setRegionToDelete(Region regionToDelete) {
        this.regionToDelete = regionToDelete;
    }

    
    
}
