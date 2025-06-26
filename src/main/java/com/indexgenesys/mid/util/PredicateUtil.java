/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.util;

import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

/**
 *
 * @author ernest
 */
public class PredicateUtil {

    @SuppressWarnings("unchecked")
    public static Path<?> getPath(Root<?> root, String fieldPath) {
        Path<?> path = root;
        for (String part : fieldPath.split("\\.")) {
            path = path.get(part);
        }
        return path;
    }

    public static <T> Join<?, ?> getJoin(Root<T> root, String joinPath) {
        String[] parts = joinPath.split("\\.");
        Join<?, ?> join = null;
        From<?, ?> from = root;
        for (String part : parts) {
            join = from.join(part, JoinType.LEFT);
            from = join;
        }
        return join;
    }

}
