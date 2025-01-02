# How to Run the Project

## Prerequisites
- **Java**: Ensure you have Java 11 or later installed.
- **Maven**: Make sure Maven is installed and added to your `PATH`.
- **Docker**: Install Docker and Docker Compose.
- **PgAdmin**: Install PgAdmin for database management.

## Steps to Run

### 1. Build Services
For each service (`user-service`, `book-service`, `order-service`):
```bash
mvn clean package
```
This will generate the JAR files required for Docker to build images.

### 2. Move to Root Directory
Navigate to the root directory of the project:
```bash
cd /path/to/project
```

### 3. Build Docker Images
Run the following command to build all Docker images without using cache:
```bash
docker-compose build --no-cache
```

### 4. Start Services
Run the following command to start all services in detached mode:
```bash
docker-compose up -d
```

---

## Setup Database in PgAdmin

### 1. Login to PgAdmin
- **Email**: `admin@admin.com`
- **Password**: `root`

### 2. Get the IP Address of `postgres_container`
Find the IP address of `postgres_container` in the `library_app_network` network:
```bash
docker inspect postgres_container
```

### 3. Create a New Server in PgAdmin
1. Open PgAdmin and create a new server.
2. Use the following details:
   - **Server Name**: `postgres`
   - **Host**: (Use the IP address obtained from the `docker inspect` command)
   - **Username**: `postgres`
   - **Password**: `1234`

### 4. Create Databases
Create the following databases in PgAdmin:
1. `library_book_service_db`
2. `library_user_service_db`
3. `library_order_service_db`

---

## Notes
- Ensure all services are running properly by checking the logs:
  ```bash
  docker-compose logs -f
  ```
- Use the respective service ports for testing endpoints (e.g., `8081` for `book-service`, `8082` for `user-service`).
- For any issues, check the container status:
  ```bash
  docker ps
  
