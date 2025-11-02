/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.payments;

/**
 *
 * @author Khenyshi
 */
public interface PaymentFactory {
    PaymentProcessor createProcessor();
}
