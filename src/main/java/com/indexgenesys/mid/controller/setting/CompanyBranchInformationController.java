/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.setting;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.setting.CompanyInformation;
import com.indexgenesys.mid.entity.setting.CompanyBranch;
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
@Named(value = "companyBranchInformationController")
@SessionScoped
public class CompanyBranchInformationController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private CompanyBranch insuranceCompany = new CompanyBranch();
    private List<CompanyBranch> insuranceCompanysList = new ArrayList<>();
    @Inject MidService insuranceCompanyFacade;
    @Inject IdGenerator idGenerator;
    private CompanyBranch companyInformationToDelete;

    public CompanyBranchInformationController() {
    }
    
      @PostConstruct
    public void init() {
        insuranceCompanysList = insuranceCompanyFacade.findAll(CompanyBranch.class, "branchName");
    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(insuranceCompany);
        if(insuranceCompanyFacade.save(insuranceCompany)!=null)
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
       insuranceCompany = new CompanyBranch();
       insuranceCompanysList = new ArrayList<>();
       companyInformationToDelete= null;
       findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
       insuranceCompany = (CompanyBranch) em;
    }

    @Override
    public void deleteMethod(EntityModel em) {
       CompanyBranch ins = (CompanyBranch) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now()); 
         if(insuranceCompanyFacade.save(ins)!=null)
        {
            JSF.addSuccessMessage(Variable.deletedSuccess);
        }
        else{
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
       insuranceCompanysList = insuranceCompanyFacade.findAll(CompanyBranch.class, "branchName"); 
    }

    public CompanyBranch getInsuranceCompanyBranch() {
        return insuranceCompany;
    }

    public CompanyBranch getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(CompanyBranch insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public List<CompanyBranch> getInsuranceCompanysList() {
        return insuranceCompanysList;
    }

    public void setInsuranceCompanysList(List<CompanyBranch> insuranceCompanysList) {
        this.insuranceCompanysList = insuranceCompanysList;
    }

    public void setInsuranceCompanyBranch(CompanyBranch insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public List<CompanyBranch> getInsuranceCompanyBranchsList() {
        return insuranceCompanysList;
    }

    public void setInsuranceCompanyBranchsList(List<CompanyBranch> insuranceCompanysList) {
        this.insuranceCompanysList = insuranceCompanysList;
    }

    public CompanyBranch getCompanyInformationToDelete() {
        return companyInformationToDelete;
    }

    public void setCompanyInformationToDelete(CompanyBranch companyInformationToDelete) {
        this.companyInformationToDelete = companyInformationToDelete;
    }
    
    

}
