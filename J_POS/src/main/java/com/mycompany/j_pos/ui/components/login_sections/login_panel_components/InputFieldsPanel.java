/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.components.login_sections.login_panel_components;

import com.mycompany.j_pos.ui.builders.TextFieldBuilder;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * @author Khenyshi
 */
public class InputFieldsPanel extends JPanel implements themeManager.ThemeChangeListener {
    
    private themeManager theme = themeManager.getInstance();
    private JTextField usernameField;
    private JPasswordField passwordField;
    
    public InputFieldsPanel() {
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        
        usernameField = buildPlaceholderField("Username");
        passwordField = buildPasswordField("Password");
        
        add(usernameField);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(passwordField);
    }
    
    /** Builds a styled placeholder text field */
    private JTextField buildPlaceholderField(String placeholder) {
        return new TextFieldBuilder()
                .withPlaceholder(placeholder)
                .withSize(350, 35)
                .withFont(Font.PLAIN, 14)
                .build();
    }
    
    /** Builds a styled placeholder password field */
    private JPasswordField buildPasswordField(String placeholder) {
        return (JPasswordField) new TextFieldBuilder()
                .withPlaceholder(placeholder)
                .withSize(350, 35)
                .withFont(Font.PLAIN, 14)
                .isPassword(true)
                .build();
    }
    
    // Getter methods for LoginPanel to access field values
    public String getUsername() {
        return usernameField.getText();
    }
    
    public char[] getPassword() {
        return passwordField.getPassword();
    }
    
    public void clearPassword() {
        passwordField.setText("");
    }
    
    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }
    
    @Override
    public void onThemeChange(boolean isDarkMode) {
        // Add theme change implementation if needed
    }
}