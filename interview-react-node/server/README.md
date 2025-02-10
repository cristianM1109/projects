React Node Interview - Backend API

Overview
This project is a backend API built with NestJS, Prisma, and PostgreSQL. It provides authentication using JWT, and allows users to retrieve and manage invoices securely.

Features
Authentication: Users can log in using email and password, receiving a JWT token.
Invoices API: Retrieve a list of invoices and fetch individual invoice details.
Database: PostgreSQL with Prisma ORM for database management.
Security: JWT authentication and middleware protection for routes.
Error Handling: Global exception handling.
Logging: Middleware-based request logging.
Pagination: Support for paginated invoice retrieval.
Unit Testing: Jest-based test coverage for services and controllers.

Technologies Used
Node.js
NestJS
Prisma ORM
PostgreSQL
Docker
Jest (for unit testing)

Installation
Clone the repository:
git clone https://github.com/cristianM1109/projects/tree/main/interview-react-node/server
cd interview-react-node/server

Install dependencies:

npm install
Configure environment variables:

Create a .env file and add the following:

DATABASE_URL=postgresql://user:password@localhost:5432/invoices_db?schema=public
JWT_SECRET="supersecret"

Start PostgreSQL using Docker:

docker-compose up -d
Run database migrations and seed data:

npx prisma migrate dev --name init
npx prisma db seed

Start the application:
npm run start

API Endpoints

Authentication
POST /auth/login - Login and receive a JWT token

Test Credentials
email: "test@user.com",
password: "password"

Invoices
GET /invoices - Get a paginated list of invoices
GET /invoices/:id - Fetch details of a specific invoice

Running Tests

To run unit tests:
npm run test

Ensure PostgreSQL is running before starting the application.
API requests must include a valid JWT token in the Authorization header.
