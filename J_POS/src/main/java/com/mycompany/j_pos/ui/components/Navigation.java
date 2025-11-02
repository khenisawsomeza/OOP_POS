/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.components;

import com.mycompany.j_pos.controllers.SidebarController;
import com.mycompany.j_pos.ui.builders.ButtonBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.function.Supplier;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author khenshi
 */
public class Navigation extends JPanel{
    static private Navigation instance;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel navigationPanel; // Keep reference to navigation panel
    private final themeManager theme = themeManager.getInstance();
    private final SidebarController sbController = SidebarController.getInstance();
    private static final int SIDEBAR_WIDTH = 70;
    private static final int ICON_SIZE = 52;
    private boolean isBlank = true;
    
    static public Navigation getInstance() {
        if (instance == null) {
            instance = new Navigation();
        }
        return instance;
    }
    
    private Navigation(){
        initializeIU();
    }
    
    private void initializeIU(){
        this.setPreferredSize(new Dimension(SIDEBAR_WIDTH, AppConstants.screenSize.height));
        this.setMinimumSize(new Dimension(SIDEBAR_WIDTH, 100));
        this.setLayout(new BorderLayout());
        
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        
        navigationPanel = createNavigationPanel(); // Store reference
        
        contentPanel.add(navigationPanel, "NAVIGATION");
        contentPanel.add(createBlankPanel(), "BLANK");
        
//        cardLayout.show(contentPanel, "BLANK");
        cardLayout.show(contentPanel, "NAVIGATION");
        this.add(contentPanel, BorderLayout.CENTER);
    }
    
    public void refreshNavigation(){
        System.out.println("Refreshing navigation bar...");
        
        // Remove old navigation panel
        contentPanel.remove(navigationPanel);
        
        // Create new navigation panel with updated buttons
        navigationPanel = createNavigationPanel();
        
        // Add it back with the same card name
        contentPanel.add(navigationPanel, "NAVIGATION");
        
        // Show the navigation
        cardLayout.show(contentPanel, "NAVIGATION");
        
        // Force UI update
        contentPanel.revalidate();
        contentPanel.repaint();
        this.revalidate();
        this.repaint();
        
        System.out.println("Navigation refreshed. Admin: " + AppConstants.isAdmin);
    }
    
    public void showNavigation(){
        System.out.println("Showing navigation");
        cardLayout.show(contentPanel, "NAVIGATION");
        isBlank = false;
        contentPanel.revalidate();
        contentPanel.repaint();
        this.revalidate();
        this.repaint();
    }

    public void hideNavigation(){
        System.out.println("Hiding navigation");
        cardLayout.show(contentPanel, "BLANK");
        isBlank = true;
        contentPanel.revalidate();
        contentPanel.repaint();
        this.revalidate();
        this.repaint();
    }
    
    public void switchSidebar(){
        if(isBlank){
            showNavigation();
            refreshNavigation(); // Refresh when showing
        }else{
            hideNavigation();
        }
    }
    
    private JPanel createNavigationPanel(){
        JPanel panel = new PanelBuilder()
                .withLayout(new FlowLayout(FlowLayout.CENTER, 8, 12))
                .withBackground(theme.getSidebarBackground())
                .build();
        
        // Add buttons based on current state
        panel.add(createNavItemButton(() -> theme.getStaticDarkModeToggleIcon(), "Toggle Darkmode", sbController::toggleDarkMode));
        panel.add(createNavItemButton(() -> theme.getCashierIcon(), "Cashier", sbController::openCashier));
        panel.add(createNavItemButton(() -> theme.getSalesIcon(), "Sales", sbController::openSales));
        panel.add(createNavItemButton(() -> theme.getInventoryIcon(), "Inventory", sbController::openInventory));
        panel.add(createNavItemButton(() -> theme.getDiscountIcon(), "Discount", sbController::setDiscount));
//        
        if(AppConstants.isAdmin){
            panel.add(createNavItemButton(() -> theme.getManageEmployeesIcon(), "Manage Employees", sbController::openManageEmployees));
            panel.add(createNavItemButton(() -> theme.getEditTaxIcon(), "Edit Tax", sbController::editTax));
        }
//        
        panel.add(createNavItemButton(() -> theme.getLogOutIcon(), "Logout", sbController::logout));
        
        return panel;
    }
    
    private JButton createNavItemButton(Supplier<ImageIcon> iconSupplier, String tooltip, Runnable action) {
        JButton button = new ButtonBuilder()
                .withSize(ICON_SIZE, ICON_SIZE)
                .withIconSupplier(iconSupplier)
                .withHoverEffect(true)
                .onClick(action)
                .build();
        
        button.setToolTipText(tooltip);
        
        return button;
    }
    
    private JPanel createBlankPanel(){
        JPanel panel = new PanelBuilder()
                .withSize(Integer.MAX_VALUE, Integer.MAX_VALUE)
                .withBackground(themeManager.getInstance().getStaticLightGreenLM())
                .build();
        
        return panel;
    }
}
