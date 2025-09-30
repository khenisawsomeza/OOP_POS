package com.mycompany.j_pos.ui.components.cashier_sections.items;

import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.models.Item;
import com.mycompany.j_pos.ui.factories.PanelFactory;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ItemsGridPanel extends JPanel {
    private final Cart cart;
    private final CartPanelRefresher refresher;
    private final List<Item> availableItems;
    private JPanel itemsListPanel;

    public interface CartPanelRefresher {
        void refreshCartDisplay();
    }

    public ItemsGridPanel(Cart cart, CartPanelRefresher refresher, List<Item> availableItems) {
        this.cart = cart;
        this.refresher = refresher;
        this.availableItems = availableItems;
        initializeGrid();
    }

    private void initializeGrid() {
        setLayout(new BorderLayout());
        itemsListPanel = PanelFactory.createSectionPanel(
                new FlowLayout(FlowLayout.LEFT, 30, 30),
                new Dimension(780, Integer.MAX_VALUE)
        );
        add(itemsListPanel, BorderLayout.CENTER);
        refreshItemsDisplay();
    }

    public void refreshItemsDisplay() {
        itemsListPanel.removeAll();

        for (Item item : availableItems) {
            JPanel itemButton = PanelFactory.createItemPanel(item, cart, this::handleItemClick);
            itemsListPanel.add(itemButton);
        }

        itemsListPanel.add(PanelFactory.addNewItemPanel());

        itemsListPanel.revalidate();
        itemsListPanel.repaint();
    }

    private void handleItemClick(Item item) {
        cart.addItem(item);
        refresher.refreshCartDisplay();
    }
}
