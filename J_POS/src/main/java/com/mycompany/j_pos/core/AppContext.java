/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.core;

import java.sql.Connection;
import java.util.List;
import com.mycompany.j_pos.models.*;
import com.mycompany.j_pos.models.items.Category;
import com.mycompany.j_pos.models.items.Item;

public class AppContext {

    private static AppContext instance;

    private Connection connection;
    private List<Item> allItems;
    private List<Category> allCategories;
    private User currentUser;

    private AppContext() {}

    public static AppContext getInstance() {
        if (instance == null) instance = new AppContext();
        return instance;
    }

    // getters and setters...
    public void setAllItems(List<Item> items) { this.allItems = items; }
    public List<Item> getAllItems() { return allItems; }

    public void setAllCategories(List<Category> categories) { this.allCategories = categories; }
    public List<Category> getAllCategories() { return allCategories; }

    public void setCurrentUser(User user) { this.currentUser = user; }
    public User getCurrentUser() { return currentUser; }
}
