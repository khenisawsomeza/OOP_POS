/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.components.login_sections.login_panel_components;

import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Khenyshi
 */
public class LabelsPanel extends JPanel{
    
    private themeManager theme = themeManager.getInstance();
    
    public LabelsPanel(){
        initializeUI();
    }
    
    private void initializeUI(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        
        addTitleLabels();
        add(Box.createRigidArea(new Dimension(0, 75)));
        addStoreLabels();
    }
    
    private void addTitleLabels() {
        JLabel titleLabel = new LabelBuilder()
                .withText("Cha-Ching!")
                .withFont(Font.BOLD, 30)
                .withForeground(theme.getTextForeground())
                .withAlignment(SwingConstants.LEFT, SwingConstants.CENTER)
                .build();

        add(titleLabel);
    }
    
    private void addStoreLabels() {
        JLabel storeLabel = new LabelBuilder()
                .withText("Store Login")
                .withFont(Font.BOLD, 30)
                .withForeground(theme.getTextForeground())
                .withAlignment(SwingConstants.LEFT, SwingConstants.CENTER)
                .build();

        JLabel subTextLabel = new LabelBuilder()
                .withText("Enter your credentials to access the POS system.")
                .withFont(Font.PLAIN, 15)
                .withForeground(theme.getTextForeground())
                .withAlignment(SwingConstants.LEFT, SwingConstants.CENTER)
                .build();

        add(storeLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(subTextLabel);
    }
}
