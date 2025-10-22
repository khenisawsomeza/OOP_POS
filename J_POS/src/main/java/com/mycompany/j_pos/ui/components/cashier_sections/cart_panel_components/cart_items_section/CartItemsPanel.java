package com.mycompany.j_pos.ui.components.cashier_sections.cart_panel_components.cart_items_section;

import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.models.CartEntry;
import com.mycompany.j_pos.models.Item;
import com.mycompany.j_pos.ui.builders.ButtonBuilder;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class CartItemsPanel extends JPanel implements themeManager.ThemeChangeListener {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 800;
    private static final int ITEM_WIDTH = 400;
    private static final int ITEM_HEIGHT = 50;
    private static final int ITEM_FONT_SIZE = 14;
    private static final int ITEM_PADDING = 5;
    private static final int PANEL_PADDING = 40;
    private static final int ITEM_GAP = 10;

    private final Cart cart = Cart.getInstance();
    private final themeManager theme = themeManager.getInstance();

    public CartItemsPanel() {
        
        initializeUI();
        applyTheme();

    }

    private void initializeUI() {
        theme.addThemeChangeListener(this);
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, ITEM_GAP));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createEmptyBorder(10, PANEL_PADDING, 10, PANEL_PADDING));
    }

    // refresh item display
    public void refreshItems() {
        removeAll();

        for (CartEntry entry : cart.getItems()) {
            JPanel cartItemPanel = new CartItem(entry.getItem(), entry.getQuantity());
            add(cartItemPanel);
        }

        revalidate();
        repaint();
    }

    //apply theme
    private void applyTheme() {
        setBackground(theme.getLightGrayColor());
        
        revalidate();
        repaint();
    }

    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}
