/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.models.sale;

import java.util.ArrayList;
import java.util.List;

public class Sale implements SaleComponent {
    private final List<SaleComponent> lineItems;

    public Sale() {
        this.lineItems = new ArrayList<>();
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

    public void printReceipt() {
        System.out.println("=== RECEIPT ===");
        for (SaleComponent item : lineItems) {
            System.out.println(item);
        }
        System.out.println("----------------");
        System.out.println("SUBTOTAL: ₱" + String.format("%.2f", getTotal()));
    }
}
