/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.sticker;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.sticker.StickerBatch;
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

/**
 *
 * @author ernest
 */
@Named(value = "stickerBatchController")
@SessionScoped
public class StickerBatchController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private StickerBatch stickerBatch = new StickerBatch();
    private List<StickerBatch> stickerBatchsList = new ArrayList<>();
    @Inject
    MidService stickerBatchFacade;
    @Inject
    IdGenerator idGenerator;
    private StickerBatch stickerBatchToDelete;

    public StickerBatchController() {
    }

    @PostConstruct
    public void init() {
        clearMethod();

    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(stickerBatch);
        if (stickerBatchFacade.save(stickerBatch) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        stickerBatch = new StickerBatch();
        stickerBatchsList = new ArrayList<>();
        stickerBatchToDelete = null;
        findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
        stickerBatch = (StickerBatch) em;

    }

    @Override
    public void deleteMethod(EntityModel em) {
        StickerBatch ins = (StickerBatch) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now());
        if (stickerBatchFacade.save(ins) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.deletedSuccess);
        } else {
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        stickerBatchsList = stickerBatchFacade.findAll(StickerBatch.class, "batchName");
    }

    public StickerBatch getStickerBatch() {
        return stickerBatch;
    }

    public void setStickerBatch(StickerBatch stickerBatch) {
        this.stickerBatch = stickerBatch;
    }

    public List<StickerBatch> getStickerBatchsList() {
        return stickerBatchsList;
    }

    public void setStickerBatchsList(List<StickerBatch> stickerBatchsList) {
        this.stickerBatchsList = stickerBatchsList;
    }

    public StickerBatch getStickerBatchToDelete() {
        return stickerBatchToDelete;
    }

    public void setStickerBatchToDelete(StickerBatch stickerBatchToDelete) {
        this.stickerBatchToDelete = stickerBatchToDelete;
    }

}
