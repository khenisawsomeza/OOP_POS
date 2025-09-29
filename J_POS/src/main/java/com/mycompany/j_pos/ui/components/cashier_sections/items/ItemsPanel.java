package com.mycompany.j_pos.ui.components.cashier_sections.items;

import com.mycompany.j_pos.ui.components.cashier_sections.cart.CartPanel;
import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.models.Category;
import com.mycompany.j_pos.models.Item;
import com.mycompany.j_pos.ui.factories.LabelFactory;
import com.mycompany.j_pos.ui.factories.PanelFactory;
import com.mycompany.j_pos.ui.utils.LoadResources;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class ItemsPanel extends JPanel {
    private final Cart cart;
    private final CartPanel cartPanel;
    private final List<Item> availableItems;
    private final List<Category> availableCategories;
    private JPanel itemsListPanel;
    
    public ItemsPanel(Cart cart, CartPanel cartPanel) throws FileNotFoundException {
        this.cart = cart;
        this.cartPanel = cartPanel;
        this.availableItems = LoadResources.loadSampleItems(); //for showing only (to be changed to retrieve data from MySQL)
        this.availableCategories = LoadResources.loadSampleCategories();
        initializeItemsPanel();
    }
    
    //Initialize the items panel with header, items grid, and bottom controls
    private void initializeItemsPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(780, Integer.MAX_VALUE));
        setBackground(themeManager.getInstance().getLightGrayColor());
        this.setName("lightGrayPanel");
        
        // Add header
        add(new HeaderPanel(), BorderLayout.NORTH);
        
        // Add items grid
        addItemsGrid();
        
        // Add bottom controls
        addBottomControls();
    }
    
    // Create and add the items grid section
    private void addItemsGrid() {
        itemsListPanel = PanelFactory.createSectionPanel(new FlowLayout(FlowLayout.LEFT, 30, 30), new Dimension(780, Integer.MAX_VALUE));
        
        // Add item buttons
        refreshItemsDisplay();
        
        add(itemsListPanel, BorderLayout.CENTER);
    }
    
    //Refresh the items display based on current available items
        private void refreshItemsDisplay() {
            itemsListPanel.removeAll();

            for (Item item : availableItems) {
                JPanel itemButton = PanelFactory.createItemPanel(item, cart, this::handleItemClick);           
                itemsListPanel.add(itemButton);
            }
            
            itemsListPanel.add(PanelFactory.addNewItemPanel());
            
            itemsListPanel.revalidate();
            itemsListPanel.repaint();
        }
    
    //Create and add bottom controls (search and categories)
    private void addBottomControls() {
        JPanel bottomControlsPanel = PanelFactory.createPanel(new BorderLayout(), new Dimension(1420, 50), themeManager.getInstance().getLightGreenColor());   
        bottomControlsPanel.setName("lightGreenPanel");

        // Category buttons
        JPanel buttonsPanel = PanelFactory.createPanel(new FlowLayout(FlowLayout.LEFT, 20, 0), null, themeManager.getInstance().getLightGreenColor());
        buttonsPanel.setName("lightGreenPanel");
        
        buttonsPanel.setMaximumSize(new Dimension(500, 50));

        for(Category cat: availableCategories){
            buttonsPanel.add(LabelFactory.createButtonLabel(cat.getName()));
        }

        buttonsPanel.add(PanelFactory.addNewCategoryPanel());
        bottomControlsPanel.add(buttonsPanel, BorderLayout.CENTER);
        
        add(bottomControlsPanel, BorderLayout.SOUTH);
    }
    
    //Handle item button click - add to cart
    private void handleItemClick(Item item) {
        cart.addItem(item);
        cartPanel.refreshCartDisplay();
    }
    
    
}
