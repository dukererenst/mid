/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.listener;

/**
 *
 * @author ernest
 */
import com.indexgenesys.mid.entity.EntityModel;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.PreRemove;
import java.time.LocalDateTime;

public class AuditListener {

    @PrePersist
    public void prePersist(Object entity) {
        if (entity instanceof EntityModel model) {
            LocalDateTime now = LocalDateTime.now();
            model.setCreatedAt(now);
            model.setLastModifiedAt(now);
            model.setCreatedBy(getCurrentUser());
            model.setLastModifiedBy(getCurrentUser());

            if (model.getId() == null || model.getId().isEmpty()) {
                model.setId(generateUUID());
            }
        }
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        if (entity instanceof EntityModel model) {
            model.setLastModifiedAt(LocalDateTime.now());
            model.setLastModifiedBy(getCurrentUser());
        }
    }

    @PreRemove
    public void preRemove(Object entity) {
        if (entity instanceof EntityModel model) {
            model.setDeleted(true);
            model.setDeletedAt(LocalDateTime.now());
            model.setDeletedBy(getCurrentUser());
        }
    }

    private String getCurrentUser() {
        // Replace this with your actual user context retrieval (e.g., session, security context)
        return "system";
    }

    private String generateUUID() {
        return java.util.UUID.randomUUID().toString();
    }
}
