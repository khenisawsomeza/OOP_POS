package com.mycompany.j_pos.models.items;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String id;
    private String name;
    private final List<Item> items;
    
    public Category(String id, String name) {
        this.id = id;
        this.name = name;
        this.items = new ArrayList<>();
    }
    
    //Add item to this category
    public void addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
            item.setCategory(this.name);
        }
    }
    
    //Remove item from this category
    public void removeItem(Item item) {
        items.remove(item);
    }
    
    // Get all items in this category
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
    
    // Get available items only
    public List<Item> getAvailableItems() {
        return items.stream()
                .filter(Item::isAvailable)
                .toList();
    }
    
    // Get item count in this category
    public int getItemCount() {
        return items.size();
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    
}
