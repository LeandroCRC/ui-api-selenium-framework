 **UI & API Automation Framework – Selenium + RestAssured**

This repository contains a **test automation framework** developed in **Java**, covering both **UI automation** (Selenium WebDriver) and **API automation** (RestAssured).  
The project follows **industry-standard best practices** and is structured to reflect how automation frameworks are built and maintained in real professional environments.

---

1 Project Overview

This framework was created to demonstrate:

- Web UI automation using Selenium WebDriver
- REST API automation using RestAssured
- Clean separation between UI and API layers
- Scalable and maintainable architecture
- Proper use of Page Object Model (POM)
- Test design aligned with real QA Automation roles

The goal of this project is to serve as a **professional portfolio** and a solid base for future expansion.

---

2 Technologies Used

- Java  
- Maven  
- Selenium WebDriver  
- TestNG  
- RestAssured  
- Hamcrest  
- Page Object Model (POM)  
- Git & GitHub  

---

3 Project Structure

## Project Structure

```text
ui-api-selenium-framework/
│
├── src/
│   └── test/
│       └── java/
│           ├── api/
│           │   ├── base/
│           │   │   └── ApiBaseTest.java
│           │   ├── pojo/
│           │   │   └── User.java
│           │   └── test/
│           │       └── UserApiTest.java
│           │
│           └── ui/
│               ├── base/
│               │   └── BaseTest.java
│               ├── pages/
│               │   ├── GooglePage.java
│               │   └── LoginPage.java
│               └── tests/
│                   ├── FirstTest.java
│                   └── LoginTest.java
│
├── pom.xml
├── .gitignore
└── README.md
```
4 UI Automation Layer
- base 
  WebDriver configuration and lifecycle management.

- pages  
  Page Object classes containing locators and actions.

- tests  
  UI test cases that validate user flows and behaviors.

  API Automation Layer
- base  
  Base API configuration (base URI, common setup).

- pojo  
  Plain Old Java Objects used as request bodies.

- test  
  API test cases validating REST endpoints.

---

5 UI Automation Details

- Selenium WebDriver with Page Object Model
- Tests contain assertions only (no UI logic)
- Pages expose behavior, not validations
- Examples included:
  - Google search validation
  - Login attempt with invalid credentials
  - URL validation after failed login
  - Error message visibility and content checks

---

6 API Automation Details

- REST API testing using RestAssured
- CRUD coverage:
  - POST – Create user
  - GET – Retrieve user
  - PUT – Update user data
  - DELETE – Delete user
- Uses POJOs instead of raw JSON strings
- Dynamic data handling
- Validates both status codes and response bodies

---

7 How to Run the Tests

1. Clone the repository:git clone https://github.com/LeandroCRC/ui-api-selenium-framework.git
2. Import the project as a **Maven project**.
3. Run tests using:mvn test

or directly via TestNG from the IDE.

---

8 Future Improvements

- Reporting integration (Allure / Extent)
- CI/CD with GitHub Actions
- Environment configuration support
- Authentication flows
- Parallel execution

---

9 Author

Leandro Rojas  
QA Manual & Automation Engineer  
UI & API Automation – Java

---

 Notes

This project follows **industry-level automation patterns** and is intended for learning, practice, and professional portfolio presentation.

