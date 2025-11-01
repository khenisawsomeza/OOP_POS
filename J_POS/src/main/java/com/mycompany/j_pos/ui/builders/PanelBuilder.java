package com.mycompany.j_pos.ui.builders;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.border.Border;

public class PanelBuilder {
    private LayoutManager layout = new FlowLayout();
    private Dimension size = null;
    private Color background = null;
    private boolean opaque = true;
    private Border border = null;
    private Cursor cursor = null;
    private final List<Component> components = new ArrayList<>();
    
    
    // Mouse behaviors
    private Runnable onClick;
    private Consumer<JPanel> onHoverEnter;
    private Consumer<JPanel> onHoverExit;
    private Consumer<JPanel> onPress;
    private Consumer<JPanel> onRelease;
    
    public PanelBuilder withLayout(LayoutManager layout) {
        this.layout = layout;
        return this;
    }

    public PanelBuilder withSize(int width, int height) {
        this.size = new Dimension(width, height);
        return this;
    }

    public PanelBuilder withBackground(Color color) {
        this.background = color;
        this.opaque = true;
        return this;
    }

    public PanelBuilder transparent() {
        this.opaque = false;
        return this;
    }

    public PanelBuilder withBorder(Border border) {
        this.border = border;
        return this;
    }

    public PanelBuilder withCursor(Cursor cursor) {
        this.cursor = cursor;
        return this;
    }

    public PanelBuilder onClick(Runnable action) {
        this.onClick = action;
        return this;
    }

    public PanelBuilder onHoverEnter(Consumer<JPanel> action) {
        this.onHoverEnter = action;
        return this;
    }

    public PanelBuilder onHoverExit(Consumer<JPanel> action) {
        this.onHoverExit = action;
        return this;
    }

    public PanelBuilder onPress(Consumer<JPanel> action) {
        this.onPress = action;
        return this;
    }

    public PanelBuilder onRelease(Consumer<JPanel> action) {
        this.onRelease = action;
        return this;
    }

    public JPanel build() {
        JPanel panel = new JPanel(layout);

        if (size != null){
            panel.setPreferredSize(size);
            panel.setSize(size);
            panel.setMaximumSize(size);
            panel.setMinimumSize(size);
        }
        if (background != null) panel.setBackground(background);
        panel.setOpaque(opaque);
        if (border != null) panel.setBorder(border);
        if (cursor != null) panel.setCursor(cursor);

        // Add mouse behaviors
        if (onClick != null || onHoverEnter != null || onHoverExit != null || onPress != null || onRelease != null) {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (onClick != null) onClick.run();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (onHoverEnter != null) onHoverEnter.accept(panel);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (onHoverExit != null) onHoverExit.accept(panel);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (onPress != null) onPress.accept(panel);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (onRelease != null) onRelease.accept(panel);
                }
            });
        }

        return panel;
    }
}
