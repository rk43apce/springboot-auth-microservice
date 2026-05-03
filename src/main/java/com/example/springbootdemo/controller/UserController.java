package com.example.springbootdemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/profile")
    public Map<String, String> getProfile(Authentication authentication) {

        return Map.of(
                "email", authentication.getName(),
                "message", "Profile fetched successfully"
        );
    }
}