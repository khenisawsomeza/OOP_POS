/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.components.inventory_sections;

import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author khenshi
 */
public class InventoryHeaderPanel extends JPanel {
    
    public InventoryHeaderPanel(){
        initializeUI();
        
        createComponents();
    }
    
    public void initializeUI(){
        setPreferredSize(new Dimension(Integer.MAX_VALUE, 70));
        setBackground(themeManager.getInstance().getLightGreenColor());
        setLayout(new BorderLayout());
    }
    
    public void createComponents(){
        add(createTitle("Inventory"), BorderLayout.WEST);
    }
    
    public JLabel createTitle(String text){
        JLabel title = new LabelBuilder()
                .withText("Inventory")
                .withFont(Font.BOLD, 30)
                .withForeground(themeManager.getInstance().getTextForeground())
                .build();
        
        return title;
    }
}
