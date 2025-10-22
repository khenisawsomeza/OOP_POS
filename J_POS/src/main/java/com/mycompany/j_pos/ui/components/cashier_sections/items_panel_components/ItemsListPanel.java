package com.mycompany.j_pos.ui.components.cashier_sections.items_panel_components;

import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.models.Item;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.commons.Icons;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import com.mycompany.j_pos.ui.utils.layouts.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ItemsListPanel extends JPanel implements themeManager.ThemeChangeListener {

    private static final int CARD_WIDTH = 220;
    private static final int CARD_HEIGHT = 170;

    private final List<Item> availableItems;
    private final themeManager theme = themeManager.getInstance();

    private JPanel itemsListPanel;
    private JPanel addItemPanel;
    private JLabel addItemIcon;

    public ItemsListPanel(List<Item> availableItems) {
        this.availableItems = availableItems;

        initializeComponents();
        buildLayout();
        applyTheme();
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

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
        refreshItemsDisplay();
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
                    panel.setBorder(null);
                })
                .onHoverExit(panel -> {
                    panel.setBackground(theme.getLightGrayColor());
                    panel.setBorder(BorderFactory.createDashedBorder(
                            theme.getTextForeground(), 2, 10, 5, true));
                })
                .onClick(() -> System.out.println("New item clicked"))
                .build();

        addItemPanel.add(addItemIcon, BorderLayout.CENTER);
        return addItemPanel;
    }
    
    //refreshes the items lists display
    public void refreshItemsDisplay() {
        itemsListPanel.removeAll();
        availableItems.forEach(item ->
                itemsListPanel.add(new ItemCard(item))
        );
        itemsListPanel.add(createAddNewItemCard());
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
