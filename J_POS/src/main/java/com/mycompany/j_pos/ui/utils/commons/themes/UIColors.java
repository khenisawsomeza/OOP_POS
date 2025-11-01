package com.mycompany.j_pos.ui.utils.commons.themes;

import java.awt.Color;

/**
 * Centralized color palette for both Light and Dark themes.
 * Provides semantic grouping for readability and future expansion.
 */
public final class UIColors {

    private UIColors() {} // Prevent instantiation

    /* =========================================================
       🟢 THEME COLORS — BASE TONES
       ========================================================= */
    public static final Color PRIMARY_GREEN_LIGHTMODE = hex(0x8AAC60);
    public static final Color LIGHT_GREEN_LIGHTMODE   = hex(0xA2CA71);
    public static final Color LIGHT_GRAY_LIGHTMODE    = hex(0xF3F4F6);

    public static final Color PRIMARY_GREEN_DARKMODE  = hex(0x6B8E46);
    public static final Color LIGHT_GREEN_DARKMODE    = hex(0x7FAE52);
    public static final Color LIGHT_GRAY_DARKMODE     = hex(0x1C1D21);

    /* =========================================================
       🧱 COMPONENT-SPECIFIC COLORS
       ========================================================= */

    // Item Buttons (Dark Mode)
    public static final Color ITEM_BUTTON_NORMAL_DARKMODE   = hex(0x4E7739);
    public static final Color ITEM_BUTTON_HOVER_DARKMODE    = hex(0x5FA95F);
    public static final Color ITEM_BUTTON_PRESSED_DARKMODE  = hex(0xF4C20D);

    // Add Item Hover States
    public static final Color ADD_ITEM_HOVER_LIGHT          = hex(0xBEBEBE);
    public static final Color ADD_ITEM_HOVER_DARK           = hex(0x505050);
    
    // Sidebar
    public static final Color SIDEBAR_ITEM_HOVER_LIGHTMODE  = new Color(220, 220, 220);
    public static final Color SIDEBAR_ITEM_HOVER_DARKMODE  = new Color(66, 68, 70);
    public static final Color SIDEBAR_ITEM_PRESS_LIGHTMODE  = new Color(200, 200, 200);
    public static final Color SIDEBAR_ITEM_PRESS_DARKMODE  = new Color(55, 57, 59);
    public static final Color SIDEBAR_BACKGROUND            = hex(0x1A1A1B);

    /* =========================================================
       ⚙️ GENERAL UI COLORS
       ========================================================= */
    public static final Color BORDER_GRAY   = hex(0x808080);
    public static final Color BLACK         = Color.BLACK;
    public static final Color WHITE         = Color.WHITE;
    public static final Color TRANSPARENT   = new Color(0, 0, 0, 0);

    /* =========================================================
       🎨 UTILITY METHODS
       ========================================================= */

    /**
     * Helper method to create a Color object from a hex int.
     * Example: hex(0xFFAA00)
     */
    private static Color hex(int rgb) {
        return new Color(rgb);
    }

    /**
     * Returns a color with adjusted brightness.
     * Use this to create hover/pressed states dynamically.
     */
    public static Color adjustBrightness(Color color, float factor) {
        int r = Math.min(255, Math.max(0, (int)(color.getRed() * factor)));
        int g = Math.min(255, Math.max(0, (int)(color.getGreen() * factor)));
        int b = Math.min(255, Math.max(0, (int)(color.getBlue() * factor)));
        return new Color(r, g, b, color.getAlpha());
    }
}
