/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.setting;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.setting.CompanyInformation;
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
@Named(value = "companyInformationController")
@SessionScoped
public class CompanyInformationController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private CompanyInformation insuranceCompany = new CompanyInformation();
    private List<CompanyInformation> insuranceCompanysList = new ArrayList<>();
    @Inject
    MidService insuranceCompanyFacade;
    @Inject
    IdGenerator idGenerator;
    private CompanyInformation companyInformationToDelete;

    public CompanyInformationController() {
    }

    @PostConstruct
    public void init() {
        insuranceCompanysList = insuranceCompanyFacade.findAll(CompanyInformation.class, "companyName");
    }

   

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(insuranceCompany);
        if (insuranceCompanyFacade.save(insuranceCompany) != null) {
            insuranceCompany = new CompanyInformation();
            JSF.addSuccessMessage(Variable.saveSuccess);
            findAll();
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        insuranceCompany = new CompanyInformation();
        insuranceCompanysList = new ArrayList<>();
        companyInformationToDelete = null;
        findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
        insuranceCompany = (CompanyInformation) em;
    }

    @Override
    public void deleteMethod(EntityModel em) {
        CompanyInformation ins = (CompanyInformation) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now());
        if (insuranceCompanyFacade.save(ins)!=null) {
            JSF.addSuccessMessage(Variable.deletedSuccess);
        } else {
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        insuranceCompanysList = insuranceCompanyFacade.findAll(CompanyInformation.class, "companyName");
    }

    public CompanyInformation getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(CompanyInformation insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public List<CompanyInformation> getInsuranceCompanysList() {
        return insuranceCompanysList;
    }

    public void setInsuranceCompanysList(List<CompanyInformation> insuranceCompanysList) {
        this.insuranceCompanysList = insuranceCompanysList;
    }

    public CompanyInformation getCompanyInformationToDelete() {
        return companyInformationToDelete;
    }

    public void setCompanyInformationToDelete(CompanyInformation companyInformationToDelete) {
        this.companyInformationToDelete = companyInformationToDelete;
    }

}
