/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.factories;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

/**
 *
 * @author Khenyshi
 */
public class FieldFactory {
    
    public static JTextField createTextField(String text) {
        JTextField textField = new JTextField(20);
        textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textField.setForeground(Color.BLACK);
        textField.setBackground(Color.WHITE);
        textField.setName("textField");
        return textField;
     }
    
}
