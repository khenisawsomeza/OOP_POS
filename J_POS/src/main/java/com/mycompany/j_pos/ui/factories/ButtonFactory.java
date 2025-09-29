/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.factories;

import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.models.Item;
import com.mycompany.j_pos.ui.utils.commons.Icons;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 *
 * @author khenshi
 */
public class ButtonFactory {
    
    public static JButton createIconButton(int width, int height, ImageIcon icon) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(width, height));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Always start with the scaled version
        button.setIcon(Icons.getScaledIcon(icon, width, height));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                button.setIcon(Icons.getScaledIcon(getThemedIcon(icon), width - 3, height - 3));
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                button.setIcon(Icons.getScaledIcon(getThemedIcon(icon), width, height));
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setIcon(Icons.getScaledIcon(getThemedIcon(icon), width + 2, height + 2));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setIcon(Icons.getScaledIcon(getThemedIcon(icon), width, height));
            }
        });

        return button;
    }

    private static ImageIcon getThemedIcon(ImageIcon original) {
        themeManager tm = themeManager.getInstance();
        Icons icons = Icons.getInstance();

        if (original == icons.getTrashIconDark() || original == icons.getTrashIconLight()) {
            return tm.getTrashIcon();
        }
        if (original == icons.getDeleteIconDark() || original == icons.getDeleteIconLight()) {
            return tm.getDeleteButtonIcon();
        }
        if (original == icons.getMenuIconDark() || original == icons.getMenuIconLight()) {
            return tm.getMenuIcon();
        }
        if (original == icons.getSearchIconDark() || original == icons.getSearchIconLight()) {
            return tm.getSearchIcon();
        }
        if (original == icons.getDarkModeToggleIconDark() || original == icons.getDarkModeToggleIconLight()) {
            return tm.getDarkModeToggleIcon();
        }
        
        return original; // fallback for other icons
    }


    
    public static JButton createIconButton(int width, int height, ImageIcon icon, Runnable action) {
        JButton button = createIconButton(width, height, icon);
        
        // Action listener
        if (action != null) {
            button.addActionListener(e -> action.run());
        }
        
        return button;
    }
    
    public static JButton createItemRemoveButton(int width, int height, ImageIcon icon, Runnable action, Cart cart, Item item){
        JButton button = createIconButton(width, height, icon, action);
        
        // Action listener
        if (action != null) {
            button.addActionListener(e -> {
                cart.removeItemCompletely(item);
                action.run();
            });
        }
        
        return button;
    }
    
}
