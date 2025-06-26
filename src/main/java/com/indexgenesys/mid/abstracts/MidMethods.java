/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.indexgenesys.mid.abstracts;



import com.indexgenesys.mid.entity.EntityModel;
import java.util.List;

/**
 *
 * @author ErnestDuker
 */
public interface MidMethods {

    public void saveMethod();
    
    public void clearMethod();

    public void editMethod(EntityModel em);

    public void deleteMethod(EntityModel em);

    public void findAll();

}
