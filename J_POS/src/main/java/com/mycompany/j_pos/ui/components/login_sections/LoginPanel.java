package com.mycompany.j_pos.ui.components.login_sections;

import com.mycompany.j_pos.SQLiteConnect;
import com.mycompany.j_pos.ui.MainFrame;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.components.login_sections.login_panel_components.InputFieldsPanel;
import com.mycompany.j_pos.ui.components.login_sections.login_panel_components.LabelsPanel;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPanel extends JPanel implements themeManager.ThemeChangeListener {

    private final themeManager theme = themeManager.getInstance();
    private JPanel textContainer;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private InputFieldsPanel inputFieldsPanel;
    
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public LoginPanel() {
        theme.addThemeChangeListener(this);
        initializeUI();
        addComponents();
        SwingUtilities.invokeLater(() -> textContainer.requestFocusInWindow());
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension((int) (screenSize.width * 0.4), screenSize.height));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        applyTheme();
    }

    private void addComponents() {
        textContainer = new PanelBuilder()
                .withBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0))
                .transparent()
                .build();
        textContainer.setLayout(new BoxLayout(textContainer, BoxLayout.Y_AXIS));

        textContainer.add(Box.createRigidArea(new Dimension(0, 100)));
        textContainer.add(new LabelsPanel());
        textContainer.add(Box.createRigidArea(new Dimension(0, 55)));
        
        inputFieldsPanel = new InputFieldsPanel();
        textContainer.add(inputFieldsPanel);
        
        textContainer.add(Box.createRigidArea(new Dimension(0, 25)));
        textContainer.add(createLoginButton());

        add(textContainer, BorderLayout.CENTER);
    }

    private JButton createLoginButton() {
        loginButton = new JButton("Login");
        loginButton.setFont(new Font(AppConstants.DEFAULT_FONT, Font.BOLD, 16));
        loginButton.setPreferredSize(new Dimension(350, 40));
        loginButton.setMaximumSize(new Dimension(350, 40));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(theme.getLightGreenColor());
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                loginButton.setBackground(theme.getStaticLightGreenDM());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                loginButton.setBackground(theme.getLightGreenColor());
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {     
                loginButton.setBackground(theme.getStaticPrimaryGreenDM());
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (loginButton.getMousePosition() != null)
                    loginButton.setBackground(theme.getStaticLightGreenDM());
                else
                    loginButton.setBackground(theme.getLightGreenColor());
            }
        });

        loginButton.addActionListener(e -> handleLogin());
        
        return loginButton;
    }

    private void handleLogin() {
        String username = inputFieldsPanel.getUsername();
        String password = new String(inputFieldsPanel.getPassword());

        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter both username and password",
                "Login Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        authenticateUser(username, password);
    }

    private void authenticateUser(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = SQLiteConnect.getConnection();
            
            if (conn == null) {
                JOptionPane.showMessageDialog(this,
                    "Database connection failed",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String sql = "SELECT user_id, username, role FROM users WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String userRole = rs.getString("role");
                String userName = rs.getString("username");
                
                System.out.println("Login successful: " + userName + " (Role: " + userRole + ")");
                
                // For now, both admin and employee go to CASHIER
                // Future: Add role-based navigation here
                if ("admin".equalsIgnoreCase(userRole)) {
                    MainFrame.getInstance().changeCard("CASHIER");
                } else if ("employee".equalsIgnoreCase(userRole)) {
                    MainFrame.getInstance().changeCard("CASHIER");
                } else {
                    // custom role if want to add or if gusto kay invalid ang unknown roles
                    MainFrame.getInstance().changeCard("CASHIER");
                }
                
                inputFieldsPanel.clearPassword();
                
            } else {
                JOptionPane.showMessageDialog(this,
                    "Invalid username or password",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
                inputFieldsPanel.clearPassword();
            }
            
        } catch (SQLException e) {
            System.err.println("SQL Error during authentication: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                "An error occurred during login",
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing database resources: " + e.getMessage());
            }
        }
    }

    private void applyTheme() {
        setBackground(theme.getLightGrayColor());
        if (loginButton != null) loginButton.setBackground(theme.getLightGreenColor());
        if (usernameField != null) usernameField.setBackground(Color.WHITE);
        if (passwordField != null) passwordField.setBackground(Color.WHITE);
    }

    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}