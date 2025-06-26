/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.mid;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.mid.Items;
import com.indexgenesys.mid.entity.mid.Items;
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
@Named(value = "itemsController")
@SessionScoped
public class ItemsController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private Items items = new Items();
    private List<Items> itemssList = new ArrayList<>();
    @Inject MidService itemsFacade;
    @Inject IdGenerator idGenerator;
     private Items itemsToDelete;

    public ItemsController() {
    }
    
    
    @PostConstruct
    public void init() {
        findAll();
    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(items);
        if(itemsFacade.save(items)!=null)
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
       items = new Items();
       itemssList = new ArrayList<>();
       itemsToDelete = null;
       findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
       items = (Items) em;
    }

    @Override
    public void deleteMethod(EntityModel em) {
       Items ins = (Items) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now()); 
         if(itemsFacade.save(ins)!=null)
        {
            clearMethod();
            JSF.addSuccessMessage(Variable.deletedSuccess);
        }
        else{
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
         itemssList = itemsFacade.findAll(Items.class, "itemName"); 
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public List<Items> getItemssList() {
        return itemssList;
    }

    public void setItemssList(List<Items> itemssList) {
        this.itemssList = itemssList;
    }

    public Items getItemsToDelete() {
        return itemsToDelete;
    }

    public void setItemsToDelete(Items itemsToDelete) {
        this.itemsToDelete = itemsToDelete;
    }
    
    

}
