package com.mycompany.j_pos.ui.components.cashier_sections;

import com.mycompany.j_pos.models.items.Category;
import com.mycompany.j_pos.ui.components.cashier_sections.items_panel_components.CategoriesPanel;
import com.mycompany.j_pos.ui.components.cashier_sections.items_panel_components.HeaderPanel;
import com.mycompany.j_pos.ui.components.cashier_sections.items_panel_components.ItemsListPanel;
import com.mycompany.j_pos.ui.utils.LoadResources;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;

public class ItemsPanel extends JPanel implements themeManager.ThemeChangeListener {

    private List<Category> availableCategories;
    private final themeManager theme = themeManager.getInstance();

    private HeaderPanel headerPanel;
    private ItemsListPanel itemsGridPanel;
    private CategoriesPanel categoriesPanel;

    public ItemsPanel() throws FileNotFoundException {
        initializeData();  
        initializeUI();   
    }

    // Loads item and category data
    private void initializeData(){
        try {
            availableCategories = LoadResources.loadSampleCategories();
        } catch (FileNotFoundException e){
            System.out.println("failed to load items and categories");
        }
    }

    // Initializes the panel structure
    private void initializeUI() {
        theme.addThemeChangeListener(this);

        setLayout(new BorderLayout());
        setName("itemsPanel");

        // Header
        headerPanel = new HeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Items grid 
        itemsGridPanel = ItemsListPanel.getInstance();
        add(itemsGridPanel, BorderLayout.CENTER);

        // Categories section
        categoriesPanel = new CategoriesPanel(availableCategories);
        add(categoriesPanel, BorderLayout.SOUTH);

        applyTheme();
    }

    //Apply the current theme
    private void applyTheme() {
        setBackground(theme.getLightGrayColor());
    }

    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}
