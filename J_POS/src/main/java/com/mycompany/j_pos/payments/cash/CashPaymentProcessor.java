/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.payments.cash;

import com.mycompany.j_pos.payments.PaymentProcessor;

/**
 *
 * @author Khenyshi
 */
public class CashPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment() {
        System.out.println("[CASH] Payment received: ₱");
    }
}


