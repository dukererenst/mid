/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller.sticker;

import com.indexgenesys.mid.abstracts.MidMethods;
import com.indexgenesys.mid.common.Variable;
import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.enums.StickerRequestStatus;
import com.indexgenesys.mid.entity.sticker.StickerRequest;
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
import java.util.Map;
import java.util.UUID;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author ernest
 */
@Named(value = "approvedStickerRequestController")
@SessionScoped
public class ApprovedStickerRequestController implements Serializable, MidMethods {

    /**
     * Creates a new instance of CompanyInformationController
     */
    private StickerRequest stickerRequest = new StickerRequest();
    private StickerRequest filteredRequests;
    private List<StickerRequest> stickerRequestsList = new ArrayList<>();
    @Inject
    MidService stickerRequestFacade;
    @Inject
    IdGenerator idGenerator;
    private StickerRequest stickerRequestToDelete;
    private StickerRequest selectedRequest;
    private LazyDataModel<StickerRequest> lazyRequests;

    public ApprovedStickerRequestController() {
    }

    @PostConstruct
    public void init() {
        lazyRequests = new LazyDataModel<StickerRequest>() {

            @Override
            public String getRowKey(StickerRequest request) {
                return request.getId() != null ? request.getId() : null;
            }

            @Override
            public StickerRequest getRowData(String rowKey) {
                try {
                    UUID uuid = UUID.fromString(rowKey);
                    return stickerRequestFacade.find(StickerRequest.class, uuid);
                } catch (IllegalArgumentException e) {
                    return null;
                }
            }

            @Override
            public int count(Map<String, FilterMeta> filters) {
                return stickerRequestFacade.countStatusFiltered(filters, StickerRequestStatus.APPROVED);
            }

            @Override
            public List<StickerRequest> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filters) {
                List<StickerRequest> results = stickerRequestFacade.loadStickerByStatusLazy(first, pageSize, sortBy, filters, StickerRequestStatus.APPROVED);
                setRowCount(stickerRequestFacade.countFiltered(filters));
                return results;
            }
        };
    }

    @Override
    public void saveMethod() {
        idGenerator.uniqueEntityId(stickerRequest);
        
        if (stickerRequestFacade.save(stickerRequest) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.saveSuccess);
        } else {
            JSF.addErrorMessage(Variable.saveError);
        }
    }

    @Override
    public void clearMethod() {
        stickerRequest = new StickerRequest();
        stickerRequestsList = new ArrayList<>();
        findAll();
    }

    @Override
    public void editMethod(EntityModel em) {
        stickerRequest = (StickerRequest) em;
    }

    @Override
    public void deleteMethod(EntityModel em) {
        StickerRequest ins = (StickerRequest) em;
        ins.setDeleted(true);
        ins.setDeletedAt(LocalDateTime.now());
        if (stickerRequestFacade.save(ins) != null) {
            clearMethod();
            JSF.addSuccessMessage(Variable.deletedSuccess);
        } else {
            JSF.addErrorMessage(Variable.deleteError);
        }
    }

    @Override
    public void findAll() {
        lazyRequests = new LazyDataModel<StickerRequest>() {

            @Override
            public String getRowKey(StickerRequest request) {
                return request.getId() != null ? request.getId() : null;
            }

            @Override
            public StickerRequest getRowData(String rowKey) {
                try {
                    UUID uuid = UUID.fromString(rowKey);
                    return stickerRequestFacade.find(StickerRequest.class, uuid);
                } catch (IllegalArgumentException e) {
                    return null;
                }
            }

            @Override
            public int count(Map<String, FilterMeta> filters) {
                return stickerRequestFacade.countStatusFiltered(filters, StickerRequestStatus.APPROVED);
            }

            @Override
            public List<StickerRequest> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filters) {
                List<StickerRequest> results = stickerRequestFacade.loadStickerByStatusLazy(first, pageSize, sortBy, filters, StickerRequestStatus.APPROVED);
                setRowCount(stickerRequestFacade.countFiltered(filters));
                return results;
            }
        };
    }

    public void prepareNew() {
        stickerRequest = new StickerRequest();

    }

    public StickerRequest getStickerRequest() {
        return stickerRequest;
    }

    public void setStickerRequest(StickerRequest stickerRequest) {
        this.stickerRequest = stickerRequest;
    }

    public List<StickerRequest> getStickerRequestsList() {
        return stickerRequestsList;
    }

    public void setStickerRequestsList(List<StickerRequest> stickerRequestsList) {
        this.stickerRequestsList = stickerRequestsList;
    }

    public StickerRequest getStickerRequestToDelete() {
        return stickerRequestToDelete;
    }

    public void setStickerRequestToDelete(StickerRequest stickerRequestToDelete) {
        this.stickerRequestToDelete = stickerRequestToDelete;
    }

    public StickerRequest getFilteredRequests() {
        return filteredRequests;
    }

    public void setFilteredRequests(StickerRequest filteredRequests) {
        this.filteredRequests = filteredRequests;
    }

    public StickerRequest getSelectedRequest() {
        return selectedRequest;
    }

    public void setSelectedRequest(StickerRequest selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

    public LazyDataModel<StickerRequest> getLazyRequests() {
        return lazyRequests;
    }

    public void setLazyRequests(LazyDataModel<StickerRequest> lazyRequests) {
        this.lazyRequests = lazyRequests;
    }

}
