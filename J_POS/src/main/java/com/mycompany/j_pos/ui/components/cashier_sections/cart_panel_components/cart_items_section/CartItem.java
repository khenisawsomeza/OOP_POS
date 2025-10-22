package com.mycompany.j_pos.ui.components.cashier_sections.cart_panel_components.cart_items_section;

import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.models.Item;
import com.mycompany.j_pos.ui.builders.ButtonBuilder;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.components.cashier_sections.CartPanel;
import com.mycompany.j_pos.ui.utils.commons.Icons;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;

public class CartItem extends JPanel implements themeManager.ThemeChangeListener {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 50;
    private static final int FONT_SIZE = 14;
    private static final int PADDING = 5;
    private static final int ICON_SIZE = 15;

    private final Item item;
    private final int quantity;

    private final Cart cart = Cart.getInstance();
    private final themeManager theme = themeManager.getInstance();

    private JLabel nameLabel;
    private JLabel quantityLabel;
    private JLabel priceLabel;
    private JButton removeButton;

    public CartItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;

        theme.addThemeChangeListener(this);
        initializeUI();
        applyTheme();
    }

    private void initializeUI() {
        setLayout(new GridLayout(1, 4));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        setOpaque(true);

        nameLabel = createLabel(item.getName(), SwingConstants.LEFT);
        quantityLabel = createLabel(String.valueOf(quantity), SwingConstants.CENTER);
        priceLabel = createLabel(item.getFormattedPrice(), SwingConstants.CENTER);
        removeButton = createRemoveButton();

        add(nameLabel);
        add(quantityLabel);
        add(priceLabel);
        add(removeButton);
    }

    // creates labels
    private JLabel createLabel(String text, int alignment) {
        return new LabelBuilder()
                .withText(text)
                .withFont(Font.BOLD, FONT_SIZE)
                .withForeground(theme.getTextForeground())
                .withAlignment(alignment, SwingConstants.CENTER)
                .build();
    }

    // creates remove button
    private JButton createRemoveButton() {
        return new ButtonBuilder()
                .withSize(ICON_SIZE, ICON_SIZE)
                .withIconSupplier(theme::getDeleteButtonIcon)
                .onClick(() -> {
                    cart.removeItemCompletely(item);
                    CartPanel.getInstance().refreshCartDisplay();
                })
                .withHoverEffect(true)
                .build();
    }

    // apply theme
    private void applyTheme() {
        setBackground(theme.getTextBackground());

        nameLabel.setForeground(theme.getTextForeground());
        quantityLabel.setForeground(theme.getTextForeground());
        priceLabel.setForeground(theme.getTextForeground());

        removeButton.setIcon(Icons.getScaledIcon(theme.getDeleteButtonIcon(), ICON_SIZE, ICON_SIZE));
    }

    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}
