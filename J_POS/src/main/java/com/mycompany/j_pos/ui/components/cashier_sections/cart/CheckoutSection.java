package com.mycompany.j_pos.ui.components.cashier_sections.cart;

import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.factories.PanelFactory;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;

public class CheckoutSection extends JPanel {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 80;

    private final Cart cart;
    private final Runnable checkoutHandler;

    private JLabel checkoutAmountLabel;

    public CheckoutSection(Cart cart, Runnable checkoutHandler) {
        this.cart = cart;
        this.checkoutHandler = checkoutHandler;
        setupCheckoutPanel();
    }

    private void setupCheckoutPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        JPanel content = PanelFactory.createCheckoutPanel(
            new FlowLayout(FlowLayout.CENTER, 10, 10),
            new Dimension(460, 60),
            checkoutHandler
        );
        add(content, BorderLayout.CENTER);

        JLabel title = new LabelBuilder()
                .withText("Checkout: ")
                .withFont(Font.BOLD, 30)
                .withForeground(themeManager.getInstance().getStaticBlack())
                .build();

        checkoutAmountLabel = new LabelBuilder()
                .withText("0")
                .withFont(Font.BOLD, 30)
                .withForeground(themeManager.getInstance().getStaticBlack())
                .build();
        
        

        content.add(title);
        content.add(checkoutAmountLabel);
    }

    public void refreshCheckout() {
        checkoutAmountLabel.setText(cart.getFormattedTotal());
    }
}
