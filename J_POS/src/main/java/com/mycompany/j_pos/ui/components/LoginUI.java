package com.mycompany.j_pos.ui.components;

import com.mycompany.j_pos.models.User;
import com.mycompany.j_pos.ui.MainFrame;
import com.mycompany.j_pos.ui.components.login_sections.items.LoginPanel;
import com.mycompany.j_pos.ui.components.login_sections.items.WelcomePanel;
import java.awt.*;
import javax.swing.*;

public class LoginUI extends JPanel {
    private User user;
    private MainFrame mainFrame;
    
    // Constructor that receives MainFrame
    public LoginUI(MainFrame mainFrame) {
        this.mainFrame = mainFrame; // assign reference
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
        add(welcomePanel, BorderLayout.WEST);
        
        LoginPanel loginPanel = new LoginPanel(mainFrame); // now not null
        add(loginPanel, BorderLayout.EAST);    
    }
    
}
