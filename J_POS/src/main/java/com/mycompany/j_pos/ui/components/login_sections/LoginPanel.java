package com.mycompany.j_pos.ui.components.login_sections;

import com.mycompany.j_pos.ui.MainFrame;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.components.login_sections.login_panel_components.InputFieldsPanel;
import com.mycompany.j_pos.ui.components.login_sections.login_panel_components.LabelsPanel;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;

/**
 * LoginPanel – Handles the login UI layout and interactions.
 */
public class LoginPanel extends JPanel implements themeManager.ThemeChangeListener {

    private final themeManager theme = themeManager.getInstance();
    private JPanel textContainer;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    
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
        textContainer.add(new InputFieldsPanel());
        textContainer.add(Box.createRigidArea(new Dimension(0, 25)));
        textContainer.add(createLoginButton());

        add(textContainer, BorderLayout.CENTER);
    }

    /** Adds login button and hover behavior */
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

        // Hover effects
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

        // Action listener
        loginButton.addActionListener(e -> MainFrame.getInstance().changeCard("CASHIER"));
        
        return loginButton;
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

