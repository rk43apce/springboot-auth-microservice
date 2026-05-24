package com.example.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@RestController
@RequestMapping("/api/v1/demo")
public class ApiController {


    @GetMapping(value = "/posts", produces = "application/json")
    public Object getPosts()  {

        try {

            return null;
    
        } catch (Exception e) {
            return "HttpClient class not found. Please ensure you are using Java 11 or higher.";
        }
    }


}
