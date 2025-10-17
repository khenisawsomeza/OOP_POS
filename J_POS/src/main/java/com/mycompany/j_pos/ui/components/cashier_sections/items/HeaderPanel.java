package com.mycompany.j_pos.ui.components.cashier_sections.items;

import com.mycompany.j_pos.ui.builders.ButtonBuilder;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.factories.FieldFactory;
import com.mycompany.j_pos.ui.utils.commons.Icons;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;

/**
 * HeaderPanel displays navigation and branding elements for the cashier UI.
 */
public class HeaderPanel extends JPanel implements themeManager.ThemeChangeListener {

    private final themeManager theme = themeManager.getInstance();

    private JPanel navigationPanel;
    private JLabel companyLogoLabel;
    private JButton menuButton;
    private JButton darkModeButton;
    private JButton searchButton;
    private JTextField searchField;

    public HeaderPanel() {
        initializePanel();
        theme.addThemeChangeListener(this);
    }

    /** Initializes layout and builds sections. */
    private void initializePanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(780, 50));
        setBackground(theme.getLightGreenColor());
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        buildNavigationSection();
        buildLogoSection();
    }

    /** Builds and adds the company logo section. */
    private void buildLogoSection() {
        companyLogoLabel = new LabelBuilder()
                .withIcon(theme.getLogoIcon(), 150, 30)
                .withAlignment(SwingConstants.CENTER, SwingConstants.CENTER)
                .build();

        add(companyLogoLabel, BorderLayout.EAST);
    }

    /** Builds the navigation area with buttons and search field. */
    private void buildNavigationSection() {
        navigationPanel = new PanelBuilder()
                .withLayout(new FlowLayout(FlowLayout.LEFT, 15, 10))
                .withSize(700, 50)
                .transparent()
                .build();

        menuButton = createIconButton(theme.getMenuIcon());
        navigationPanel.add(menuButton);

        darkModeButton = createIconButton(theme.getDarkModeToggleIcon());
        darkModeButton.addActionListener(e -> theme.toggleDarkMode());
        navigationPanel.add(darkModeButton);

        searchButton = createIconButton(theme.getSearchIcon());
        searchField = FieldFactory.createTextField("Search Item");
        searchField.setVisible(false);

        navigationPanel.add(searchButton);
        navigationPanel.add(searchField);

        attachSearchToggle(searchButton, searchField, navigationPanel);
        add(navigationPanel, BorderLayout.WEST);
    }

    /** Helper method to create icon buttons consistently. */
    private JButton createIconButton(ImageIcon icon) {
        return new ButtonBuilder()
                .withSize(30, 30)
                .withIcon(icon)
                .build();
    }

    /** Toggles search field visibility. */
    private void attachSearchToggle(JButton toggleButton, JTextField searchField, JPanel parentPanel) {
        toggleButton.addActionListener(e -> {
            searchField.setVisible(!searchField.isVisible());
            parentPanel.revalidate();
            parentPanel.repaint();
        });
    }

    /** Applies theme colors and icons directly. */
    private void applyTheme() {
        setBackground(theme.getLightGreenColor());
        navigationPanel.setBackground(theme.getLightGreenColor());

        companyLogoLabel.setIcon(Icons.getScaledIcon(theme.getLogoIcon(), 150, 30));
        menuButton.setIcon(Icons.getScaledIcon(theme.getMenuIcon(), 30, 30));
        darkModeButton.setIcon(Icons.getScaledIcon(theme.getDarkModeToggleIcon(), 30, 30));
        searchButton.setIcon(Icons.getScaledIcon(theme.getSearchIcon(), 30, 30));
    }

    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}
