package com.mycompany.j_pos.ui.components.cashier_sections.items;

import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.models.Category;
import com.mycompany.j_pos.models.Item;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.components.cashier_sections.cart.CartPanel;
import com.mycompany.j_pos.ui.utils.LoadResources;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;

public class ItemsPanel extends JPanel {
    private final Cart cart;
    private final CartPanel cartPanel;
    private final List<Item> availableItems;
    private final List<Category> availableCategories;

    public ItemsPanel(Cart cart, CartPanel cartPanel) throws FileNotFoundException {
        this.cart = cart;
        this.cartPanel = cartPanel;
        this.availableItems = LoadResources.loadSampleItems();
        this.availableCategories = LoadResources.loadSampleCategories();
        initializeItemsPanel();
    }

    private void initializeItemsPanel() {
        themeManager theme = themeManager.getInstance();

        setLayout(new BorderLayout());
        setBackground(theme.getLightGrayColor());
        setName("itemsPanel");

        // Header
        add(new HeaderPanel(), BorderLayout.NORTH);

        // Items Grid (dynamic width)
        ItemsGridPanel itemsGrid = new ItemsGridPanel(cart, cartPanel::refreshCartDisplay, availableItems);
        add(itemsGrid, BorderLayout.CENTER);

        // Categories section
        add(new CategoriesPanel(availableCategories), BorderLayout.SOUTH);
    }
}
