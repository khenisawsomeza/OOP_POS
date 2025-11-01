/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.controllers;

import com.mycompany.j_pos.controllers.SidebarFunctions.editTax;
import com.mycompany.j_pos.ui.MainFrame;
import com.mycompany.j_pos.ui.Navigation;
import com.mycompany.j_pos.ui.components.login_sections.login_panel_components.InputFieldsPanel;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

/**
 *
 * @author Marc Jacob
 */
public class SidebarController {
    static private SidebarController instance;
    private MainFrame mainframe;
    private Navigation nav;
    private final themeManager theme = themeManager.getInstance();
    
    private SidebarController() {
        // Leave empty - don't call MainFrame.getInstance() here!
    }
    
    static public SidebarController getInstance(){
        if (instance == null) instance = new SidebarController();
        return instance;
    }
    
    private MainFrame getMainFrame() {
        return MainFrame.getInstance();
    }
    
    private Navigation getNavigation() {
        return Navigation.getInstance();
    }
    
    
    public void toggleDarkMode(){
        theme.toggleDarkMode();
    }
    
    public void openCashier(){
        getMainFrame().changeCard("CASHIER");
    }
    
    public void openSales(){
        getMainFrame().changeCard("SALES");
    }
    
    public void openInventory(){
        getMainFrame().changeCard("INVENTORY");
    }
    
    public void setDiscount(){
        
    }
    
    public void openManageEmployees(){
        getMainFrame().changeCard("EMPLOYEES");
    }
    
    public void editTax(){
        editTax.setTax();
    }
    
    public void logout(){
        
        // Clear fields FIRST (while still on current screen)
        try {
            InputFieldsPanel inputFields = InputFieldsPanel.getInstance();
            System.out.println("InputFieldsPanel instance: " + inputFields);
            System.out.println("Username field: " + inputFields.getUsername());
            inputFields.clearFields();
            System.out.println("Fields cleared - Username now: " + inputFields.getUsername());
        } catch (Exception e) {
            System.err.println("Error clearing fields: " + e.getMessage());
            e.printStackTrace();
        }
        //CLEAR FIELDS NOT WORKING
        
        AppConstants.isAdmin = false;
        AppConstants.DEFAULT_CASHIER_NAME = "";
        
        getNavigation().hideNavigation();
        getMainFrame().changeCard("LOGIN");
    }
}
