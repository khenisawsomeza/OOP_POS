/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui;

import com.mycompany.j_pos.ui.components.CashierUI;
import com.mycompany.j_pos.ui.components.LoginUI;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import com.mycompany.j_pos.ui.utils.commons.Icons;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author khenshi
 */
public class MainFrame extends JFrame{
    
     private static MainFrame instance;
     private CardLayout cardLayout;
     private JPanel mainPanel;
     
     private MainFrame(){
         initializeFrame();
         setupUI();
         setIconImage(Icons.getInstance().getAppIcon().getImage());
         this.setVisible(true);
     }
     
     public static MainFrame getInstance(){
         if (instance == null) instance = new MainFrame();
         return instance;
     }
     
     private void initializeFrame(){
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setResizable(true);
         this.setTitle(AppConstants.APP_TITLE);
         
         Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
         this.setSize(screensize.width, screensize.height);
     }
     
     private void setupUI(){
         cardLayout = new CardLayout();
         mainPanel = new JPanel(cardLayout);
         mainPanel.add(new LoginUI(), "LOGIN");
         mainPanel.add(new CashierUI(), "CASHIER");
         
         this.setContentPane(mainPanel);
         changeCard("LOGIN");
     }
     
     public void changeCard(String text){
         cardLayout.show(mainPanel, text);
     }
}
