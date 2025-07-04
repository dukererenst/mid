/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.service;

import com.indexgenesys.mid.entity.EntityModel;
import com.indexgenesys.mid.entity.enums.ModeOfPayment;
import com.indexgenesys.mid.entity.enums.StickerRequestStatus;
import com.indexgenesys.mid.entity.enums.StickerStatus;
import com.indexgenesys.mid.entity.mid.CompanyCustomer;
import com.indexgenesys.mid.entity.mid.IndividualCustomer;
import com.indexgenesys.mid.entity.mid.Product;
import com.indexgenesys.mid.entity.mid.VehicleInformation;
import com.indexgenesys.mid.entity.setting.CompanyInformation;
import com.indexgenesys.mid.entity.sticker.StickerInformation;
import com.indexgenesys.mid.entity.sticker.StickerRequest;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Ernest
 */
@Stateless
public class MidService implements Serializable {

    @PersistenceContext(name = "midConnectionPool")
    EntityManager em;

    public Boolean delete(EntityModel information) {
        try {
            information.setDeleted(Boolean.TRUE);
            em.merge(information);
            em.flush();
            em.clear();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public <T> T find(Class<T> t, Object id) {
        try {

            if (em == null) {
                return null;
            }
            return (T) em.find(t, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public <T> T save(Object model) {
        try {
            return saveEntity(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    public boolean saveAll(List<? extends EntityModel> models) throws Exception {
        try {
            for (EntityModel m : models) {
                em.persist(m);
            }
            em.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
//            throw new Exception(e);
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public <T> T save(EntityModel model) {

        try {
//            if (loginController.getUserAccount() != null) {
//                model.setLastModifiedBy(loginController.getUserAccount().getFullName());
//                model.setLastModifiedAt(new Date());
//            }
            if (model.getId() == null) {
                em.persist(model);
            } else if (find(model.getClass(), model.getId()) != null) {
                em.merge(model);
            } else {
                em.persist(model);
            }

            em.flush();

            return (T) model;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T save(EntityModel model, String modifiedBy) {

        try {

            model.setLastModifiedBy(modifiedBy);
            model.setLastModifiedAt(LocalDateTime.now());
            if (model.getId() == null) {
                em.persist(model);
            } else if (find(model.getClass(), model.getId()) != null) {
                em.merge(model);
            } else {
                em.persist(model);
            }

            em.flush();

            return (T) model;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public <T> T saveEntity(Object object) {

        em.merge(object);
        em.flush();

        return (T) object;
    }

    public boolean rollBack() {
        try {
            em.getTransaction().rollback();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("rawtypes")
    public List findAll(Class t) {
        try {
            String qry = "SELECT e FROM " + t.getSimpleName() + " e";

            return em.createQuery(qry).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.EMPTY_LIST;
    }

    @SuppressWarnings("rawtypes")
    public List findAll(Class t, boolean deleted) {
        try {
            String qry = "SELECT e FROM " + t.getSimpleName() + " e"
                    + " WHERE e.deleted =:deleted";
            return em.createQuery(qry).setParameter("deleted", deleted).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.EMPTY_LIST;
    }

    @SuppressWarnings("rawtypes")
    public List findAll(Class t, String orderName) {
        try {
            String qry = "SELECT e FROM " + t.getSimpleName() + " e"
                    + " WHERE e.deleted =:deleted "
                    + " ORDER BY e." + orderName + " ASC";
            return em.createQuery(qry).setParameter("deleted", false).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.EMPTY_LIST;
    }

    @SuppressWarnings("rawtypes")
    public List findAll(Class t, String orderName, String searchBy, Object searchValue) {
        try {
            String qry = "SELECT e FROM " + t.getSimpleName() + " e "
                    + " WHERE e.deleted =:deleted "
                    + " AND e." + searchBy + " =:searchValue"
                    + " ORDER BY e." + orderName + " ASC";
            return em.createQuery(qry).
                    setParameter("deleted", false).
                    setParameter("searchValue", searchValue).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.EMPTY_LIST;
    }

    @SuppressWarnings("rawtypes")
    public List findAll(Class t, String orderName, String searchBy, String searchValue) {
        try {
            String qry = "SELECT e FROM " + t.getSimpleName() + " e "
                    + " WHERE e.deleted =:deleted "
                    + " AND e." + searchBy + " ='" + searchValue + "'"
                    + " ORDER BY e." + orderName + " DESC";
            return em.createQuery(qry).
                    setParameter("deleted", false)
                    //                    setParameter("searchValue", searchValue)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.EMPTY_LIST;
    }

    @SuppressWarnings("rawtypes")
    public List findAll(Class t, String orderName, String searchBy, Object searchValue, String searchBy2, Object searchValue2) {
        try {
            String qry = "SELECT e FROM " + t.getSimpleName() + " e "
                    + " WHERE e.deleted =:deleted "
                    + " AND e." + searchBy + " =:searchValue"
                    + " AND e." + searchBy2 + " =:searchValue2"
                    + " ORDER BY e." + orderName + " ASC";
            return em.createQuery(qry).
                    setParameter("deleted", false).
                    setParameter("searchValue2", searchValue2).
                    setParameter("searchValue", searchValue).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.EMPTY_LIST;
    }

    @SuppressWarnings("rawtypes")
    public List findAll(Class t, String orderName, String searchBy, Object searchValue, String searchBy2, Object searchValue2, String searchBy3, Object searchValue3) {
        try {
            String qry = "SELECT e FROM " + t.getSimpleName() + " e "
                    + " WHERE e.deleted =:deleted "
                    + " AND e." + searchBy + " =:searchValue"
                    + " AND e." + searchBy2 + " =:searchValue2"
                    + " AND e." + searchBy3 + " =:searchValue3"
                    + " ORDER BY e." + orderName + " ASC";
            return em.createQuery(qry).
                    setParameter("deleted", false).
                    setParameter("searchValue2", searchValue2).
                    setParameter("searchValue3", searchValue3).
                    setParameter("searchValue", searchValue).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.EMPTY_LIST;
    }

    @SuppressWarnings("rawtypes")
    public List findAllWithDelete(Class t, String orderName, String searchBy, Object searchValue, String searchBy2, Object searchValue2) {
        try {
            String qry = "SELECT e FROM " + t.getSimpleName() + " e "
                    + " WHERE e." + searchBy + " =:searchValue"
                    + " AND e." + searchBy2 + " =:searchValue2"
                    + " ORDER BY e." + orderName + " DESC";
            return em.createQuery(qry).
                    setParameter("searchValue2", searchValue2).
                    setParameter("searchValue", searchValue).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.EMPTY_LIST;
    }

    @SuppressWarnings("rawtypes")
    public List findAllWithOr(Class t, String orderName, String searchBy, Object searchValue, String searchBy2, Object searchValue2) {
        try {
            String qry = "SELECT e FROM " + t.getSimpleName() + " e "
                    + " WHERE e.deleted =:deleted "
                    + " AND e." + searchBy + " =:searchValue"
                    + " OR e." + searchBy2 + " =:searchValue2"
                    + " ORDER BY e." + orderName + " ASC";
            return em.createQuery(qry).
                    setParameter("deleted", false).
                    setParameter("searchValue2", searchValue2).
                    setParameter("searchValue", searchValue).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.EMPTY_LIST;
    }

    @SuppressWarnings("rawtypes")
    public List findAllNoDelete(Class t, String orderName, String searchBy, String searchValue) {
        try {
            String qry = "SELECT e FROM " + t.getSimpleName() + " e "
                    + " WHERE e." + searchBy + " ='" + searchValue + "'"
                    + " ORDER BY e." + orderName + " DESC";
            return em.createQuery(qry)
                    //                    setParameter("searchValue", searchValue)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.EMPTY_LIST;
    }

    public List<VehicleInformation> findPaginated(int first, int pageSize, String sortField, boolean asc, Map<String, FilterMeta> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<VehicleInformation> cq = cb.createQuery(VehicleInformation.class);
        Root<VehicleInformation> root = cq.from(VehicleInformation.class);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();
        String globalFilterValue = null;

        for (Map.Entry<String, FilterMeta> entry : filters.entrySet()) {
            String field = entry.getKey();
            String value = String.valueOf(entry.getValue().getFilterValue());

            if ("globalFilter".equals(field)) {
                globalFilterValue = value;
                continue;
            }

            if (value != null && !value.isBlank()) {
                if (field.contains(".")) {
                    // For nested fields (e.g., vehicleMake.vehicleMakeName)
                    String[] parts = field.split("\\.");
                    Path<String> nestedPath = root.get(parts[0]).get(parts[1]);
                    predicates.add(cb.like(cb.lower(nestedPath.as(String.class)), "%" + value.toLowerCase() + "%"));
                } else {
                    predicates.add(cb.like(cb.lower(root.get(field).as(String.class)), "%" + value.toLowerCase() + "%"));
                }
            }
        }

        // Global filter applied to multiple fields
        if (globalFilterValue != null && !globalFilterValue.isBlank()) {
            String filterVal = "%" + globalFilterValue.toLowerCase() + "%";
            Predicate global = cb.or(
                    cb.like(cb.lower(root.get("registrationNumber")), filterVal),
                    cb.like(cb.lower(root.get("chassisNumber")), filterVal),
                    cb.like(cb.lower(root.get("color")), filterVal),
                    cb.like(cb.lower(root.get("purposeOfUse")), filterVal)
            // Add more as needed
            );
            predicates.add(global);
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // Sorting
        if (sortField != null && !sortField.isBlank()) {
            Path<?> sortPath = sortField.contains(".")
                    ? root.get(sortField.split("\\.")[0]).get(sortField.split("\\.")[1])
                    : root.get(sortField);
            cq.orderBy(asc ? cb.asc(sortPath) : cb.desc(sortPath));
        }

        return em.createQuery(cq)
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public int count(Map<String, FilterMeta> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<VehicleInformation> root = cq.from(VehicleInformation.class);
        cq.select(cb.count(root));

        List<Predicate> predicates = new ArrayList<>();
        String globalFilterValue = null;

        for (Map.Entry<String, FilterMeta> entry : filters.entrySet()) {
            String field = entry.getKey();
            String value = String.valueOf(entry.getValue().getFilterValue());

            if ("globalFilter".equals(field)) {
                globalFilterValue = value;
                continue;
            }

            if (value != null && !value.isBlank()) {
                if (field.contains(".")) {
                    String[] parts = field.split("\\.");
                    Path<String> nestedPath = root.get(parts[0]).get(parts[1]);
                    predicates.add(cb.like(cb.lower(nestedPath.as(String.class)), "%" + value.toLowerCase() + "%"));
                } else {
                    predicates.add(cb.like(cb.lower(root.get(field).as(String.class)), "%" + value.toLowerCase() + "%"));
                }
            }
        }

        if (globalFilterValue != null && !globalFilterValue.isBlank()) {
            String filterVal = "%" + globalFilterValue.toLowerCase() + "%";
            Predicate global = cb.or(
                    cb.like(cb.lower(root.get("registrationNumber")), filterVal),
                    cb.like(cb.lower(root.get("chassisNumber")), filterVal),
                    cb.like(cb.lower(root.get("color")), filterVal),
                    cb.like(cb.lower(root.get("purposeOfUse")), filterVal)
            );
            predicates.add(global);
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        return em.createQuery(cq).getSingleResult().intValue();
    }

    public List<StickerRequest> loadLazy(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StickerRequest> cq = cb.createQuery(StickerRequest.class);
        Root<StickerRequest> root = cq.from(StickerRequest.class);

        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, FilterMeta> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object filterValue = entry.getValue().getFilterValue();

            if (filterValue != null && !filterValue.toString().trim().isEmpty()) {
                Path<?> path = getPath(root, key);

                // Check for Enum fields
                if ("requestStatus".equals(key)) {
                    try {
                        StickerRequestStatus status = StickerRequestStatus.valueOf(filterValue.toString());
                        predicates.add(cb.equal(path, status));
                    } catch (IllegalArgumentException e) {
                        // ignore invalid enum
                    }
                } else if ("modeOfPayment".equals(key)) {
                    try {
                        ModeOfPayment payment = ModeOfPayment.valueOf(filterValue.toString());
                        predicates.add(cb.equal(path, payment));
                    } catch (IllegalArgumentException e) {
                        // ignore invalid enum
                    }
                } else {
                    predicates.add(cb.like(cb.lower(path.as(String.class)), "%" + filterValue.toString().toLowerCase() + "%"));
                }
            }
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // Sorting
        if (sortBy != null && !sortBy.isEmpty()) {
            List<Order> orders = new ArrayList<>();
            for (Map.Entry<String, SortMeta> entry : sortBy.entrySet()) {
                Path<?> path = getPath(root, entry.getKey());
                if (entry.getValue().getOrder() == SortOrder.ASCENDING) {
                    orders.add(cb.asc(path));
                } else if (entry.getValue().getOrder() == SortOrder.DESCENDING) {
                    orders.add(cb.desc(path));
                }
            }
            cq.orderBy(orders);
        } else {
            // Default sort by createdAt descending
            cq.orderBy(cb.desc(root.get("createdAt")));
        }

        return em.createQuery(cq)
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public int countFiltered(Map<String, FilterMeta> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<StickerRequest> root = cq.from(StickerRequest.class);

        cq.select(cb.count(root));

        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, FilterMeta> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object filterValue = entry.getValue().getFilterValue();

            if (filterValue != null && !filterValue.toString().trim().isEmpty()) {
                Path<?> path = getPath(root, key);

                if ("requestStatus".equals(key)) {
                    try {
                        StickerRequestStatus status = StickerRequestStatus.valueOf(filterValue.toString());
                        predicates.add(cb.equal(path, status));
                    } catch (IllegalArgumentException ignored) {
                    }
                } else if ("modeOfPayment".equals(key)) {
                    try {
                        ModeOfPayment payment = ModeOfPayment.valueOf(filterValue.toString());
                        predicates.add(cb.equal(path, payment));
                    } catch (IllegalArgumentException ignored) {
                    }
                } else {
                    predicates.add(cb.like(cb.lower(path.as(String.class)), "%" + filterValue.toString().toLowerCase() + "%"));
                }
            }
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        return em.createQuery(cq).getSingleResult().intValue();
    }

    private Path<?> getPath(Root<?> root, String fieldPath) {
        Path<?> path = root;
        for (String part : fieldPath.split("\\.")) {
            path = path.get(part);
        }
        return path;
    }

    public List<StickerRequest> loadStickerByStatusLazy(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filters, StickerRequestStatus requestStatus) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StickerRequest> cq = cb.createQuery(StickerRequest.class);
        Root<StickerRequest> root = cq.from(StickerRequest.class);

        List<Predicate> predicates = new ArrayList<>();

        // Only load REQUESTED
        predicates.add(cb.equal(root.get("requestStatus"), requestStatus));

        // Other filter fields
        for (Map.Entry<String, FilterMeta> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object filterValue = entry.getValue().getFilterValue();
            if (filterValue != null && !filterValue.toString().trim().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get(key).as(String.class)), "%" + filterValue.toString().toLowerCase() + "%"));
            }
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // Sorting
        if (!sortBy.isEmpty()) {
            List<Order> orders = new ArrayList<>();
            for (SortMeta sm : sortBy.values()) {
                if (sm.getOrder().isAscending()) {
                    orders.add(cb.asc(root.get(sm.getField())));
                } else {
                    orders.add(cb.desc(root.get(sm.getField())));
                }
            }
            cq.orderBy(orders);
        } else {
            // Default sort
            cq.orderBy(cb.desc(root.get("createdAt")));
        }

        return em.createQuery(cq)
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public int countStatusFiltered(Map<String, FilterMeta> filters, StickerRequestStatus requestStatus) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<StickerRequest> root = cq.from(StickerRequest.class);
        cq.select(cb.count(root));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("requestStatus"), requestStatus));

        for (Map.Entry<String, FilterMeta> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object filterValue = entry.getValue().getFilterValue();
            if (filterValue != null && !filterValue.toString().trim().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get(key).as(String.class)), "%" + filterValue.toString().toLowerCase() + "%"));
            }
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        return em.createQuery(cq).getSingleResult().intValue();
    }

    public List<StickerInformation> loadStickerByStatusLazy(int first, int pageSize,
            Map<String, SortMeta> sortBy,
            Map<String, FilterMeta> filters,
            StickerStatus status) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StickerInformation> cq = cb.createQuery(StickerInformation.class);
        Root<StickerInformation> root = cq.from(StickerInformation.class);

        List<Predicate> predicates = new ArrayList<>();

        // Filter by status
        predicates.add(cb.equal(root.get("stickerStatus"), status));

        // Filtering
        for (Map.Entry<String, FilterMeta> entry : filters.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue().getFilterValue();
            if (value == null || value.toString().isBlank()) {
                continue;
            }

            if ("globalFilter".equals(field)) {
                String val = "%" + value.toString().toLowerCase() + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("stickerNumber")), val),
                        cb.like(cb.lower(root.get("serialNumber")), val),
                        cb.like(cb.lower(root.get("checkSum")), val),
                        cb.like(cb.lower(root.get("qrCodeData")), val)
                ));
            } else if ("companyInformation.companyName".equals(field)) {
                predicates.add(cb.like(cb.lower(root.get("companyInformation").get("companyName")),
                        "%" + value.toString().toLowerCase() + "%"));
            } else if ("stickerBatch.batchName".equals(field)) {
                predicates.add(cb.like(cb.lower(root.get("stickerBatch").get("batchName")),
                        "%" + value.toString().toLowerCase() + "%"));
            } else if ("stickerStatus".equals(field)) {
                try {
                    predicates.add(cb.equal(root.get("stickerStatus"),
                            Enum.valueOf(StickerStatus.class, value.toString())));
                } catch (IllegalArgumentException ignored) {
                }
            } else {
                // Default string-based filtering
                predicates.add(cb.like(cb.lower(root.get(field).as(String.class)),
                        "%" + value.toString().toLowerCase() + "%"));
            }
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // Sorting
        List<Order> orders = new ArrayList<>();
        sortBy.forEach((field, meta) -> {
            if (meta.getOrder().isAscending()) {
                orders.add(cb.asc(root.get(field)));
            } else if (meta.getOrder().isDescending()) {
                orders.add(cb.desc(root.get(field)));
            }
        });

        // Default order by issuedDate descending
        if (orders.isEmpty()) {
            orders.add(cb.desc(root.get("issuedDate")));
        }

        cq.orderBy(orders);

        return em.createQuery(cq)
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public int countStatusFiltered(Map<String, FilterMeta> filters, StickerStatus status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<StickerInformation> root = cq.from(StickerInformation.class);

        List<Predicate> predicates = new ArrayList<>();

        // Always filter by status
        predicates.add(cb.equal(root.get("stickerStatus"), status));

        for (Map.Entry<String, FilterMeta> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue().getFilterValue();

            if (value == null || value.toString().isBlank()) {
                continue;
            }

            if ("globalFilter".equals(key)) {
                String search = "%" + value.toString().toLowerCase() + "%";
                Predicate global = cb.or(
                        cb.like(cb.lower(root.get("stickerNumber")), search),
                        cb.like(cb.lower(root.get("serialNumber")), search),
                        cb.like(cb.lower(root.get("checkSum")), search),
                        cb.like(cb.lower(root.get("qrCodeData")), search)
                );
                predicates.add(global);
            } else if ("companyInformation.companyName".equals(key)) {
                predicates.add(cb.like(
                        cb.lower(root.get("companyInformation").get("companyName")),
                        "%" + value.toString().toLowerCase() + "%"
                ));
            } else if ("stickerBatch.batchName".equals(key)) {
                predicates.add(cb.like(
                        cb.lower(root.get("stickerBatch").get("batchName")),
                        "%" + value.toString().toLowerCase() + "%"
                ));
            } else if ("requestStatus".equals(key) || "modeOfPayment".equals(key) || "stickerStatus".equals(key)) {
                try {
                    predicates.add(cb.equal(root.get(key), Enum.valueOf(
                            (Class<Enum>) root.get(key).getJavaType(), value.toString()
                    )));
                } catch (IllegalArgumentException ignored) {
                }
            } else {
                // Default string-based filtering
                predicates.add(cb.like(cb.lower(root.get(key).as(String.class)), "%" + value.toString().toLowerCase() + "%"));
            }
        }

        cq.select(cb.count(root));
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        return em.createQuery(cq).getSingleResult().intValue();
    }

    public List<Product> getFilteredProducts(int first, int pageSize,
            Map<String, SortMeta> sortBy,
            Map<String, FilterMeta> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, FilterMeta> entry : filters.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue().getFilterValue();
            if ("globalFilter".equals(field)) {
                continue;
            }
            if (value != null) {
                predicates.add(cb.like(cb.lower(root.get(field).as(String.class)), "%" + value.toString().toLowerCase() + "%"));
            }
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        if (sortBy != null) {
            for (SortMeta meta : sortBy.values()) {
                String sortField = meta.getField();
                if (meta.getOrder().isAscending()) {
                    cq.orderBy(cb.asc(root.get(sortField)));
                } else {
                    cq.orderBy(cb.desc(root.get(sortField)));
                }
            }
        }

        TypedQuery<Product> query = em.createQuery(cq);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public int countProductFiltered(Map<String, FilterMeta> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Product> root = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, FilterMeta> entry : filters.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue().getFilterValue();
            if ("globalFilter".equals(field)) {
                continue;
            }
            if (value != null) {
                predicates.add(cb.like(cb.lower(root.get(field).as(String.class)), "%" + value.toString().toLowerCase() + "%"));
            }
        }

        cq.select(cb.count(root));
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        return em.createQuery(cq).getSingleResult().intValue();
    }

    public List<Product> getFilteredProducts(int first, int pageSize,
            Map<String, SortMeta> sortBy,
            Map<String, FilterMeta> filters,
            CompanyInformation companyInformation) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);

        Join<Product, CompanyInformation> companyJoin = root.join("companyInformation", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        // Static filter by company
        if (companyInformation != null && companyInformation.getId() != null) {
            predicates.add(cb.equal(companyJoin.get("id"), companyInformation.getId()));
        }

        // Dynamic filters
        for (Map.Entry<String, FilterMeta> entry : filters.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue().getFilterValue();
            if ("globalFilter".equals(field)) {
                continue;
            }
            if (value != null && !value.toString().isBlank()) {
                switch (field) {
                    case "companyInformation.name":
                        predicates.add(cb.like(cb.lower(companyJoin.get("name")),
                                "%" + value.toString().toLowerCase() + "%"));
                        break;
                    case "productName":
                    case "productCode":
                    case "description":
                        predicates.add(cb.like(cb.lower(root.get(field)),
                                "%" + value.toString().toLowerCase() + "%"));
                        break;
                    default:
                        try {
                            predicates.add(cb.like(cb.lower(root.get(field).as(String.class)),
                                    "%" + value.toString().toLowerCase() + "%"));
                        } catch (IllegalArgumentException ignored) {
                            // Handle or log invalid field
                        }
                }
            }
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // Sorting
        if (sortBy != null && !sortBy.isEmpty()) {
            List<Order> orders = new ArrayList<>();
            for (SortMeta meta : sortBy.values()) {
                String field = meta.getField();
                boolean asc = meta.getOrder().isAscending();

                if ("companyInformation.name".equals(field)) {
                    orders.add(asc ? cb.asc(companyJoin.get("name")) : cb.desc(companyJoin.get("name")));
                } else {
                    orders.add(asc ? cb.asc(root.get(field)) : cb.desc(root.get(field)));
                }
            }
            cq.orderBy(orders);
        }

        TypedQuery<Product> query = em.createQuery(cq);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public int countFiltered(Map<String, FilterMeta> filters, CompanyInformation companyInformation) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Product> root = cq.from(Product.class);
        Join<Product, CompanyInformation> companyJoin = root.join("companyInformation", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        if (companyInformation != null && companyInformation.getId() != null) {
            predicates.add(cb.equal(companyJoin.get("id"), companyInformation.getId()));
        }

        for (Map.Entry<String, FilterMeta> entry : filters.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue().getFilterValue();
//            if ("globalFilter".equals(field)) {
//                continue;
//            }
            if (value != null && !value.toString().isBlank()) {
                if ("companyInformation.name".equals(field)) {
                    predicates.add(cb.like(cb.lower(companyJoin.get("name")),
                            "%" + value.toString().toLowerCase() + "%"));
                } else {
                    predicates.add(cb.like(cb.lower(root.get(field).as(String.class)),
                            "%" + value.toString().toLowerCase() + "%"));
                }
            }
        }

        cq.select(cb.count(root));
        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        return em.createQuery(cq).getSingleResult().intValue();
    }

    public List<IndividualCustomer> getFilteredCustomers(int first, int pageSize,
            Map<String, SortMeta> sortBy,
            Map<String, FilterMeta> filters) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<IndividualCustomer> cq = cb.createQuery(IndividualCustomer.class);
        Root<IndividualCustomer> root = cq.from(IndividualCustomer.class);

        List<Predicate> predicates = buildFilterPredicates(cb, root, filters);
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // Sorting
        if (sortBy != null && !sortBy.isEmpty()) {
            List<Order> orders = new ArrayList<>();
            for (SortMeta meta : sortBy.values()) {
                String field = meta.getField();
                boolean ascending = meta.getOrder().isAscending();

                if (field == null || field.trim().isEmpty()) {
                    continue;
                }

                try {
                    orders.add(ascending ? cb.asc(root.get(field)) : cb.desc(root.get(field)));
                } catch (IllegalArgumentException ignored) {
                    // Invalid sort field - optionally log
                }
            }
            cq.orderBy(orders);
        }

        TypedQuery<IndividualCustomer> query = em.createQuery(cq);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public int countFilteredCustomers(Map<String, FilterMeta> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<IndividualCustomer> root = cq.from(IndividualCustomer.class);

        List<Predicate> predicates = buildFilterPredicates(cb, root, filters);
        cq.select(cb.count(root)).where(cb.and(predicates.toArray(new Predicate[0])));

        return em.createQuery(cq).getSingleResult().intValue();
    }

    private List<Predicate> buildFilterPredicates(CriteriaBuilder cb, Root<IndividualCustomer> root,
            Map<String, FilterMeta> filters) {
        List<Predicate> predicates = new ArrayList<>();

        // Soft-deletion
        predicates.add(cb.equal(root.get("deleted"), false));

        // Global filter support
        String globalFilter = Optional.ofNullable(filters.get("globalFilter"))
                .map(FilterMeta::getFilterValue)
                .map(Object::toString)
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(s -> !s.isEmpty())
                .orElse(null);

        for (Map.Entry<String, FilterMeta> entry : filters.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue().getFilterValue();

            if ("globalFilter".equals(field)) {
                continue;
            }

            if (value != null && !value.toString().isBlank()) {
                try {
                    predicates.add(cb.like(cb.lower(root.get(field).as(String.class)),
                            "%" + value.toString().toLowerCase() + "%"));
                } catch (IllegalArgumentException e) {
                    // Optional logging
                }
            }
        }

        // Global search fields
        if (globalFilter != null) {
            Predicate global = cb.or(
                    cb.like(cb.lower(root.get("firstName")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("lastName")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("ghanaCardNumber")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("emailAddress")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("primaryTelephone")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("secondaryTelephone")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("gpsCode")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("nationality")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("occupation")), "%" + globalFilter + "%")
            );
            predicates.add(global);
        }

        return predicates;
    }
    
    
     public List<CompanyCustomer> getFilteredCompanyCustomers(int first, int pageSize,
                                                      Map<String, SortMeta> sortBy,
                                                      Map<String, FilterMeta> filters) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CompanyCustomer> cq = cb.createQuery(CompanyCustomer.class);
        Root<CompanyCustomer> root = cq.from(CompanyCustomer.class);

        List<Predicate> predicates = buildCustCompanyFilterPredicates(cb, root, filters);
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // Sorting
        if (sortBy != null && !sortBy.isEmpty()) {
            List<Order> orders = new ArrayList<>();
            for (SortMeta meta : sortBy.values()) {
                String field = meta.getField();
                boolean ascending = meta.getOrder().isAscending();

                if (field == null || field.trim().isEmpty()) continue;

                try {
                    orders.add(ascending ? cb.asc(root.get(field)) : cb.desc(root.get(field)));
                } catch (IllegalArgumentException ignored) {
                    // Optionally log
                }
            }
            cq.orderBy(orders);
        }

        TypedQuery<CompanyCustomer> query = em.createQuery(cq);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }


    public int countFilteredCompanyCustomers(Map<String, FilterMeta> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CompanyCustomer> root = cq.from(CompanyCustomer.class);

        List<Predicate> predicates = buildCustCompanyFilterPredicates(cb, root, filters);
        cq.select(cb.count(root)).where(cb.and(predicates.toArray(new Predicate[0])));

        return em.createQuery(cq).getSingleResult().intValue();
    }
    
    private List<Predicate> buildCustCompanyFilterPredicates(CriteriaBuilder cb, Root<CompanyCustomer> root,
                                                  Map<String, FilterMeta> filters) {
        List<Predicate> predicates = new ArrayList<>();

        // Soft delete logic
        predicates.add(cb.equal(root.get("deleted"), false));

        // Global filter (optional)
        String globalFilter = Optional.ofNullable(filters.get("globalFilter"))
                .map(FilterMeta::getFilterValue)
                .map(Object::toString)
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(s -> !s.isEmpty())
                .orElse(null);

        for (Map.Entry<String, FilterMeta> entry : filters.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue().getFilterValue();

            if ("globalFilter".equals(field)) continue;

            if (value != null && !value.toString().isBlank()) {
                try {
                    predicates.add(cb.like(cb.lower(root.get(field).as(String.class)),
                            "%" + value.toString().toLowerCase() + "%"));
                } catch (IllegalArgumentException e) {
                    // Invalid filter field, optionally log
                }
            }
        }

        if (globalFilter != null) {
            Predicate global = cb.or(
                    cb.like(cb.lower(root.get("companyName")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("companyRegNo")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("emailAddress")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("primaryTelephone")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("secondaryTelephone")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("ceoFullName")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("ceoEmailAddress")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("taxId")), "%" + globalFilter + "%"),
                    cb.like(cb.lower(root.get("gpsCode")), "%" + globalFilter + "%")
            );
            predicates.add(global);
        }

        return predicates;
    }

}
