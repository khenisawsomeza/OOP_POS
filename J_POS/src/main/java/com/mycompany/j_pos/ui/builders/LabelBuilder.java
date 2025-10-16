package com.mycompany.j_pos.ui.builders;

import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.border.Border;

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
    private Runnable onClick;
    private Consumer<JLabel> onHoverEnter;
    private Consumer<JLabel> onHoverExit;

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
        this.icon = new ImageIcon(scaledImage);
        return this;
    }

    public LabelBuilder onClick(Runnable action) {
        this.onClick = action;
        return this;
    }

    public LabelBuilder onHoverEnter(Consumer<JLabel> action) {
        this.onHoverEnter = action;
        return this;
    }

    public LabelBuilder onHoverExit(Consumer<JLabel> action) {
        this.onHoverExit = action;
        return this;
    }

    public JLabel build() {
        JLabel label = (icon != null) ? new JLabel(icon, hAlign) : new JLabel(text, hAlign);
        label.setVerticalAlignment(vAlign);
        label.setFont(new Font(fontFamily, fontStyle, fontSize));
        label.setForeground(foreground);

        if (background != null) {
            label.setBackground(background);
            label.setOpaque(opaque);
        }

        if (border != null) label.setBorder(border);
        if (cursor != null) label.setCursor(cursor);

        // Combine hover and click behavior
        if (onClick != null || onHoverEnter != null || onHoverExit != null) {
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (onClick != null) onClick.run();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (onHoverEnter != null) onHoverEnter.accept(label);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (onHoverExit != null) onHoverExit.accept(label);
                }
            });
        }

        return label;
    }
}
