/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.mid;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.mid.CompanyCustomer;
import com.indexgenesys.mid.service.IdGenerator;
import com.indexgenesys.mid.service.MidService;
import com.indexgenesys.mid.util.JSF;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author ernest
 */
@Named(value = "companyCustomerController")
@SessionScoped
public class CompanyCustomerController implements Serializable, MidMethods {

    private List<CompanyCustomer> filteredCompanyCustomers;
    private CompanyCustomer companyCustomer = new CompanyCustomer();
    private LazyDataModel<CompanyCustomer> lazyCompanyCustomers;
    private String globalFilter;

    @Inject
    private MidService customerService;

    @Inject
    private IdGenerator idGenerator;

    @PostConstruct
    public void init() {
        lazyCompanyCustomers = new LazyDataModel<>() {
            @Override
            public int count(Map<String, FilterMeta> map) {
                return customerService.countFilteredCompanyCustomers(map);
            }

            @Override
            public List<CompanyCustomer> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
                return customerService.getFilteredCompanyCustomers(first, pageSize, sortBy, filterBy);
            }
        };
    }

    public void prepareNewCustomer() {
        companyCustomer = new CompanyCustomer();
        companyCustomer.setStatus(true);
        companyCustomer.setActive(true);
    }

    public void editCustomer() {
        // no-op: selected via dataTable
    }

    public void deleteCustomer() {
        if (customerService.delete(companyCustomer)) {
            JSF.addSuccessMessage("Company deleted");
        } else {
            JSF.addErrorMessage("Error deleting company");
        }
    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(companyCustomer);
        if (customerService.save(companyCustomer) != null) {
            JSF.addSuccessMessage("Company saved");
        } else {
            JSF.addErrorMessage("Error saving company");
        }
    }

    // Getters and Setters
    public LazyDataModel<CompanyCustomer> getLazyCompanyCustomers() {
        return lazyCompanyCustomers;
    }

    public List<CompanyCustomer> getFilteredCompanyCustomers() {
        return filteredCompanyCustomers;
    }

    public void setFilteredCompanyCustomers(List<CompanyCustomer> filteredCompanyCustomers) {
        this.filteredCompanyCustomers = filteredCompanyCustomers;
    }

    public CompanyCustomer getCompanyCustomer() {
        return companyCustomer;
    }

    public void setCompanyCustomer(CompanyCustomer companyCustomer) {
        this.companyCustomer = companyCustomer;
    }

    public String getGlobalFilter() {
        return globalFilter;
    }

    public void setGlobalFilter(String globalFilter) {
        this.globalFilter = globalFilter;
    }

    @Override
    public void clearMethod() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editMethod(EntityModel em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteMethod(EntityModel em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
