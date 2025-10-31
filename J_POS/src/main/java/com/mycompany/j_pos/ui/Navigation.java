/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui;

import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author khenshi
 */
public class Navigation extends JPanel{
    private CardLayout cardLayout;
    private JPanel contentPanel;
    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    
    public Navigation(){
        initializeIU();
    }
    
    private void initializeIU(){
        this.setPreferredSize(new Dimension(50, screensize.width));
        this.setLayout(new BorderLayout());
        
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.add(createNavigationPanel(), "NAVIGATION");
        contentPanel.add(createBlankPanel(), "BLANK");
        
        cardLayout.show(contentPanel, "BLANK");
        
        this.add(contentPanel);
    }
    
    private JPanel createNavigationPanel(){
        JPanel panel = new PanelBuilder()
                .withSize(Integer.MAX_VALUE, Integer.MAX_VALUE)
                .build();
        
        
        return panel;
    }
    
    private JPanel createBlankPanel(){
        JPanel panel = new PanelBuilder()
                .withSize(Integer.MAX_VALUE, Integer.MAX_VALUE)
                .withBackground(themeManager.getInstance().getStaticLightGreenLM())
                .build();
        
        return panel;
    }
}
