package com.mycompany.j_pos.ui.components.cashier_sections.cart;

import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.models.CartEntry;
import com.mycompany.j_pos.models.Item;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.factories.ButtonFactory;
import com.mycompany.j_pos.ui.factories.LabelFactory;
import com.mycompany.j_pos.ui.factories.PanelFactory;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;

public class CartItemsSection extends JPanel {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 800;

    private final Cart cart;
    private final Runnable refreshCallback;

    public CartItemsSection(Cart cart, Runnable refreshCallback) {
        this.cart = cart;
        this.refreshCallback = refreshCallback;
        setupItemsPanel();
    }

    private void setupItemsPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
    }

    public void refreshItems() {
        removeAll();

        for (CartEntry entry : cart.getItems()) {
            add(createCartItemPanel(entry.getItem(), entry.getQuantity()));
        }

        revalidate();
        repaint();
        themeManager.getInstance().applyThemeToAllComponents();
    }

    private JPanel createCartItemPanel(Item item, int quantity) {
        JPanel itemRow = PanelFactory.createPanel(
            new GridLayout(1, 4),
            new Dimension(400, 50),
            themeManager.getInstance().getTextBackground()
        );
        itemRow.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel qty = new LabelBuilder()
                .withText(String.valueOf(quantity))
                .withFont(Font.BOLD, 14)
                .withForeground(themeManager.getInstance().getTextForeground())
                .build();

        JLabel price = new LabelBuilder()
                .withText(item.getFormattedPrice())
                .withFont(Font.BOLD, 14)
                .withForeground(themeManager.getInstance().getTextForeground())
                .build();

        JLabel name = new LabelBuilder()
                .withText(item.getName())
                .withFont(Font.BOLD, 14)
                .withForeground(themeManager.getInstance().getTextForeground())
                .build();

        
        name.setHorizontalAlignment(SwingConstants.LEFT);
        qty.setHorizontalAlignment(SwingConstants.CENTER);
        price.setHorizontalAlignment(SwingConstants.CENTER);

        JButton removeBtn = ButtonFactory.createItemRemoveButton(
            15, 15, themeManager.getInstance().getDeleteButtonIcon(),
            refreshCallback, cart, item
        );

        itemRow.add(name);
        itemRow.add(qty);
        itemRow.add(price);
        itemRow.add(removeBtn);

        return itemRow;
    }
}
