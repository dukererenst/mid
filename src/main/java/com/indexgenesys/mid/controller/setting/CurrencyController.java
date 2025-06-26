/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.setting;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.setting.Currency;
import com.indexgenesys.mid.service.IdGenerator;
import com.indexgenesys.mid.service.MidService;
import com.indexgenesys.mid.util.JSF;

/**
 *
 * @author ernest
 */
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CurrencyController implements Serializable, MidMethods {

    private Currency currency = new Currency();
    private List<Currency> currencys;
    private Currency selectedCurrency;
    private Currency currencyToDelete;

    @Inject
    private MidService currencyService;
    @Inject
    private IdGenerator idGenerator;

//    @PostConstruct
//    public void init() {
//        currencys = currencyService.findAll();
//    }
    public void save() {
        idGenerator.uniqueEntityId(currency);
        if (currencyService.save(currency) != null) {
            currency = new Currency();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    public void delete() {
        if (selectedCurrency != null) {
            //currencyService.delete(selectedCurrency.getId());
            //currencys = currencyService.findAll();
        }
    }

    // Getters and Setters
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<Currency> getCurrencys() {
        currencys = currencyService.findAll(Currency.class, "currencyName");
        return currencys;
    }

    public void setCurrencys(List<Currency> currencys) {
        this.currencys = currencys;
    }

    public Currency getSelectedCurrency() {
        return selectedCurrency;
    }

    public void setSelectedCurrency(Currency selectedCurrency) {
        this.selectedCurrency = selectedCurrency;
    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(currency);
        if (currencyService.save(currency) != null) {
            currency = new Currency();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        currency = new Currency();
        currencyToDelete = null;
    }

    @Override
    public void editMethod(EntityModel em) {
        currency = (Currency) em;
    }

    @Override
    public void deleteMethod(EntityModel em) {
        currency = (Currency) em;
        currency.setDeleted(true);
        if (currencyService.save(currency) != null) {
            
            JSF.addSuccessMessage(Variable.deletedSuccess);
        } else {
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Currency getCurrencyToDelete() {
        return currencyToDelete;
    }

    public void setCurrencyToDelete(Currency currencyToDelete) {
        this.currencyToDelete = currencyToDelete;
    }

    
    
}
