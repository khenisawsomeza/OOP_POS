package com.mycompany.j_pos.ui.components.cashier_sections.cart;

import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;

public class CashierSection extends JPanel {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 30;
    private static final int PADDING = 20;

    private JLabel cashierNameLabel;

    public CashierSection() {
        initializeCashierSection();
    }

    private void initializeCashierSection() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createMatteBorder(
            0, 0, 1, 0, themeManager.getInstance().getStaticBorderGray()
        ));

        JPanel contentPanel = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withSize(WIDTH, HEIGHT)
                .transparent()
                .build();
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, PADDING, 0, PADDING));
        add(contentPanel, BorderLayout.CENTER);
        
        cashierNameLabel = new LabelBuilder()
                .withText("Cashier Name: " + AppConstants.DEFAULT_CASHIER_NAME)
                .withFont(Font.BOLD, 13)
                .withForeground(themeManager.getInstance().getTextForeground())
                .build();
        

        contentPanel.add(cashierNameLabel, BorderLayout.WEST);
    }

    // Optionally, allow updating the cashier name dynamically
    public void setCashierName(String name) {
        cashierNameLabel.setText("Cashier Name: " + name);
    }
}
