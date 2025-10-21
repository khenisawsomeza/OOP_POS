package com.mycompany.j_pos.ui.components.login_sections.items;

import com.mycompany.j_pos.ui.MainFrame;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.*;
import javax.swing.*;

public class LoginPanel extends JPanel {
    private JPanel textContainer;
    private MainFrame mainFrame; // reference to MainFrame

    public LoginPanel(MainFrame frame) {
        this.mainFrame = frame; // ← assign the reference
        initializeLogin();
        addComponents();
        SwingUtilities.invokeLater(() -> textContainer.requestFocusInWindow());
    }

    private void initializeLogin() {
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension((int) (screensize.width * 0.4), screensize.height));
        setBackground(themeManager.getInstance().getLightGrayColor());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    private void addComponents() {
        textContainer = new PanelBuilder()
                .withBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0))
                .transparent()
                .build();
        textContainer.setLayout(new BoxLayout(textContainer, BoxLayout.Y_AXIS));

        textContainer.add(Box.createRigidArea(new Dimension(0, 100)));

        JLabel chachingLabel = new LabelBuilder()
                .withText("Cha-Ching!")
                .withFont(Font.BOLD, 30)
                .withForeground(themeManager.getInstance().getTextForeground())
                .withAlignment(SwingConstants.LEFT, SwingConstants.CENTER)
                .build();
        textContainer.add(chachingLabel);

        textContainer.add(Box.createRigidArea(new Dimension(0, 75)));
        initializeStoreLabel();
        textContainer.add(Box.createRigidArea(new Dimension(0, 50)));
        initializeInfoPlacement();

        add(textContainer, BorderLayout.CENTER);
    }

    private void initializeStoreLabel() {
        JLabel storeLabel = new LabelBuilder()
                .withText("Store Login")
                .withFont(Font.BOLD, 30)
                .withForeground(themeManager.getInstance().getTextForeground())
                .withAlignment(SwingConstants.LEFT, SwingConstants.CENTER)
                .build();

        JLabel storesSubtextLabel = new LabelBuilder()
                .withText("Enter your credentials to access the POS system.")
                .withFont(Font.PLAIN, 15)
                .withForeground(themeManager.getInstance().getTextForeground())
                .withAlignment(SwingConstants.LEFT, SwingConstants.CENTER)
                .build();

        textContainer.add(storeLabel);
        textContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        textContainer.add(storesSubtextLabel);
    }

    private void initializeInfoPlacement() {
        // Username field
        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font(AppConstants.DEFAULT_FONT, Font.PLAIN, 14));
        usernameField.setMaximumSize(new Dimension(350, 35));
        usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                usernameField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        String usernamePlaceholder = "Username";
        usernameField.setForeground(Color.GRAY);
        usernameField.setText(usernamePlaceholder);
        usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (usernameField.getText().equals(usernamePlaceholder)) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText(usernamePlaceholder);
                    usernameField.setForeground(Color.GRAY);
                }
            }
        });

        // Password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font(AppConstants.DEFAULT_FONT, Font.PLAIN, 14));
        passwordField.setMaximumSize(new Dimension(350, 35));
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                passwordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        String passwordPlaceholder = "Password";
        passwordField.setEchoChar((char) 0); // show placeholder
        passwordField.setForeground(Color.GRAY);
        passwordField.setText(passwordPlaceholder);
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals(passwordPlaceholder)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('•');
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(passwordPlaceholder);
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setEchoChar((char) 0);
                }
            }
        });

        // Add fields to container
        textContainer.add(Box.createRigidArea(new Dimension(0, 5)));
        textContainer.add(usernameField);
        textContainer.add(Box.createRigidArea(new Dimension(0, 15)));
        textContainer.add(passwordField);

        // LOGIN BUTTON
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font(AppConstants.DEFAULT_FONT, Font.BOLD, 16));
        loginButton.setPreferredSize(new Dimension(350, 40));
        loginButton.setMaximumSize(new Dimension(350, 40));
        loginButton.setMinimumSize(new Dimension(350, 40));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(themeManager.getInstance().getLightGreenColor());
        loginButton.setOpaque(true);
        loginButton.setContentAreaFilled(true); // Let custom background show
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Hover and press effects
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                loginButton.setBackground(themeManager.getInstance().getStaticLightGreenDM());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                loginButton.setBackground(themeManager.getInstance().getLightGreenColor());
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                loginButton.setBackground(themeManager.getInstance().getStaticPrimaryGreenDM());
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                // Check if mouse is still over button
                Point mousePos = loginButton.getMousePosition();
                if (mousePos != null && loginButton.contains(mousePos)) {
                    loginButton.setBackground(themeManager.getInstance().getStaticLightGreenDM());
                } else {
                    loginButton.setBackground(themeManager.getInstance().getLightGreenColor());
                }
            }
        });

        // Action to switch card
        loginButton.addActionListener(e -> mainFrame.changeCard("CASHIER"));

        // Add spacing and button
        textContainer.add(Box.createRigidArea(new Dimension(0, 25)));
        textContainer.add(loginButton);

    }
}
