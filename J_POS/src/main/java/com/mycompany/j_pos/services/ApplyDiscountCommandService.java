package com.mycompany.j_pos.services;

import com.mycompany.j_pos.core.BaseCommand;
import com.mycompany.j_pos.models.cart.Cart;
import com.mycompany.j_pos.models.sale.DiscountType;
import com.mycompany.j_pos.models.sale.Sale;
import com.mycompany.j_pos.ui.components.cashier_sections.CartPanel;

import javax.swing.JOptionPane;

public class ApplyDiscountCommandService extends BaseCommand {
    private Sale sale;
    private Cart cart;
    private DiscountType discountType;
    private double discountValue;
    private String reason;
    private double calculatedDiscount;
    private CartPanel cartpanel = CartPanel.getInstance();
    
    public ApplyDiscountCommandService(Sale sale,
                                DiscountType discountType,
                                double discountValue,
                                String reason,
                                String executedBy) {
        super(executedBy);
        this.sale = sale;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.reason = reason;
        this.calculatedDiscount = 0.0;
    }
    
    @Override
    public boolean execute() {
        if (executed) {
            System.out.println("Command already executed");
            return false;
        }

        // Validate input
        if (discountType == DiscountType.PERCENTAGE) {
            if (discountValue < 0 || discountValue > 100) {
                System.out.println("Invalid percentage: must be between 0 and 100");
                return false;
            }
            calculatedDiscount = sale.getSubtotal() * (discountValue / 100.0);
        } else {
            if (discountValue < 0) {
                System.out.println("Invalid discount amount: cannot be negative");
                return false;
            }
            calculatedDiscount = discountValue;
        }

        // Validate discount doesn't exceed subtotal
        if (calculatedDiscount > sale.getSubtotal()) {
            System.out.println("Discount cannot exceed subtotal");
            return false;
        }

        // Pass the original discount value (percentage or fixed amount), not the calculated amount
        sale.applyDiscount(discountValue, discountType);
        executed = true;
        cartpanel.refreshCartDisplay();
        System.out.println("Discount applied: ₱" + String.format("%.2f", calculatedDiscount));
        return true;
    }
    
    @Override
    public boolean undo() {
        if (!executed) {
            System.out.println("Command was not executed, cannot undo");
            return false;
        }

        sale.clearDiscount(); // Assuming Sale has a clearDiscount() method
        cartpanel.refreshCartDisplay();
        executed = false;
        System.out.println("Discount undone: ₱" + String.format("%.2f", calculatedDiscount));
        return true;
    }
    
    @Override
    public String getLogEntry() {
        String discountDesc = discountType == DiscountType.PERCENTAGE 
            ? discountValue + "%" 
            : "₱" + String.format("%.2f", discountValue);
        
        return String.format("[%s] APPLY_DISCOUNT | Transaction: %s | Type: %s | Value: %s | " +
                           "Calculated: ₱%.2f | Reason: %s | By: %s",
            timestamp, sale.getTransactionId(), discountType, discountDesc,
            calculatedDiscount, reason, executedBy);
    }
    
    public double getCalculatedDiscount() {
        return calculatedDiscount;
    }
}