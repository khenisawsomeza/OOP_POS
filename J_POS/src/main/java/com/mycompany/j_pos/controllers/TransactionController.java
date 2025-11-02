package com.mycompany.j_pos.controllers;

import com.mycompany.j_pos.core.CommandInvoker;
import com.mycompany.j_pos.models.sale.DiscountType;
import com.mycompany.j_pos.models.sale.Sale;
import com.mycompany.j_pos.models.sale.SaleComponent;
import com.mycompany.j_pos.services.ApplyDiscountCommandService;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;

public class TransactionController {
    private static TransactionController instance;
    private CommandInvoker invoker;
    private Sale currentTransaction;
    
    private TransactionController() {
        this.invoker = new CommandInvoker();
        this.currentTransaction = Sale.getInstance();
    }
    
    public static TransactionController getInstance() {
        if (instance == null) {
            instance = new TransactionController();
        }
        return instance;
    }
    
    public void startNewTransaction(String transactionId) {
        this.currentTransaction = Sale.getInstance();
        this.currentTransaction.setTransactionId(transactionId);
        System.out.println("New transaction started: " + transactionId);
    }
    
    public void addItem(SaleComponent item) {
        if (currentTransaction == null) {
            System.out.println("No active transaction");
            return;
        }
        currentTransaction.addItem(item);
        System.out.println("Item added to transaction");
    }
    
    public boolean applyPercentageDiscount(double percentage, String reason) {
        if (currentTransaction == null) {
            System.out.println("No active transaction");
            return false;
        }
        
        String executedBy = AppConstants.DEFAULT_CASHIER_NAME;
        
        ApplyDiscountCommandService command = new ApplyDiscountCommandService(
            currentTransaction,
            DiscountType.PERCENTAGE,
            percentage,
            reason,
            executedBy
        );
        
        return invoker.executeCommand(command);
    }
    
    public boolean applyFixedDiscount(double amount, String reason) {
        if (currentTransaction == null) {
            System.out.println("No active transaction");
            return false;
        }
        
        String executedBy = AppConstants.DEFAULT_CASHIER_NAME;
        
        ApplyDiscountCommandService command = new ApplyDiscountCommandService(
            currentTransaction,
            DiscountType.FIXED_AMOUNT,
            amount,
            reason,
            executedBy
        );
        
        return invoker.executeCommand(command);
    }
    
    public boolean undoLastAction() {
        return invoker.undoLastCommand();
    }
    
    public void printTransactionSummary() {
        if (currentTransaction == null) {
            System.out.println("No active transaction");
            return;
        }
        System.out.println("\n" + currentTransaction);
    }
    
    public void printAuditLog() {
        invoker.printAuditLog();
    }
    
    public Sale getCurrentTransaction() {
        return currentTransaction;
    }
    
    public CommandInvoker getInvoker() {
        return invoker;
    }
    
    public void clearHistory() {
        invoker.clearHistory();
    }
}