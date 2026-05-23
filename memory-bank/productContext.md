# Product Context

## API Behavior

The app exposes REST endpoints under `/api/v1`.

### Authentication

Base path: `/api/v1/auth`

- `POST /register`
  - Accepts `UserRegistrationRequest`.
  - Required fields: `name`, `email`, `password`.
  - Validates email format.
  - Rejects duplicate email addresses.
  - Returns a success message string.

- `POST /login`
  - Accepts `LoginRequest`.
  - Required fields: `email`, `password`.
  - Validates credentials against the stored BCrypt password.
  - Returns `AuthResponse` containing a JWT token.

### User Profile

Base path: `/api/v1/users`

- `GET /profile`
  - Requires `Authorization: Bearer <token>`.
  - Returns the authenticated email and a success message.

### Payment

Base path: `/api/v1/payment`

- `GET /process`
  - Requires authentication because only `/api/v1/auth/**` is publicly allowed.
  - Currently creates `CreditCard` and `PaymentManager` manually.
  - Processes a fixed amount of `100.0`.
  - Returns `"Payment processed successfully"`.

## Error Handling

`GlobalExceptionHandler` converts:

- `RuntimeException` to HTTP 400 with `{ "message": "..." }`.
- Validation failures to HTTP 400 with `{ "<field>": "<message>" }`.

## Expected Client Flow

1. Register a user.
2. Login with the registered credentials.
3. Send returned JWT as a bearer token for protected endpoints.

## Product Risks

- JWT secret is hardcoded in source.
- Authentication failures and JWT parsing failures are handled broadly through runtime exceptions.
- Payment endpoint is a demo and not yet integrated with Spring dependency injection or real request data.
