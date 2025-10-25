/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.models.sale;

import com.mycompany.j_pos.models.cart.Cart;
import com.mycompany.j_pos.models.cart.CartEntry;
import com.mycompany.j_pos.models.items.Item;

public class CheckoutService {

    public static Sale finalizeSaleFromCart(Cart cart) {
        Sale sale = new Sale();

        for (CartEntry entry : cart.getItems()) {
            Item item = entry.getItem();
            int qty = entry.getQuantity();

            SaleLineItem lineItem = new SaleLineItem(
                    item.getName(),
                    item.getPrice(),
                    qty
            );
            sale.addItem(lineItem);
        }

        double subtotal = cart.getSubtotal();
        double tax = cart.getTaxAmount();
        double discount = cart.getDiscountAmount();
        double finalTotal = subtotal + tax - discount;

        System.out.println("=== RECEIPT ===");
        sale.printReceipt();
        System.out.println("TAX: ₱" + String.format("%.2f", tax));
        System.out.println("DISCOUNT: ₱" + String.format("%.2f", discount));
        System.out.println("----------------");
        System.out.println("TOTAL: ₱" + String.format("%.2f", finalTotal));

        cart.clearCart();

        return sale;
    }
}
