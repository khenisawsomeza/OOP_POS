package com.mycompany.j_pos.controllers;

import com.mycompany.j_pos.controllers.SidebarFunctions.EditDiscount;
import com.mycompany.j_pos.controllers.SidebarFunctions.EditTax;
import com.mycompany.j_pos.models.cart.Cart;
import com.mycompany.j_pos.ui.MainFrame;
import com.mycompany.j_pos.ui.components.Navigation;
import com.mycompany.j_pos.ui.components.cashier_sections.CartPanel;
import com.mycompany.j_pos.ui.components.login_sections.login_panel_components.InputFieldsPanel;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import javax.swing.JOptionPane;

public class SidebarController {
    static private SidebarController instance;
    private MainFrame mainframe;
    private Navigation nav;
    private final themeManager theme = themeManager.getInstance();
    private final DiscountController discountController = DiscountController.getInstance();
    private final CartPanel cartpanel = CartPanel.getInstance();
    private boolean hasDiscount = false;
    
    private SidebarController() {
        
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
    
    /**
     * Open discount dialog and apply discount
     */
    public void setDiscount(){
        EditDiscount editdisc = EditDiscount.getInstance();
        if(!hasDiscount){
            hasDiscount = editdisc.setDiscount();
        }else{
            editdisc.deleteDiscount();
        }
    }
    
    /**
     * Undo last discount
     */
    public void undoDiscount() {
        discountController.undoLastDiscount();
    }
    
    public void openManageEmployees(){
        getMainFrame().changeCard("EMPLOYEES");
    }
    
    public void editTax(){
        EditTax.setTax();
        cartpanel.refreshCartDisplay();
    }
    
    public void logout(){
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
        
        // Clear discount history on logout
        discountController.clearHistory();
        
        AppConstants.isAdmin = false;
        AppConstants.DEFAULT_CASHIER_NAME = "";
        
        getNavigation().hideNavigation();
        getMainFrame().changeCard("LOGIN");
    }
}