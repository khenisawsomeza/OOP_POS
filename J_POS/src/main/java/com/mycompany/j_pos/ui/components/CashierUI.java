/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.components;

import com.mycompany.j_pos.ui.components.cashier_sections.CartPanel;
import com.mycompany.j_pos.ui.components.cashier_sections.ItemsPanel;
import com.mycompany.j_pos.ui.components.cashier_sections.MenuBurgerSidebar;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import javax.swing.*;

public class CashierUI extends JPanel {
//    private CartPanel cartPanel;
    
    public CashierUI() {
        
        // Setup main window
        setupMainWindow();
        
        // Create and add components
        createComponents();
        
        // Make window visible
        setVisible(true);
    }
    
    // Setup main window properties
    private void setupMainWindow() {
        setSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        setMinimumSize(new Dimension(1280, 720));
        setLayout(new BorderLayout());
    }
    
    //Create and add main sections to the window
    private void createComponents() {
    // Right side: Cart panel
    add(CartPanel.getInstance(), BorderLayout.EAST);

    // Center: LayeredPane for items + sidebar
    JLayeredPane layeredPane = new JLayeredPane();
    layeredPane.setLayout(null);
    add(layeredPane, BorderLayout.CENTER);

    // Create items panel (base layer)
    try {
        ItemsPanel itemsPanel = new ItemsPanel();
        layeredPane.add(itemsPanel, JLayeredPane.DEFAULT_LAYER);

        layeredPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                itemsPanel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
            }
        });
    } catch (FileNotFoundException e) {
        System.out.println(e);
    }

    // Sidebar (overlay layer)
    MenuBurgerSidebar sidebar = MenuBurgerSidebar.getInstance();
    sidebar.setBounds(-220, 0, 220, AppConstants.screenSize.height);
    layeredPane.add(sidebar, JLayeredPane.PALETTE_LAYER);
    
    // auto close sidebar 
    SwingUtilities.invokeLater(() -> {
    Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
        if (event instanceof MouseEvent me) {
            if (me.getID() == MouseEvent.MOUSE_MOVED && sidebar.isVisible()) {
                try {
                    Point mouseOnScreen = me.getLocationOnScreen();
                    Rectangle sidebarBounds = new Rectangle(sidebar.getLocationOnScreen(), sidebar.getSize());
                    if (!sidebarBounds.contains(mouseOnScreen)) {
                        sidebar.offSidebar();
                    }
                } catch (IllegalComponentStateException ex) {
                    // Sidebar not yet visible, ignore for now
                }
            }
        }
    }, AWTEvent.MOUSE_MOTION_EVENT_MASK);
});

}

    
}
