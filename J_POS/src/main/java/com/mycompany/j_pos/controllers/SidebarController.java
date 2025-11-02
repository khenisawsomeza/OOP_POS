package com.mycompany.j_pos.controllers;

import com.mycompany.j_pos.controllers.SidebarFunctions.editTax;
import com.mycompany.j_pos.models.cart.Cart;
import com.mycompany.j_pos.ui.MainFrame;
import com.mycompany.j_pos.ui.components.Navigation;
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
        if(!Cart.getInstance().isEmpty()){
            // Discount Category Selection
            String[] discountCategories = {"PWD/Senior (20%)", "Custom Discount", "Cancel"};
            int categoryChoice = JOptionPane.showOptionDialog(null,
                "Select discount category:",
                "Apply Discount",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                discountCategories,
                discountCategories[0]);

            // User cancelled
            if (categoryChoice == 2 || categoryChoice == JOptionPane.CLOSED_OPTION) {
                return;
            }

            // PWD/SENIOR - Automatic 20% Discount
            if (categoryChoice == 0) {
                // Verify ID
                int confirmPWD = JOptionPane.showConfirmDialog(null,
                    "Has valid PWD/Senior ID been verified?",
                    "ID Verification",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

                if (confirmPWD != JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, 
                        "Please verify ID before applying discount",
                        "Verification Required",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String reason = "PWD/Senior Citizen Discount";

                // Apply 20% discount immediately
                discountController.applyPercentageDiscount(20.0, reason);
                return;
            }

            // CUSTOM DISCOUNT
            if (categoryChoice == 1) {
                // Select discount type
                String[] discountTypes = {"Percentage", "Fixed Amount", "Cancel"};
                int typeChoice = JOptionPane.showOptionDialog(null,
                    "Select discount type:",
                    "Custom Discount",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    discountTypes,
                    discountTypes[0]);

                if (typeChoice == 2 || typeChoice == JOptionPane.CLOSED_OPTION) {
                    return;
                }

                boolean isPercentage = (typeChoice == 0);

                // Get discount value
                String valueStr = JOptionPane.showInputDialog(null,
                    isPercentage ? "Enter percentage (0-100):" : "Enter discount amount (₱):",
                    "Discount Value",
                    JOptionPane.QUESTION_MESSAGE);

                if (valueStr == null || valueStr.trim().isEmpty()) {
                    return;
                }

                double discountValue;
                try {
                    discountValue = Double.parseDouble(valueStr.trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                        "Invalid number format. Please enter a valid number.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get reason
                String customReason = JOptionPane.showInputDialog(null,
                    "Enter reason for discount:",
                    "Discount Reason",
                    JOptionPane.QUESTION_MESSAGE);

                String reason = (customReason != null && !customReason.trim().isEmpty()) 
                    ? customReason.trim() 
                    : "Custom Discount";

                // Apply custom discount (no authorization needed)
                if (isPercentage) {
                    discountController.applyPercentageDiscount(discountValue, reason);
                } else {
                    discountController.applyFixedDiscount(discountValue, reason);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Cannot apply discount to an empty cart");
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
        editTax.setTax();
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