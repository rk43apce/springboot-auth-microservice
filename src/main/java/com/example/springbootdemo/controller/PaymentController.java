package com.example.springbootdemo.controller;

import com.example.springbootdemo.service.CreditCard;
import com.example.springbootdemo.service.PaymentManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @GetMapping("/process")
    public String gerUserName(RequestBody requestBody) {

        CreditCard creditCard = new CreditCard();
        PaymentManager paymentManager = new PaymentManager(creditCard); // Pass a mock or real implementation of IPayment

        paymentManager.processPayment(creditCard, 100.0); // Process a payment of $100

        return "Payment processed successfully";
    }

}
