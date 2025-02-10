Frontend - React Invoices App

This is the frontend for the Invoices Management Application, built with React, Vite, TypeScript, Redux Toolkit, and Tailwind CSS.

Features

Authentication using email and password
Invoice management with listing and detailed view
Pagination and search for better navigation
Modern styling using Tailwind CSS and ShadCN UI
State management with Redux Toolkit
Automated testing with Playwright
Installation & Running

Clone the repository
git clone https://github.com/cristianM1109/projects/tree/main/interview-react-node/client
cd client

Install dependencies
npm install

Start development server
npm run dev
The frontend will be available at http://localhost:5173

Build and run with Docker
docker build -t react-frontend
docker run -p 5173:80 react-frontend

Testing with Playwright
npx playwright test