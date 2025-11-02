package com.mycompany.j_pos.ui.components;

import com.mycompany.j_pos.database.SQLiteConnect;
import com.mycompany.j_pos.ui.utils.commons.themes.UIColors;
import java.awt.*;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SalesUI extends JPanel {
    private JTable salesTable;
    private DefaultTableModel tableModel;
    private JLabel totalSalesLabel;
    private JLabel totalAmountLabel;
    private JLabel avgSaleLabel;
    private NumberFormat currencyFormat;
    
    public SalesUI() {
        currencyFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
        
        setPreferredSize(new Dimension(1280, 720));
        setBackground(UIColors.WHITE);
        setLayout(new BorderLayout(10, 10));
        
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        
        loadSalesData();
        
        setVisible(true);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(UIColors.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Title section with primary green background
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(UIColors.PRIMARY_GREEN_LIGHTMODE);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel titleLabel = new JLabel("Sales Dashboard");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(UIColors.WHITE);
        titlePanel.add(titleLabel);
        
        // Metrics panel with proper alignment
        JPanel metricsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        metricsPanel.setBackground(UIColors.WHITE);
        metricsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        
        metricsPanel.add(createMetricCard("Total Sales", "0", UIColors.PRIMARY_GREEN_LIGHTMODE));
        metricsPanel.add(createMetricCard("Total Amount", "CA$0.00", UIColors.LIGHT_GREEN_LIGHTMODE));
        metricsPanel.add(createMetricCard("Average Sale", "CA$0.00", UIColors.PRIMARY_GREEN_DARKMODE));
        
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(UIColors.WHITE);
        container.add(titlePanel, BorderLayout.NORTH);
        container.add(metricsPanel, BorderLayout.CENTER);
        
        return container;
    }
    
    private JPanel createMetricCard(String title, String value, Color accentColor) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(UIColors.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIColors.LIGHT_GRAY_LIGHTMODE, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleLabel.setForeground(UIColors.BORDER_GRAY);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueLabel.setForeground(accentColor);
        
        if (title.equals("Total Sales")) {
            totalSalesLabel = valueLabel;
        } else if (title.equals("Total Amount")) {
            totalAmountLabel = valueLabel;
        } else if (title.equals("Average Sale")) {
            avgSaleLabel = valueLabel;
        }
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(UIColors.WHITE);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        String[] columns = {"Sale ID", "User ID", "Sale Date", "Total Amount", "Payment Method"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        salesTable = new JTable(tableModel);
        salesTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        salesTable.setRowHeight(35);
        salesTable.setSelectionBackground(UIColors.adjustBrightness(UIColors.LIGHT_GREEN_LIGHTMODE, 1.2f));
        salesTable.setSelectionForeground(UIColors.BLACK);
        salesTable.setShowVerticalLines(true);
        salesTable.setGridColor(UIColors.LIGHT_GRAY_LIGHTMODE);
        salesTable.setBackground(UIColors.WHITE);
        
        JTableHeader header = salesTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(UIColors.PRIMARY_GREEN_LIGHTMODE);
        header.setForeground(UIColors.WHITE);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        header.setBorder(BorderFactory.createEmptyBorder());
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        for (int i = 0; i < salesTable.getColumnCount(); i++) {
            salesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        salesTable.getColumnModel().getColumn(0).setPreferredWidth(100);  // Sale ID
        salesTable.getColumnModel().getColumn(1).setPreferredWidth(100);  // User ID
        salesTable.getColumnModel().getColumn(2).setPreferredWidth(200);  // Sale Date
        salesTable.getColumnModel().getColumn(3).setPreferredWidth(150);  // Total Amount
        salesTable.getColumnModel().getColumn(4).setPreferredWidth(150);  // Payment Method
        
        JScrollPane scrollPane = new JScrollPane(salesTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(UIColors.LIGHT_GRAY_LIGHTMODE, 1));
        scrollPane.getViewport().setBackground(UIColors.WHITE);
        
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        return tablePanel;
    }
    
    private void loadSalesData() {
        try (Connection conn = SQLiteConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT sale_id, user_id, sale_date, total_amount, payment_method FROM sales ORDER BY sale_date DESC")) {
            
            tableModel.setRowCount(0);
            
            int totalSales = 0;
            double totalAmount = 0.0;
            
            while (rs.next()) {
                int saleId = rs.getInt("sale_id");
                int userId = rs.getInt("user_id");
                String saleDate = rs.getString("sale_date");
                double amount = rs.getDouble("total_amount");
                String paymentMethod = rs.getString("payment_method");
                
                tableModel.addRow(new Object[]{
                    saleId,
                    userId,
                    saleDate,
                    currencyFormat.format(amount),
                    paymentMethod.substring(0, 1).toUpperCase() + paymentMethod.substring(1)
                });
                
                totalSales++;
                totalAmount += amount;
            }
          
            totalSalesLabel.setText(String.valueOf(totalSales));
            totalAmountLabel.setText(currencyFormat.format(totalAmount));
            if (totalSales > 0) {
                avgSaleLabel.setText(currencyFormat.format(totalAmount / totalSales));
            } else {
                avgSaleLabel.setText(currencyFormat.format(0));
            }
            
            System.out.println("✅ Sales data loaded successfully. Total sales: " + totalSales);
            
        } catch (SQLException e) {
            System.err.println("❌ Error loading sales data: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Error loading sales data: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void refreshData() {
        loadSalesData();
    }
}