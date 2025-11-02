package com.mycompany.j_pos.ui.components.inventory_sections.inventory_content_panel_components;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class InventoryItemListPanel extends JPanel {

    private final JPanel contentPanel;
    private final List<Item> items = new ArrayList<>();

    public InventoryItemListPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Sample hardcoded items
        items.add(new Item("Chocolate Cake", "Dessert", 10, 150.00));
        items.add(new Item("Iced Coffee", "Beverage", 25, 75.00));
        items.add(new Item("Spaghetti", "Entree", 15, 120.00));
        items.add(new Item("Burger", "Entree", 20, 99.00));
        items.add(new Item("Mango Shake", "Beverage", 18, 80.00));

        // Vertical stacking panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(250, 250, 250));

        // Scrollable container (vertical only)
        JScrollPane scrollPane = new JScrollPane(contentPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.LIGHT_GRAY));
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0)); // hide scrollbar
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smooth scroll

        add(scrollPane, BorderLayout.CENTER);

        loadItems();
    }

    private void loadItems() {
        contentPanel.removeAll();

        for (Item item : items) {
            JPanel itemPanel = new JPanel(new BorderLayout(10, 0));
            itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70)); // fill width, fixed height
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

            // LEFT: Name and Category
            JPanel leftPanel = new JPanel(new GridLayout(2, 1));
            leftPanel.setOpaque(false);
            JLabel nameLabel = new JLabel(item.name);
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            JLabel categoryLabel = new JLabel(item.category);
            categoryLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            categoryLabel.setForeground(Color.GRAY);
            leftPanel.add(nameLabel);
            leftPanel.add(categoryLabel);

            // CENTER: Stock & Price
            JPanel centerPanel = new JPanel(new GridLayout(2, 1));
            centerPanel.setOpaque(false);
            centerPanel.add(new JLabel("Stock: " + item.stock));
            centerPanel.add(new JLabel("₱ " + item.price));

            // RIGHT: Buttons
            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
            rightPanel.setOpaque(false);
            JButton editButton = new JButton("Edit");
            JButton deleteButton = new JButton("Delete");
            editButton.addActionListener(e -> openEditDialog(item));
            rightPanel.add(editButton);
            rightPanel.add(deleteButton);

            itemPanel.add(leftPanel, BorderLayout.WEST);
            itemPanel.add(centerPanel, BorderLayout.CENTER);
            itemPanel.add(rightPanel, BorderLayout.EAST);

            contentPanel.add(itemPanel);
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void openEditDialog(Item item) {
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField nameField = new JTextField(item.name);
        JTextField categoryField = new JTextField(item.category);
        JTextField stockField = new JTextField(String.valueOf(item.stock));
        JTextField priceField = new JTextField(String.valueOf(item.price));

        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(new JLabel("Category:"));
        form.add(categoryField);
        form.add(new JLabel("Stock:"));
        form.add(stockField);
        form.add(new JLabel("Price:"));
        form.add(priceField);

        int result = JOptionPane.showConfirmDialog(
                this, form, "Edit Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            item.name = nameField.getText();
            item.category = categoryField.getText();
            item.stock = Integer.parseInt(stockField.getText());
            item.price = Double.parseDouble(priceField.getText());
            loadItems();
        }
    }

    // Simple model class
    private static class Item {
        String name, category;
        int stock;
        double price;

        Item(String name, String category, int stock, double price) {
            this.name = name;
            this.category = category;
            this.stock = stock;
            this.price = price;
        }
    }

}
