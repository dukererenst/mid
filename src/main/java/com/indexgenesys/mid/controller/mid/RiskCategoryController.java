/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.mid;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.mid.RiskCategory;
import com.indexgenesys.mid.service.IdGenerator;
import com.indexgenesys.mid.service.MidService;
import com.indexgenesys.mid.service.RiskCategoryService;
import com.indexgenesys.mid.util.JSF;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;

import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author ernest
 */
@Named(value = "riskCategoryController")
@SessionScoped
public class RiskCategoryController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private RiskCategory riskCategory = new RiskCategory();
    private List<RiskCategory> riskCategorysList = new ArrayList<>();
    @Inject
    MidService riskCategoryFacade;
    @Inject
    IdGenerator idGenerator;
    private RiskCategory riskCategoryToDelete = null;
    
     @Inject
    private RiskCategoryService service;

    private LazyDataModel<RiskCategory> lazyRiskCategories;
    private List<RiskCategory> filteredRiskCategories;
    private RiskCategory selected;

    public RiskCategoryController() {
    }

    @PostConstruct
    public void init() {
        findAll();
    }
    
     public void prepareNew() {
        selected = new RiskCategory();
    }


    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(riskCategory);
        if (riskCategoryFacade.save(riskCategory) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        riskCategory = new RiskCategory();
        riskCategorysList = new ArrayList<>();
        riskCategoryToDelete = null;
        findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
        riskCategory = (RiskCategory) em;
    }

    @Override
    public void deleteMethod(EntityModel em) {
        RiskCategory ins = (RiskCategory) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now());
        if (riskCategoryFacade.save(ins) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.deletedSuccess);
        } else {
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        riskCategorysList = riskCategoryFacade.findAll(RiskCategory.class, "categoryName");
    }

    public RiskCategory getRiskCategory() {
        return riskCategory;
    }

    public void setRiskCategory(RiskCategory riskCategory) {
        this.riskCategory = riskCategory;
    }

    public List<RiskCategory> getRiskCategorysList() {
        return riskCategorysList;
    }

    public void setRiskCategorysList(List<RiskCategory> riskCategorysList) {
        this.riskCategorysList = riskCategorysList;
    }

    public RiskCategory getRiskCategoryToDelete() {
        return riskCategoryToDelete;
    }

    public void setRiskCategoryToDelete(RiskCategory riskCategoryToDelete) {
        this.riskCategoryToDelete = riskCategoryToDelete;
    }

    public LazyDataModel<RiskCategory> getLazyRiskCategories() {
        return lazyRiskCategories;
    }

    public void setLazyRiskCategories(LazyDataModel<RiskCategory> lazyRiskCategories) {
        this.lazyRiskCategories = lazyRiskCategories;
    }

    public List<RiskCategory> getFilteredRiskCategories() {
        return filteredRiskCategories;
    }

    public void setFilteredRiskCategories(List<RiskCategory> filteredRiskCategories) {
        this.filteredRiskCategories = filteredRiskCategories;
    }

    public RiskCategory getSelected() {
        return selected;
    }

    public void setSelected(RiskCategory selected) {
        this.selected = selected;
    }

}
