/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.services;

import com.mycompany.j_pos.models.sale.Sale;
import javax.swing.JOptionPane;

/**
 *
 * @author Khenyshi
 */
public class ReceiptService {
    public void printReceipt(Sale sale, double tax, double discount) {
        double subtotal = sale.getTotal();
        double finalTotal = subtotal + tax - discount;
        String receiptText = "";
        receiptText += sale.printReceipt();
        
        receiptText += String.format("TAX: ₱%.2f \n"
                                   + "DISCOUNT: ₱%.2f \n"
                                   + "---------------------------------\n"
                                   + "TOTAL: ₱%.2f", tax, discount, finalTotal);

        System.out.println(receiptText);
        JOptionPane.showMessageDialog(
            null, 
            receiptText, 
            "Receipt", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
