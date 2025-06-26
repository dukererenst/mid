/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.mid;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.mid.CoverType;
import com.indexgenesys.mid.entity.mid.CoverType;
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
@Named(value = "coverTypeController")
@SessionScoped
public class CoverTypeController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private CoverType coverType = new CoverType();
    private List<CoverType> coverTypesList = new ArrayList<>();
    @Inject MidService coverTypeFacade;
    @Inject IdGenerator idGenerator;
     private CoverType coverTypeToDelete;

    public CoverTypeController() {
    }
    
    @PostConstruct
    public void init() {
        findAll();
    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(coverType);
        if(coverTypeFacade.save(coverType)!=null)
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
       
       coverTypesList = new ArrayList<>();
       coverType = new CoverType();
        coverTypeToDelete = null;
       findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
       coverType = (CoverType) em;
      
    }

   @Override
public void deleteMethod(EntityModel em) {
    if (em == null) {
        JSF.addErrorMessage("No Cover Type selected for deletion.");
        return;
    }
    CoverType ins = (CoverType) em;
    ins.setDeleted(true);
    ins.setDeletedAt(LocalDateTime.now()); 
    if (coverTypeFacade.save(ins) != null) {
        JSF.addSuccessMessage(Variable.deletedSuccess);
       clearMethod();
    } else {
        JSF.addErrorMessage(Variable.deleteError);
    }
}


    @Override
    public void findAll() {
        coverTypesList = coverTypeFacade.findAll(CoverType.class, "coverName"); 
    }

    public CoverType getCoverType() {
        return coverType;
    }

    public void setCoverType(CoverType coverType) {
        this.coverType = coverType;
    }

    public List<CoverType> getCoverTypesList() {
        return coverTypesList;
    }

    public void setCoverTypesList(List<CoverType> coverTypesList) {
        this.coverTypesList = coverTypesList;
    }

    public CoverType getCoverTypeToDelete() {
        return coverTypeToDelete;
    }

    public void setCoverTypeToDelete(CoverType coverTypeToDelete) {
        this.coverTypeToDelete = coverTypeToDelete;
    }
    
    

}
