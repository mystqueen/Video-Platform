# Video-Platform

A comprehensive platform for managing and viewing video content. This project provides features like video upload, playback, categorization, and sharing.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## Features

- Upload and manage video content
- Next and previous video navigation
- Search functionality to find videos easily
- Share video links
- User authentication and authorization (if applicable)

## Installation

### Prerequisites

- Node.js
- npm or yarn
- Git
- Java JDK 11 or later
- Maven

### Backend (Java Spring Boot)

1. Clone the repository:

    ```bash
    git clone https://github.com/mystqueen/Video-Platform.git
    cd Video-Platform/backend
    ```

2. Install the dependencies and build the project:

    ```bash
    mvn clean install
    ```

3. Set up the environment variables:

    Create an `application.properties` file in the `src/main/resources` directory and add the necessary environment variables. Example:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:8080/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    jwt.secret=your_jwt_secret
    ```

4. Run the server:

    ```bash
    mvn spring-boot:run
    ```

### Frontend


1. Navigate to the `frontend` repository:

    https://github.com/mystqueen/videoPlatform-frontend.git

2. Install the dependencies:

    ```bash
    npm install
    ```

3. Run the development server:

    ```bash
    npm start
    ```

## Usage

1. Open your browser and navigate to `http://localhost:3000` for the frontend.
2. The backend API should be running on `http://localhost:8080` (or the port specified in your application properties).

## API Documentation

The API provides the following endpoints:

- `GET /api/v1/videos`: Retrieve a list of all videos
- `GET /api/v1/video/{id}`: Retrieve details of a specific video by ID
- `POST /api/v1/upload`: Upload a new video
- `GET /api/v1/video/{id}/next`: Retrieve the next video
- `GET /api/v1/video/{id}/previous`: Retrieve the previous video

For detailed API documentation, refer to the [API Docs](./docs/api.md).

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes.
4. Push to your branch.
5. Create a pull request.

Please ensure your code follows the project's coding standards and includes tests for new functionality.
