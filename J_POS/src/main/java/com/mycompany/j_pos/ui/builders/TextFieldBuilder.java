package com.mycompany.j_pos.ui.builders;

import com.mycompany.j_pos.ui.utils.commons.AppConstants;

import javax.swing.*;
import java.awt.*;

public class TextFieldBuilder {
    private String placeholder = "";
    private boolean isPassword = false;
    private int characters = 0;
    private int width = 350;
    private int height = 35;
    private int fontStyle = Font.PLAIN;
    private int fontSize = 14;
    private Color foreground = Color.GRAY;
    private Color background = Color.WHITE;
    private String fontFamily = AppConstants.DEFAULT_FONT;

    public TextFieldBuilder withPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }
    
    public TextFieldBuilder withPlaceholder(String placeholder, int characters) {
        this.placeholder = placeholder;
        this.characters = characters;
        return this;
    }

    public TextFieldBuilder isPassword(boolean isPassword) {
        this.isPassword = isPassword;
        return this;
    }

    public TextFieldBuilder withSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public TextFieldBuilder withFont(int style, int size) {
        this.fontStyle = style;
        this.fontSize = size;
        return this;
    }

    public TextFieldBuilder withForeground(Color color) {
        this.foreground = color;
        return this;
    }

    public TextFieldBuilder withBackground(Color color) {
        this.background = color;
        return this;
    }

    public JTextField build() {
        if (isPassword) {
            return buildPasswordField();
        } else {
            return buildTextField();
        }
    }

    private JTextField buildTextField() {
        JTextField field;
        
        if (characters > 0) field = new JTextField(placeholder, characters);
        else field = new JTextField(placeholder);
        styleCommon(field);

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });

        return field;
    }

    private JPasswordField buildPasswordField() {
        JPasswordField field = new JPasswordField(placeholder);
        styleCommon(field);
        field.setEchoChar((char) 0); // show placeholder

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    field.setEchoChar('•');
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (String.valueOf(field.getPassword()).isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                    field.setEchoChar((char) 0);
                }
            }
        });

        return field;
    }

    private void styleCommon(JTextField field) {
        field.setFont(new Font(fontFamily, fontStyle, fontSize));
        field.setForeground(foreground);
        field.setBackground(background);
        field.setSize(new Dimension(width, height));
        field.setMaximumSize(new Dimension(width, height));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setBorder(BorderFactory.createCompoundBorder(
                field.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }
}
