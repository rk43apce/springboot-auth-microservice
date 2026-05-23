package com.example.springbootdemo.service;

import com.example.springbootdemo.contract.IPayment;

public class DebitCard implements IPayment {

    @Override
    public void pay(double amount) {
        System.out.println("Paying with debit card: " + amount);
    }
}
