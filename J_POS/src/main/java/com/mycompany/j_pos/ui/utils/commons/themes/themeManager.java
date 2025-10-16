//package com.mycompany.j_pos.ui.utils.commons.themes;
//
//import com.mycompany.j_pos.ui.utils.commons.Icons;
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Container;
//import java.awt.Window;
//import java.util.ArrayList;
//import javax.swing.BorderFactory;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.border.Border;
//
//public class themeManager {
//    private static themeManager instance;
//    private boolean isDarkMode = false;
//    private final ArrayList<ThemeChangeListener> listeners = new ArrayList<>();
//    
//    private themeManager(){
//        
//    }
//    
//    public static themeManager getInstance(){
//        if(instance == null){
//            instance = new themeManager();
//        }
//        return instance;
//    }
//    
//    public void toggleDarkMode(){
//        isDarkMode = !isDarkMode;
//        applyThemeToAllComponents();
//        notifyListeners();
//    }
//    
//    public void setDarkMode(boolean darkMode){
//        this.isDarkMode = darkMode;
//        applyThemeToAllComponents();
//        notifyListeners();
//    }
//    
//    public void addThemeChangeListener(ThemeChangeListener listener){
//        listeners.add(listener);
//    }
//    
//     public void removeThemeChangeListener(ThemeChangeListener listener){
//        listeners.remove(listener);
//    }
//    
//    private void notifyListeners(){
//        for(ThemeChangeListener listener : listeners){
//            listener.onThemeChange(isDarkMode);
//        }
//    }
//    
//    public void applyThemeToAllComponents(){
//        for(Window window: Window.getWindows()){
//            applyThemeToContainer(window);
//            window.revalidate();
//            window.repaint();
//        }
//    }
//    
//    private void applyThemeToContainer(Container container){
//        applyTheme(container);
//        for(Component comp : container.getComponents()){
//            if(comp instanceof Container){
//                applyThemeToContainer((Container)comp);
//            }else{
//                applyTheme(comp);
//            }
//        }
//    }
//    
//    public void applyTheme(Component comp){
//        if (comp instanceof JLabel label) {
//            if("regularLabel".equals(label.getName())){
//                label.setForeground(getTextForeground());
//            }
//            if ("buttonLabel".equals(label.getName())){
//                label.setForeground(getTextForeground());
//                label.setBackground(getLightGreenColor());
//            }
//            if ("logoIcon".equals(label.getName())){
//                label.setIcon(Icons.getScaledIcon(themeManager.getInstance().getLogoIcon(), 150, 30));
//            }
//        }
//        if (comp instanceof JPanel panel){
//            if ("lightGreenPanel".equals(panel.getName())){
//                panel.setBackground(getLightGreenColor());
//            }
//            if("lightGrayPanel".equals(panel.getName())){
//                panel.setBackground(getLightGrayColor());
//            }
//            if("addNewItemPanel".equals(panel.getName())){
//                panel.setBackground(getLightGrayColor());
//                Border border = BorderFactory.createDashedBorder(getTextForeground(), 2, 10, 5, true);
//                panel.setBorder(border);
//            }
//            if("addNewCatPanel".equals(panel.getName())){
//                panel.setBackground(getLightGreenColor());
//                Border border = BorderFactory.createDashedBorder(getTextForeground(), 2, 10, 5, true);
//                panel.setBorder(border);
//            }
//            if("cartItemRow".equals(panel.getName())){
//                panel.setBackground(getTextBackground());
//            }
//        }
//        if (comp instanceof JButton button){
//            if("trashIcon".equals(button.getName())){
//                button.setIcon(Icons.getScaledIcon(getTrashIcon(), 30, 30));
//            }
//            if("deleteItemIcon".equals(button.getName())){
//                button.setIcon(Icons.getScaledIcon(getDeleteButtonIcon(), 15, 15));
//            }
//            if("menuIcon".equals(button.getName())){
//                button.setIcon(Icons.getScaledIcon(getMenuIcon(), 30, 30));
//            }
//            if("searchIcon".equals(button.getName())){
//                button.setIcon(Icons.getScaledIcon(getSearchIcon(), 30, 30));
//            }
//            if("darkModeToggleIcon".equals(button.getName())){
//                button.setIcon(Icons.getScaledIcon(getDarkModeToggleIcon(), 30, 30));
//            }
//            if("logoIcon".equals(button.getName())){
//                button.setIcon(Icons.getScaledIcon(getLogoIcon(), 30, 30));
//            }
//        }
//        
//    }
//    
//    public boolean isDarkMode(){
//        return isDarkMode;
//    }
//    
//    public Color getLightGreenColor(){
//        return isDarkMode ? UIColors.LIGHT_GREEN_DARKMODE : UIColors.LIGHT_GREEN_LIGHTMODE;
//    }    
//    public Color getPrimaryGreenColor(){
//        return isDarkMode ? UIColors.PRIMARY_GREEN_DARKMODE : UIColors.PRIMARY_GREEN_LIGHTMODE;
//    }    
//    public Color getLightGrayColor(){
//        return isDarkMode ? UIColors.LIGHT_GRAY_DARKMODE : UIColors.LIGHT_GRAY_LIGHTMODE;
//    }
//    public Color getAddItemHoverColor(){
//        return isDarkMode ? UIColors.ADD_ITEM_HOVER_DARK : UIColors.ADD_ITEM_HOVER_LIGHT;
//    }
//    public Color getTextForeground(){
//        return isDarkMode ? Color.WHITE : Color.BLACK;
//    }
//    public Color getTextBackground(){
//        return isDarkMode ? new Color(0x252629) : Color.WHITE;
//    }
//    
//    public Color getStaticLightGreenLM(){
//        return UIColors.LIGHT_GREEN_LIGHTMODE;
//    }
//    
//    public Color getStaticLightGreenDM(){
//        return UIColors.LIGHT_GREEN_DARKMODE;
//    }
//    
//    public Color getStaticPrimaryGreenLM(){
//        return UIColors.PRIMARY_GREEN_LIGHTMODE;
//    }
//    
//    public Color getStaticPrimaryGreenDM(){
//        return UIColors.PRIMARY_GREEN_DARKMODE;
//    }
//    
//    public Color getStaticBorderGray(){
//        return UIColors.BORDER_GRAY;
//    }
//    
//    public Color getStaticBlack(){
//        return UIColors.BLACK;
//    }
//    
//    public ImageIcon getTrashIcon(){
//        return isDarkMode ? Icons.getInstance().getTrashIconLight() : Icons.getInstance().getTrashIconDark();
//    }
//    
//    public ImageIcon getDeleteButtonIcon(){
//        return isDarkMode ? Icons.getInstance().getDeleteIconLight(): Icons.getInstance().getDeleteIconDark();
//    }
//    
//    public ImageIcon getPlusIcon(){
//        return isDarkMode ? Icons.getInstance().getPlusIconLight(): Icons.getInstance().getPlusIconDark();
//    }
//    
//    public ImageIcon getMenuIcon(){
//        return isDarkMode ? Icons.getInstance().getMenuIconLight(): Icons.getInstance().getMenuIconDark();
//    }
//    
//    public ImageIcon getSearchIcon(){
//        return isDarkMode ? Icons.getInstance().getSearchIconLight(): Icons.getInstance().getSearchIconDark();
//    }
//    
//    public ImageIcon getDarkModeToggleIcon(){
//        return isDarkMode ? Icons.getInstance().getDarkModeToggleIconLight(): Icons.getInstance().getDarkModeToggleIconDark();
//    }
//    
//    public ImageIcon getLogoIcon(){
//        return isDarkMode ? Icons.getInstance().getLogoIconLight(): Icons.getInstance().getLogoIconDark();
//    }
//    
//    public interface ThemeChangeListener{
//        void onThemeChange(boolean isDarkMode);
//    }
//}

package com.mycompany.j_pos.ui.utils.commons.themes;

import com.mycompany.j_pos.ui.utils.commons.Icons;
import java.awt.Color;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class themeManager {
    private static themeManager instance;
    private boolean isDarkMode = false;
    private final ArrayList<ThemeChangeListener> listeners = new ArrayList<>();
    
    private themeManager() {}

    public static themeManager getInstance() {
        if (instance == null) {
            instance = new themeManager();
        }
        return instance;
    }

    /* ---------------------- THEME TOGGLING ---------------------- */

    public void toggleDarkMode() {
        System.out.println("test");
        isDarkMode = !isDarkMode;
        notifyListeners();
    }

    public void setDarkMode(boolean darkMode) {
        if (this.isDarkMode != darkMode) {
            this.isDarkMode = darkMode;
            notifyListeners();
        }
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    /* ---------------------- LISTENER MANAGEMENT ---------------------- */

    public void addThemeChangeListener(ThemeChangeListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
            // Apply current theme immediately when added
            listener.onThemeChange(isDarkMode);
        }
    }

    public void removeThemeChangeListener(ThemeChangeListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (ThemeChangeListener listener : listeners) {
            listener.onThemeChange(isDarkMode);
        }

        // Optionally refresh open windows if needed
        for (Window window : Window.getWindows()) {
            window.revalidate();
            window.repaint();
        }
    }

    /* ---------------------- COLOR HELPERS ---------------------- */

    public Color getLightGreenColor() {
        return isDarkMode ? UIColors.LIGHT_GREEN_DARKMODE : UIColors.LIGHT_GREEN_LIGHTMODE;
    }

    public Color getPrimaryGreenColor() {
        return isDarkMode ? UIColors.PRIMARY_GREEN_DARKMODE : UIColors.PRIMARY_GREEN_LIGHTMODE;
    }

    public Color getLightGrayColor() {
        return isDarkMode ? UIColors.LIGHT_GRAY_DARKMODE : UIColors.LIGHT_GRAY_LIGHTMODE;
    }

    public Color getAddItemHoverColor() {
        return isDarkMode ? UIColors.ADD_ITEM_HOVER_DARK : UIColors.ADD_ITEM_HOVER_LIGHT;
    }

    public Color getTextForeground() {
        return isDarkMode ? Color.WHITE : Color.BLACK;
    }

    public Color getTextBackground() {
        return isDarkMode ? new Color(0x252629) : Color.WHITE;
    }

    /* ---------------------- STATIC COLORS (unchanging) ---------------------- */

    public Color getStaticLightGreenLM() { return UIColors.LIGHT_GREEN_LIGHTMODE; }
    public Color getStaticLightGreenDM() { return UIColors.LIGHT_GREEN_DARKMODE; }
    public Color getStaticPrimaryGreenLM() { return UIColors.PRIMARY_GREEN_LIGHTMODE; }
    public Color getStaticPrimaryGreenDM() { return UIColors.PRIMARY_GREEN_DARKMODE; }
    public Color getStaticBorderGray() { return UIColors.BORDER_GRAY; }
    public Color getStaticBlack() { return UIColors.BLACK; }

    /* ---------------------- ICON HELPERS ---------------------- */

    public ImageIcon getTrashIcon() {
        return isDarkMode ? Icons.getInstance().getTrashIconLight() : Icons.getInstance().getTrashIconDark();
    }

    public ImageIcon getDeleteButtonIcon() {
        return isDarkMode ? Icons.getInstance().getDeleteIconLight() : Icons.getInstance().getDeleteIconDark();
    }

    public ImageIcon getPlusIcon() {
        return isDarkMode ? Icons.getInstance().getPlusIconLight() : Icons.getInstance().getPlusIconDark();
    }

    public ImageIcon getMenuIcon() {
        return isDarkMode ? Icons.getInstance().getMenuIconLight() : Icons.getInstance().getMenuIconDark();
    }

    public ImageIcon getSearchIcon() {
        return isDarkMode ? Icons.getInstance().getSearchIconLight() : Icons.getInstance().getSearchIconDark();
    }

    public ImageIcon getDarkModeToggleIcon() {
        return isDarkMode ? Icons.getInstance().getDarkModeToggleIconLight() : Icons.getInstance().getDarkModeToggleIconDark();
    }

    public ImageIcon getLogoIcon() {
        return isDarkMode ? Icons.getInstance().getLogoIconLight() : Icons.getInstance().getLogoIconDark();
    }

    /* ---------------------- INTERFACE ---------------------- */

    public interface ThemeChangeListener {
        void onThemeChange(boolean isDarkMode);
    }
}

