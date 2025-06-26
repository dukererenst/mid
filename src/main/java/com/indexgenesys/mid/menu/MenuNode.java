/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.menu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ernest
 */
public class MenuNode {
    private String label;
    private String icon;
    private String outcome;
    private List<MenuNode> children;

    public MenuNode(String label, String icon, String outcome, List<MenuNode> children) {
        this.label = label;
        this.icon = icon;
        this.outcome = outcome;
        this.children = children != null ? children : new ArrayList<>();
    }

    // Getters and setters

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public List<MenuNode> getChildren() {
        return children;
    }

    public void setChildren(List<MenuNode> children) {
        this.children = children;
    }
    
    
}
