/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.components.inventory_sections;

import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.components.inventory_sections.inventory_content_panel_components.FilterPanel;
import com.mycompany.j_pos.ui.components.inventory_sections.inventory_content_panel_components.InventoryItemListPanel;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author khenshi
 */
public class InventoryContentPanel extends JPanel{
    public InventoryContentPanel(){
        initializeUI();
        createComponents();
    }
    
    public void initializeUI(){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        setBackground(themeManager.getInstance().getLightGrayColor());
        setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
    }
    
    public void createComponents(){
        add(new FilterPanel(), BorderLayout.NORTH);
        add(InventoryItemListPanel.getInstance(), BorderLayout.CENTER);
        
        JPanel leftMargin = new JPanel();
        leftMargin.setPreferredSize(new Dimension(50, Integer.MAX_VALUE));
        JPanel rightMargin = new JPanel();
        rightMargin.setPreferredSize(new Dimension(50, Integer.MAX_VALUE));
        
        add(leftMargin, BorderLayout.WEST);
        add(rightMargin, BorderLayout.EAST);
    }

}
