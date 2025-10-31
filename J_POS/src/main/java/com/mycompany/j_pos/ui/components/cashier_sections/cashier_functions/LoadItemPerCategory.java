/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.components.cashier_sections.cashier_functions;

import com.mycompany.j_pos.models.items.Item;
import com.mycompany.j_pos.ui.components.cashier_sections.items_panel_components.ItemsListPanel;
import com.mycompany.j_pos.ui.utils.LoadResources;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mvnjacob
 */
public class LoadItemPerCategory {
    public static void loadItems(String categoryName) {
        ItemsListPanel itemsListPanel = ItemsListPanel.getInstance();
        List<Item> allItems = LoadResources.getItems();
        
        List<Item> itemsInCategory = new ArrayList<>();
        
        if (categoryName.equalsIgnoreCase("All Items")) {
            System.out.println("test");
            itemsListPanel.refreshItemsDisplay(allItems);
            return;
        }
        
        for (Item item : allItems){
            if (item.getCategory().equalsIgnoreCase(categoryName))
                itemsInCategory.add(item);
        }
        
        itemsListPanel.refreshItemsDisplay(itemsInCategory);
    }
}
