package com.mycompany.j_pos.ui.components.cashier_sections.items;

import com.mycompany.j_pos.ui.builders.ButtonBuilder;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.factories.FieldFactory;
import com.mycompany.j_pos.ui.factories.PanelFactory;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.*;
import javax.swing.*;

public class HeaderPanel extends JPanel {
    
    public HeaderPanel() {
        initializeHeader();
    }
    
    //Initialize the header panel with all components
    private void initializeHeader() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(780, 50));
        setBackground(themeManager.getInstance().getLightGreenColor());
        setName("lightGreenPanel");
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0 ,20));
        
        // Add logo on the right
        addLogoSection();
        
        // Add navigation buttons on the left
        addNavigationSection();
    }
    
    //Create and add the logo section
    private void addLogoSection() {
//        JLabel companyLogoLabel = LabelFactory.createIconLabel(150, 30, SwingConstants.CENTER, SwingConstants.CENTER, themeManager.getInstance().getLogoIcon());
        JLabel companyLogoLabel = new LabelBuilder()
                .withIcon(themeManager.getInstance().getLogoIcon(), 150, 30)
                .withAlignment(SwingConstants.CENTER, SwingConstants.CENTER)
                .build();
        companyLogoLabel.setName("logoIcon");
        add(companyLogoLabel, BorderLayout.EAST);
    }
    
    
    //Create and add the navigation buttons section
    private void addNavigationSection() {
        JPanel navigationButtonsPanel = PanelFactory.createSectionPanel(new FlowLayout(FlowLayout.LEFT, 15, 10), new Dimension(700, 50)
        );
        
        //Menu
        JButton menuButton = new ButtonBuilder()
            .withSize(30, 30)
            .withIcon(themeManager.getInstance().getMenuIcon())
            .build();
        menuButton.setName("menuIcon");
        navigationButtonsPanel.add(menuButton);
        
        //Dark Mode
        JButton darkModeToggleButton = new ButtonBuilder()
            .withSize(30, 30)
            .withIcon(themeManager.getInstance().getDarkModeToggleIcon())
            .build();
        darkModeToggleButton.setName("darkModeToggleIcon");
        navigationButtonsPanel.add(darkModeToggleButton);
        darkModeToggleButton.addActionListener(e -> {
            themeManager.getInstance().toggleDarkMode();
        });

        //Search
        JButton searchButton = new ButtonBuilder()
            .withSize(30, 30)
            .withIcon(themeManager.getInstance().getSearchIcon())
            .build();
        searchButton.setName("searchIcon");
        navigationButtonsPanel.add(searchButton);

        JTextField searchField = FieldFactory.createTextField("Search Item");
        navigationButtonsPanel.add(searchField);
        searchField.setVisible(false);
        
        attachSearchToggle(searchButton, searchField, navigationButtonsPanel);
        
        add(navigationButtonsPanel, BorderLayout.WEST);
    }
    
    private void attachSearchToggle(JButton button, JTextField searchField, JPanel navigationButtonsPanel) {
        button.addActionListener(e -> {
            boolean newValue = !searchField.isVisible();
            searchField.setVisible(newValue);
            navigationButtonsPanel.revalidate();
            navigationButtonsPanel.repaint();
        });
    }
}
