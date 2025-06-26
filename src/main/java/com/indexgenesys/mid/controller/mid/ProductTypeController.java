/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.mid;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.mid.ProductType;
import com.indexgenesys.mid.entity.mid.ProductType;
import com.indexgenesys.mid.entity.mid.ProductType;
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
@Named(value = "productTypeController")
@SessionScoped
public class ProductTypeController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private ProductType productType = new ProductType();
    private List<ProductType> productTypesList = new ArrayList<>();
    @Inject
    MidService productTypeFacade;
    @Inject
    IdGenerator idGenerator;
    private ProductType productTypeToDelete;

    public ProductTypeController() {
    }

    @PostConstruct
    public void init() {
        findAll();
    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(productType);
        if (productTypeFacade.save(productType) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        productType = new ProductType();
        productTypesList = new ArrayList<>();
        findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
        productType = (ProductType) em;
    }

    @Override
    public void deleteMethod(EntityModel em) {
        ProductType ins = (ProductType) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now());
        if (productTypeFacade.save(ins) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.deletedSuccess);
        } else {
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        productTypesList = productTypeFacade.findAll(ProductType.class, "productTypeName");
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<ProductType> getProductTypesList() {
        return productTypesList;
    }

    public void setProductTypesList(List<ProductType> productTypesList) {
        this.productTypesList = productTypesList;
    }

    public ProductType getProductTypeToDelete() {
        return productTypeToDelete;
    }

    public void setProductTypeToDelete(ProductType productTypeToDelete) {
        this.productTypeToDelete = productTypeToDelete;
    }

}
