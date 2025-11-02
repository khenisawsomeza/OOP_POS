package com.mycompany.j_pos.ui.components.inventory_sections.inventory_content_panel_components;

import java.awt.*;
import javax.swing.*;

public class FilterPanel extends JPanel {

    private JComboBox<String> categoryDropdown;
    private JTextField searchField;

    public FilterPanel() {
        initializeUI();
        createComponents();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // margin-like border
        setBackground(new Color(245, 245, 245)); // light gray background
    }

    private void createComponents() {
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        leftPanel.setOpaque(false); // make it blend with background

        // Dropdown
        categoryDropdown = new JComboBox<>(new String[]{"All", "Desserts", "Beverages", "Entrees"});
        categoryDropdown.setPreferredSize(new Dimension(150, 30));

        // Search bar
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.putClientProperty("JTextField.placeholderText", "Search..."); // works in modern Swing LAFs

        // Add components to the left side
        leftPanel.add(categoryDropdown);
        leftPanel.add(searchField);

        // Add to main panel (left-aligned)
        add(leftPanel, BorderLayout.WEST);
    }
}
