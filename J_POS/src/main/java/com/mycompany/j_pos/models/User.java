/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.models;

/**
 *
 * @author Marc Jacob
 */
public class User {
    private String username;
    private String password;

    public User(){
        
    }
    
    // constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // getters & setters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}

