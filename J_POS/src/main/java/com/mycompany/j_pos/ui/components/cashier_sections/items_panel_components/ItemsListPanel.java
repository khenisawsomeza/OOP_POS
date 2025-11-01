package com.mycompany.j_pos.ui.components.cashier_sections.items_panel_components;

import com.mycompany.j_pos.models.items.Item;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.LoadResources;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import com.mycompany.j_pos.ui.utils.commons.Icons;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import com.mycompany.j_pos.ui.utils.layouts.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ItemsListPanel extends JPanel implements themeManager.ThemeChangeListener {
    
    private static final int CARD_WIDTH = 220;
    private static final int CARD_HEIGHT = 170;

    private static List<Item> availableItems;
    private final themeManager theme = themeManager.getInstance();
    private static ItemsListPanel instance;

    private JPanel itemsListPanel;
    private JPanel addItemPanel;
    private JLabel addItemIcon;

    private ItemsListPanel() {
        try {
            availableItems = LoadResources.getItems();
        } catch (Exception e) {
            System.out.println("Unable to load availble items");
        }

        initializeComponents();
        buildLayout();
        applyTheme();
    }

    public static ItemsListPanel getInstance() {
        if (instance == null) instance = new ItemsListPanel();
        return instance;
    }

    private void initializeComponents() {
        theme.addThemeChangeListener(this);
        itemsListPanel = new PanelBuilder()
                .withLayout(new WrapLayout(FlowLayout.CENTER, 30, 30))
                .build();
    }

    // wrap layout with scroll
    private void buildLayout() {
        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(
                itemsListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
        refreshItemsDisplay(availableItems);
    }

    // creates the add new item card
    private JPanel createAddNewItemCard() {
        addItemIcon = new LabelBuilder()
                .withAlignment(JLabel.CENTER, JLabel.CENTER)
                .build();

        addItemPanel = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withSize(CARD_WIDTH, CARD_HEIGHT)
                .withCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
                .onHoverEnter(panel -> {
                    panel.setBackground(theme.getStaticPrimaryGreenLM());
                })
                .onHoverExit(panel -> {
                    panel.setBackground(theme.getLightGrayColor());
                    panel.setBorder(BorderFactory.createDashedBorder(
                            theme.getTextForeground(), 2, 10, 5, true));
                })
                .onClick(() -> System.out.println("New item clicked"))
                .onPress(panel -> panel.setBackground(theme.getStaticPrimaryGreenLM().darker()))
                .onRelease(panel -> panel.setBackground(theme.getStaticPrimaryGreenLM()))
                .build();

        addItemPanel.add(addItemIcon, BorderLayout.CENTER);
        return addItemPanel;
    }

    public void refreshItemsDisplay(List<Item> items) {
        items.forEach(item -> {
            System.out.println(item.getName());
        });
        itemsListPanel.removeAll();
        
        items.forEach(item -> itemsListPanel.add(new ItemCard(item)));
        if (AppConstants.isAdmin){
            itemsListPanel.add(createAddNewItemCard());
        }
        applyTheme();
        itemsListPanel.revalidate();
        itemsListPanel.repaint();
    }

    // apply the current theme
    private void applyTheme() {
        itemsListPanel.setBackground(theme.getLightGrayColor());

        if (addItemPanel != null && addItemIcon != null) {
            addItemPanel.setBackground(theme.getLightGrayColor());
            addItemPanel.setBorder(BorderFactory.createDashedBorder(
                    theme.getTextForeground(), 2, 10, 5, true));
            addItemIcon.setIcon(Icons.getScaledIcon(theme.getPlusIcon(), 64, 64));
        }
    }

    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}
