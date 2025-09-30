package com.mycompany.j_pos.ui.components.cashier_sections.cart;

import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.*;
import javax.swing.*;

public class CartPanel extends JPanel {
    private final Cart cart;

    private CartItemsSection itemsSection;
    private CartSummarySection summarySection;
    private CheckoutSection checkoutSection;

    public CartPanel(Cart cart) {
        this.cart = cart;
        setupCartPanel();
    }

    private void setupCartPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, Integer.MAX_VALUE));
        setBackground(themeManager.getInstance().getLightGrayColor());
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, themeManager.getInstance().getStaticBorderGray()));

        // Top section
        JPanel topSection = new JPanel(new BorderLayout());
        topSection.setPreferredSize(new Dimension(500, 80));
        add(topSection, BorderLayout.NORTH);

        topSection.add(new CartTitle(cart, this::handleClearCart), BorderLayout.NORTH);
        topSection.add(new CashierSection(), BorderLayout.SOUTH);

        // Center (items)
        itemsSection = new CartItemsSection(cart, this::refreshCartDisplay);
        add(itemsSection, BorderLayout.CENTER);

        // Bottom (summary + checkout)
        JPanel bottomSection = new JPanel(new BorderLayout());
        bottomSection.setPreferredSize(new Dimension(500, 200));
        add(bottomSection, BorderLayout.SOUTH);

        summarySection = new CartSummarySection(cart);
        checkoutSection = new CheckoutSection(cart, this::handleCheckout);

        bottomSection.add(summarySection, BorderLayout.NORTH);
        bottomSection.add(checkoutSection, BorderLayout.SOUTH);
    }

    public void refreshCartDisplay() {
        itemsSection.refreshItems();
        summarySection.refreshSummary();
        checkoutSection.refreshCheckout();
    }

    private void handleCheckout() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cart is empty! Add items before checkout.");
            return;
        }
        JOptionPane.showMessageDialog(this, "Processing checkout for: " + cart.getFormattedTotal());
    }

    private void handleClearCart() {
        int result = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to clear all items from the cart?",
            "Clear Cart",
            JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.YES_OPTION) {
            cart.clearCart();
            refreshCartDisplay();
        }
    }
}
