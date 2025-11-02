/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.controllers.SidebarFunctions;

import com.mycompany.j_pos.database.SQLiteConnect;
import com.mycompany.j_pos.ui.components.cashier_sections.cart_panel_components.bottom_section.CartSummaryPanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Marc Jacob
 */
public class EditTax {
    public static void setTax() {
        try {
            String input = JOptionPane.showInputDialog(null, "Input new tax rate (e.g., 12 for 12%)");
            if (input == null) return;

            double taxInput = Double.parseDouble(input);
            double taxRate = taxInput / 100.0;

            setTaxInDB(taxRate);
            JOptionPane.showMessageDialog(null, "Tax rate updated to " + taxInput + "% \n"
                                              + "Kindly restart the program");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
        }
        CartSummaryPanel.getInstance().refreshSummary();
    }


    
    public static void setTaxInDB(double tax){
        String query = """
            UPDATE settings
            SET setting_value = ?
            WHERE setting_key = 'tax';
        """;
        
        try (Connection conn = SQLiteConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, String.valueOf(tax));
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Tax rate updated to " + tax);
            } else {
                System.out.println("Tax setting not found. Try inserting it first.");
            }

        } catch (SQLException e) {
            System.out.println("Database get tax error: " + e.getMessage());
        }
    }
}
