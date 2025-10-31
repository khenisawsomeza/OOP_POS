package com.mycompany.j_pos.ui.utils;

import com.mycompany.j_pos.models.items.Category;
import com.mycompany.j_pos.models.items.Item;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
    
    /**
     * Create sample items for demonstration
     * TODO: Replace with actual item loading from database/file
     */
    public static void loadSampleItems() throws FileNotFoundException {
        ArrayList<Item> itemsList = new ArrayList<>();
        try (Scanner scanner = new Scanner(LoadResources.class.getResourceAsStream("/tempDB/entrees.txt"))) {
            while (scanner.hasNext()) {
                int prodID;
                String prodName, prodCat;
                double prodPrice;

                prodID = scanner.nextInt();
                scanner.nextLine();
                prodName = scanner.nextLine();
                prodPrice = scanner.nextDouble();
                scanner.nextLine();
                prodCat = scanner.nextLine();

                Item newItem = new Item(prodID, prodName, prodPrice, prodCat);
                itemsList.add(newItem);
                
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        items = itemsList;
    }
    
    public static List<Category> loadSampleCategories() throws FileNotFoundException {
        ArrayList<Category> catList = new ArrayList<>(); 
        try (Scanner scanner = new Scanner(LoadResources.class.getResourceAsStream("/tempDB/categories.txt"))) {
            while (scanner.hasNext()) {
                String catID = scanner.nextLine();
                String catName = scanner.nextLine();
                Category newCat = new Category(catID, catName);
                catList.add(newCat);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return catList;
    }
}
