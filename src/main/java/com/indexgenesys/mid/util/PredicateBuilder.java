/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ernest
 */
public class PredicateBuilder<T> {
    private final CriteriaBuilder cb;
    private final Root<T> root;
    private final List<Predicate> predicates = new ArrayList<>();

    public PredicateBuilder(CriteriaBuilder cb, Root<T> root) {
        this.cb = cb;
        this.root = root;
    }

    public PredicateBuilder<T> equal(String field, Object value) {
        if (value != null) predicates.add(cb.equal(root.get(field), value));
        return this;
    }

    public PredicateBuilder<T> like(String field, String value) {
        if (value != null && !value.isBlank())
            predicates.add(cb.like(cb.lower(root.get(field).as(String.class)), "%" + value.toLowerCase() + "%"));
        return this;
    }

    public PredicateBuilder<T> orLike(String... fields) {
        List<Predicate> orPredicates = Arrays.stream(fields)
            .map(f -> cb.like(cb.lower(root.get(f).as(String.class)), "%" + fields[0].toLowerCase() + "%"))
            .collect(Collectors.toList());
        predicates.add(cb.or(orPredicates.toArray(new Predicate[0])));
        return this;
    }

    public Predicate[] build() {
        return predicates.toArray(new Predicate[0]);
    }
}

