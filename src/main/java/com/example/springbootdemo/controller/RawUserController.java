package com.example.springbootdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/raw-users")
public class RawUserController {

    private final JdbcTemplate jdbcTemplate;

    public RawUserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/by-email")
    public ResponseEntity<Map<String, Object>> findUserByEmail(@RequestParam String email) {
        log.debug("Searching user by email with JdbcTemplate: {}", email);

        String sql = "SELECT id, name, email FROM users WHERE email = ?";

        try {
            Map<String, Object> user = jdbcTemplate.queryForMap(sql, email);
            log.info("User found with JdbcTemplate for email: {}", email);
            return ResponseEntity.ok(user);
        } catch (EmptyResultDataAccessException ex) {
            log.warn("User not found with JdbcTemplate for email: {}", email);
            return ResponseEntity.notFound().build();
        }
    }
}
