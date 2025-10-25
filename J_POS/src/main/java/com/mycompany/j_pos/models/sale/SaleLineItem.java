/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.models.sale;

/**
 *
 * @author Khenyshi
 */

public class SaleLineItem implements SaleComponent {
    private final String productName;
    private final double price;
    private final int quantity;

    public SaleLineItem(String productName, double price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public double getTotal() {
        return price * quantity;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return productName + " x" + quantity + " = ₱" + String.format("%.2f", getTotal());
    }
}

