# Ryde - E-Bike Sharing Microservice Platform

Ryde is a comprehensive e-bike sharing platform built as a containerized microservice application using Spring Boot and Hibernate. The system offers distinct functionality for users, employees, and administrators.

## Architecture

The application follows a microservice architecture with:

1. **User Service** - Handles authentication, user profiles, and billing
2. **Bike Service** - Manages bike inventory, status, and location tracking
3. **Station Service** - Controls station information and bike availability
4. **Rental Service** - Processes rental transactions and trip records
5. **Analytics Service** - Generates reports and metrics

All services are containerized with Docker and orchestrated for seamless deployment.

## Features

### User Features
- Account creation and management
- Bike reservation and rental between stations
- Real-time bike availability and station maps
- Trip history and detailed reports
- Payment processing and invoice generation
- Mobile-responsive web interface

### Admin/Employee Features
- Comprehensive dashboard with real-time stats
- Bike utilization and maintenance tracking
- User behavior analytics
- Revenue and usage reports
- Station capacity optimization tools
- System-wide configuration management

## Technology Stack

- **Backend**: Spring Boot microservices with REST APIs
- **Database**: PostgreSQL with Hibernate ORM
- **Security**: Spring Security with JWT authentication
- **Reporting**: JasperReports for PDF generation
- **Containerization**: Docker with docker-compose
- **Testing**: JUnit, Mockito, and TestContainers
