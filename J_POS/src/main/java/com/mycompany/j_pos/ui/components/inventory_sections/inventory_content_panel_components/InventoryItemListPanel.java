package com.mycompany.j_pos.ui.components.inventory_sections.inventory_content_panel_components;

import com.mycompany.j_pos.models.items.Item;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.LoadResources;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class InventoryItemListPanel extends JPanel {

    static private InventoryItemListPanel instance;
    private JPanel contentPanel;
    private final List<Item> items = LoadResources.getItems();

    private InventoryItemListPanel() {
        initializeUI();
        createComponents();
    }
    
    static public InventoryItemListPanel getInstance(){
        if (instance == null) instance = new InventoryItemListPanel();
        return instance;
    }
    
    private void initializeUI(){
        setLayout(new BorderLayout());
        setBorder(null);
        setBackground(themeManager.getInstance().getLightGrayColor());
    }
    
    public void createComponents(){
        // Vertical stacking panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(null);
        contentPanel.setBackground(themeManager.getInstance().getLightGrayColor());
        
        // Scrollable container
        JScrollPane scrollPane = new JScrollPane(contentPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0)); 
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        loadItems(items);
        
    }

    public void loadItems(List<Item> items) {
    contentPanel.removeAll();

    for (Item item : items) {
        InventoryItemPanel itemPanel = new InventoryItemPanel(item);

//        itemPanel.getEditButton().addActionListener(e -> openEditDialog(item));
//        itemPanel.getDeleteButton().addActionListener(e -> deleteItem(item));

        contentPanel.add(itemPanel);
    }

    contentPanel.revalidate();
    contentPanel.repaint();
}

    

}
