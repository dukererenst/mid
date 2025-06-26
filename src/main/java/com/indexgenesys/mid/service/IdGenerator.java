/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SingletonEjbClass.java to edit this template
 */
package com.indexgenesys.mid.service;

import com.google.common.base.Strings;
import com.indexgenesys.mid.entity.EntityModel;
import jakarta.ejb.Stateless;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author ernest
 */
@Stateless
public class IdGenerator implements Serializable{

     public void uniqueEntityId(EntityModel information) {
        if (Strings.isNullOrEmpty(information.getId())) {
            information.setId(UUID.randomUUID().toString());
            information.setCreatedAt(LocalDateTime.now());
        } else {
            information.setLastModifiedAt(LocalDateTime.now());
        }
    }
}
