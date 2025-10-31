/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.services;

import com.mycompany.j_pos.database.SQLiteConnect;
import com.mycompany.j_pos.ui.MainFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Khenyshi
 */
public class LoginService {
    
    public void authenticateUser(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = SQLiteConnect.getConnection();
            
            if (conn == null) {
                JOptionPane.showMessageDialog(null,
                    "Database connection failed",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String sql = "SELECT user_id, username, role FROM users WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String userRole = rs.getString("role");
                String userName = rs.getString("username");
                
                System.out.println("Login successful: " + userName + " (Role: " + userRole + ")");
                
                // For now, both admin and employee go to CASHIER
                // Future: Add role-based navigation here
                if ("admin".equalsIgnoreCase(userRole)) {
                    MainFrame.getInstance().changeCard("CASHIER");
                } else if ("employee".equalsIgnoreCase(userRole)) {
                    MainFrame.getInstance().changeCard("CASHIER");
                } else {
                    // custom role if want to add or if gusto kay invalid ang unknown roles
                    MainFrame.getInstance().changeCard("CASHIER");
                }
                
                
            } else {
                JOptionPane.showMessageDialog(null,
                    "Invalid username or password",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            System.err.println("SQL Error during authentication: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                "An error occurred during login",
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing database resources: " + e.getMessage());
            }
        }
    }
}
