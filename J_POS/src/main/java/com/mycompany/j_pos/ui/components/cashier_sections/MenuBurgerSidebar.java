/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.components.cashier_sections;

import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Khenyshi
 */
    public class MenuBurgerSidebar extends JPanel {
    static private MenuBurgerSidebar instance;

    private boolean isVisible = false;
    private final int width = 220;
    private final int height = 720;
    private final Color bgColor = themeManager.getInstance().getLightGrayColor();
    
    private JLabel companyLogoLabel;
    private static final Dimension LOGO_SIZE = new Dimension(150, 30);
    private final themeManager theme = themeManager.getInstance();

    private MenuBurgerSidebar() {
        initializeUI();
    }
    
    static public MenuBurgerSidebar getInstance(){
        if (instance == null) instance = new MenuBurgerSidebar();
        return instance;
    }
    
    private void initializeUI(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(bgColor);
        setBounds(-width, 0, width, height); // start hidden
        
        createContents();
    }

    private void createContents() {
        companyLogoLabel = new LabelBuilder()
                .withIcon(theme.getLogoIcon(), LOGO_SIZE.width, LOGO_SIZE.height)
                .withAlignment(SwingConstants.CENTER, SwingConstants.CENTER)
                .build();
        companyLogoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(companyLogoLabel, BorderLayout.CENTER);
        
        add(createSidebarItem("Sales", () -> System.out.println("Sales")));
        add(createSidebarItem("Inventory", () -> System.out.println("Inventory")));
        add(createSidebarItem("Discount", () -> System.out.println("Discount")));
        add(createSidebarItem("Logout", () -> System.out.println("Logout")));
        if(AppConstants.isAdmin){
            add(createSidebarItem("Manage Employees", () -> System.out.println("Manage Employees")));
            add(createSidebarItem("Edit Tax", () -> System.out.println("Edit Tax")));
        }
    }

    private JPanel createSidebarItem(String text, Runnable onClick) {
        JPanel sidebarItemPanel = new JPanel();
        sidebarItemPanel.setLayout(new BorderLayout());
        sidebarItemPanel.setBackground(new Color(245, 245, 245)); // light gray
        sidebarItemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        sidebarItemPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel label = new JLabel(text);
        label.setFont(new Font(AppConstants.DEFAULT_FONT, Font.PLAIN, 14));
        sidebarItemPanel.add(label, BorderLayout.CENTER);

        // Hover effect
        sidebarItemPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                sidebarItemPanel.setBackground(new Color(220, 220, 220)); // darker gray
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                sidebarItemPanel.setBackground(new Color(245, 245, 245)); // reset
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (onClick != null) onClick.run();
            }
        });

        return sidebarItemPanel;
    }


    public void onSidebar() {
        setLocation(0, 0);
        revalidate();
        repaint();
    }
    
    public void offSidebar() {
        setLocation(-width, 0);
        revalidate();
        repaint();
    }
}

