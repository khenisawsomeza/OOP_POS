package com.mycompany.j_pos.models.items;

import com.mycompany.j_pos.ui.utils.LoadResources;
import java.io.File;
import javax.swing.ImageIcon;

public class Item {
    private int id;
    private String name;
    private double price;
    private String category;
    private boolean available;
    private final ImageIcon picture;
    
    private static final String IMAGES_PATH = "src/main/resources/images/foodPics/";  
    
    public Item() {
        this.name = "";
        this.price = 0.00;
        this.picture = LoadResources.loadImage("", ""); // fallback directly
    }

    public Item(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = true;

        // load from foodpics folder directly
        this.picture = LoadResources.loadImage(IMAGES_PATH, name + ".png");
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return available; }
    public ImageIcon getPicture() { return picture; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }
    public void setAvailable(boolean available) { this.available = available; }

    // Helper methods
    public String getFormattedPrice() {
        return String.format("₱%.2f", price);
    }

}
