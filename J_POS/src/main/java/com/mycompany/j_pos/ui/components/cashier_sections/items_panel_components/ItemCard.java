/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.ui.components.cashier_sections.items_panel_components;

import com.mycompany.j_pos.models.cart.Cart;
import com.mycompany.j_pos.models.items.Item;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.components.cashier_sections.CartPanel;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;

public class ItemCard extends JPanel implements themeManager.ThemeChangeListener {
    
    private static final int CARD_WIDTH = 220;
    private static final int CARD_HEIGHT = 170;
    private static final int IMAGE_WIDTH = 200;
    private static final int IMAGE_HEIGHT = 120;

    private final Cart cart = Cart.getInstance();
    private final themeManager theme = themeManager.getInstance();
    
    private final JLabel nameLabel;
    private final JLabel promptLabel;
    private final JLabel pictureLabel;
    private final JPanel centerContainer;
    private final JPanel namePanel;
    private final JPanel picturePanel;
    private final Item item;
    private final CartPanel cartPanel = CartPanel.getInstance(); 

    public ItemCard(Item item) {
        this.item = item;
        
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setToolTipText("Click to add " + item.getName() + " to cart");

        pictureLabel = createPictureLabel();
        promptLabel = createPromptLabel();
        nameLabel = createNameLabel();

        picturePanel = createPicturePanel();
        centerContainer = createCenterContainer();
        namePanel = createNamePanel();

        buildUI();
        setupListeners();
        applyTheme();

        theme.addThemeChangeListener(this);
        
    }

    private JLabel createPictureLabel() {
        return new LabelBuilder()
                .withIcon(item.getPicture(), IMAGE_WIDTH, IMAGE_HEIGHT)
                .withAlignment(JLabel.CENTER, JLabel.CENTER)
                .build();
    }

    private JLabel createPromptLabel() {
        return new LabelBuilder()
                .withText("Add " + item.getName() + " to cart?")
                .withFont(Font.BOLD, 16)
                .withAlignment(JLabel.CENTER, JLabel.CENTER)
                .build();
    }

    private JLabel createNameLabel() {
        return new LabelBuilder()
                .withText(item.getName())
                .withFont(Font.BOLD, 22)
                .withAlignment(JLabel.CENTER, JLabel.CENTER)
                .build();
    }

    private JPanel createPicturePanel() {
        JPanel panel = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withSize(Integer.MAX_VALUE, Integer.MAX_VALUE)
                .withBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5))
                .build();
        panel.add(pictureLabel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCenterContainer() {
        JPanel panel = new PanelBuilder()
                .withLayout(new CardLayout())
                .build();
        panel.add(picturePanel, "PICTURE");
        panel.add(promptLabel, "PROMPT");
        return panel;
    }

    private JPanel createNamePanel() {
        JPanel panel = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withSize(CARD_WIDTH, 40)
                .build();
        panel.add(nameLabel, BorderLayout.CENTER);
        return panel;
    }

    private void buildUI() {
        add(centerContainer, BorderLayout.CENTER);
        add(namePanel, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cart.addItem(item);
                cartPanel.refreshCartDisplay();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                showPrompt(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                showPrompt(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                centerContainer.setBackground(theme.getStaticPrimaryGreenDM());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                centerContainer.setBackground(theme.getStaticLightGreenLM());
            }
        });
    }

    private void showPrompt(boolean show) {
        ((CardLayout) centerContainer.getLayout()).show(centerContainer, show ? "PROMPT" : "PICTURE");
        namePanel.setVisible(!show);
    }

    private void applyTheme() {
        setBackground(theme.getStaticLightGreenLM());
        namePanel.setBackground(theme.getLightGreenColor());
        picturePanel.setBackground(theme.getLightGreenColor());
        centerContainer.setBackground(theme.getStaticLightGreenLM());
        nameLabel.setForeground(theme.getTextForeground());
        promptLabel.setForeground(theme.getStaticBlack());
    }

    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}

