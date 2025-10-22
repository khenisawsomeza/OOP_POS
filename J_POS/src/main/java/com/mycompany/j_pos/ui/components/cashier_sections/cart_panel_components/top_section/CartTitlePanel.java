package com.mycompany.j_pos.ui.components.cashier_sections.cart_panel_components.top_section;

import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.ui.builders.ButtonBuilder;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.components.cashier_sections.CartPanel;
import com.mycompany.j_pos.ui.utils.commons.Icons;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;

public class CartTitlePanel extends JPanel implements themeManager.ThemeChangeListener {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 50;
    private static final int PADDING = 20;
    private static final int ICON_SIZE = 30;

    private final themeManager theme = themeManager.getInstance();
    
    private final Cart cart = Cart.getInstance();

    private JLabel titleLabel;
    private JButton clearButton;

    public CartTitlePanel() {
        initializeUI();
        applyTheme();
    }

    private void initializeUI() {
        theme.addThemeChangeListener(this);
        
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, theme.getStaticBorderGray()));

        add(buildTitlePanel(), BorderLayout.CENTER);
    }

    // builds the title panel
    private JPanel buildTitlePanel() {
        JPanel titlePanel = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withSize(WIDTH, HEIGHT)
                .transparent()
                .build();

        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, PADDING, 0, PADDING));

        titleLabel = createHeaderLabel();
        clearButton = createClearButton();

        titlePanel.add(titleLabel, BorderLayout.WEST);
        titlePanel.add(clearButton, BorderLayout.EAST);

        return titlePanel;
    }

    // creates header
    private JLabel createHeaderLabel() {
        return new LabelBuilder()
                .withText("Cart")
                .withFont(Font.BOLD, 30)
                .withForeground(theme.getTextForeground())
                .build();
    }
    
    //creates clear button
    private JButton createClearButton() {
        JButton button = new ButtonBuilder()
                .withSize(ICON_SIZE, ICON_SIZE)
                .withIconSupplier(() -> theme.getTrashIcon())
                .onClick(() -> {
                    handleClearCart();
                        })
                .withHoverEffect(true)
                .build();

        button.setName("trashIcon");
        return button;
    }
    
    //handle clearing of cart
    private void handleClearCart() {
        int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to clear all items from the cart?",
                "Clear Cart",
                JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.YES_OPTION) {
            cart.clearCart();
            CartPanel.getInstance().refreshCartDisplay();
        }
    }

    // apply theme
    private void applyTheme() {
        setBackground(theme.getLightGrayColor());
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, theme.getStaticBorderGray()));

        if (titleLabel != null) {
            titleLabel.setForeground(theme.getTextForeground());
        }

        if (clearButton != null) {
            clearButton.setIcon(Icons.getScaledIcon(theme.getTrashIcon(), ICON_SIZE, ICON_SIZE));
        }

        revalidate();
        repaint();
    }

    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}
