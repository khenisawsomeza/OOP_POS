package com.mycompany.j_pos.ui.components.cashier_sections.cart;

import com.mycompany.j_pos.models.Cart;
import com.mycompany.j_pos.ui.builders.ButtonBuilder;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.factories.PanelFactory;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CartTitle extends JPanel {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 50;
    private static final int PADDING = 20;
    private static final int ICON_SIZE = 30;

    private final Cart cart;
    private final Runnable handleClearCart;

    public CartTitle(Cart cart, Runnable handleClearCart) {
        this.cart = cart;
        this.handleClearCart = handleClearCart;

        setupMainPanel();
        add(buildTitlePanel(), BorderLayout.NORTH);
    }

    private void setupMainPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createMatteBorder(
            0, 0, 1, 0, themeManager.getInstance().getStaticBorderGray()
        ));
    }

    private JPanel buildTitlePanel() {
        JPanel titlePanel = PanelFactory.createSectionPanel(new BorderLayout(), new Dimension(WIDTH, HEIGHT));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, PADDING, 0, PADDING));

        titlePanel.add(createHeaderLabel(), BorderLayout.WEST);
        titlePanel.add(createClearButton(), BorderLayout.EAST);

        return titlePanel;
    }

    private JLabel createHeaderLabel() {
      
        return new LabelBuilder()
                .withText("Cart")
                .withFont(Font.BOLD, 30)
                .withForeground(themeManager.getInstance().getTextForeground())
                .build();
    }

    private JButton createClearButton() {
        JButton clearButton = new ButtonBuilder()
            .withSize(ICON_SIZE, ICON_SIZE)
            .withIcon(themeManager.getInstance().getTrashIcon())
            .onClick(handleClearCart) 
            .build();
        clearButton.setName("trashIcon");
        return clearButton;
    }
}
