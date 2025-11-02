/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.components.cashier_sections.items_functions;

import com.mycompany.j_pos.models.items.Item;
import java.util.ArrayList;
import java.util.List;

public class SearchFunction {
    /**
     * Filters the provided items by name containing the query (case-insensitive).
     * Returns a new list (does not modify the original).
     */
    public static List<Item> search(String text, List<Item> items) {
        ArrayList<Item> result = new ArrayList<>();
        if (items == null || items.isEmpty()) {
            return result;
        }
        if (text == null || text.trim().isEmpty()) {
            result.addAll(items);
            return result;
        }

        String q = text.trim().toLowerCase();
        for (Item item : items) {
            if (item == null) continue;
            String name = item.getName();
            if (name != null && name.toLowerCase().contains(q)) {
                result.add(item);
            }
        }
        
        return result;
    }
}
