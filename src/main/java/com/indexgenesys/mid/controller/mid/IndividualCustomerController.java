/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.controller.mid;

/**
 *
 * @author ernest
 */
import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.mid.IndividualCustomer;
import com.indexgenesys.mid.service.IdGenerator;
import com.indexgenesys.mid.service.MidService;
import com.indexgenesys.mid.util.JSF;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

@Named(value = "individualCustomerController")
@SessionScoped
public class IndividualCustomerController implements Serializable, MidMethods {

    private List<IndividualCustomer> customers;
    private List<IndividualCustomer> filteredCustomers;

    private IndividualCustomer selectedCustomer;

    private IndividualCustomer individualCustomer = new IndividualCustomer();
    private LazyDataModel<IndividualCustomer> lazyCustomers;

    @Inject
    IdGenerator idGenerator;

    @Inject
    MidService customerService;

    @PostConstruct
    public void init() {
        loadCustomers();
    }

    public void loadCustomers() {

        lazyCustomers = new LazyDataModel<IndividualCustomer>() {

            @Override
            public int count(Map<String, FilterMeta> map) {
                return customerService.countFilteredCustomers(map);
            }

            @Override
            public List<IndividualCustomer> load(int first, int pageSize, Map<String, SortMeta> map, Map<String, FilterMeta> filterBy) {
                setRowCount(customerService.countFilteredCustomers(filterBy));
                return customerService.getFilteredCustomers(first, pageSize, map, filterBy);
            }
        };

    }

    public void prepareNewCustomer() {
        individualCustomer = new IndividualCustomer();
        individualCustomer.setStatus(true);
        individualCustomer.setActive(true);
        individualCustomer.setDateOfBirth(LocalDate.now().minusYears(18)); // Default DOB
    }

    public void editCustomer() {
        // selectedCustomer is already set from dataTable selection
    }

    // Getters and Setters
    public IndividualCustomer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(IndividualCustomer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public List<IndividualCustomer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<IndividualCustomer> customers) {
        this.customers = customers;
    }

    public List<IndividualCustomer> getFilteredCustomers() {
        return filteredCustomers;
    }

    public void setFilteredCustomers(List<IndividualCustomer> filteredCustomers) {
        this.filteredCustomers = filteredCustomers;
    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(individualCustomer);
        if (customerService.save(individualCustomer) != null) {
            clearMethod();
            loadCustomers();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        individualCustomer = new IndividualCustomer();
        loadCustomers();
    }

    @Override
    public void editMethod(EntityModel em) {
        individualCustomer = (IndividualCustomer) em;
    }

    @Override
    public void deleteMethod(EntityModel em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public IndividualCustomer getIndividualCustomer() {
        return individualCustomer;
    }

    public void setIndividualCustomer(IndividualCustomer individualCustomer) {
        this.individualCustomer = individualCustomer;
    }

    public LazyDataModel<IndividualCustomer> getLazyCustomers() {
        return lazyCustomers;
    }

    public void setLazyCustomers(LazyDataModel<IndividualCustomer> lazyCustomers) {
        this.lazyCustomers = lazyCustomers;
    }

}
