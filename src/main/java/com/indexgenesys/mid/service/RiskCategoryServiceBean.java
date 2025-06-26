/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.service;

/**
 *
 * @author ernest
 */


import com.indexgenesys.mid.entity.mid.RiskCategory;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

@Stateless
public class RiskCategoryServiceBean implements RiskCategoryService {

    @PersistenceContext
    private EntityManager em;

   @Override
public List<RiskCategory> fetchPaginated(int first,
                                         int pageSize,
                                         Map<String, SortMeta> sortBy,
                                         Map<String, FilterMeta> filters) {

    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<RiskCategory> cq = cb.createQuery(RiskCategory.class);
    Root<RiskCategory> root = cq.from(RiskCategory.class);

    // --- Filtering (unchanged) ---
    List<Predicate> preds = new ArrayList<>();
    for (FilterMeta fm : filters.values()) {
        String field = fm.getField();
        Object value = fm.getFilterValue();
        if (value != null) {
            preds.add(cb.like(
                cb.lower(root.get(field).as(String.class)),
                "%" + value.toString().toLowerCase() + "%"
            ));
        }
    }
    cq.where(preds.toArray(new Predicate[0]));

    // --- Sorting (updated) ---
    List<Order> orders = new ArrayList<>();
    for (Map.Entry<String, SortMeta> entry : sortBy.entrySet()) {
        String field = entry.getKey();
        SortMeta sm = entry.getValue();
        Path<?> path = root.get(field);
        if (sm.getOrder() == SortOrder.ASCENDING) {
            orders.add(cb.asc(path));
        } else if (sm.getOrder() == SortOrder.DESCENDING) {
            orders.add(cb.desc(path));
        }
        // Note: if sm.getOrder() == SortOrder.UNSORTED you can skip
    }
    if (!orders.isEmpty()) {
        cq.orderBy(orders);
    }

    return em.createQuery(cq)
             .setFirstResult(first)
             .setMaxResults(pageSize)
             .getResultList();
}

    @Override
    public int count(Map<String, FilterMeta> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RiskCategory> root = cq.from(RiskCategory.class);

        List<Predicate> preds = new ArrayList<>();
        for (FilterMeta fm : filters.values()) {
            String field = fm.getField();
            Object value = fm.getFilterValue();
            if (value != null) {
                preds.add(cb.like(
                    cb.lower(root.get(field).as(String.class)),
                    "%" + value.toString().toLowerCase() + "%"
                ));
            }
        }
        cq.select(cb.count(root))
          .where(preds.toArray(new Predicate[0]));

        return em.createQuery(cq)
                 .getSingleResult()
                 .intValue();
    }

    @Override
    public RiskCategory findById(Long id) {
        return em.find(RiskCategory.class, id);
    }

    @Override
    public RiskCategory save(RiskCategory rc) {
        if (rc.getId() == null) {
            em.persist(rc);
            return rc;
        } else {
            return em.merge(rc);
        }
    }

    @Override
    public void delete(RiskCategory rc) {
        RiskCategory attached = em.contains(rc) ? rc : em.merge(rc);
        em.remove(attached);
    }
}

