/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.components;

import com.mycompany.j_pos.ui.components.inventory_sections.InventoryHeaderPanel;
import com.mycompany.j_pos.ui.components.inventory_sections.InventoryContentPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class InventoryUI extends JPanel {

    public InventoryUI() {
        // Setup main window
        initializeUI();
        
        // Create and add components
        createComponents();
    }
    
    private void initializeUI(){
        setSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        setMinimumSize(new Dimension(1280, 720));
        setLayout(new BorderLayout());
    }
    
    private void createComponents(){
        add(new InventoryHeaderPanel(), BorderLayout.NORTH);
        add(new InventoryContentPanel(), BorderLayout.CENTER);
    }
}