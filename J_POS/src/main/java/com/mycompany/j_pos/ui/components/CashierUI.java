/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.components;

import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.ui.components.cashier_sections.cart.CartPanel;
import com.mycompany.j_pos.ui.components.cashier_sections.items.ItemsPanel;
import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.*;

public class CashierUI extends JPanel {
    private Cart cart;
    private CartPanel cartPanel;
    
    public CashierUI() {
        // Initialize data models
        initializeDataModels();
        
        // Setup main window
        setupMainWindow();
        
        // Create and add components
        createComponents();
        
        // Make window visible
        setVisible(true);
    }
    
    // Initialize data models (cart, items, etc.)
    private void initializeDataModels() {
        cart = new Cart();
    }
    
    // Setup main window properties
    private void setupMainWindow() {
        setSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        setMinimumSize(new Dimension(1280, 720));
        setLayout(new BorderLayout());
    }
    
    //Create and add main sections to the window
    private void createComponents() {
        // Create cart panel (right side)
        cartPanel = new CartPanel(cart);
        add(cartPanel, BorderLayout.EAST);
        
        // Create items panel (left side)
        try {
            ItemsPanel itemsPanel = new ItemsPanel(cart, cartPanel);
            add(itemsPanel);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
    
}
