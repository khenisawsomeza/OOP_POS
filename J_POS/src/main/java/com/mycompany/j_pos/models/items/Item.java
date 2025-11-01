package com.mycompany.j_pos.models.items;

import com.mycompany.j_pos.database.SQLiteConnect;
import com.mycompany.j_pos.ui.utils.LoadResources;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;

public class Item {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int category_id;
    private boolean available;
    private final ImageIcon picture;
    
    private static final String IMAGES_PATH = "src/main/resources/images/foodPics/";  
    
    public Item() {
        this.name = "";
        this.price = 0.00;
        this.picture = LoadResources.loadImage("", ""); // fallback directly
    }

    public Item(int id, String name, double price, int stock, int category_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category_id = category_id;
        this.available = true;

        // load from foodpics folder directly
        this.picture = LoadResources.loadImage(IMAGES_PATH, name + ".png");
        
        System.out.println(this.category_id);
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getCategory() {
        String category = "";
        String query = "SELECT category_name FROM category WHERE category_id = ?";

        try (Connection conn = SQLiteConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, category_id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                category = rs.getString("category_name");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return category;
    }
    public boolean isAvailable() {
        return (stock > 0);
    }
    public ImageIcon getPicture() { return picture; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    public void setCategoryID(int categoryID) { this.category_id = categoryID; }
    public void setAvailable(boolean available) { this.available = available; }

    // Helper methods
    public String getFormattedPrice() {
        return String.format("₱%.2f", price);
    }

}
