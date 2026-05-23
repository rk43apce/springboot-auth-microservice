package com.example.springbootdemo.service;

import com.example.springbootdemo.contract.IPayment;

public class CreditCard implements IPayment {

    @Override
    public void pay(double amount) {
        System.out.println("Paying with credit card: " + amount);
    }
}
