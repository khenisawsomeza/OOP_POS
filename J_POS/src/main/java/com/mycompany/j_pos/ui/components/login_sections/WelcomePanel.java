package com.mycompany.j_pos.ui.components.login_sections;

import com.mycompany.j_pos.ui.builders.LabelBuilder;
import com.mycompany.j_pos.ui.builders.PanelBuilder;
import com.mycompany.j_pos.ui.utils.commons.Icons;
import com.mycompany.j_pos.ui.utils.commons.themes.themeManager;
import java.awt.*;
import javax.swing.*;

public class WelcomePanel extends JPanel {

    private JPanel textContainer;

    public WelcomePanel() {
        initializeWelcome();
        addComponents();
    }

    private void initializeWelcome() {
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension((int) (screensize.width * 0.6), screensize.height));
        setBackground(themeManager.getInstance().getLightGreenColor());
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    private void addComponents() {
        textContainer = new PanelBuilder()
                .withBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0))
                .transparent()
                .build();
        textContainer.setLayout(new BoxLayout(textContainer, BoxLayout.Y_AXIS));

        textContainer.add(Box.createRigidArea(new Dimension(0, 75)));
        initializeLogo();
        textContainer.add(Box.createRigidArea(new Dimension(0, 30)));
        initializeText();
        textContainer.add(Box.createRigidArea(new Dimension(0, 100)));
        initializeFooter();

        add(textContainer, BorderLayout.CENTER);
    }

    private void initializeLogo() {
        ImageIcon appIcon = Icons.getInstance().getAppIcon();
        JLabel logoLabel = new JLabel(Icons.getScaledIcon(appIcon, 120, 120));
        logoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        textContainer.add(logoLabel);
    }

    private void initializeText() {
        JLabel welcomeLabel = new LabelBuilder()
                .withText("WELCOME TO")
                .withFont(Font.BOLD, 50)
                .withForeground(themeManager.getInstance().getTextBackground())
                .withAlignment(SwingConstants.LEFT, SwingConstants.CENTER)
                .build();

        JLabel chachingLabel = new LabelBuilder()
                .withText("Cha-Ching!")
                .withFont(Font.BOLD, 50)
                .withForeground(themeManager.getInstance().getTextBackground())
                .withAlignment(SwingConstants.LEFT, SwingConstants.CENTER)
                .build();

        JLabel descriptionLabel = new LabelBuilder()
                .withText("<html><div style='width:90%;'>"
                        + "A smart and efficient point-of-sale system designed to make "
                        + "every transaction seamless and every sale count."
                        + "</div></html>")
                .withFont(Font.PLAIN, 25)
                .withForeground(themeManager.getInstance().getTextBackground())
                .withAlignment(SwingConstants.LEFT, SwingConstants.CENTER)
                .build();

        textContainer.add(welcomeLabel);
        textContainer.add(chachingLabel);
        textContainer.add(Box.createRigidArea(new Dimension(0, 50)));
        textContainer.add(descriptionLabel);
    }

    private void initializeFooter() {
        JPanel footerPanel = new PanelBuilder()
                .transparent()
                .withBorder(BorderFactory.createEmptyBorder(0, 100, 30, 0))
                .build();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));

        // Logo
        ImageIcon companyLogo = Icons.getInstance().getcompanyIcon();
        JLabel logoLabel = new JLabel(Icons.getScaledIcon(companyLogo, 40, 40));
        logoLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Company name
        JLabel companyLabel = new LabelBuilder()
                .withText("Cha-Ching POS by Jhoeritch and Others")
                .withFont(Font.PLAIN, 18)
                .withForeground(new Color(255, 255, 255, 200))
                .build();

        footerPanel.add(logoLabel);
        footerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        footerPanel.add(companyLabel);

        add(footerPanel, BorderLayout.SOUTH);
    }
}
