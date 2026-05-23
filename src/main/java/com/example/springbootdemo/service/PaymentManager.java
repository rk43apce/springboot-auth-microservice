package com.example.springbootdemo.service;

import com.example.springbootdemo.contract.IPayment;

public class PaymentManager {

         IPayment payment;

    public PaymentManager(IPayment payment) {
            this.payment = payment;
        }

        public void processPayment(IPayment payment, double amount) {
            payment.pay(amount);
        }
}
