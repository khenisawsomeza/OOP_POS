package com.mycompany.j_pos.services;

import com.mycompany.j_pos.core.BaseCommand;
import com.mycompany.j_pos.models.sale.DiscountType;
import com.mycompany.j_pos.models.sale.Sale;
import javax.swing.JOptionPane;

public class ApplyDiscountCommandService extends BaseCommand {
    private Sale sale;
    private DiscountType discountType;
    private double discountValue;
    private String reason;
    private String authorizationCode;
    private double calculatedDiscount;
    
    public ApplyDiscountCommandService(Sale sale,
                                DiscountType discountType,
                                double discountValue,
                                String reason,
                                String executedBy,
                                String authorizationCode) {
        super(executedBy);
        this.sale = sale;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.reason = reason;
        this.authorizationCode = authorizationCode;
        this.calculatedDiscount = 0.0;
    }
    
    // Overloaded constructor without authorization code
    public ApplyDiscountCommandService(Sale sale,
                                DiscountType discountType,
                                double discountValue,
                                String reason,
                                String executedBy) {
        this(sale, discountType, discountValue, reason, executedBy, null);
    }
    
    @Override
    public boolean execute() {
        if (executed) {
            System.out.println("Command already executed");
            return false;
        }
        
        // Calculate actual discount amount
        if (discountType == DiscountType.PERCENTAGE) {
            if (discountValue < 0 || discountValue > 100) {
                System.out.println("Invalid percentage: must be between 0 and 100");
                return false;
            }
            JOptionPane.showMessageDialog(null, sale.getTotal());
            calculatedDiscount = sale.getTotal() * (discountValue / 100.0);
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
        
        sale.applyDiscount(calculatedDiscount);
        executed = true;
        System.out.println("Discount applied: ₱" + String.format("%.2f", calculatedDiscount));
        return true;
    }
    
    @Override
    public boolean undo() {
        if (!executed) {
            System.out.println("Command was not executed, cannot undo");
            return false;
        }
        
        sale.removeDiscount(calculatedDiscount);
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
                           "Calculated: ₱%.2f | Reason: %s | Auth: %s | By: %s",
            timestamp, sale.getTransactionId(), discountType, discountDesc,
            calculatedDiscount, reason,
            authorizationCode != null ? authorizationCode : "N/A", executedBy);
    }
    
    public double getCalculatedDiscount() {
        return calculatedDiscount;
    }
}