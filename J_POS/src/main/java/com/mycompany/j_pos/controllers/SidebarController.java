/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.controllers;

import com.mycompany.j_pos.controllers.SidebarFunctions.editTax;
import com.mycompany.j_pos.ui.MainFrame;
import com.mycompany.j_pos.ui.components.login_sections.login_panel_components.InputFieldsPanel;

/**
 *
 * @author Marc Jacob
 */
public class SidebarController {
    static private SidebarController instance;
    private final MainFrame mainframe = MainFrame.getInstance();
    private final InputFieldsPanel inputfields = InputFieldsPanel.getInstance();
    
    static public SidebarController getInstance(){
        if (instance == null) instance = new SidebarController();
        return instance;
    }
    
    public void openSales(){
        
    }
    
    public void openInventory(){
        
    }
    
    public void editDiscount(){
        
    }
    
    public void openManageEmployees(){
        
    }
    
    public void editTax(){
        editTax.setTax();
    }
    
    public void logout(){
        inputfields.clearFields(); //NOT WORKING
        mainframe.changeCard("LOGIN");
    }
}
