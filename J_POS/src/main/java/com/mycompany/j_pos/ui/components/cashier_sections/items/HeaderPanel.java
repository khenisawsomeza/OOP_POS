 package com.mycompany.j_pos.ui.components.cashier_sections.items;

import com.mycompany.j_pos.ui.builders.ButtonBuilder;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.factories.FieldFactory;
import com.mycompany.j_pos.ui.utils.commons.Icons;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import java.awt.*;
import javax.swing.*;

/**
 * HeaderPanel displays navigation and branding elements for the cashier UI.
 */
public class HeaderPanel extends JPanel implements themeManager.ThemeChangeListener{

    private final themeManager theme = themeManager.getInstance();
    JLabel companyLogoLabel;
    JButton menuButton;
    JButton darkModeButton;
    JButton searchButton;

    public HeaderPanel() {
        initializeHeader();
        theme.addThemeChangeListener(this);
    }

    /**
     * Initializes the header layout and adds major UI sections.
     */
    private void initializeHeader() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(780, 50));
        setBackground(theme.getLightGreenColor());
        setName("lightGreenPanel");
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        addNavigationSection();
        addLogoSection();
    }

    /**
     * Builds and adds the logo section to the right side.
     */
    private void addLogoSection() {
        companyLogoLabel = new LabelBuilder()
                .withIcon(theme.getLogoIcon(), 150, 30)
                .withAlignment(SwingConstants.CENTER, SwingConstants.CENTER)
                .build();
        companyLogoLabel.setName("logoIcon");

        add(companyLogoLabel, BorderLayout.EAST);
    }

    /**
     * Builds and adds the navigation buttons and search components.
     */
    private void addNavigationSection() {
        JPanel navigationPanel = new PanelBuilder()
                .withLayout(new FlowLayout(FlowLayout.LEFT, 15, 10))
                .withSize(700, 50)
                .transparent()
                .build();

        // Menu Button
        menuButton = createIconButton(theme.getMenuIcon());
        navigationPanel.add(menuButton);

        // Dark Mode Toggle
        darkModeButton = createIconButton(theme.getDarkModeToggleIcon());
        darkModeButton.addActionListener(e -> theme.toggleDarkMode());
        navigationPanel.add(darkModeButton);

        // Search Button + Field
        searchButton = createIconButton(theme.getSearchIcon());
        JTextField searchField = FieldFactory.createTextField("Search Item");
        searchField.setVisible(false);
        navigationPanel.add(searchButton);
        navigationPanel.add(searchField);

        attachSearchToggle(searchButton, searchField, navigationPanel);

        add(navigationPanel, BorderLayout.WEST);
    }

    /**
     * Helper method to create standardized icon buttons.
     */
    private JButton createIconButton(ImageIcon icon) {
        JButton button = new ButtonBuilder()
                .withSize(30, 30)
                .withIcon(icon)
                .build();
        return button;
    }

    /**
     * Toggles visibility of the search field when the search button is pressed.
     */
    private void attachSearchToggle(JButton toggleButton, JTextField searchField, JPanel parentPanel) {
        toggleButton.addActionListener(e -> {
            searchField.setVisible(!searchField.isVisible());
            parentPanel.revalidate();
            parentPanel.repaint();
        });
    }

    @Override
    public void onThemeChange(boolean isDarkMode) {
        setBackground(theme.getLightGreenColor());
        companyLogoLabel.setIcon(Icons.getScaledIcon(theme.getLogoIcon(), 150, 30));
        menuButton.setIcon(Icons.getScaledIcon(theme.getMenuIcon(), 30, 30));
        darkModeButton.setIcon(Icons.getScaledIcon(theme.getDarkModeToggleIcon(), 30, 30));
        searchButton.setIcon(Icons.getScaledIcon(theme.getSearchIcon(), 30, 30));
    }
}
