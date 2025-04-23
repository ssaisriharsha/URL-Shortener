# URL Shortener

A simple Java backend to shorten long URLs, similar to [bit.ly](https://bit.ly).

## Technologies Used

- Java  
- Spring Boot  
- Spring MVC  
- Spring Data JPA  
- PostgreSQL  
- `java.security.SecureRandom`  
- `java.net.http`  

## Key Features

- Takes any long URL as input, shortens it, and returns the short URL.
- Maintains the record of how many times the URL is clicked, allowing the user to view analytics.
- For every 5 clicks, the short URL redirects to [google.com](https://google.com). This mechanism can be replaced and utilized to display ads for monetization, if needed.
- CORS is implemented by default. You can simply download the JAR file from the releases, run it, and integrate it with your frontend.

## API Documentation

The following endpoints are exposed by the application:

- `GET /api/{shortURL}`  
  Redirects you to the corresponding long URL, if it exists.

- `POST /api/shorten`  
  Takes a long URL as input and returns a unique short URL.

  **Headers:**  
  `Content-Type: application/json`

  **Request Body:**

  ```json
  {
    "longURL": "https://www.example.com"
  }

If no protocol is specified, the API assumes https by default.
