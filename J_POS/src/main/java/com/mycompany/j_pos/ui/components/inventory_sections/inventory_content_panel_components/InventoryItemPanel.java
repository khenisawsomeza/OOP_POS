package com.mycompany.j_pos.ui.components.inventory_sections.inventory_content_panel_components;

import com.mycompany.j_pos.models.items.Item;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import java.awt.*;
import javax.swing.*;

public class InventoryItemPanel extends JPanel {

    private final Item item;
    private final JButton editButton;
    private final JButton deleteButton;

    public InventoryItemPanel(Item item) {
        this.item = item;

        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

        JPanel detailsPanel = new JPanel(new GridLayout(1, 4)); // 4 columns
        detailsPanel.setOpaque(false);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel nameLabel = new JLabel(item.getName(), SwingConstants.LEFT);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel categoryLabel = new JLabel(item.getCategory(), SwingConstants.CENTER);
        categoryLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        categoryLabel.setForeground(Color.GRAY);

        JLabel stockLabel = new JLabel("Stock: " + item.getStock(), SwingConstants.CENTER);
        stockLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));

        JLabel priceLabel = new JLabel("₱ " + String.format("%.2f", item.getPrice()), SwingConstants.RIGHT);
        priceLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        priceLabel.setForeground(new Color(0, 102, 0));

        // Add labels 
        detailsPanel.add(nameLabel);
        detailsPanel.add(categoryLabel);
        detailsPanel.add(stockLabel);
        detailsPanel.add(priceLabel);

        // buttons
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        rightPanel.setOpaque(false);

        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        
        if(AppConstants.isAdmin){
            rightPanel.add(editButton);
            rightPanel.add(deleteButton);
        }
        
        add(detailsPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
    
    private JButton createEditButton(){
        JButton button = new JButton("Edit");
//        button.addActionListener(e -> openEditDialog(item));

        return button;
    }
    
    private void openEditDialog(Item item) {
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField nameField = new JTextField(item.getName());
        JTextField categoryField = new JTextField(item.getCategory());
        JTextField stockField = new JTextField(String.valueOf(item.getStock()));
        JTextField priceField = new JTextField(String.valueOf(item.getPrice()));

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

//        if (result == JOptionPane.OK_OPTION) {
//            item.name = nameField.getText();
//            item.category = categoryField.getText();
//            item.stock = Integer.parseInt(stockField.getText());
//            item.price = Double.parseDouble(priceField.getText());
//            loadItems();
//        }
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public Item getItem() {
        return item;
    }
}
