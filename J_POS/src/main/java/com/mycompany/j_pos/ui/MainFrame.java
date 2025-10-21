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
     private CardLayout cardLayout;
     private JPanel mainPanel;
     
     public MainFrame(){
         initializeFrame();
         setupUI();
         setIconImage(Icons.getInstance().getAppIcon().getImage());
         this.setVisible(true);
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
         mainPanel.add(new LoginUI(this), "LOGIN");
         mainPanel.add(new CashierUI(), "CASHIER");
         
         this.setContentPane(mainPanel);
         changeCard("LOGIN");
     }
     
     public void changeCard(String text){
         cardLayout.show(mainPanel, text);
     }
}
