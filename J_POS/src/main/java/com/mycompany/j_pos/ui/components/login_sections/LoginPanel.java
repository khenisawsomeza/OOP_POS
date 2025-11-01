package com.mycompany.j_pos.ui.components.login_sections;

import com.mycompany.j_pos.facade.POSFacade;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.components.login_sections.login_panel_components.InputFieldsPanel;
import com.mycompany.j_pos.ui.components.login_sections.login_panel_components.LabelsPanel;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel{

    private final themeManager theme = themeManager.getInstance();
    static private LoginPanel instance;
    
    
    private JPanel textContainer;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private InputFieldsPanel inputFieldsPanel;
    private POSFacade facade = POSFacade.getInstance();
    
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    static public LoginPanel getInstance(){
        if (instance == null) instance = new LoginPanel();
        return instance;
    }

    public LoginPanel() {
        initializeUI();
        addComponents();
        SwingUtilities.invokeLater(() -> textContainer.requestFocusInWindow());
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension((int) (screenSize.width * 0.4), screenSize.height));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
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
        textContainer.add(createLoginButton(), BorderLayout.CENTER);

        add(textContainer, BorderLayout.CENTER);
    }

    private JPanel createLoginButton() {
        JPanel loginPanel = new PanelBuilder().withLayout(new BorderLayout())
                                              .withSize(350, 40)
                                              .withBackground(theme.getLightGreenColor())
                                              .withCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
                                              .onHoverEnter(panel -> panel.setBackground(theme.getStaticLightGreenDM()))
                                              .onHoverExit(panel -> panel.setBackground(theme.getLightGreenColor()))
                                              .onPress(panel -> panel.setBackground(theme.getStaticPrimaryGreenDM()))
                                              .onRelease(panel -> {
                                                  if (panel.getMousePosition() != null)
                                                      panel.setBackground(theme.getStaticLightGreenDM());
                                                  else
                                                      panel.setBackground(theme.getLightGreenColor());
                                              })
                                              .onClick(this::handleLogin)
                                              .build();
                                
        // Label for text
        JLabel loginLabel = new JLabel("Login", SwingConstants.CENTER);
        loginLabel.setFont(new Font(AppConstants.DEFAULT_FONT, Font.BOLD, 16));
        loginLabel.setForeground(Color.WHITE);

        // Add label centered
        loginPanel.add(loginLabel, BorderLayout.CENTER);
        loginPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return loginPanel;
    }

    private void handleLogin() {
        String username = inputFieldsPanel.getUsername();
        String password = new String(inputFieldsPanel.getPassword());

        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Please enter both username and password",
                "Login Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        facade.login(username, password);
    }
}