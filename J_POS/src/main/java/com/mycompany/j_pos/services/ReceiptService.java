/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.services;

import com.mycompany.j_pos.models.sale.Sale;

/**
 *
 * @author Khenyshi
 */
public class ReceiptService {
    public void printReceipt(Sale sale, double tax, double discount) {
        double subtotal = sale.getTotal();
        double finalTotal = subtotal + tax - discount;

        System.out.println("=== RECEIPT ===");
        sale.printReceipt();
        System.out.println("TAX: ₱" + String.format("%.2f", tax));
        System.out.println("DISCOUNT: ₱" + String.format("%.2f", discount));
        System.out.println("----------------");
        System.out.println("TOTAL: ₱" + String.format("%.2f", finalTotal));
    }
}
