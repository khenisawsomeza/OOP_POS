/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.services;

import com.mycompany.j_pos.models.items.Item;
import com.mycompany.j_pos.ui.components.cashier_sections.items_panel_components.ItemsListPanel;
import com.mycompany.j_pos.ui.utils.LoadResources;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khenyshi
 */
public class ItemService {
    
    private static List<Item> items = LoadResources.getItems();
    
    public static List<Item> searchItem(String text) {
        
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
    
    public static List<Item> searchItem(String text, List<Item> items) {
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
    
    public static List<Item> getItemsInCategory(String categoryName) {
        ItemsListPanel itemsListPanel = ItemsListPanel.getInstance();
        
        List<Item> itemsInCategory = new ArrayList<>();
        
        if (categoryName.equalsIgnoreCase("All Items")) {
            return items;
        }
        
        for (Item item : items){
            if (item.getCategory().equalsIgnoreCase(categoryName))
                itemsInCategory.add(item);
        }
        
        return itemsInCategory;
    }
}

