package com.mycompany.j_pos.ui.utils;

import com.mycompany.j_pos.database.SQLiteConnect;
import com.mycompany.j_pos.models.items.Category;
import com.mycompany.j_pos.models.items.Item;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class LoadResources {
    private static final String FALLBACK_ICON = "src/main/resources/images/noFileIcon.png"; 
    
    private static List<Item> items;
    
    public static List<Item> getItems(){
        return items;
    }
    
    // helper to load safely using File.exists()
    public static ImageIcon loadImage(String IMAGES_PATH, String filename) {
        String fullPath = IMAGES_PATH + filename;
        File iconFile = new File(fullPath);
        if (iconFile.exists()) {
            return new ImageIcon(fullPath);
        } else {
            System.err.println("Missing picture: " + filename);
            return new ImageIcon(FALLBACK_ICON);
        }
    }
    
//    public static boolean hasImage()
    
    public static void loadItems(){
        ArrayList<Item> itemsList = new ArrayList<>();

        String query = """
            SELECT p.product_id, p.product_name, p.price, p.stock_quantity, c.category_id
            FROM products p
            JOIN category c ON p.category_id = c.category_id
        """;

        try (Connection conn = SQLiteConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int prodID = rs.getInt("product_id");
                String prodName = rs.getString("product_name");
                double prodPrice = rs.getDouble("price");
                int stock = rs.getInt("stock_quantity");
                int cat_id = rs.getInt("category_id");

                Item newItem = new Item(prodID, prodName, prodPrice, stock, cat_id);
                itemsList.add(newItem);
            }

        } catch (SQLException e) {
            System.out.println("Database get items error: " + e.getMessage());
        }

        items = itemsList;
    }
    
    public static List<Category> loadCategories() throws FileNotFoundException {
        ArrayList<Category> catList = new ArrayList<>(); 
        String query = """
            SELECT category_id, category_name
            FROM category           
        """;

        try (Connection conn = SQLiteConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int catID = rs.getInt("category_id");
                String catName = rs.getString("category_name");

                Category newCat = new Category(catID, catName);
                catList.add(newCat);
            }

        } catch (SQLException e) {
            System.out.println("Database get categories error: " + e.getMessage());
        }
        return catList;
    }
    
    public static double getTax(){
        String taxString = "";
        double taxValue = 0;
        String query = """
            SELECT setting_value
            FROM settings
            WHERE setting_key = 'tax'
        """;
        
        try (Connection conn = SQLiteConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

           if (rs.next()) {
               taxValue = rs.getDouble("setting_value");
           } else {
               System.out.println("No tax record found.");
           }

       } catch (SQLException e) {
           System.out.println("Database get tax error: " + e.getMessage());
       }

       System.out.println("THE TAX IS: " + taxValue);
        return taxValue;
    }
}
