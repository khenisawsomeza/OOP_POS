package com.mycompany.j_pos.ui.components.cashier_sections.items_panel_components;

import com.mycompany.j_pos.models.items.Item;
import com.mycompany.j_pos.ui.builders.ButtonBuilder;
import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.builders.TextFieldBuilder;
import com.mycompany.j_pos.ui.components.cashier_sections.cashier_functions.SearchFunction;
import com.mycompany.j_pos.ui.utils.LoadResources;
import com.mycompany.j_pos.ui.utils.commons.Icons;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;

import java.util.function.Supplier;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HeaderPanel extends JPanel implements themeManager.ThemeChangeListener {

    private static final Dimension PANEL_SIZE = new Dimension(780, 50);
    private static final int ICON_SIZE = 30;
    private static final Dimension LOGO_SIZE = new Dimension(150, 30);

    private final themeManager theme = themeManager.getInstance();
    private final ItemsListPanel itemsListPanel = ItemsListPanel.getInstance();
    
    private JPanel navigationPanel;
    private JLabel companyLogoLabel;
    private JButton menuButton;
    private JButton darkModeButton;
    private JButton searchButton;
    private JTextField searchField;

    public HeaderPanel() {
        initializeUI();
    }

    private void initializeUI() {
        theme.addThemeChangeListener(this);
        
        setLayout(new BorderLayout());
        setPreferredSize(PANEL_SIZE);
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        buildNavigationSection();
        buildLogoSection();
        
        applyTheme();
    }


    // Builds the navigation section (menu, dark mode, and search)
    private void buildNavigationSection() {
        
        navigationPanel = new PanelBuilder()
                .withLayout(new FlowLayout(FlowLayout.LEFT, 15, 10))
                .transparent()
                .build();

        searchButton = createIconButton(() -> theme.getSearchIcon());
        attachSearchToggle();
        navigationPanel.add(searchButton);

        searchField = createSearchField();
        searchField.setVisible(false);
        navigationPanel.add(searchField);


        add(navigationPanel, BorderLayout.WEST);
    }

    // Builds the company logo section
    private void buildLogoSection() {
        companyLogoLabel = new LabelBuilder()
                .withIcon(theme.getLogoIcon(), LOGO_SIZE.width, LOGO_SIZE.height)
                .withAlignment(SwingConstants.CENTER, SwingConstants.CENTER)
                .build();

        add(companyLogoLabel, BorderLayout.EAST);
    }

    // Creates a reusable icon button
    private JButton createIconButton(Supplier<ImageIcon> iconSupplier) {
        
        JButton button = new ButtonBuilder()
                .withSize(ICON_SIZE, ICON_SIZE)
                .withIconSupplier(iconSupplier)
                .withHoverEffect(true)
                .build();
        
        return button;
    }

    // Attaches toggle behavior for the search field
    private void attachSearchToggle() {
        searchButton.addActionListener(e -> {
            boolean visible = !searchField.isVisible();
            searchField.setVisible(visible);
            navigationPanel.revalidate();
            navigationPanel.repaint();

            if (visible) searchField.requestFocusInWindow();
        });
        
        
    }

    //Apply the current theme
    private void applyTheme() {
        setBackground(theme.getLightGreenColor());
        if (navigationPanel != null) {
            navigationPanel.setBackground(theme.getLightGreenColor());
        }

        if (companyLogoLabel != null)
            companyLogoLabel.setIcon(Icons.getScaledIcon(theme.getLogoIcon(), LOGO_SIZE.width, LOGO_SIZE.height));

        if (menuButton != null)
            menuButton.setIcon(Icons.getScaledIcon(theme.getMenuIcon(), ICON_SIZE, ICON_SIZE));


        if (darkModeButton != null)
            darkModeButton.setIcon(Icons.getScaledIcon(theme.getDarkModeToggleIcon(), ICON_SIZE, ICON_SIZE));


        if (searchButton != null)
            searchButton.setIcon(Icons.getScaledIcon(theme.getSearchIcon(), ICON_SIZE, ICON_SIZE));
        
        revalidate();
        repaint();
    }

    private JTextField createSearchField() {
        
        JTextField field = new TextFieldBuilder()
                .withPlaceholder("", 16)
                .withFont(Font.PLAIN, 12)
                .withBackground(Color.WHITE)
                .withForeground(theme.getStaticBlack())
                .build();       
        
        field.getDocument().addDocumentListener(new DocumentListener() {
            private void runSearch() {
                search(searchField.getText());    
            }

            @Override public void insertUpdate(DocumentEvent e) { runSearch(); }
            @Override public void removeUpdate(DocumentEvent e) { runSearch(); }
            @Override public void changedUpdate(DocumentEvent e) { runSearch(); }
        });
        
        return field;
    }
    
    private void search(String query){
        
        List<Item> items = null;
            try {
                items = SearchFunction.search(searchField.getText(), LoadResources.getItems());
            } catch (Exception a){
                System.out.println("failed to pass availble items in search");
            }
           
        itemsListPanel.refreshItemsDisplay(items);
    }
    
    
    @Override
    public void onThemeChange(boolean isDarkMode) {
        applyTheme();
    }
}
