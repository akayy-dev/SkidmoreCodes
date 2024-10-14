# SkidmoreCodesClub

## Overview

SkidmoreCodesClub is a Spring Boot application designed to manage email subscriptions and suggestions. It uses PostgreSQL as the database and provides RESTful endpoints for subscribing users and submitting suggestions.

## Features

- **Email Subscription**: Users can subscribe with their email addresses.
- **Suggestions**: Users can submit suggestions along with their email addresses.

## Technologies Used

- **Java 17**
- **Spring Boot 3.3.4**
- **PostgreSQL**
- **Maven**
- **JUnit 5**

## Getting Started

### Prerequisites

- Java 17
- Maven
- PostgreSQL

### Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/SkidmoreCodesClub.git
    cd SkidmoreCodesClub
    ```

2. **Set up the database**:
    - Create a PostgreSQL database.
    - Set the following environment variables:
        ```bash
        export DB_URL=jdbc:postgresql://localhost:5432/yourdatabase
        export DB_USERNAME=yourusername
        export DB_PASSWORD=yourpassword
        ```

3. **Build the project**:
    ```bash
    mvn clean install
    ```

4. **Run the application**:
    ```bash
    mvn spring-boot:run
    ```

## API Endpoints

### Subscribe User

- **URL**: `/subscribe`
- **Method**: `GET`
- **Query Parameter**: `email`
- **Description**: Subscribes a user with the provided email address.
- **Example**:
    ```bash
    curl -X GET "http://localhost:8080/subscribe?email=test@example.com"
    ```

### Send Suggestion

- **URL**: `/send_suggestion`
- **Method**: `POST`
- **Request Body**: JSON
- **Description**: Submits a suggestion along with the user's email address.
- **Example**:
    ```bash
    curl -X POST "http://localhost:8080/send_suggestion" -H "Content-Type: application/json" -d '{"email": "test@example.com", "content": "This is a suggestion."}'
    ```