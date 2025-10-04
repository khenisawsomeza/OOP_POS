package com.mycompany.j_pos.ui.components.cashier_sections.cart;

import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.factories.PanelFactory;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;

public class CartSummarySection extends JPanel {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 120;

    private final Cart cart;

    private JLabel taxAmountLabel;
    private JLabel subtotalAmountLabel;
    private JLabel discountAmountLabel;

    public CartSummarySection(Cart cart) {
        this.cart = cart;
        setupSummaryPanel();
    }

    private void setupSummaryPanel() {
        setLayout(new GridLayout(3, 1));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        // Tax
        JPanel taxPanel = createRow("Tax: ", "0");
        taxAmountLabel = (JLabel) taxPanel.getComponent(1);
        add(taxPanel);

        // Subtotal
        JPanel subtotalPanel = createRow("Subtotal: ", "0");
        subtotalAmountLabel = (JLabel) subtotalPanel.getComponent(1);
        add(subtotalPanel);

        // Discount
        JPanel discountPanel = createRow("Discount: ", "0");
        discountAmountLabel = (JLabel) discountPanel.getComponent(1);
        add(discountPanel);
    }

    private JPanel createRow(String labelText, String amountText) {
        JPanel row = PanelFactory.createSectionPanel(new BorderLayout(), new Dimension(WIDTH, 40));
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, themeManager.getInstance().getStaticBorderGray()));
        
        JLabel label = new LabelBuilder()
                .withText(labelText)
                .withFont(Font.BOLD, 15)
                .withForeground(themeManager.getInstance().getTextForeground())
                .build();
        
        JLabel amount = new LabelBuilder()
                .withText(amountText)
                .withFont(Font.BOLD, 15)
                .withForeground(themeManager.getInstance().getTextForeground())
                .build();

        row.add(label, BorderLayout.WEST);
        row.add(amount, BorderLayout.EAST);

        return row;
    }

    public void refreshSummary() {
        taxAmountLabel.setText(cart.getFormattedTaxAmount());
        subtotalAmountLabel.setText(cart.getFormattedSubtotal());
        discountAmountLabel.setText(cart.getFormattedDiscount());
    }
}
