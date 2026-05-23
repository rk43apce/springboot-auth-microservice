# Progress

## Implemented

- Spring Boot application skeleton.
- MySQL-backed JPA user entity and repository.
- User registration with validation and duplicate-email check.
- BCrypt password hashing.
- Login with password verification.
- JWT generation.
- JWT authentication filter.
- Stateless Spring Security configuration.
- Protected profile endpoint.
- Global validation and runtime exception handling.
- Payment interface plus credit/debit implementations.
- Basic payment controller endpoint.

## Partially Implemented

- JWT validation:
  - Token parsing exists.
  - Subject matching exists.
  - No explicit application-level handling for expired or malformed token responses.

- Payment:
  - Interface and implementations exist.
  - Controller demonstrates fixed credit-card payment.
  - Not yet request-driven or dependency-injected.

## Not Yet Implemented

- Role/authority model.
- Refresh tokens.
- Logout/token revocation.
- User update/delete endpoints.
- API documentation.
- Integration tests.
- Environment-based configuration for secrets and database credentials.
- Real payment workflow.

## Verification Status

Memory bank was created from static source inspection. Tests were not run during memory-bank creation.

Potential blockers for running tests:

- Local MySQL dependency.
- Incomplete property line in `application.properties`.
- Any dependency resolution/network constraints in the local environment.

## Suggested Test Plan

When ready, run:

```bash
./mvnw test
```

For manual API checks:

1. Start MySQL and create `springbootdb`.
2. Run `./mvnw spring-boot:run`.
3. Register a user through `POST /api/v1/auth/register`.
4. Login through `POST /api/v1/auth/login`.
5. Call `GET /api/v1/users/profile` with `Authorization: Bearer <token>`.
