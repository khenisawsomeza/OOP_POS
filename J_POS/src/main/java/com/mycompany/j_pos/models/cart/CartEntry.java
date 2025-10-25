/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.models.cart;

import com.mycompany.j_pos.models.items.Item;

public class CartEntry {
    private final Item item;
    private int quantity;

    public CartEntry(Item item) {
        this.item = item;
        this.quantity = 1;
    }

    public Item getItem() { return item; }
    public int getQuantity() { return quantity; }
    public void incrementQuantity() { quantity++; }
    public void decrementQuantity() { quantity--; }
}

