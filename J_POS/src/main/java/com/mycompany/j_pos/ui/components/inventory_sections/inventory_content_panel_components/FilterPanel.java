package com.mycompany.j_pos.ui.components.inventory_sections.inventory_content_panel_components;

import com.mycompany.j_pos.models.items.Category;
import com.mycompany.j_pos.models.items.Item;
import com.mycompany.j_pos.services.ItemService;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.LoadResources;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.*;
import java.awt.event.ItemEvent;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.List;

public class FilterPanel extends JPanel {

    private JComboBox<String> categoryDropdown;
    private JTextField searchField;
    private List<Item> items = LoadResources.getItems();
    private List<Category> categories = null;

    public FilterPanel() {
        initializeUI();
        createComponents();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Integer.MAX_VALUE, 70));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 15, 10)); // margin-like border
        setBackground(themeManager.getInstance().getLightGrayColor()); // light gray background
        
        try{
            categories = LoadResources.loadCategories();
        } catch (Exception e){
            System.out.println("failed to laod categories in inventory dropdown");
        }
    }

    private void createComponents() {
        JPanel leftPanel = new PanelBuilder()
                .withLayout(new FlowLayout(FlowLayout.LEFT, 10, 5))
                .transparent()
                .build();
        
        // text
        JLabel searchLabel = new LabelBuilder()
                .withText("Search:")
                .withFont(Font.BOLD, 20)
                .build();
        
        JLabel categoryLabel = new LabelBuilder()
                .withText("Category:")
                .withFont(Font.BOLD, 20)
                .build();

        // Dropdown
        categoryDropdown = createDropdown();
        // Search bar
        searchField = createSearchField();

        // Add components to the left side
        leftPanel.add(categoryLabel);
        leftPanel.add(categoryDropdown);
        leftPanel.add(searchLabel);
        leftPanel.add(searchField);

        // Add to main panel (left-aligned)
        add(leftPanel, BorderLayout.WEST);
    }
    
    private JTextField createSearchField() {
        
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.putClientProperty("JTextField.placeholderText", "Search...");      
        
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            private void runSearch() {
                search(searchField.getText());    
            }

            @Override public void insertUpdate(DocumentEvent e) { runSearch(); }
            @Override public void removeUpdate(DocumentEvent e) { runSearch(); }
            @Override public void changedUpdate(DocumentEvent e) { runSearch(); }
        });
        
        return searchField;
    }
    
    private void search(String query){
        
        List<Item> items = null;
            try {
                items = ItemService.searchItem(searchField.getText());
            } catch (Exception a){
                System.out.println("failed to pass availble items in search");
            }
           
        InventoryItemListPanel.getInstance().loadItems(items);
    }
    
    private JComboBox createDropdown(){
        categoryDropdown = new JComboBox<>();
        categoryDropdown.setPreferredSize(new Dimension(150, 30));
        
        categoryDropdown.addItem("All");
        
        

        // Add each category name from database
        for (Category cat : categories) {
            categoryDropdown.addItem(cat.getName());
        }
        
        categoryDropdown.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                
                String selectedCategory = e.getItem().toString();
                System.out.println(selectedCategory);
                List<Item> itemsSelected = ItemService.getItemsInCategory(selectedCategory);
                
                if (selectedCategory == "All"){
                    InventoryItemListPanel.getInstance().loadItems(items);
                } else {
                    InventoryItemListPanel.getInstance().loadItems(itemsSelected);
                }
            }
        });
        
        return categoryDropdown;
    }
    
    
    
}
