/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.facade;

import com.mycompany.j_pos.models.cart.*;
import com.mycompany.j_pos.models.items.*;
import com.mycompany.j_pos.models.sale.*;
import com.mycompany.j_pos.services.*;
import com.mycompany.j_pos.ui.components.Navigation;
import com.mycompany.j_pos.ui.components.cashier_sections.items_panel_components.ItemsListPanel;
import com.mycompany.j_pos.ui.utils.LoadResources;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class POSFacade {

    private static POSFacade instance;

    private final Cart cart;
//    private final ItemService itemService;
    private final SaleService saleService;
    private final ReceiptService receiptService;
    private final LoginService loginService;
    private List<Item> items = null;

    private POSFacade() {
        this.cart = Cart.getInstance();
//        this.itemService = new ItemService();
        this.saleService = new SaleService();
        this.receiptService = new ReceiptService();
        this.loginService = new LoginService();
    }

    public static POSFacade getInstance() {
        if (instance == null) instance = new POSFacade();
        return instance;
    }

//    public List<Category> loadMenuFromDB() {
//        return itemService.loadCategories();
//    }

//    /** Returns all available items from a given category. */
//    public List<Item> getItemsByCategory(String categoryName) {
//        return itemService.getItemsByCategory(categoryName);
//    }

    public void addItemToCart(Item item) {
        cart.addItem(item);
    }

    public void removeItemFromCart(Item item) {
        cart.removeItem(item);
    }

    public void clearCart() {
        cart.clearCart();
    }

    public List<CartEntry> getCartItems() {
        return cart.getItems();
    }

    public double getCartTotal() {
        return cart.getTotal();
    }

    public String getFormattedCartTotal() {
        return cart.getFormattedTotal();
    }

    public int getTotalItemCount() {
        return cart.getTotalItemCount();
    }

    public void applyDiscount(double amount) {
        cart.setDiscountAmount(amount);
    }

    /** Finalizes the sale: creates Sale, saves it, prints receipt, clears cart. */
    public Sale checkout() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Cannot checkout.");
            JOptionPane.showMessageDialog(
                null, 
                "Cart is empty. Cannot checkout.", 
                "Receipt", 
                JOptionPane.INFORMATION_MESSAGE
            );
            return null;
        }

        // Create sale from cart
        Sale sale = saleService.createSaleFromCart(cart);

        // Save sale record
        saleService.saveSale(sale);

        // Print receipt
        receiptService.printReceiptToPDF(
            sale,
            cart.getTaxAmount(),
            sale.getDiscountAmount()
        );

        // Clear cart
        cart.clearCart();
        System.out.println("Checkout completed successfully!");

        return sale;
    }
    
    public void login(String uname, String pass){
        loginService.authenticateUser(uname, pass);
        try{
            LoadResources.loadItems(); 
            items = LoadResources.getItems();
        } catch (Exception e){
            System.out.println("failed to load items");
        }
    }
    
    public void refreshItemsDisplay(){
        ItemsListPanel.getInstance().refreshItemsDisplay(items);
    }
}
