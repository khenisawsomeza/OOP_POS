package com.mycompany.j_pos.ui.components.cashier_sections.items;

import com.mycompany.j_pos.models.Category;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.factories.PanelFactory;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CategoriesPanel extends JPanel {
    private final List<Category> availableCategories;

    public CategoriesPanel(List<Category> availableCategories) {
        this.availableCategories = availableCategories;
        initializeCategories();
    }

    private void initializeCategories() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1420, 50));
        setBackground(themeManager.getInstance().getLightGreenColor());
        setName("lightGreenPanel");

        JPanel buttonsPanel = PanelFactory.createPanel(
                new FlowLayout(FlowLayout.LEFT, 20, 0),
                null,
                themeManager.getInstance().getLightGreenColor()
        );
        buttonsPanel.setMaximumSize(new Dimension(500, 50));

        for (Category cat : availableCategories) {
            buttonsPanel.add(new LabelBuilder().withText(cat.getName()).build());
        }

        buttonsPanel.add(PanelFactory.addNewCategoryPanel());
        add(buttonsPanel, BorderLayout.CENTER);
    }
}
