# Food Delivery Platform

This is a food delivery platform project developed using the Spring Boot framework. The project is organized into different packages, including `model`, `controller`, `repo`, and `service`, each of which contains various classes and interfaces to manage different aspects of the system.

## Frameworks and Language Used
- **Language:** Java
- **Framework:** Spring Boot
- **Database:** SQL

## Data Flow

### Model
The `model` package contains the following classes:
- `Admin`: Represents administrative data.
- `User`: Represents user data.
- `Address`: Represents address data.
- `Order`: Represents order data.
- `Food`: Represents food-related data.
- `Restaurant`: Represents restaurant data.

### Controller
The `controller` package includes two main controllers:
- `AdminController`: Manages administrative operations.
- `UserController`: Handles user-related operations.

### Services
Service classes for each model are named similarly, with "Service" appended at the end of the model's name. The services are as follows:
- `AdminService`: Manages administrative operations.
- `UserService`: Handles user-related operations.
- `FoodService`: Manages food-related operations.
- `AddressService`: Manages address-related operations.
- `OrderService`: Manages order-related operations.
- `RestaurantService`: Manages restaurant-related operations.
- `UserAuthService`: Provides authentication services for users.
- `AdminAuthService`: Provides authentication services for administrators.

### Repository
The `repo` package contains repository interfaces that extend `JpaRepository` for each model. The interface names are structured as follows:
- `IAdminRepo`: Repository for admin-related data.
- `IUserRepo`: Repository for user-related data.
- `IFoodRepo`: Repository for food-related data.
- `IAddressRepo`: Repository for address-related data.
- `IOrderRepo`: Repository for order-related data.
- `IRestaurantRepo`: Repository for restaurant-related data.
- `IUserAuthRepo`: Repository for user authentication data.
- `IAdminAuthRepo`: Repository for admin authentication data.

### Database Design
The database schema mirrors the structure of the models, with tables created for `Admin`, `User`, `Food`, `Address`, `Order`, `Restaurant`, `UserAuth`, and `AdminAuth`. These tables store relevant data required for the platform's functionality.

## Data Structures Used
The project employs standard Java data structures, including Lists, Maps, and Plain Old Java Objects (POJOs), to manage data and implement logic for various operations.

## Project Summary
This food delivery platform project, built on the Spring Boot framework and using an SQL database, offers efficient management of admin and user operations. Users can place orders from different restaurants while ensuring secure authentication through the `UserAuth` and `AdminAuth` classes.

The system is structured with a clear separation of concerns, ensuring that the controller, service, and repository layers are distinct. This design facilitates maintainability and scalability of the codebase.

Please note that this README provides a high-level overview of the project's structure and key components. For in-depth information and implementation details, refer to the source code and accompanying documentation.
