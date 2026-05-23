# Active Context

## Current Focus

The project currently appears to be a learning/demo Spring Boot API centered on authentication and basic dependency-inversion ideas.

The auth flow is the most complete part of the system. The payment flow looks experimental and should be treated as in progress.

## Working Tree State At Memory Bank Creation

`git status --short` showed modified or added files including:

- `pom.xml`
- `src/main/java/com/example/springbootdemo/contract/IPayment.java`
- `src/main/java/com/example/springbootdemo/controller/PaymentController.java`
- `src/main/java/com/example/springbootdemo/service/CreditCard.java`
- `src/main/java/com/example/springbootdemo/service/DebitCard.java`
- `src/main/java/com/example/springbootdemo/service/PaymentManager.java`
- `src/main/java/com/example/springbootdemo/service/UserService.java`
- `src/main/resources/application.properties`

Do not revert those changes unless the user asks.

## Known Issues And Sharp Edges

- `application.properties` ends with an incomplete line: `sprin.main.`
- `pom.xml` contains duplicate `spring-boot-devtools` dependencies.
- `JwtService` has a hardcoded secret key.
- JWT expiration is set but `isTokenValid` only checks subject equality; expiration is enforced indirectly if parsing throws.
- `JwtAuthenticationFilter` does not locally handle malformed or expired tokens.
- `PaymentController` has method name `gerUserName`, which does not match behavior.
- `PaymentController` imports `Authentication` but does not use it.
- `PaymentController.gerUserName` accepts `RequestBody requestBody` without `@RequestBody` and without using it.
- `PaymentController` manually instantiates `CreditCard` and `PaymentManager` instead of using Spring beans.
- `PaymentManager` stores an `IPayment` field but `processPayment` also accepts a separate `IPayment` parameter.
- `IPayment.pay` explicitly says `public`, which is redundant in an interface.
- Test coverage is minimal.

## Good Next Steps

1. Fix the malformed `application.properties` line.
2. Remove duplicate `spring-boot-devtools` dependency.
3. Externalize JWT secret and expiration.
4. Add tests for registration, login, and protected endpoint access.
5. Refactor payment code into Spring-managed services or clarify that it is a plain Java strategy demo.
6. Add request/response DTOs for payment processing if payment remains an API feature.

## Development Caution

The app likely needs a running MySQL instance at `localhost:3306` with database `springbootdb` before `./mvnw spring-boot:run` or context-load tests will pass.
