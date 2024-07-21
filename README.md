# Travel Planner App

## Project Overview
Travel Planner App is a modular monolithic application designed to help users find place coordinates and the cheapest flights based on search criteria. It leverages Google GeoApi for place coordinates and KIWI API for flight searches. The app features a dynamic frontend built with Angular 14 and a robust backend using Java, Spring Boot, and Hibernate. PostgreSQL is used as the database solution, and Docker ensures consistent environments through containerization.

## Screens
![home page](/assets/home-page.png)
![search results](/assets/search-result.png)

## Tech Stack
- **Frontend**: Angular 14
- **Backend**: Java, Spring Boot, Hibernate
- **Database**: PostgreSQL
- **APIs**: Google GeoApi, KIWI API
- **Containerization**: Docker

## Features
- **Place Coordinate Search**: Uses Google GeoApi to find place coordinates.
- **Flight Search**: Uses KIWI API to find nearest airports and cheapest flights.
- **Modular Architecture**: Enhances maintainability and scalability.
- **Interactive UI**: Built with Angular 14 for a responsive user experience.

## Setup and Installation

### Backend
1. **Clone the Repository**:
    ```bash
    git clone https://github.com/jasokolowska/travel-planner-app.git
    cd travel-planner-app
    ```
2. **Configure the Database**:
    ```bash
    docker-compose up
    ```
3. **Build and Run**:
    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```

### Frontend
1. **Clone the Repository**:
    ```bash
    git clone https://github.com/jasokolowska/travel-planner-app-frontend.git
    cd travel-planner-app-frontend
    ```
2. **Install Dependencies**:
    ```bash
    npm install
    ```
3. **Run the Application**:
    ```bash
    ng serve
    ```
4. **Access the Application**:
    - Open your browser and navigate to `http://localhost:4200`.

## Future Improvements
- **User Authentication**: Implement JWT or OAuth2.
- **Enhanced UI/UX**: Use Material-UI or Bootstrap.
- **Caching**: Integrate Redis.
- **Advanced Search**: Add filters for multi-city flights, flexible dates, and preferred airlines.
- **Performance Monitoring**: Use Prometheus and Grafana.
- **API Rate Limiting**: Implement rate limiting for external APIs.

## Contribution
Feel free to fork the repository and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.

## License
This project is licensed under the MIT License.

For more details, visit the [GitHub repository](https://github.com/jasokolowska/travel-planner-app).
