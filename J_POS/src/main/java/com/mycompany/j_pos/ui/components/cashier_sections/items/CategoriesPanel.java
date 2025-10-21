package com.mycompany.j_pos.ui.components.cashier_sections.items;

import com.mycompany.j_pos.models.Category;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.commons.Icons;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesPanel extends JPanel implements themeManager.ThemeChangeListener {

    private static final Dimension PANEL_SIZE = new Dimension(1420, 50);
    private static final int CATEGORY_FONT_SIZE = 20;

    private final List<Category> availableCategories;
    private final themeManager theme = themeManager.getInstance();

    private JPanel buttonsPanel;
    private JPanel newCategoryPanel;
    private JLabel addCategoryIcon;
    private final List<JLabel> categoryLabels = new ArrayList<>();

    public CategoriesPanel(List<Category> availableCategories) {
        this.availableCategories = availableCategories;
        initializePanel();
        applyTheme();
    }

    private void initializePanel() {
        theme.addThemeChangeListener(this);
        
        setLayout(new BorderLayout());
        setPreferredSize(PANEL_SIZE);

        buildCategoryButtonsPanel();
        add(buttonsPanel, BorderLayout.CENTER);
    }
    
    // builds the buttons section
    private void buildCategoryButtonsPanel() {
        buttonsPanel = new PanelBuilder()
                .withLayout(new FlowLayout(FlowLayout.LEFT, 20, 0))
                .build();

        availableCategories.forEach(category -> 
            buttonsPanel.add(createCategoryLabel(category.getName()))
        );

        buttonsPanel.add(createAddCategoryButton());
    }

    // creates resuable category labels
    private JLabel createCategoryLabel(String name) {
        JLabel label = new LabelBuilder()
                .withText(name)
                .withCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
                .withBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20))
                .withFont(Font.BOLD, CATEGORY_FONT_SIZE)
                .build();

        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                label.setBackground(theme.getPrimaryGreenColor());
                label.setOpaque(true);
                label.setForeground(theme.getStaticBlack());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                label.setBackground(theme.getLightGreenColor());
                label.setOpaque(true);
                label.setForeground(theme.getTextForeground());
            }
        });

        categoryLabels.add(label);
        return label;
    }

    // creates the add new category label
    private JPanel createAddCategoryButton() {
        addCategoryIcon = new LabelBuilder()
                .withAlignment(SwingConstants.CENTER, SwingConstants.CENTER)
                .build();

        newCategoryPanel = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withSize(120, 40)
                .withCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
                .onHoverEnter(panel -> panel.setBackground(theme.getStaticPrimaryGreenLM()))
                .onHoverExit(panel -> panel.setBackground(theme.getLightGreenColor()))
                .build();

        newCategoryPanel.add(addCategoryIcon, BorderLayout.CENTER);
        return newCategoryPanel;
    }

    // apply the current theme
    private void applyTheme() {
        setBackground(theme.getLightGreenColor());
        buttonsPanel.setBackground(theme.getLightGreenColor());

        if (newCategoryPanel != null) {
            newCategoryPanel.setBackground(theme.getLightGreenColor());
            newCategoryPanel.setBorder(BorderFactory.createDashedBorder(
                    theme.getTextForeground(), 2, 10, 5, true));
        }

        if (addCategoryIcon != null) {
            addCategoryIcon.setIcon(Icons.getScaledIcon(theme.getPlusIcon(), 30, 30));
        }

        categoryLabels.forEach(label -> {
            label.setBackground(theme.getLightGreenColor());
            label.setForeground(theme.getTextForeground());
        });
    }

    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}
