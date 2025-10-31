package com.mycompany.j_pos.ui.components.cashier_sections.cart_panel_components.top_section;

import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.commons.AppConstants;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import javax.swing.*;
import java.awt.*;

public class cashierPanel extends JPanel implements themeManager.ThemeChangeListener {

    private static cashierPanel instance;
    
    private static final int WIDTH = 500;
    private static final int HEIGHT = 30;
    private static final int PADDING = 20;
    private static final int FONT_SIZE = 13;

    private final themeManager theme = themeManager.getInstance();
    private JLabel cashierNameLabel;

    public static cashierPanel getInstance(){
        if (instance == null) instance = new cashierPanel();
        return instance;
    }
    
    public cashierPanel() {
        initializeUI();
        applyTheme();
        theme.addThemeChangeListener(this);
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, theme.getStaticBorderGray()));

        JPanel contentPanel = new PanelBuilder()
                .withLayout(new BorderLayout())
                .withSize(WIDTH, HEIGHT)
                .transparent()
                .build();

        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, PADDING, 0, PADDING));

        cashierNameLabel = new LabelBuilder()
                .withText("Cashier Name: " + AppConstants.DEFAULT_CASHIER_NAME)
                .withFont(Font.BOLD, FONT_SIZE)
                .withForeground(theme.getTextForeground())
                .build();

        contentPanel.add(cashierNameLabel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    // updates cashier name
    public void setCashierName(String name) {
        cashierNameLabel.setText("Cashier Name: " + name);
    }

    // applyt theme
    private void applyTheme() {
        setBackground(theme.getLightGrayColor());
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, theme.getStaticBorderGray()));

        if (cashierNameLabel != null) {
            cashierNameLabel.setForeground(theme.getTextForeground());
        }

        revalidate();
        repaint();
    }

    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}
