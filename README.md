# Spring Microservices Project

This repository contains a Spring Boot microservices project with the following components:

- Eureka Server
- Gateway Service
- User Management Service
- Auth Service (JWT)

## Project Overview

The project follows a microservices architecture, where each service has its own responsibility, and communication between services is managed through Eureka Server and API Gateway.

### Components:

1. **Eureka Server:**
   - Service discovery server that allows microservices to register and discover each other.

2. **Gateway Service:**
   - API Gateway that serves as the entry point for external clients.
   - Routes incoming requests to the appropriate microservices.
   - Handles common concerns like authentication, authorization, and load balancing.

3. **User Management Service:**
   - Manages user-related operations such as user registration, profile retrieval, etc.

4. **Auth Service (JWT):**
   - Provides authentication and authorization using JSON Web Tokens (JWT).
   - Generates tokens for authenticated users and validates them for secure endpoints.
