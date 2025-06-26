/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.controller;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;

/**
 *
 * @author ernest
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

   
     public LoginController() {
    }
    
    public void login()
    {
         Faces.redirect("dashboard.xhtml");
    }
    
     public void logout() {
         
     }
    private String username = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }
   
    private String passsword = null;
   
    
}
