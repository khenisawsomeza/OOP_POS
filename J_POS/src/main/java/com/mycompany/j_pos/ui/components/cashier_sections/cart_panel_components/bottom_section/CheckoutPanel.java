package com.mycompany.j_pos.ui.components.cashier_sections.cart_panel_components.bottom_section;

import com.mycompany.j_pos.facade.POSFacade;
import com.mycompany.j_pos.models.cart.Cart;
import com.mycompany.j_pos.models.sale.Sale;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;

public class CheckoutPanel extends JPanel implements themeManager.ThemeChangeListener {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 80;

    private final Cart cart = Cart.getInstance();
    private final themeManager theme = themeManager.getInstance();
    private final POSFacade POS = POSFacade.getInstance();

    private JLabel checkoutAmountLabel;
    private JPanel contentPanel;

    public CheckoutPanel() {
        initializeUI();
        applyTheme();
    }

    private void initializeUI() {
        theme.addThemeChangeListener(this);
        
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        contentPanel = new PanelBuilder()
                .withLayout(new FlowLayout(FlowLayout.CENTER, 10, 10))
                .withSize(460, 60)
                .withBackground(theme.getStaticLightGreenLM())
                .withCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
                .onHoverEnter(panel -> panel.setBackground(theme.getStaticPrimaryGreenLM()))
                .onHoverExit(panel -> panel.setBackground(theme.getStaticLightGreenLM()))
                .onPress(panel -> panel.setBackground(theme.getStaticPrimaryGreenLM().darker()))
                .onRelease(panel -> panel.setBackground(theme.getStaticPrimaryGreenLM()))
                .onClick(POS::checkout)
                .build();

        JLabel title = new LabelBuilder()
                .withText("Checkout: ")
                .withFont(Font.BOLD, 28)
                .build();

        checkoutAmountLabel = new LabelBuilder()
                .withText("₱0.00")
                .withFont(Font.BOLD, 28)
                .build();

        contentPanel.add(title);
        contentPanel.add(checkoutAmountLabel);

        add(contentPanel, BorderLayout.CENTER);
    }

    public void refreshCheckout() {
        checkoutAmountLabel.setText(cart.getFormattedTotal());
    }

    private void applyTheme() {
        setBackground(theme.getLightGrayColor());
        
        contentPanel.setBackground(theme.getStaticLightGreenLM());
        checkoutAmountLabel.setForeground(theme.getStaticBlack());
        
    }

    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}
