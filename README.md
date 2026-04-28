# InvestPro Backend

Spring Boot backend for the InvestPro mutual fund investment platform.

## Tech Stack
- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## Configuration
The application uses environment variables for configuration:
- `DATABASE_URL`: JDBC URL for MySQL database
- `DB_USERNAME`: Database username
- `DB_PASSWORD`: Database password
- `PORT`: Server port (default 8081)
- `MAIL_HOST`, `MAIL_PORT`, `MAIL_USERNAME`, `MAIL_PASSWORD`: Email SMTP settings

## Building
```bash
./mvnw clean package
```

## Running
```bash
java -jar target/investpro-0.0.1-SNAPSHOT.jar
```

## API Endpoints
- `/auth/register` - User registration
- `/auth/login` - User login
- `/auth/send-otp` - Send OTP
- `/auth/verify-otp` - Verify OTP
- `/auth/resend-otp` - Resend OTP
- `/users/profile/{id}` - Get user profile
- `/funds` - Get all funds
- `/investments/add` - Add investment
- `/investments/user/{id}` - Get user investments
- `/chat` - Chat support