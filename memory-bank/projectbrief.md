# Project Brief

## Overview

`springbootdemo` is a Java 17 Spring Boot application that demonstrates a REST API with:

- User registration and login.
- JWT-based stateless authentication.
- A protected user profile endpoint.
- A simple payment strategy example using an `IPayment` interface with credit and debit card implementations.

The main package is `com.example.springbootdemo`.

## Primary Goals

- Provide a basic authentication flow:
  - Register a user with name, email, and password.
  - Store users in MySQL through Spring Data JPA.
  - Hash passwords with BCrypt.
  - Login and return a JWT.
  - Use a bearer token to access protected endpoints.
- Keep controller, service, repository, entity, DTO, config, and exception concerns separated.
- Demonstrate interface-driven payment processing.

## Non-Goals For Now

- Production-grade user management.
- Role-based authorization.
- Refresh tokens.
- Payment provider integration.
- Full test coverage.

## Repository Snapshot

- Build tool: Maven wrapper.
- Framework: Spring Boot.
- Database: MySQL.
- Security: Spring Security plus JJWT.
- Tests: one generated context-load test.

## Important Current Note

There are uncommitted changes in the working tree. Treat the current code as the source of truth and avoid reverting existing edits unless explicitly requested.
