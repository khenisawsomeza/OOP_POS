package com.mycompany.j_pos.ui.components.cashier_sections.items;

import com.mycompany.j_pos.models.Category;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
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
        
        
        JPanel buttonsPanel = new PanelBuilder()
                .withLayout(new FlowLayout(FlowLayout.LEFT, 20, 0))
                .withBackground(themeManager.getInstance().getLightGreenColor())
                .build();
        
        buttonsPanel.setMaximumSize(new Dimension(500, 50));

        for (Category cat : availableCategories) {
        JLabel label = new LabelBuilder()
        .withText(cat.getName())
        .withBackground(themeManager.getInstance().getStaticLightGreenLM()) // default color
        .onHoverEnter(lbl -> {
                    lbl.setOpaque(true);
                    lbl.setBackground(themeManager.getInstance().getStaticPrimaryGreenLM());
                })
                .onHoverExit(lbl -> {
                    lbl.setOpaque(true);
                    lbl.setBackground(themeManager.getInstance().getStaticLightGreenLM());
                })
                .build();

            buttonsPanel.add(label);
        }

        buttonsPanel.add(PanelFactory.addNewCategoryPanel());
        add(buttonsPanel, BorderLayout.CENTER);
    }
}
