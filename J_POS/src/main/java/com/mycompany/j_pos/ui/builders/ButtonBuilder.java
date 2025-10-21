/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.builders;

import com.mycompany.j_pos.ui.utils.commons.Icons;
import java.awt.Cursor;
import java.awt.Dimension;
import java.util.function.Supplier;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonBuilder {
    private int width = 50;
    private int height = 50;
    private ImageIcon icon;
    private Supplier<ImageIcon> iconSupplier;
    private Runnable onClick;
    private boolean withHoverEffect = false;

    public ButtonBuilder withSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public ButtonBuilder withIcon(ImageIcon icon) {
        this.icon = icon;
        return this;
    }
    
    public ButtonBuilder withIconSupplier(Supplier<ImageIcon> iconSupplier) {
        this.iconSupplier = iconSupplier;
        return this;
    }

    public ButtonBuilder onClick(Runnable action) {
        this.onClick = action;
        return this;
    }

    public ButtonBuilder withHoverEffect(boolean enable) {
        this.withHoverEffect = enable;
        return this;
    }

    public JButton build() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(width, height));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (icon != null) {
            button.setIcon(Icons.getScaledIcon(icon, width, height));
        } else if (iconSupplier != null){
            button.setIcon(Icons.getScaledIcon(iconSupplier.get(), width, height));
        }

        // Hover effects
        if (withHoverEffect && (icon != null || iconSupplier != null)) {
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    System.out.println("test");
                    button.setIcon(Icons.getScaledIcon(iconSupplier.get(), width + 2, height + 2));
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    button.setIcon(Icons.getScaledIcon(iconSupplier.get(), width, height));
                }
            });
        }

        // Click action
        if (onClick != null) {
            button.addActionListener(e -> onClick.run());
        }

        return button;
    }
}