/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.payments.gcash;

import com.mycompany.j_pos.payments.PaymentProcessor;

/**
 *
 * @author Khenyshi
 */
public class GCashPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment() {
        System.out.println("[GCASH] Payment processed via GCash for ₱");
    }
}
