/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.components.cashier_sections;

import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import com.mycompany.j_pos.ui.utils.commons.Icons;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Khenyshi
 */
    public class MenuBurgerSidebar extends JPanel implements themeManager.ThemeChangeListener{
    static private MenuBurgerSidebar instance;
    private final themeManager theme = themeManager.getInstance();
    private final Icons icons = Icons.getInstance();

    private final int width = 220;
    private final int height = 720;
    private static final Dimension LOGO_SIZE = new Dimension(150, 30);
    
    private JLabel companyLogoLabel;
    private ArrayList<JLabel> labels = new ArrayList<>();
    private ArrayList<JPanel> sidebarItems = new ArrayList<>();
    
    private MenuBurgerSidebar() {
        theme.addThemeChangeListener(this);
        initializeUI();
    }
    
    static public MenuBurgerSidebar getInstance(){
        if (instance == null) instance = new MenuBurgerSidebar();
        return instance;
    }
    
    private void initializeUI(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(theme.getLightGrayColor());
        setBounds(-width, 0, width, height); // start hidden
        
        createContents();
        applyTheme();
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
        JLabel label = new LabelBuilder()
            .withText(text)
            .withFont(Font.PLAIN, 14)
            .withForeground(theme.getTextForeground())
            .withAlignment(SwingConstants.LEFT, SwingConstants.CENTER)
            .build();

        JPanel sidebarItemPanel = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withBackground(theme.getLightGrayColor())
                .withBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10))
                .withCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
                .onHoverEnter(panel -> panel.setBackground(theme.getSidebarItemHoverBackground()))
                .onHoverExit(panel -> panel.setBackground(theme.getLightGrayColor()))
                .onPress(panel -> panel.setBackground(theme.getSidebarItemPressBackground()))
                .onRelease(panel -> panel.setBackground(theme.getSidebarItemHoverBackground()))
                .onClick(onClick)
                .build();

        sidebarItemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        sidebarItemPanel.add(label, BorderLayout.CENTER);

        labels.add(label);
        sidebarItems.add(sidebarItemPanel);

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
    
    //apply theme
    private void applyTheme() {
        // Set sidebar background color
        setBackground(theme.getLightGrayColor());

        // Apply text color to labels
        for (JLabel label : labels) {
            label.setForeground(theme.getTextForeground());
        }

        if (companyLogoLabel != null) {
            companyLogoLabel.setIcon(
                icons.getScaledIcon(theme.getLogoIcon(), LOGO_SIZE.width, LOGO_SIZE.height)
            );
        }
        
        // Apply background color to sidebar items (if you have item panels)
        for (JPanel item : sidebarItems) {
            item.setBackground(theme.getLightGrayColor());
        }

        // Optional: set hover or accent colors if defined in theme
        revalidate();
        repaint();
    }
    
    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}

