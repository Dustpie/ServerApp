# ServerApp

Disclaimer 12.01.2023:
The Backend API calls work and can be tested with Postman.
The Frontend is still under development.
I am currently in my exam phase and will continue the project after the 30th of January.

Planned: Unit Tests, Thorough documentation, Class Diagramm

This is a full-stack web application for managing server metadata. It allows users to view, add, update, delete, and monitor servers through an interactive Angular frontend interface. The purpose of the application is to provide a system for organizing and tracking server information in a development or IT environment. 

How it works:
On the frontend, the application is built using Angular and utilizes reactive patterns and services for managing state and interacting with the backend API. The UI components make use of Angular directives like ngModel and ngForm for building reactive forms, ngClass for dynamic styling, and async pipe for subscribing to observables. The application state is centralized through a service, and components access it through observables to reactively update the view. 
On the backend, the application uses Spring Boot to build a RESTful API that the Angular frontend calls. It provides endpoints for CRUD operations on the server data. The server data is persisted in a database using Spring Data JPA. 
Key techniques used in the application include centralizing state management, separating concerns into services, reactive programming with observables, declarative UI updates, and integrating frontend and backend components.
