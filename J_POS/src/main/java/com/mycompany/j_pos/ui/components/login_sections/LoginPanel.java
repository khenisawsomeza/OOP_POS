package com.mycompany.j_pos.ui.components.login_sections;

import com.mycompany.j_pos.ui.MainFrame;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
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
//        addTitleLabels();
//        textContainer.add(Box.createRigidArea(new Dimension(0, 75)));
//        addStoreLabels();
        textContainer.add(new LabelsPanel());
        textContainer.add(Box.createRigidArea(new Dimension(0, 50)));
        addInputFields();
        addLoginButton();

        add(textContainer, BorderLayout.CENTER);
    }

//    /** Adds main login title */
//    private void addTitleLabels() {
//        JLabel titleLabel = new LabelBuilder()
//                .withText("Cha-Ching!")
//                .withFont(Font.BOLD, 30)
//                .withForeground(theme.getTextForeground())
//                .withAlignment(SwingConstants.LEFT, SwingConstants.CENTER)
//                .build();
//
//        textContainer.add(titleLabel);
//    }
//
//    /** Adds subtext labels */
//    private void addStoreLabels() {
//        JLabel storeLabel = new LabelBuilder()
//                .withText("Store Login")
//                .withFont(Font.BOLD, 30)
//                .withForeground(theme.getTextForeground())
//                .withAlignment(SwingConstants.LEFT, SwingConstants.CENTER)
//                .build();
//
//        JLabel subTextLabel = new LabelBuilder()
//                .withText("Enter your credentials to access the POS system.")
//                .withFont(Font.PLAIN, 15)
//                .withForeground(theme.getTextForeground())
//                .withAlignment(SwingConstants.LEFT, SwingConstants.CENTER)
//                .build();
//
//        textContainer.add(storeLabel);
//        textContainer.add(Box.createRigidArea(new Dimension(0, 20)));
//        textContainer.add(subTextLabel);
//    }

    /** Adds username and password input fields with placeholders */
    private void addInputFields() {
        usernameField = buildPlaceholderField("Username");
        passwordField = buildPasswordField("Password");

        textContainer.add(Box.createRigidArea(new Dimension(0, 5)));
        textContainer.add(usernameField);
        textContainer.add(Box.createRigidArea(new Dimension(0, 15)));
        textContainer.add(passwordField);
    }

    /** Adds login button and hover behavior */
    private void addLoginButton() {
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

        textContainer.add(Box.createRigidArea(new Dimension(0, 25)));
        textContainer.add(loginButton);
    }

    /** Builds a styled placeholder text field */
    private JTextField buildPlaceholderField(String placeholder) {
        JTextField field = new JTextField(placeholder);
        field.setFont(new Font(AppConstants.DEFAULT_FONT, Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(350, 35));
        field.setForeground(Color.GRAY);
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setBorder(BorderFactory.createCompoundBorder(
                field.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });

        return field;
    }

    /** Builds a styled placeholder password field */
    private JPasswordField buildPasswordField(String placeholder) {
        JPasswordField field = new JPasswordField(placeholder);
        field.setFont(new Font(AppConstants.DEFAULT_FONT, Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(350, 35));
        field.setForeground(Color.GRAY);
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setBorder(BorderFactory.createCompoundBorder(
                field.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        field.setEchoChar((char) 0); // show placeholder

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    field.setEchoChar('•');
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (String.valueOf(field.getPassword()).isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                    field.setEchoChar((char) 0);
                }
            }
        });

        return field;
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

