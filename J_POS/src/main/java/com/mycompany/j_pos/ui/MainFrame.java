/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui;

import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.components.CashierUI;
import com.mycompany.j_pos.ui.components.LoginUI;
import com.mycompany.j_pos.ui.utils.LoadResources;
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
     private JPanel navigation;
     private JPanel displayPanel;
     
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
         try {
            LoadResources.loadItems();
        } catch (Exception e){
            System.out.println("Failed to Load items");
        }
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setResizable(true);
         this.setTitle(AppConstants.APP_TITLE);
         
         Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
         this.setSize(screensize.width, screensize.height);
     }
     
     private void setupUI(){
         mainPanel = new PanelBuilder()
                 .withLayout(new BorderLayout())
                 .build();
         
         navigation = new Navigation();
         
         cardLayout = new CardLayout();
         displayPanel = new JPanel(cardLayout);
         displayPanel.add(new LoginUI(), "LOGIN");
         
         changeCard("LOGIN");
         
         mainPanel.add(navigation, BorderLayout.WEST);
         mainPanel.add(displayPanel, BorderLayout.CENTER);
         
         this.setContentPane(mainPanel);
     }
     
     public void addCashierUI(){
         displayPanel.add(new CashierUI(), "CASHIER");
     }
     
     public void changeCard(String text){
         cardLayout.show(displayPanel, text);
     }
}
