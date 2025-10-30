package com.mycompany.j_pos.models.cart;

import com.mycompany.j_pos.models.items.Item;
import com.mycompany.j_pos.ui.components.cashier_sections.CartPanel;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    
    private static Cart instance;
    private final List<CartEntry> items;
    private double taxRate;
    private double discountAmount;

    private Cart() {
        this.items = new ArrayList<>();
        this.taxRate = 0.12; // 12% tax
        this.discountAmount = 0.0;
    }
    
    public static Cart getInstance(){
        if (instance == null){
            instance = new Cart();
        }
        
        return instance;
    }

    // Add item or increment quantity
    public void addItem(Item item) {
        for (CartEntry entry : items) {
            if (entry.getItem().equals(item)) {
                entry.incrementQuantity();
                return;
            }
        }
        items.add(new CartEntry(item));
    }

    // Remove one quantity
    public void removeItem(Item item) {
        for (int i = 0; i < items.size(); i++) {
            CartEntry entry = items.get(i);
            if (entry.getItem().equals(item)) {
                entry.decrementQuantity();
                if (entry.getQuantity() <= 0) items.remove(i);
                return;
            }
        }
    }

    // Remove completely
    public void removeItemCompletely(Item item) {
        items.removeIf(entry -> entry.getItem().equals(item));
    }

    // Clear cart
    public void clearCart() {
        items.clear();
        CartPanel.getInstance().refreshCartDisplay();
    }

    // Get quantity of a specific item
    public int getItemQuantity(Item item) {
        for (CartEntry entry : items) {
            if (entry.getItem().equals(item)) return entry.getQuantity();
        }
        return 0;
    }

    // Get all items in cart
    public List<CartEntry> getItems() {
        return new ArrayList<>(items);
    }

    // Subtotal before tax/discount
    public double getSubtotal() {
        double subtotal = 0;
        for (CartEntry entry : items) {
            subtotal += entry.getItem().getPrice() * entry.getQuantity();
        }
        return subtotal;
    }

    // Tax
    public double getTaxAmount() {
        return getSubtotal() * taxRate;
    }

    // Total after tax & discount
    public double getTotal() {
        return getSubtotal() + getTaxAmount() - discountAmount;
    }

    // Formatted strings
    public String getFormattedSubtotal() {
        return String.format("₱%.2f", getSubtotal());
    }

    public String getFormattedTaxAmount() {
        return String.format("₱%.2f", getTaxAmount());
    }

    public String getFormattedDiscount() {
        return String.format("₱%.2f", discountAmount);
    }

    public String getFormattedTotal() {
        return String.format("₱%.2f", getTotal());
    }

    // Cart status
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getTotalItemCount() {
        int count = 0;
        for (CartEntry entry : items) count += entry.getQuantity();
        return count;
    }

    // Getters & setters
    public double getTaxRate() { return taxRate; }
    public void setTaxRate(double taxRate) { this.taxRate = taxRate; }

    public double getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(double discountAmount) { this.discountAmount = discountAmount; }

}
