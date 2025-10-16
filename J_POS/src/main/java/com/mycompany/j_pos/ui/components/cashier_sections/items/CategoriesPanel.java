package com.mycompany.j_pos.ui.components.cashier_sections.items;

import com.mycompany.j_pos.models.Category;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
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
                    .withBackground(themeManager.getInstance().getStaticLightGreenLM())
                    .withForeground(themeManager.getInstance().getTextForeground())
                    .withCursor(new Cursor(Cursor.HAND_CURSOR))
                    .withBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20))
                    .withFont(Font.BOLD, 20)
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
        
        JLabel imageLabel = new LabelBuilder()
                .withAlignment(SwingConstants.CENTER, SwingConstants.CENTER)
                .withIcon(themeManager.getInstance().getPlusIcon(), 32, 32)
                .build();
            
        JPanel newCategoryPanel = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withSize(120, 50)
                .withBackground(themeManager.getInstance().getLightGreenColor())
                .withCursor(new Cursor(Cursor.HAND_CURSOR))
                .withBorder(BorderFactory.createDashedBorder(themeManager.getInstance().getTextForeground(), 2, 10, 5, true))
                .onHoverEnter(panel -> {
                    System.out.println("test2");
                    panel.setBackground(themeManager.getInstance().getStaticPrimaryGreenLM());
                })
                .onHoverExit(panel -> {
                    System.out.println("test");
                    panel.setBackground(themeManager.getInstance().getLightGreenColor());
                })
                .build();
        
        newCategoryPanel.add(imageLabel, BorderLayout.CENTER);
        buttonsPanel.add(newCategoryPanel);
        
        
        add(buttonsPanel, BorderLayout.CENTER);
    }
}
