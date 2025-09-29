/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.factories;

import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Khenyshi
 */
public class LabelFactory {
    
    public static JLabel createLabel(String text,int fontStroke, int fontSize, Color color) {
        JLabel label = new JLabel(text);  
        label.setFont(new Font("SansSerif", fontStroke, fontSize));
        label.setForeground(Color.BLACK);     
        
        label.setName("regularLabel");
        return label;
    }
//    
    public static JLabel createLabel(String text,int fontStroke, int fontSize, int hAlign, int vAlign) {
        JLabel label = new JLabel(text);  
        label.setFont(new Font("SansSerif", fontStroke, fontSize));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(hAlign);
        label.setVerticalAlignment(vAlign);
        
        label.setName("regularLabel");
        return label;
    }
    
    public static JLabel createIconLabel(int width, int height, int hAlign, int vAlign, ImageIcon icon){
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon, hAlign);
        label.setVerticalAlignment(vAlign);
        
        return label;
    }
    
    public static JLabel createButtonLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setOpaque(true);
        label.setBackground(themeManager.getInstance().getLightGreenColor());
        label.setForeground(themeManager.getInstance().getTextForeground());
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                label.setBackground(themeManager.getInstance().getStaticPrimaryGreenDM());
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                label.setBackground(themeManager.getInstance().getStaticPrimaryGreenLM());
                JOptionPane.showMessageDialog(null, "Changing items to " + text);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                label.setBackground(themeManager.getInstance().getStaticPrimaryGreenLM());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                label.setBackground(themeManager.getInstance().getLightGreenColor());
            }
        });
        
        label.setName("buttonLabel");
        return label;
    }
    
}
