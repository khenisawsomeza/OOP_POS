package com.mycompany.j_pos.services;

import com.mycompany.j_pos.models.cart.Cart;
import com.mycompany.j_pos.models.cart.CartEntry;
import com.mycompany.j_pos.models.items.Item;
import com.mycompany.j_pos.models.sale.Sale;
import com.mycompany.j_pos.models.sale.SaleLineItem;

public class SaleService {

    public Sale createSaleFromCart(Cart cart) {
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

        return sale;
    }

    public void saveSale(Sale sale) {
        System.out.println("Sale saved successfully!");
    }
}

