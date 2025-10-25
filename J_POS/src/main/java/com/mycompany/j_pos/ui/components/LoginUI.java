package com.mycompany.j_pos.ui.components;

import com.mycompany.j_pos.models.User;
import com.mycompany.j_pos.ui.MainFrame;
import com.mycompany.j_pos.ui.components.login_sections.LoginPanel;
import com.mycompany.j_pos.ui.components.login_sections.WelcomePanel;
import java.awt.*;
import javax.swing.*;

public class LoginUI extends JPanel {
    private User user;
    
    // Constructor 
    public LoginUI() {
        initializeDataModels();
        setupMainWindow();
        createComponents();
        setVisible(true);
    }
    
    private void initializeDataModels() {
        user = new User();
    }

    private void setupMainWindow() {
       setLayout(new BorderLayout());
    }
    
    private void createComponents() {
        WelcomePanel welcomePanel = new WelcomePanel();
        add(welcomePanel, BorderLayout.CENTER);
        
        LoginPanel loginPanel = new LoginPanel(); // now not null
        add(loginPanel, BorderLayout.EAST);    
    }
    
}
