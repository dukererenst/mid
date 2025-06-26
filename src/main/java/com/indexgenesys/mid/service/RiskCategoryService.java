/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.indexgenesys.mid.service;


/**
 *
 * @author ernest
 */


import com.indexgenesys.mid.entity.mid.RiskCategory;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import java.util.List;
import java.util.Map;

public interface RiskCategoryService {
    /**
     * Load a page of RiskCategory entities, applying PrimeFaces filters & sorting.
     *
     * @param first    zero‐based index of first row
     * @param pageSize number of rows per page
     * @param sortBy   map of columnField → SortMeta
     * @param filters  map of columnField → FilterMeta
     */
    List<RiskCategory> fetchPaginated(int first,
                                      int pageSize,
                                      Map<String, SortMeta> sortBy,
                                      Map<String, FilterMeta> filters);

    /**
     * Count total entities matching the given filters.
     */
    int count(Map<String, FilterMeta> filters);

    /**
     * Standard CRUD:
     */
    RiskCategory findById(Long id);
    RiskCategory save(RiskCategory riskCategory);
    void delete(RiskCategory riskCategory);
}

