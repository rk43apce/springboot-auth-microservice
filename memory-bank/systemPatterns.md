# System Patterns

## Package Layout

```text
com.example.springbootdemo
├── config
├── contract
├── controller
├── dto
├── entity
├── exception
├── repository
└── service
```

## Controller-Service-Repository Flow

Authentication follows a typical Spring layered flow:

1. `AuthController` accepts validated request DTOs.
2. `UserService` handles registration and login logic.
3. `UserRepository` persists and fetches `User` entities.
4. `JwtService` creates and reads JWTs.
5. `AuthResponse` returns the token to the client.

## Security Flow

1. `SecurityConfig` permits `/api/v1/auth/**` and protects everything else.
2. `JwtAuthenticationFilter` reads the `Authorization` header.
3. If the header starts with `Bearer `, the filter extracts the token.
4. `JwtService` extracts the email subject.
5. `CustomUserDetailsService` loads the user by email.
6. If valid, the filter stores an authenticated `UsernamePasswordAuthenticationToken` in the security context.

## DTO And Validation Pattern

Request DTOs use Jakarta Validation annotations:

- `@NotBlank` for required strings.
- `@Email` for email format.

Controllers annotate request bodies with `@Valid`.

## Exception Pattern

`GlobalExceptionHandler` centralizes API error responses using `@RestControllerAdvice`.

## Payment Pattern

Payment code demonstrates an interface strategy pattern:

- `IPayment` declares `pay(double amount)`.
- `CreditCard` and `DebitCard` implement `IPayment`.
- `PaymentManager` delegates payment execution.

This area is not yet wired into Spring as beans and currently uses manual `new` construction in the controller.

## Style Notes

- Lombok is used for DTOs, entity boilerplate, and constructor injection.
- Some code has rough formatting and small naming issues; preserve behavior when cleaning it up.
