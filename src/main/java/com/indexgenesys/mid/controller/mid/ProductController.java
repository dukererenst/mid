/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.mid;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.mid.Product;
import com.indexgenesys.mid.entity.mid.Product;
import com.indexgenesys.mid.entity.mid.Product;
import com.indexgenesys.mid.entity.mid.Product;
import com.indexgenesys.mid.entity.mid.VehicleInformation;
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
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author ernest
 */
@Named(value = "productController")
@SessionScoped
public class ProductController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private Product product = new Product();
    private List<Product> productsList = new ArrayList<>();
    @Inject
    MidService productFacade;
    @Inject
    IdGenerator idGenerator;
    private Product productToDelete;
    
     private Product selectedProduct;
    private LazyDataModel<Product> productsLazyModel;
    private List<Product> filteredProducts;

    public ProductController() {
    }
    
    
    public void prepareNewProduct() {
        this.product = new Product(); // resets the form to blank
    }


    @PostConstruct
    public void init() {
         productsLazyModel = new LazyDataModel<Product>() {
            @Override
            public int count(Map<String, FilterMeta> map) {
                return productFacade.countProductFiltered(map);
            }

            @Override
            public List<Product> load(int first, int pageSize, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                String sortField = null;
                boolean asc = true;

                if (map != null && !map.isEmpty()) {
                    SortMeta sortMeta = map.values().iterator().next();
                    sortField = sortMeta.getField();
                    asc = sortMeta.getOrder() == SortOrder.ASCENDING;
                }

                return productFacade.getFilteredProducts(first, pageSize, map, map1); 
            }
        };
    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(product);
        if (productFacade.save(product) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        product = new Product();
        productsList = new ArrayList<>();
        findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
        product = (Product) em;
    }

    @Override
    public void deleteMethod(EntityModel em) {
        Product ins = (Product) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now());
        if (productFacade.save(ins) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.deletedSuccess);
        } else {
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        productsList = productFacade.findAll(Product.class, "productName");
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }

    public Product getProductToDelete() {
        return productToDelete;
    }

    public void setProductToDelete(Product productToDelete) {
        this.productToDelete = productToDelete;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public LazyDataModel<Product> getProductsLazyModel() {
        return productsLazyModel;
    }

    public void setProductsLazyModel(LazyDataModel<Product> productsLazyModel) {
        this.productsLazyModel = productsLazyModel;
    }

    public List<Product> getFilteredProducts() {
        return filteredProducts;
    }

    public void setFilteredProducts(List<Product> filteredProducts) {
        this.filteredProducts = filteredProducts;
    }
    
    

}
