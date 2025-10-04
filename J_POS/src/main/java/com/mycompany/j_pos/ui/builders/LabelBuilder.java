package com.mycompany.j_pos.ui.builders;

import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Builder pattern for creating JLabels with flexible styling and behavior.
 * Keeps UI creation reusable and clean.
 */
public class LabelBuilder {
    private final String fontFamily = AppConstants.DEFAULT_FONT;
    private String text;
    private int fontStyle = Font.PLAIN;
    private int fontSize = 12;
    private Color foreground = Color.BLACK;
    private Color background = null;
    private int hAlign = SwingConstants.LEFT;
    private int vAlign = SwingConstants.CENTER;
    private Border border = null;
    private boolean opaque = false;
    private Cursor cursor = null;
    private ImageIcon icon = null;
    private Runnable onClick; // optional action

    public LabelBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public LabelBuilder withFont(int style, int size) {
        this.fontStyle = style;
        this.fontSize = size;
        return this;
    }

    public LabelBuilder withForeground(Color color) {
        this.foreground = color;
        return this;
    }

    public LabelBuilder withBackground(Color color) {
        this.background = color;
        this.opaque = true;
        return this;
    }

    public LabelBuilder withAlignment(int hAlign, int vAlign) {
        this.hAlign = hAlign;
        this.vAlign = vAlign;
        return this;
    }

    public LabelBuilder withBorder(Border border) {
        this.border = border;
        return this;
    }

    public LabelBuilder withCursor(Cursor cursor) {
        this.cursor = cursor;
        return this;
    }

    public LabelBuilder withIcon(ImageIcon icon, int width, int height) {
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        this.icon = scaledIcon;
        return this;
    }

    public LabelBuilder onClick(Runnable action) {
        this.onClick = action;
        return this;
    }

    public JLabel build() {
        JLabel label = (icon != null) ? new JLabel(icon, hAlign) : new JLabel(text, hAlign);
        label.setVerticalAlignment(vAlign);

        // font
        label.setFont(new Font(fontFamily, fontStyle, fontSize));
        label.setForeground(foreground);

        // background
        if (background != null) {
            label.setBackground(background);
            label.setOpaque(opaque);
        }

        // border
        if (border != null) label.setBorder(border);

        // cursor
        if (cursor != null) label.setCursor(cursor);

        // optional click action
        if (onClick != null) {
            label.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    onClick.run();
                }
            });
        }

        return label;
    }
}
