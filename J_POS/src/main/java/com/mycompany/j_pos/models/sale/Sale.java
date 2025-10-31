/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.models.sale;

import com.mycompany.j_pos.models.cart.Cart;
import java.util.ArrayList;
import java.util.List;

public class Sale implements SaleComponent {
    private final Cart cart = Cart.getInstance();
    private final List<SaleComponent> lineItems;
    private static Sale instance;
    
    public static Sale getInstance() {
        if (instance == null) instance = new Sale();
        return instance;
    }
    
    public Sale() {
        this.lineItems = new ArrayList<>();
    }
    
    public boolean isCartEmpty() {
        return lineItems.isEmpty();
    }
    
    public void addItem(SaleComponent item) {
        lineItems.add(item);
    }

    public void removeItem(SaleComponent item) {
        lineItems.remove(item);
    }

    public List<SaleComponent> getItems() {
        return new ArrayList<>(lineItems);
    }

    @Override
    public double getTotal() {
        double total = 0;
        for (SaleComponent item : lineItems) {
            total += item.getTotal();
        }
        return total;
    }

    public String printReceipt() {
        String upperReceiptPortion = "";
        upperReceiptPortion += "===== RECEIPT ===== \n";
        for (SaleComponent item : lineItems) {
            upperReceiptPortion += (item + "\n");
        }
        upperReceiptPortion += "--------------------------------- \n";
        upperReceiptPortion += ("SUBTOTAL: ₱" + String.format("%.2f", getTotal()) + "\n");
        return upperReceiptPortion;
    }
}
