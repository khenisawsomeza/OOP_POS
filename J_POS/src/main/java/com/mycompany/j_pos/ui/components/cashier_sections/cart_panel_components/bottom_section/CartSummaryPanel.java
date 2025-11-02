package com.mycompany.j_pos.ui.components.cashier_sections.cart_panel_components.bottom_section;

import com.mycompany.j_pos.models.cart.Cart;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CartSummaryPanel extends JPanel implements themeManager.ThemeChangeListener {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 120;
    private static final int ROW_HEIGHT = 40;
    private static final int SIDE_PADDING = 30;
    private static final int FONT_SIZE = 15;

    private final Cart cart = Cart.getInstance();
    private final themeManager theme = themeManager.getInstance();
    private static CartSummaryPanel instance;

    private JLabel taxAmountLabel;
    private JLabel subtotalAmountLabel;
    private JLabel discountAmountLabel;
    private ArrayList<JLabel> labels = new ArrayList<>();

    public static CartSummaryPanel getInstance(){
        if (instance == null){
            instance = new CartSummaryPanel();
        }
        return instance;
    }

    
    public CartSummaryPanel() {
        initializeUI();
        applyTheme();
    }

    private void initializeUI() {
        theme.addThemeChangeListener(this);
        
        setLayout(new GridLayout(3, 1));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createEmptyBorder(0, SIDE_PADDING, 0, SIDE_PADDING));
        
        taxAmountLabel = createAmountLabel(cart.getFormattedTaxAmount());
        add(createRow(createAmountLabel("Tax: "), taxAmountLabel));
        subtotalAmountLabel = createAmountLabel(cart.getFormattedSubtotal());
        add(createRow(createAmountLabel("Subtotal: "), subtotalAmountLabel));
        discountAmountLabel = createAmountLabel(cart.getFormattedDiscount());
        add(createRow(createAmountLabel("Discount: "), discountAmountLabel));
    }
    
    private JLabel createAmountLabel(String text){
        JLabel label = new LabelBuilder()
                .withText(text)
                .withFont(Font.BOLD, FONT_SIZE) 
                .build();
        labels.add(label);
        
        return label;
    }

    private JPanel createRow(JLabel label ,JLabel amount) {
        JPanel row = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withSize(WIDTH, ROW_HEIGHT)
                .transparent()
                .build();

        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, theme.getStaticBorderGray()));

        row.add(label, BorderLayout.WEST);
        row.add(amount, BorderLayout.EAST);

        return row;
    }

    public void refreshSummary() {
        taxAmountLabel.setText(cart.getFormattedTaxAmount());
        subtotalAmountLabel.setText(cart.getFormattedSubtotal());
        discountAmountLabel.setText(cart.getFormattedDiscount());
        
        revalidate();
        repaint();
    }

    private void applyTheme() {
        setBackground(theme.getLightGrayColor());
             
        for (JLabel label : labels){
            label.setForeground(theme.getTextForeground());
        }
    }

    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}
