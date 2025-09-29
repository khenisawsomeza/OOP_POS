/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.factories;

import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.models.Item;
import com.mycompany.j_pos.ui.utils.commons.Icons;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import javax.swing.*;

/**
 *
 * @author khenshi
 */
public class PanelFactory {
    
    public static JPanel createPanel(LayoutManager layout, Dimension dim, Color color){
                
        JPanel panel = new JPanel(layout);
        panel.setPreferredSize(dim);
        panel.setBackground(color);
        
        return panel;
    }
    
    public static JPanel createSectionPanel(LayoutManager layout, Dimension dim){
                
        JPanel panel = new JPanel(layout);
        panel.setPreferredSize(dim);
        panel.setOpaque(false);
        
        return panel;
    }
    
    public static JPanel createItemPanel(Item item, Cart cart, Consumer<Item> consumer) {
        JPanel itemPanel = createPanel(new BorderLayout(), new Dimension(220, 170), themeManager.getInstance().getStaticLightGreenLM());
        itemPanel.setName("lightGreenPanel");
        itemPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // --- Picture panel (with padding) ---
        JPanel picturePanel = createPanel(new BorderLayout(), new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE), themeManager.getInstance().getStaticLightGreenLM());
        picturePanel.setName("lightGreenPanel");
        picturePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        
        JLabel pictureLabel = LabelFactory.createIconLabel(200, 120, JLabel.CENTER, JLabel.CENTER, item.getPicture());
        picturePanel.add(pictureLabel, BorderLayout.CENTER);

        // --- Prompt label (hover message) ---
        JLabel promptLabel = LabelFactory.createLabel("Add " + item.getName() + " to cart?", Font.BOLD, 16, JLabel.CENTER, JLabel.CENTER);
        promptLabel.setName("promptLabel");

        // --- Container that switches between picture and prompt ---
        JPanel centerContainer = new JPanel(new CardLayout());
        centerContainer.setBackground(themeManager.getInstance().getStaticLightGreenLM());
        centerContainer.add(picturePanel, "PICTURE");
        centerContainer.add(promptLabel, "PROMPT");

        itemPanel.add(centerContainer, BorderLayout.CENTER);

        // --- Bottom name panel ---
        JPanel namePanel = createPanel(new BorderLayout(), new Dimension(220, 40), themeManager.getInstance().getLightGreenColor());
        namePanel.setName("lightGreenPanel");
        JLabel nameLabel = LabelFactory.createLabel(item.getName(), Font.BOLD, 22, JLabel.CENTER, JLabel.CENTER);
        nameLabel.setName("regularLabel");
        namePanel.add(nameLabel, BorderLayout.CENTER);

        itemPanel.add(namePanel, BorderLayout.SOUTH);

        // --- Hover behavior ---
        itemPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ((CardLayout) centerContainer.getLayout()).show(centerContainer, "PROMPT");
                namePanel.setVisible(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((CardLayout) centerContainer.getLayout()).show(centerContainer, "PICTURE");
                namePanel.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                centerContainer.setBackground(themeManager.getInstance().getStaticPrimaryGreenDM());
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                centerContainer.setBackground(themeManager.getInstance().getStaticLightGreenLM());
            }
            
            @Override
            public void mouseClicked(MouseEvent e){
                consumer.accept(item);
            }
        });
        
        return itemPanel;
    }
    
    public static JPanel createCheckoutPanel(LayoutManager layout, Dimension dim, Runnable action){
        JPanel checkOutPanel = createPanel(layout, dim, themeManager.getInstance().getStaticLightGreenLM());
        // Checkout button hover and click effects
        checkOutPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkOutPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                checkOutPanel.setBackground(themeManager.getInstance().getStaticPrimaryGreenLM());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                checkOutPanel.setBackground(themeManager.getInstance().getStaticLightGreenLM());
            }
        });
        
        if (action != null) {
            checkOutPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    action.run();
                }
            });
        }
            
        return checkOutPanel;
    }
    
    public static JPanel addNewItemPanel() {
        JPanel itemPanel = createPanel(new BorderLayout(), new Dimension(220, 170), themeManager.getInstance().getLightGrayColor());
        itemPanel.setName("addNewItemPanel");
        itemPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        itemPanel.setVisible(true);
        itemPanel.setBorder(BorderFactory.createDashedBorder(themeManager.getInstance().getTextForeground(), 2, 10, 5, true));
        
        
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon scaledIcon = Icons.getScaledIcon(themeManager.getInstance().getPlusIcon(), 64, 64);
        imageLabel.setIcon(scaledIcon);
        
        themeManager.getInstance().addThemeChangeListener(isDarkMode -> {
            imageLabel.setIcon(Icons.getScaledIcon(themeManager.getInstance().getPlusIcon(), 64, 64));
        });

        
        itemPanel.add(imageLabel, BorderLayout.CENTER);

        // --- Hover behavior ---
        itemPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                itemPanel.setBackground(themeManager.getInstance().getStaticPrimaryGreenLM());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                itemPanel.setBackground(themeManager.getInstance().getLightGrayColor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                itemPanel.setBackground(themeManager.getInstance().getStaticPrimaryGreenDM());
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                itemPanel.setBackground(themeManager.getInstance().getStaticPrimaryGreenLM());
            }
            
            @Override
            public void mouseClicked(MouseEvent e){
                System.out.println("hi");
            }
        });
        
        return itemPanel;
    }
    public static JPanel addNewCategoryPanel() {
        JPanel itemPanel = createPanel(new BorderLayout(), new Dimension(120, 50), themeManager.getInstance().getLightGreenColor());
        itemPanel.setName("addNewCatPanel");
        itemPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        itemPanel.setVisible(true);
        itemPanel.setBorder(BorderFactory.createDashedBorder(themeManager.getInstance().getTextForeground(), 2, 10, 5, true));
        
        
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon scaledIcon = Icons.getScaledIcon(themeManager.getInstance().getPlusIcon(), 32, 32);
        
        themeManager.getInstance().addThemeChangeListener(isDarkMode -> {
            imageLabel.setIcon(Icons.getScaledIcon(themeManager.getInstance().getPlusIcon(), 32, 32));
        });
        
        imageLabel.setIcon(scaledIcon);

        itemPanel.add(imageLabel, BorderLayout.CENTER);

        // --- Hover behavior ---
        itemPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                itemPanel.setBackground(themeManager.getInstance().getStaticPrimaryGreenLM());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                itemPanel.setBackground(themeManager.getInstance().getLightGreenColor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                itemPanel.setBackground(themeManager.getInstance().getStaticPrimaryGreenDM());
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                itemPanel.setBackground(themeManager.getInstance().getStaticPrimaryGreenLM());
            }
            
            @Override
            public void mouseClicked(MouseEvent e){
                System.out.println("hi");
            }
        });
        
        return itemPanel;
    }
}
