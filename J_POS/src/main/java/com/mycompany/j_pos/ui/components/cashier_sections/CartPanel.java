package com.mycompany.j_pos.ui.components.cashier_sections;

import com.mycompany.j_pos.ui.components.cashier_sections.cart_panel_components.bottom_section.CheckoutPanel;
import com.mycompany.j_pos.ui.components.cashier_sections.cart_panel_components.bottom_section.CartSummaryPanel;
import com.mycompany.j_pos.ui.components.cashier_sections.cart_panel_components.cart_items_section.CartItemsPanel;
import com.mycompany.j_pos.ui.components.cashier_sections.cart_panel_components.top_section.cashierPanel;
import com.mycompany.j_pos.ui.components.cashier_sections.cart_panel_components.top_section.CartTitlePanel;
import com.mycompany.j_pos.models.cart.Cart;
import com.mycompany.j_pos.models.sale.Sale;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import javax.swing.*;
import java.awt.*;

public class CartPanel extends JPanel implements themeManager.ThemeChangeListener {
    
    private static CartPanel instance;

    private static final int PANEL_WIDTH = 500;
    private static final int TOP_HEIGHT = 80;
    private static final int BOTTOM_HEIGHT = 200;

    private final Cart cart = Cart.getInstance();
    private final themeManager theme = themeManager.getInstance();

    private CartItemsPanel itemsSection;
    private CartSummaryPanel summarySection;
    private CheckoutPanel checkoutSection;

    private CartPanel() {
        initializeUI();
        applyTheme();
    }
    
    public static CartPanel getInstance(){
        if (instance == null) instance = new CartPanel();
        return instance;
    }

    private void initializeUI() {
        theme.addThemeChangeListener(this);
        
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(PANEL_WIDTH, Integer.MAX_VALUE));
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, theme.getStaticBorderGray()));

        add(buildTopSection(), BorderLayout.NORTH);
        add(buildScrollableItemsSection(), BorderLayout.CENTER);
        add(buildBottomSection(), BorderLayout.SOUTH);
        
        applyTheme();
    }

    // builds top section
    private JPanel buildTopSection() {
        JPanel topSection = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withSize(PANEL_WIDTH, TOP_HEIGHT)
                .transparent()
                .build();

        topSection.add(new CartTitlePanel(), BorderLayout.NORTH);
        topSection.add(new cashierPanel(), BorderLayout.SOUTH);

        return topSection;
    }

    // builds a scrollable section for added items
    private JScrollPane buildScrollableItemsSection() {
        itemsSection = new CartItemsPanel();

        JScrollPane scrollPane = new JScrollPane(
                itemsSection, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smooth scroll
        scrollPane.getViewport().setBackground(theme.getLightGrayColor());

        return scrollPane;
    }

    // build bottom section
    private JPanel buildBottomSection() {
        summarySection = new CartSummaryPanel();
        checkoutSection = new CheckoutPanel();

        JPanel bottomSection = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withSize(PANEL_WIDTH, BOTTOM_HEIGHT)
                .build();

        bottomSection.add(summarySection, BorderLayout.NORTH);
        bottomSection.add(checkoutSection, BorderLayout.SOUTH);

        return bottomSection;
    }

    // refreshes the current cart
    public void refreshCartDisplay() {
        Sale.getInstance().refreshDiscount();
        if (itemsSection != null) itemsSection.refreshItems();
        if (summarySection != null) summarySection.refreshSummary();
        if (checkoutSection != null) checkoutSection.refreshCheckout();
    }

    // apply theme
    private void applyTheme() {
        setBackground(Color.BLUE);
    }

    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}
