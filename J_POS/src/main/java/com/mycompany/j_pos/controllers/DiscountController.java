package com.mycompany.j_pos.controllers;

import com.mycompany.j_pos.core.CommandInvoker;
import com.mycompany.j_pos.models.cart.Cart;
import com.mycompany.j_pos.models.sale.DiscountType;
import com.mycompany.j_pos.models.sale.Sale;
import com.mycompany.j_pos.services.ApplyDiscountCommandService;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import javax.swing.JOptionPane;

public class DiscountController {
    private static DiscountController instance;
    private CommandInvoker invoker;
    
    private DiscountController() {
        this.invoker = new CommandInvoker();
    }
    
    public static DiscountController getInstance() {
        if (instance == null) {
            instance = new DiscountController();
        }
        return instance;
    }
    
    /**
     * Apply percentage discount
     */
    public boolean applyPercentageDiscount(double percentage, String reason) {
        String executedBy = AppConstants.DEFAULT_CASHIER_NAME;
        
        Sale sale = Sale.getInstance();
        ApplyDiscountCommandService command = new ApplyDiscountCommandService(
            sale,
            DiscountType.PERCENTAGE,
            percentage,
            reason,
            executedBy
        );
        
        boolean success = invoker.executeCommand(command);
        
        if (success) {
            JOptionPane.showMessageDialog(null, 
                String.format("%.0f%% discount applied: ₱%.2f", percentage, command.getCalculatedDiscount()),
                "Discount Applied",
                JOptionPane.INFORMATION_MESSAGE);
        }
        
        return success;
    }
    
    /**
     * Apply fixed amount discount
     */
    public boolean applyFixedDiscount(double amount, String reason) {
        Sale sale = Sale.getInstance();
        String executedBy = AppConstants.DEFAULT_CASHIER_NAME;
        
        ApplyDiscountCommandService command = new ApplyDiscountCommandService(
            sale,
            DiscountType.FIXED_AMOUNT,
            amount,
            reason,
            executedBy
        );
        
        boolean success = invoker.executeCommand(command);
        
        if (success) {
            JOptionPane.showMessageDialog(null, 
                String.format("Fixed discount applied: ₱%.2f", command.getCalculatedDiscount()),
                "Discount Applied",
                JOptionPane.INFORMATION_MESSAGE);
        }
        
        return success;
    }
    
    /**
     * Undo last discount
     */
    public boolean undoLastDiscount() {
        boolean success = invoker.undoLastCommand();
        
        if (success) {
            JOptionPane.showMessageDialog(null, 
                "Last discount has been removed",
                "Discount Removed",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, 
                "No discount to undo",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
        
        return success;
    }
    
    /**
     * Get audit log
     */
    public void showAuditLog() {
        invoker.printAuditLog(); // Prints to console
        // Or create a dialog to show the audit log
    }
    
    /**
     * Clear all discounts and history
     */
    public void clearHistory() {
        invoker.clearHistory();
    }
    
    public CommandInvoker getInvoker() {
        return invoker;
    }
}