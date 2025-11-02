/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.controllers.SidebarFunctions;

import com.mycompany.j_pos.controllers.DiscountController;
import com.mycompany.j_pos.models.cart.Cart;
import javax.swing.JOptionPane;

/**
 *
 * @author Marc Jacob
 */
public class EditDiscount {
    private final DiscountController discountController = DiscountController.getInstance();
    private static EditDiscount instance;
    public static EditDiscount getInstance(){
        if (instance == null){
            instance = new EditDiscount();
        }
        return instance;
    }
    
    public boolean setDiscount(){
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
                return false;
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
                    return false;
                }

                String reason = "PWD/Senior Citizen Discount";

                // Apply 20% discount immediately
                discountController.applyPercentageDiscount(20.0, reason);
                return true;
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
                    return false;
                }

                boolean isPercentage = (typeChoice == 0);

                // Get discount value
                String valueStr = JOptionPane.showInputDialog(null,
                    isPercentage ? "Enter percentage (0-100):" : "Enter discount amount (₱):",
                    "Discount Value",
                    JOptionPane.QUESTION_MESSAGE);

                if (valueStr == null || valueStr.trim().isEmpty()) {
                    return false;
                }

                double discountValue;
                try {
                    discountValue = Double.parseDouble(valueStr.trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                        "Invalid number format. Please enter a valid number.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return false;
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
        return false;
    }
    
    public boolean deleteDiscount(){
        String[] options = {"Edit Discount", "Remove Discount", "Cancel"};
        int choice = JOptionPane.showOptionDialog(
            null,
            "What would you like to do with the discount?",
            "Manage Discount",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        switch (choice) {
            case 0:
                return setDiscount();
            case 1:
                DiscountController.getInstance().undoLastDiscount();
                return false;
            default:
                System.out.println("User cancelled.");
                break;
        }
        return true;
    }
}
