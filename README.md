
# 📎 URL Shortener API

A simple and secure URL shortener service built with **Spring Boot**.  
It supports **user authentication**, **unique URL mapping per user**, **analytics tracking**, and **ad redirection** after every few clicks.

---

## ✨ Features
- **User Authentication** using Basic Auth (JWT coming soon 🔒)
- **Generate Short URLs** from Long URLs
- **Different users** get different short URLs for the same long URL
- **Same user** gets the same short URL for the same long URL
- **Redirect to original URL** from short URL
- **Track Hit Counts** for each URL
- **Ad Redirection** after every 5 hits
- **User Profile API** (see your own URLs and stats)
- **Pagination** support for URL listings
- **Link Expiry**

---

## 🛠️ Tech Stack
- **Spring Boot** (Backend)
- **Spring Security** (Authentication)
- **H2 / MySQL** (Database)
- **JPA/Hibernate** (ORM)
- **Postman** (API testing)

---

## 📦 Installation and Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/url-shortener.git
   cd url-shortener
   ```

2. **Configure your database** in `application.properties`.  
   Example for H2 (in-memory):
   ```properties
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.h2.console.enabled=true
   ```

3. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access H2 Console** (optional for debugging):
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`

---

## 📜 API Endpoints

| Method | Endpoint | Description |
|:------:|:---------|:-------------|
| `POST` | `/api/signup` | Create new user |
| `POST` | `/api/shorten` | Create a new short URL |
| `GET` | `/api/redirect/{shortUrl}` | Redirect to original URL |
| `GET` | `/api/profile` | Get your profile and URLs |
| `GET` | `/api/myurls` | Get paginated list of your URLs |

---

## 🧠 Future Improvements
- ✅ Add JWT Authentication
- ✅ Customizable ad redirection


---

## 🤝 Contributing
Pull requests are welcome!  
For major changes, please open an issue first to discuss what you would like to change.

---

## 📄 License
This project is licensed under the MIT License.

---

### 🚀 Project Developed By:
**Sai Sri Harsha**

---

## 📌 Badges

![Spring Boot](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
