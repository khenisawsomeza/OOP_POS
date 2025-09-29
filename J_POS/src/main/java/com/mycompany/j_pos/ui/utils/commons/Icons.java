package com.mycompany.j_pos.ui.utils.commons;

import com.mycompany.j_pos.ui.utils.LoadResources;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;

public class Icons {
    private static Icons instance;
    private static final String IMAGES_PATH = "src/main/resources/images/icons/";      

    // declare only icons
    // DARK ICONS
    private static final ImageIcon logoIconDark            = LoadResources.loadImage(IMAGES_PATH, "logoDark.png");
    private static final ImageIcon menuIconDark            = LoadResources.loadImage(IMAGES_PATH, "menuBurgerIconDark.png");
    private static final ImageIcon searchIconDark          = LoadResources.loadImage(IMAGES_PATH, "searchIconDark.png");
    private static final ImageIcon trashIconDark           = LoadResources.loadImage(IMAGES_PATH, "trashDark.png");
    private static final ImageIcon deleteIconDark          = LoadResources.loadImage(IMAGES_PATH, "crossCircleIconDark.png");
    private static final ImageIcon userIcon                = LoadResources.loadImage(IMAGES_PATH, "userIconDark.png");
    private static final ImageIcon darkModeToggleIconDark  = LoadResources.loadImage(IMAGES_PATH, "themeModeIconDark.png");
    private static final ImageIcon plusIconDark            = LoadResources.loadImage(IMAGES_PATH, "plusIconDark.png");
    
    //LIGHT ICONS
    private static final ImageIcon logoIconLight           = LoadResources.loadImage(IMAGES_PATH, "logoLight.png");
    private static final ImageIcon menuIconLight           = LoadResources.loadImage(IMAGES_PATH, "menuBurgerIconLight.png");
    private static final ImageIcon searchIconLight         = LoadResources.loadImage(IMAGES_PATH, "searchIconLight.png");
    private static final ImageIcon trashIconLight          = LoadResources.loadImage(IMAGES_PATH, "trashLight.png");
    private static final ImageIcon deleteIconLight         = LoadResources.loadImage(IMAGES_PATH, "crossCircleIconLight.png");
    private static final ImageIcon darkModeToggleIconLight = LoadResources.loadImage(IMAGES_PATH, "themeModeIconLight.png");
    private static final ImageIcon plusIconLight           = LoadResources.loadImage(IMAGES_PATH, "plusIconLight.png");
    
    //Non dark/light Icons
    private static final ImageIcon appIcon                 = LoadResources.loadImage(IMAGES_PATH, "appIcon.png");
    
    // Getters
    public ImageIcon getLogoIconDark()       { return logoIconDark; }
    public ImageIcon getLogoIconLight()      { return logoIconLight; }
    public ImageIcon getAppIcon()            { return appIcon; }
    public ImageIcon getMenuIconDark()       { return menuIconDark; }
    public ImageIcon getMenuIconLight()      { return menuIconLight; }
    public ImageIcon getSearchIconDark()     { return searchIconDark; }
    public ImageIcon getSearchIconLight()    { return searchIconLight; }
    public ImageIcon getTrashIconDark()      { return trashIconDark; }
    public ImageIcon getTrashIconLight()     { return trashIconLight; }
    public ImageIcon getDeleteIconDark()     { return deleteIconDark; }
    public ImageIcon getDeleteIconLight()    { return deleteIconLight; }
    public ImageIcon getUserIcon()           { return userIcon; }
    public ImageIcon getDarkModeToggleIconDark()  { return darkModeToggleIconDark; }
    public ImageIcon getDarkModeToggleIconLight() { return darkModeToggleIconLight; }
    public ImageIcon getPlusIconDark()       { return plusIconDark; }
    public ImageIcon getPlusIconLight()      { return plusIconLight; }

    // Scaling helper
    public static ImageIcon getScaledIcon(ImageIcon icon, int width, int height) {
        if (icon == null) return null;
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public static Icons getInstance() {
        if (instance == null) {
            instance = new Icons();
        }
        return instance;
    }
}
