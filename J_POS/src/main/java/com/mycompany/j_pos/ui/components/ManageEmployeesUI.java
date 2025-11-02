package com.mycompany.j_pos.ui.components;

import com.mycompany.j_pos.database.SQLiteConnect;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ManageEmployeesUI extends JPanel {
    private JTextField nameField;
    private JTextField genderField;
    private JTextField ageField;
    private JTextField contactField;
    private JTextField emailField;
    private JComboBox<String> roleComboBox;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    
    public ManageEmployeesUI() {
        setPreferredSize(new Dimension(1280, 720));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        
        JPanel contentPanel = new JPanel(new BorderLayout(20, 0));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel formPanel = createFormPanel();
        contentPanel.add(formPanel, BorderLayout.WEST);
        
        JPanel tablePanel = createTablePanel();
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        
        add(contentPanel, BorderLayout.CENTER);
        
        loadEmployeesFromDatabase();
        
        setVisible(true);
    }
    
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel();
        header.setBackground(new Color(240, 240, 240));
        header.setPreferredSize(new Dimension(0, 80));
        header.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 25));
        
        JLabel titleLabel = new JLabel("Manage Employees");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(76, 175, 80));
        header.add(titleLabel);
        
        return header;
    }
    
    private JPanel createFormPanel() {
        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setBackground(new Color(250, 250, 250));
        formContainer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        formContainer.setPreferredSize(new Dimension(350, 0));
        
        formContainer.add(createFieldPanel("Full Name", nameField = createTextField()));
        formContainer.add(Box.createVerticalStrut(15));
        
        formContainer.add(createFieldPanel("Gender", genderField = createTextField()));
        formContainer.add(Box.createVerticalStrut(15));
        
        formContainer.add(createFieldPanel("Age", ageField = createTextField()));
        formContainer.add(Box.createVerticalStrut(15));
        
        formContainer.add(createFieldPanel("Contact", contactField = createTextField()));
        formContainer.add(Box.createVerticalStrut(15));
        
        formContainer.add(createFieldPanel("Email", emailField = createTextField()));
        formContainer.add(Box.createVerticalStrut(15));
        
        JPanel rolePanel = new JPanel();
        rolePanel.setLayout(new BoxLayout(rolePanel, BoxLayout.Y_AXIS));
        rolePanel.setBackground(new Color(250, 250, 250));
        rolePanel.setMaximumSize(new Dimension(310, 70));
        rolePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel roleLabel = new JLabel("Role");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        roleLabel.setForeground(Color.BLACK);
        roleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        roleComboBox = new JComboBox<>(new String[]{"employee", "admin"});
        roleComboBox.setPreferredSize(new Dimension(310, 35));
        roleComboBox.setMaximumSize(new Dimension(310, 35));
        roleComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        roleComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        roleComboBox.setBackground(Color.WHITE);
        
        rolePanel.add(roleLabel);
        rolePanel.add(Box.createVerticalStrut(5));
        rolePanel.add(roleComboBox);
        
        formContainer.add(rolePanel);
        formContainer.add(Box.createVerticalStrut(25));
        
        // Button panel
        JPanel buttonPanel = createButtonPanel();
        formContainer.add(buttonPanel);
        
        formContainer.add(Box.createVerticalGlue());
        
        return formContainer;
    }
    
    private JPanel createFieldPanel(String label, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(250, 250, 250));
        panel.setMaximumSize(new Dimension(310, 70));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldLabel.setForeground(Color.BLACK);
        fieldLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        textField.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(fieldLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(textField);
        
        return panel;
    }
    
    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(310, 35));
        field.setMaximumSize(new Dimension(310, 35));
        field.setBackground(Color.WHITE);
        field.setForeground(Color.BLACK);
        field.setCaretColor(Color.BLACK);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        return field;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBackground(new Color(250, 250, 250));
        panel.setMaximumSize(new Dimension(310, 90));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JButton saveBtn = createButton("Save", new Color(76, 175, 80));
        JButton editBtn = createButton("Edit", new Color(255, 193, 7)); // Gold color
        JButton deleteBtn = createButton("Delete", new Color(244, 67, 54));
        JButton clearBtn = createButton("Clear", new Color(158, 158, 158));
        
        saveBtn.addActionListener(e -> saveEmployee());
        editBtn.addActionListener(e -> editEmployee());
        deleteBtn.addActionListener(e -> deleteEmployee());
        clearBtn.addActionListener(e -> clearFields());
        
        panel.add(saveBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);
        panel.add(clearBtn);
        
        return panel;
    }
    
    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(145, 40));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bgColor);
            }
        });
        
        return btn;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(250, 250, 250));
        panel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        String[] columnNames = {"ID", "Username", "Full Name", "Gender", "Age", "Contact", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        employeeTable = new JTable(tableModel);
        employeeTable.setBackground(Color.WHITE);
        employeeTable.setForeground(Color.BLACK);
        employeeTable.setFont(new Font("Arial", Font.PLAIN, 13));
        employeeTable.setRowHeight(35);
        employeeTable.setGridColor(new Color(220, 220, 220));
        employeeTable.setSelectionBackground(new Color(76, 175, 80, 100));
        employeeTable.setSelectionForeground(Color.BLACK);
        employeeTable.setShowVerticalLines(true);
        employeeTable.setShowHorizontalLines(true);

        employeeTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && employeeTable.getSelectedRow() != -1) {
                populateFieldsFromTable();
            }
        });
        
        JTableHeader header = employeeTable.getTableHeader();
        header.setBackground(new Color(230, 230, 230));
        header.setForeground(new Color(76, 175, 80));
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(0, 40));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(76, 175, 80)));
        
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBackground(new Color(250, 250, 250));
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void loadEmployeesFromDatabase() {
        tableModel.setRowCount(0);
        String query = "SELECT user_id, username, full_name, gender, age, contact, email FROM users WHERE role IN ('employee', 'admin')";
        
        try (Connection conn = SQLiteConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                String fullName = rs.getString("full_name");
                String gender = rs.getString("gender");
                String age = rs.getString("age");
                String contact = rs.getString("contact");
                String email = rs.getString("email");
                
                tableModel.addRow(new Object[]{
                    String.format("%03d", userId),
                    username,
                    fullName != null ? fullName : "",
                    gender != null ? gender : "",
                    age != null ? age : "",
                    contact != null ? contact : "",
                    email != null ? email : ""
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading employees: " + e.getMessage() + 
                "\n\nMake sure the database has the required columns: gender, age, contact, email", 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void saveEmployee() {
        String fullName = nameField.getText().trim();
        String gender = genderField.getText().trim();
        String age = ageField.getText().trim();
        String contact = contactField.getText().trim();
        String email = emailField.getText().trim();
        String role = (String) roleComboBox.getSelectedItem();
        
        if (fullName.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Full name is required!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String firstName = fullName.split("\\s+")[0];
        
        int nextUserId = getNextUserId();
        if (nextUserId == -1) {
            JOptionPane.showMessageDialog(this, 
                "Error generating user ID!", 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String username = String.format("%03d%s", nextUserId, firstName.toLowerCase());
        
        String password = role + firstName;
        
        String insertUser = "INSERT INTO users (username, password, full_name, gender, age, contact, email, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = SQLiteConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertUser)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, fullName);
            pstmt.setString(4, gender);
            pstmt.setString(5, age);
            pstmt.setString(6, contact);
            pstmt.setString(7, email);
            pstmt.setString(8, role);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Employee saved successfully!\n\nUsername: " + username + "\nPassword: " + password, 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                clearFields();
                loadEmployeesFromDatabase();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to save employee!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Error saving employee: " + e.getMessage() + 
                "\n\nMake sure the database has the required columns: gender, age, contact, email", 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private int getNextUserId() {
        String query = "SELECT MAX(user_id) as max_id FROM users";
        
        try (Connection conn = SQLiteConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            if (rs.next()) {
                int maxId = rs.getInt("max_id");
                return maxId + 1;
            }
            return 1;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    private void editEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select an employee to edit!", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String userId = (String) tableModel.getValueAt(selectedRow, 0);
        String fullName = nameField.getText().trim();
        String gender = genderField.getText().trim();
        String age = ageField.getText().trim();
        String contact = contactField.getText().trim();
        String email = emailField.getText().trim();
        String role = (String) roleComboBox.getSelectedItem();
        
        if (fullName.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Full name is required!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String firstName = fullName.split("\\s+")[0];
        
        String username = String.format("%s%s", userId, firstName.toLowerCase());
        String password = role + firstName;
        
        String updateQuery = "UPDATE users SET username = ?, password = ?, full_name = ?, gender = ?, age = ?, contact = ?, email = ?, role = ? WHERE user_id = ?";
        
        try (Connection conn = SQLiteConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, fullName);
            pstmt.setString(4, gender);
            pstmt.setString(5, age);
            pstmt.setString(6, contact);
            pstmt.setString(7, email);
            pstmt.setString(8, role);
            pstmt.setInt(9, Integer.parseInt(userId));
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Employee updated successfully!\n\nNew Username: " + username + "\nNew Password: " + password, 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                clearFields();
                loadEmployeesFromDatabase();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to update employee!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Error updating employee: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select an employee to delete!", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String userId = (String) tableModel.getValueAt(selectedRow, 0);
        String username = (String) tableModel.getValueAt(selectedRow, 1);
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete user: " + username + "?\n\nThis will revoke all their access privileges.", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm != JOptionPane.YES_OPTION) return;
        
        String deleteQuery = "DELETE FROM users WHERE user_id = ?";
        
        try (Connection conn = SQLiteConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
            
            pstmt.setInt(1, Integer.parseInt(userId));
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Employee deleted successfully!\n\nAll access privileges have been revoked.", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                clearFields();
                loadEmployeesFromDatabase();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to delete employee!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Error deleting employee: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void populateFieldsFromTable() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) {
            nameField.setText((String) tableModel.getValueAt(selectedRow, 2));
            genderField.setText((String) tableModel.getValueAt(selectedRow, 3));
            ageField.setText((String) tableModel.getValueAt(selectedRow, 4));
            contactField.setText((String) tableModel.getValueAt(selectedRow, 5));
            emailField.setText((String) tableModel.getValueAt(selectedRow, 6));
            
            // Load role from database
            String userId = (String) tableModel.getValueAt(selectedRow, 0);
            String query = "SELECT role FROM users WHERE user_id = ?";
            
            try (Connection conn = SQLiteConnect.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                
                pstmt.setInt(1, Integer.parseInt(userId));
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    String role = rs.getString("role");
                    roleComboBox.setSelectedItem(role);
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void clearFields() {
        nameField.setText("");
        genderField.setText("");
        ageField.setText("");
        contactField.setText("");
        emailField.setText("");
        roleComboBox.setSelectedIndex(0);
        employeeTable.clearSelection();
    }
}