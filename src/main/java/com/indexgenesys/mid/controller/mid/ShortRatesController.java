/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.mid;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.mid.ShortRates;
import com.indexgenesys.mid.entity.mid.ShortRates;
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
@Named(value = "shortRatesController")
@SessionScoped
public class ShortRatesController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private ShortRates shortRates = new ShortRates();
    private List<ShortRates> shortRatessList = new ArrayList<>();
    @Inject
    MidService shortRatesFacade;
    @Inject
    IdGenerator idGenerator;
    private ShortRates shortRatesToDelete;

    public ShortRatesController() {
    }

    @PostConstruct
    public void init() {
       
        findAll();

    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(shortRates);
        if (shortRatesFacade.save(shortRates) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        shortRates = new ShortRates();
        shortRatessList = new ArrayList<>();
        shortRatesToDelete = null;
        findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
        shortRates = (ShortRates) em;

    }

    @Override
    public void deleteMethod(EntityModel em) {
        ShortRates ins = (ShortRates) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now());
        if (shortRatesFacade.save(ins) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.deletedSuccess);
        } else {
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        
        shortRatessList = shortRatesFacade.findAll(ShortRates.class, "rate");
    }

    public ShortRates getShortRates() {
        return shortRates;
    }

    public void setShortRates(ShortRates shortRates) {
        this.shortRates = shortRates;
    }

    public List<ShortRates> getShortRatessList() {
        return shortRatessList;
    }

    public void setShortRatessList(List<ShortRates> shortRatessList) {
        this.shortRatessList = shortRatessList;
    }

    public ShortRates getShortRatesToDelete() {
        return shortRatesToDelete;
    }

    public void setShortRatesToDelete(ShortRates shortRatesToDelete) {
        this.shortRatesToDelete = shortRatesToDelete;
    }

}
