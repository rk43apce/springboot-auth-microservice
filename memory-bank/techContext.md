# Tech Context

## Runtime

- Java: 17.
- Build: Maven using `./mvnw`.
- App entry point: `SpringbootdemoApplication`.
- Default server port: `8080`.

## Main Dependencies

From `pom.xml`:

- `spring-boot-starter-webmvc`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`
- `spring-boot-starter-validation`
- `mysql-connector-j`
- `lombok`
- `jjwt-api`, `jjwt-impl`, `jjwt-jackson` version `0.11.5`
- Spring Boot test starters for JPA, Security, Validation, and Web MVC

Current parent version is `spring-boot-starter-parent` `4.0.6`.

## Configuration

`src/main/resources/application.properties` currently configures:

```properties
spring.application.name=springbootdemo
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/springbootdb
spring.datasource.username=root
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

There is also a trailing incomplete property line: `sprin.main.`

## Common Commands

```bash
./mvnw test
./mvnw spring-boot:run
```

The app requires a local MySQL database named `springbootdb` unless configuration is changed.

## Database Model

`User` maps to table `users`:

- `id`: generated primary key.
- `name`: non-null.
- `email`: non-null, unique.
- `password`: non-null, BCrypt hash.

## Security Model

- Stateless sessions.
- CSRF disabled.
- Form login disabled.
- HTTP Basic disabled.
- Public routes: `/api/v1/auth/**`.
- All other routes require authentication.
- JWT filter runs before `UsernamePasswordAuthenticationFilter`.

## Build Notes

- Lombok annotation processing is configured in the Maven compiler plugin.
- `spring-boot-devtools` appears twice in `pom.xml`.
