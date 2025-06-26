/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.mid;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.mid.RiskCategory;
import com.indexgenesys.mid.entity.mid.RiskCategoryItem;
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
import org.primefaces.PrimeFaces;

/**
 *
 * @author ernest
 */
@Named(value = "riskCategoryItemController")
@SessionScoped
public class RiskCategoryItemController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private RiskCategoryItem riskCategoryItem = new RiskCategoryItem();
    private List<RiskCategoryItem> riskCategoryItemsList = new ArrayList<>();
    private List<RiskCategory> riskCategorysList = new ArrayList<>();
     private RiskCategory riskCategory = new RiskCategory();
    @Inject
    MidService riskCategoryItemFacade;
    @Inject
    IdGenerator idGenerator;
    private RiskCategoryItem riskCategoryItemToDelete = null;
    private RiskCategory selectedRiskCategory;

    public RiskCategoryItemController() {
    }

//    @PostConstruct
//    public void init() {
//        riskCategorysList = riskCategoryItemFacade.findAll(RiskCategory.class, "categoryName");
//    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(riskCategoryItem);
        riskCategoryItem.setRiskCategory(selectedRiskCategory); 
        if (riskCategoryItemFacade.save(riskCategoryItem) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        riskCategoryItem = new RiskCategoryItem();
        riskCategoryItemsList = new ArrayList<>();
        riskCategoryItemToDelete = null;
        findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
        riskCategoryItem = (RiskCategoryItem) em;
    }
    public void loadData() {
       riskCategorysList = riskCategoryItemFacade.findAll(RiskCategory.class, "categoryName");
    }
    
    public void viewItems(RiskCategory rc)
    {
        selectedRiskCategory = rc;
        riskCategoryItemsList = riskCategoryItemFacade.findAll(RiskCategoryItem.class, "riskCategory.categoryName", "riskCategory", rc);
    }

    @Override
    public void deleteMethod(EntityModel em) {
        RiskCategoryItem ins = (RiskCategoryItem) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now());
        if (riskCategoryItemFacade.save(ins) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.deletedSuccess);
        } else {
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        riskCategoryItemsList = riskCategoryItemFacade.findAll(RiskCategoryItem.class, "categoryName");
    }

    public RiskCategoryItem getRiskCategoryItem() {
        return riskCategoryItem;
    }

    public void setRiskCategoryItem(RiskCategoryItem riskCategoryItem) {
        this.riskCategoryItem = riskCategoryItem;
    }

    public List<RiskCategoryItem> getRiskCategoryItemsList() {
        return riskCategoryItemsList;
    }

    public void setRiskCategoryItemsList(List<RiskCategoryItem> riskCategoryItemsList) {
        this.riskCategoryItemsList = riskCategoryItemsList;
    }

    public RiskCategoryItem getRiskCategoryItemToDelete() {
        return riskCategoryItemToDelete;
    }

    public void setRiskCategoryItemToDelete(RiskCategoryItem riskCategoryItemToDelete) {
        this.riskCategoryItemToDelete = riskCategoryItemToDelete;
        PrimeFaces.current().executeScript("PF('delConfirm').show();");
    }

    public RiskCategory getSelectedRiskCategory() {
        return selectedRiskCategory;
    }

    public void setSelectedRiskCategory(RiskCategory selectedRiskCategory) {
        this.selectedRiskCategory = selectedRiskCategory;
       
         
    }

    public List<RiskCategory> getRiskCategorysList() {
        return riskCategorysList;
    }

    public void setRiskCategorysList(List<RiskCategory> riskCategorysList) {
        this.riskCategorysList = riskCategorysList;
    }

    public RiskCategory getRiskCategory() {
        return riskCategory;
    }

    public void setRiskCategory(RiskCategory riskCategory) {
        this.riskCategory = riskCategory;
    }
    
    

}
