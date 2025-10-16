package com.mycompany.j_pos.ui.components.cashier_sections.items;

import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.models.Item;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ItemsGridPanel extends JPanel {
    private static final int CARD_WIDTH = 220;
    private static final int CARD_HEIGHT = 170;
    private static final int IMAGE_WIDTH = 200;
    private static final int IMAGE_HEIGHT = 120;

    private final Cart cart;
    private final CartPanelRefresher refresher;
    private final List<Item> availableItems;
    private JPanel itemsListPanel;

    public interface CartPanelRefresher {
        void refreshCartDisplay();
    }

    public ItemsGridPanel(Cart cart, CartPanelRefresher refresher, List<Item> availableItems) {
        this.cart = cart;
        this.refresher = refresher;
        this.availableItems = availableItems;
        initializeGrid();
    }

    private void initializeGrid() {
        setLayout(new BorderLayout());

        itemsListPanel = new PanelBuilder()
                .withLayout(new FlowLayout(FlowLayout.LEFT, 30, 30))
                .withSize(780, Integer.MAX_VALUE)
                .transparent()
                .build();

        add(itemsListPanel, BorderLayout.CENTER);
        refreshItemsDisplay();
    }

    public void refreshItemsDisplay() {
        itemsListPanel.removeAll();

        themeManager theme = themeManager.getInstance();

        for (Item item : availableItems) {
            itemsListPanel.add(createItemCard(item, theme));
        }

        // “Add new item” button
        JLabel imageLabel = new LabelBuilder()
                .withAlignment(SwingConstants.CENTER, SwingConstants.CENTER)
                .withIcon(theme.getPlusIcon(), 64, 64)
                .build();

        JPanel newItemPanel = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withSize(CARD_WIDTH, CARD_HEIGHT)
                .withBackground(theme.getLightGrayColor())
                .withBorder(BorderFactory.createDashedBorder(theme.getTextForeground(), 2, 10, 5, true))
                .withCursor(new Cursor(Cursor.HAND_CURSOR))
                .onHoverEnter(panel -> panel.setBackground(theme.getStaticPrimaryGreenLM()))
                .onHoverExit(panel -> panel.setBackground(theme.getLightGrayColor()))
                .build();

        newItemPanel.add(imageLabel, BorderLayout.CENTER);
        itemsListPanel.add(newItemPanel);

        itemsListPanel.revalidate();
        itemsListPanel.repaint();
    }

    private JPanel createItemCard(Item item, themeManager theme) {
        // Picture
        JLabel pictureLabel = new LabelBuilder()
                .withIcon(item.getPicture(), IMAGE_WIDTH, IMAGE_HEIGHT)
                .withAlignment(JLabel.CENTER, JLabel.CENTER)
                .build();

        JPanel picturePanel = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withBackground(theme.getStaticLightGreenLM())
                .withBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5))
                .build();
        picturePanel.add(pictureLabel, BorderLayout.CENTER);

        // Prompt
        JLabel promptLabel = new LabelBuilder()
                .withText("Add " + item.getName() + " to cart?")
                .withFont(Font.BOLD, 16)
                .withAlignment(JLabel.CENTER, JLabel.CENTER)
                .build();

        JPanel centerContainer = new PanelBuilder()
                .withLayout(new CardLayout())
                .withBackground(theme.getStaticLightGreenLM())
                .build();

        centerContainer.add(picturePanel, "PICTURE");
        centerContainer.add(promptLabel, "PROMPT");

        // Name
        JLabel nameLabel = new LabelBuilder()
                .withText(item.getName())
                .withFont(Font.BOLD, 22)
                .withAlignment(JLabel.CENTER, JLabel.CENTER)
                .build();

        JPanel namePanel = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withSize(CARD_WIDTH, 40)
                .withBackground(theme.getLightGreenColor())
                .build();
        namePanel.add(nameLabel, BorderLayout.CENTER);

        // Full card
        JPanel itemPanel = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withSize(CARD_WIDTH, CARD_HEIGHT)
                .withBackground(theme.getStaticLightGreenLM())
                .withCursor(new Cursor(Cursor.HAND_CURSOR))
                .onHoverEnter(panel -> {
                    ((CardLayout) centerContainer.getLayout()).show(centerContainer, "PROMPT");
                    namePanel.setVisible(false);
                })
                .onHoverExit(panel -> {
                    ((CardLayout) centerContainer.getLayout()).show(centerContainer, "PICTURE");
                    namePanel.setVisible(true);
                })
                .onPress(() -> centerContainer.setBackground(theme.getStaticPrimaryGreenDM()))
                .onRelease(() -> centerContainer.setBackground(theme.getStaticLightGreenLM()))
                .onClick(() -> {
                    System.out.println("Clicked: " + item.getName());
                    handleItemClick(item);
                })
                .build();

        itemPanel.add(centerContainer, BorderLayout.CENTER);
        itemPanel.add(namePanel, BorderLayout.SOUTH);

        return itemPanel;
    }

    private void handleItemClick(Item item) {
        cart.addItem(item);
        refresher.refreshCartDisplay();
    }
}
