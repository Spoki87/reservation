
# Reservation system

Hotel booking system. It allows staff to manage reservations, hotel rooms using authenticated accounts, while guests can book their stays

---

## 🔧 Tech Stack

- Java 17  
- Spring Boot 3
- Spring Security – for session-based authentication (JSESSIONID)
- Hibernate / JPA  
- PostgreSQL
- Lombok  
- Redis
- Kafka
- Docker
- SMTP
- JUnit + Mockito
- Swagger / OpenAPI 3

---

## 🚀 Getting Started

### Clone and Run the Project

```bash
git clone git@github.com:Spoki87/reservation.git
cd reservation
mvn clean package 
docker compose up
```
## 🛡️ Security

The application uses session-based authentication with JSESSIONID tokens managed by Spring Security. This allows secure user login and session persistence without relying on stateless JWT tokens.
- Based on Spring Security's session mechanism
- Protected endpoints require authentication via session cookie
- Easy to extend for role-based access control (RBAC)

## 🗂️ Project Structure

```
com.reservation
├── config             
├── email            
├── exception             
├── hotel          
├── reservation          
├── response    
├── user
    ├──appuser
    ├──security          
```

---
## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

DATABASE:

`DB_URL`
`DB_USERNAME`
`DB_PASSWORD`

MAIL SMTP:

`MAIL_HOST`
`MAIL_PORT`
`MAIL_USERNAME`
`MAIL_PASSWORD`

REDIS:

`REDIS_HOST`
`REDIS_PORT`

KAFKA:

`KAFKA_BOOTSTRAP_SERVERS`




## Example API Endpoints


| Endpoint                         | Method | Description                     |
|----------------------------------|--------|---------------------------------|
| `/api/auth`            | POST   | User authentication               |
| `/api/user`               | POST   | Add new admin   |
| `/api/rooms`                     | GET   | All rooms           |
| `/api/rooms/available`          | GET   | Available rooms     |
| `/api/rooms/available/by-dates`         | GET   | Available rooms filterd by date range      |
| `/api/reservations`         | POST   | Add reservation     |


**Swagger UI available at:**  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
## Authors

- [@Patryk Pawlak](https://www.github.com/Spoki87)

