/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.menu;

/**
 *
 * @author ernest
 */
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.primefaces.model.menu.*;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named
@RequestScoped
public class MenuBean implements Serializable {

    private MenuModel menuModel;

    List<MenuNode> menuNodes = List.of(
            new MenuNode("Main", "pi pi-home", null, List.of(
                    new MenuNode("Dashboard", "pi pi-chart-bar", "/dashboard", null),
                    new MenuNode("Settings", "pi pi-cog", null, List.of(
                            new MenuNode("Region", "pi pi-users", "/setting/region", null),
                            new MenuNode("Company", "pi pi-id-card", "/setting/company_information", null),
                            new MenuNode("Branch", "pi pi-users", "/setting/company_branch_information", null),
                            new MenuNode("Currency", "pi pi-users", "/setting/currency", null),
                            new MenuNode("Users", "pi pi-users", "/dashboard", null),
                            new MenuNode("Roles", "pi pi-id-card", "/dashboard", null)
                    )),
                    new MenuNode("Policy Settings", "pi pi-cog", null, List.of(
                            new MenuNode("Vehicle Type", "pi pi-users", "/mid/vehicle_type", null),
                            new MenuNode("Vehicle Body Type", "pi pi-users", "/mid/vehicle_body_type", null),
                            new MenuNode("Product Type", "pi pi-users", "/mid/product_type", null),
                            new MenuNode("Cover Type", "pi pi-id-card", "/mid/cover_type", null),
                            new MenuNode("Risk Cateory", "pi pi-id-card", "/mid/risk_category", null),
                            new MenuNode("Vehicle Make", "pi pi-id-card", "/mid/vehicle_make", null),
                            new MenuNode("Vehicle Model", "pi pi-id-card", "/mid/vehicle_model", null),
                            new MenuNode("Item", "pi pi-id-card", "/mid/items", null),
                            new MenuNode("Risk Item", "pi pi-id-card", "/mid/risk_item", null),
                            new MenuNode("Discounts", "pi pi-id-card", "/dashboard", null),
                            new MenuNode("Short Rates", "pi pi-id-card", "/mid/short_rate", null),
                            new MenuNode("Product", "pi pi-id-card", "/mid/product", null)
                    )),
                     new MenuNode("Customer", "pi pi-cog", null, List.of(
                            new MenuNode("Vehicle Information", "pi pi-users", "/mid/vehicle_information", null),
                            new MenuNode("Individual Information", "pi pi-id-card", "/mid/individual_customer", null),
                            new MenuNode("Company Information", "pi pi-id-card", "/mid/company_customer", null)
                            
                    )),
                     new MenuNode("Policy", "pi pi-cog", null, List.of(
                            new MenuNode("Vehicle Information", "pi pi-users", "/mid/vehicle_information", null),
                            new MenuNode("Individual Customer Information", "pi pi-id-card", "/mid/individual_customer", null),
                            new MenuNode("Company Customer Information", "pi pi-id-card", "/mid/individual_customer", null),
                            new MenuNode("Create Policy", "pi pi-users", "/setting/company_branch_information", null),
                            new MenuNode("Pending Policy", "pi pi-users", "/setting/currency", null),
                            new MenuNode("Approved Policy", "pi pi-users", "/dashboard", null),
                            new MenuNode("Policy Sticker", "pi pi-id-card", "/dashboard", null)
                    )),
                     new MenuNode("Sticker", "pi pi-cog", null, List.of(
                            new MenuNode("Sticker Batch", "pi pi-users", "/sticker/sticker_batch", null),
                            new MenuNode("Sticker Request", "pi pi-id-card", "/sticker/sticker_request", null),
                            new MenuNode("Pending Request", "pi pi-users", "/sticker/pending_sticker_request", null),
                            new MenuNode("Generation", "pi pi-users", "/setting/currency", null),
                            new MenuNode("Inventory", "pi pi-users", "/sticker/sticker_information", null),
                            new MenuNode("Issued Sticker", "pi pi-id-card", "/dashboard", null)
                    )),
                     new MenuNode("NIA", "pi pi-cog", null, List.of(
                            new MenuNode("Verification", "pi pi-users", "/nia/verification", null),
                            new MenuNode("Verification Search", "pi pi-id-card", "/setting/company_information", null)
                            
                    ))
            )),
            new MenuNode("Reports", "pi pi-file", "/dashboard", null)
    );

// In @PostConstruct:
    @PostConstruct
    public void init() {
        menuModel = new DefaultMenuModel();

        menuModel = buildMenuModel(menuNodes);
        // You can fetch user roles here
//        String role = getCurrentUserRole();
//
//       
//        
//
//        // Policy
//        DefaultSubMenu policy = DefaultSubMenu.builder()
//                .label("Policy")
//                .icon("pi pi-file")
//                .build();
//
//        policy.getElements().add(DefaultMenuItem.builder()
//                .value("Vehicle Info")
//                .icon("pi pi-car")
//                .outcome("/dashboard")
//                .build());
//
//        menuModel.getElements().add(policy);
//
//        DefaultSubMenu hierarchy = DefaultSubMenu.builder()
//                .label("Hierarchy")
//                .icon("pi pi-align-left")
//                .build();
//
//        DefaultSubMenu submenu1 = DefaultSubMenu.builder()
//                .label("Submenu 1")
//                .icon("pi pi-align-left")
//                .build();
//
//        submenu1.getElements()
//                .add(DefaultMenuItem.builder()
//                        .value("Link 1.1")
//                        .icon("pi pi-angle-right")
//                        .outcome("/dashboard")
//                        .build());
//
//        DefaultSubMenu submenu2 = DefaultSubMenu.builder()
//                .label("Submenu 2")
//                .icon("pi pi-align-left")
//                .build();
//
//        submenu2.getElements().add(DefaultMenuItem.builder()
//                .value("Link 2.1")
//                .icon("pi pi-angle-right")
//                .outcome("/dashboard")
//                .build());
//
//        hierarchy.getElements().add(submenu1);
//        hierarchy.getElements().add(submenu2);
//        menuModel.getElements().add(hierarchy);

    }
//    @PostConstruct
//    public void init() {
//        menuModel = new DefaultMenuModel();
//
//        // You can fetch user roles here
//        String role = getCurrentUserRole();
//
//        // Favorites
//        DefaultSubMenu favorites = DefaultSubMenu.builder()
//                .label("Favorites")
//                .icon("pi pi-home")
//                .build();
//
//        favorites.getElements().add(DefaultMenuItem.builder()
//                .value("Dashboard")
//                .icon("pi pi-home")
//                .outcome("/dashboard")
//                .build());
//
//        menuModel.getElements().add(favorites);
//
//        // Settings
//        if ("ADMIN".equals(role)) {
//            DefaultSubMenu settings = DefaultSubMenu.builder()
//                    .label("Settings")
//                    .icon("pi pi-cog")
//                    .expanded(false)
//                    .build();
//
//            settings.getElements().add(DefaultMenuItem.builder()
//                    .value("Region")
//                    .icon("pi pi-building")
//                    .outcome("/setting/region")
//                    .build());
//            settings.getElements().add(DefaultMenuItem.builder()
//                    .value("Company Information")
//                    .icon("pi pi-building")
//                    .outcome("/setting/company_information")
//                    .build());
//            settings.getElements().add(DefaultMenuItem.builder()
//                    .value("Branch Information")
//                    .icon("pi pi-building")
//                    .outcome("/setting/company_branch_information")
//                    .build());
//
//            settings.getElements().add(DefaultMenuItem.builder()
//                    .value("User Account")
//                    .icon("pi pi-user")
//                    .outcome("/dashboard")
//                    .build());
//
//            DefaultSubMenu mid = DefaultSubMenu.builder()
//                    .label("MID")
//                    .icon("pi pi-cog")
//                    .expanded(false)
//                    .build();
//
//            mid.getElements().add(DefaultMenuItem.builder()
//                    .value("Vehicle Type")
//                    .icon("pi pi-building")
//                    .outcome("/mid/vehicle_type")
//                    .build());
//
//            menuModel.getElements().add(settings);
//            menuModel.getElements().add(mid);
//        }
//
//        // Policy
//        DefaultSubMenu policy = DefaultSubMenu.builder()
//                .label("Policy")
//                .icon("pi pi-file")
//                .build();
//
//        policy.getElements().add(DefaultMenuItem.builder()
//                .value("Vehicle Info")
//                .icon("pi pi-car")
//                .outcome("/dashboard")
//                .build());
//
//        menuModel.getElements().add(policy);
//
//        DefaultSubMenu hierarchy = DefaultSubMenu.builder()
//                .label("Hierarchy")
//                .icon("pi pi-align-left")
//                .build();
//
//        DefaultSubMenu submenu1 = DefaultSubMenu.builder()
//                .label("Submenu 1")
//                .icon("pi pi-align-left")
//                .build();
//
//        submenu1.getElements()
//                .add(DefaultMenuItem.builder()
//                        .value("Link 1.1")
//                        .icon("pi pi-angle-right")
//                        .outcome("/dashboard")
//                        .build());
//
//        DefaultSubMenu submenu2 = DefaultSubMenu.builder()
//                .label("Submenu 2")
//                .icon("pi pi-align-left")
//                .build();
//
//        submenu2.getElements().add(DefaultMenuItem.builder()
//                .value("Link 2.1")
//                .icon("pi pi-angle-right")
//                .outcome("/dashboard")
//                .build());
//
//        hierarchy.getElements().add(submenu1);
//        hierarchy.getElements().add(submenu2);
//        menuModel.getElements().add(hierarchy);
//
//    }

    public MenuModel buildMenuModel(List<MenuNode> menuNodes) {
        MenuModel model = new DefaultMenuModel();
        for (MenuNode node : menuNodes) {
            model.getElements().add(createMenuElement(node));
        }
        return model;
    }

    private MenuElement createMenuElement(MenuNode node) {
        if (node.getChildren() == null || node.getChildren().isEmpty()) {
            // It's a leaf, create menu item
            return DefaultMenuItem.builder()
                    .value(node.getLabel())
                    .icon(node.getIcon())
                    .ajax(true)
                    
                    .outcome(node.getOutcome())
                    .build();
        } else {
            // It's a submenu
            DefaultSubMenu submenu = DefaultSubMenu.builder()
                    .label(node.getLabel())
                    .icon(node.getIcon())
                    .build();
            for (MenuNode child : node.getChildren()) {
                submenu.getElements().add(createMenuElement(child));
            }
            return submenu;
        }
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    private String getCurrentUserRole() {
        // Replace with actual session logic
        return "ADMIN"; // stubbed for example
    }
}
