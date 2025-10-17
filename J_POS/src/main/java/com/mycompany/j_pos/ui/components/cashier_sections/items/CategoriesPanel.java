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

    private final List<Category> availableCategories;
    private final themeManager theme = themeManager.getInstance();

    private JPanel buttonsPanel;
    private JPanel newCategoryPanel;
    private JLabel imageLabel;
    private final List<JLabel> categoryLabels = new ArrayList<>();

    public CategoriesPanel(List<Category> availableCategories) {
        this.availableCategories = availableCategories;
        initializePanel();
        applyTheme();
    }

    /** Initializes layout and builds all sections. */
    private void initializePanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1420, 50));
        setBackground(theme.getLightGreenColor());

        buildButtonsSection();
        add(buttonsPanel, BorderLayout.CENTER);
    }

    /** Builds the list of category buttons and the add-category panel. */
    private void buildButtonsSection() {
        buttonsPanel = new PanelBuilder()
                .withLayout(new FlowLayout(FlowLayout.LEFT, 20, 0))
                .build();

        for (Category cat : availableCategories) {
            JLabel label = createCategoryLabel(cat.getName());
            buttonsPanel.add(label);
        }

        buildNewCategoryButton();
    }

    /** Creates a label for a specific category. */
    private JLabel createCategoryLabel(String name) {
        JLabel label = new LabelBuilder()
                .withText(name)
                .withCursor(new Cursor(Cursor.HAND_CURSOR))
                .withBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20))
                .withFont(Font.BOLD, 20)
                .build();

        // Hover effects
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                label.setBackground(theme.getPrimaryGreenColor());
                label.setOpaque(true);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                label.setBackground(theme.getLightGreenColor());
                label.setOpaque(true);
            }
        });

        categoryLabels.add(label);

        return label;
    }

    /** Builds the "Add Category" panel with plus icon. */
    private void buildNewCategoryButton() {
        imageLabel = new LabelBuilder()
                .withAlignment(SwingConstants.CENTER, SwingConstants.CENTER)
                .build();

        newCategoryPanel = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withSize(120, 50)
                .withCursor(new Cursor(Cursor.HAND_CURSOR))
                .withBorder(BorderFactory.createDashedBorder(theme.getTextForeground(), 2, 10, 5, true))
                .onHoverEnter(panel -> panel.setBackground(theme.getStaticPrimaryGreenLM()))
                .onHoverExit(panel -> panel.setBackground(theme.getLightGreenColor()))
                .build();

        newCategoryPanel.add(imageLabel, BorderLayout.CENTER);
        buttonsPanel.add(newCategoryPanel);
    }

    /** Applies theme colors and icons directly. */
    private void applyTheme() {
        theme.addThemeChangeListener(this);
        
        setBackground(theme.getLightGreenColor());
        buttonsPanel.setBackground(theme.getLightGreenColor());
        newCategoryPanel.setBackground(theme.getLightGreenColor());
        newCategoryPanel.setBorder(BorderFactory.createDashedBorder(theme.getTextForeground(), 2, 10, 5, true));
        
        for (JLabel label : categoryLabels) {
            label.setBackground(theme.getLightGreenColor());
            label.setForeground(theme.getTextForeground());
            
            label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                label.setForeground(theme.getStaticBlack());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                label.setForeground(theme.getTextForeground());
            }
        });
        }

        imageLabel.setIcon(Icons.getScaledIcon(theme.getPlusIcon(), 30, 30));
    }

    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}
