# UI & API Automation Framework – Selenium + RestAssured

![API Tests](https://github.com/LeandroCRC/ui-api-selenium-framework/actions/workflows/api-tests.yml/badge.svg)
![UI Tests](https://github.com/LeandroCRC/ui-api-selenium-framework/actions/workflows/ui-tests.yml/badge.svg)

Selenium WebDriver + RestAssured + TestNG + WireMock + GitHub Actions + Allure


This repository contains a fully functional end-to-end automation framework built in Java.  
It includes:

- UI test automation using Selenium WebDriver
- API test automation using RestAssured
- Mocked backend services using WireMock  
- CI/CD pipelines using GitHub Actions (separate UI + API workflows)
- Allure reporting exported as downloadable artifacts  
- Clean, scalable framework architecture, aligned with industry best practices  

This project was designed as a professional portfolio piece to demonstrate real automation engineering capabilities and practical problem-solving relevant to QA Automation roles.

---

## 1. Project Objectives

The framework was created to demonstrate:

- Full-stack QA Automation (UI + API)  
- Architecture used in real companies  
- Maintainability and scalability  
- CI-ready tests (Headless UI + Mocked APIs)  
- Proper engineering practices:
  - TestNG groups (api, ui)
  - Separation of concerns
  - Reusable utilities
  - Report generation (Allure)
  - Dependency management with Maven  

- Handling real-world challenges such as:
  - Google anti-bot blocking (CAPTCHA)
  - Non-deterministic external APIs
  - Tests running reliably inside CI pipelines
  - Mocking backend responses with WireMock

---

## 2. Technologies Used

### Backend/API
- Java 17  
- RestAssured  
- WireMock  

### UI
- Selenium WebDriver  
- Chrome Headless (CI)  
- Explicit Waits  
- Page Object Model  

### Test Framework
- TestNG  
- Maven Surefire  

### Reporting
- Allure Reports  

### CI/CD
- GitHub Actions (separate workflows for UI and API)

---

## 3. Project Structure

```
ui-api-selenium-framework/
│
├── src/test/java/
│   ├── api/
│   │   ├── base/ApiBaseTest.java
│   │   ├── mock/WireMockConfig.java
│   │   ├── pojo/User.java
│   │   └── test/UserApiTest.java
│   │   └── utils/AllureUtils.java
│   └── ui/
│       ├── base/BaseTest.java
│       ├── listeners/UiTestListener.java
│       ├── pages/
│       │   ├── GooglePage.java
│       │   └── LoginPage.java
│       └── tests/
│           ├── GoogleSearchTest.java
│           └── LoginTest.java
│
├── .github/workflows/
│   ├── api-tests.yml
│   └── ui-tests.yml
│
├── pom.xml
└── README.md
```

---

## 4. UI Automation Layer

### BaseTest
- Starts WebDriver  
- Auto-headless in CI  
- Handles lifecycle and cleanup  

### Page Objects
- GooglePage  
- LoginPage  

### Tests
- LoginTest → stable, deterministic  
- GoogleSearchTest → intentionally fails due to Google anti-bot protection

Purpose of Google test:  
Shows understanding of CAPTCHA limitations and why Google is not a reliable AUT in professional automation.

---

## 5. API Automation Layer

### WireMock Integration
Backend endpoints are mocked using WireMock to ensure deterministic and stable API tests.

Stubs cover:
- POST `/users/add`
- GET `/users/1`
- PUT `/users/1`
- DELETE `/users/1`

### UserApiTest
CRUD operations with:
- Status code validation  
- Body validation  
- POJO-based request payloads  

---

## 6. GitHub Actions (CI/CD)

The project contains **two separate pipelines**:

### api-tests.yml
- Runs WireMock mock server  
- Runs only TestNG group `api`  
- Exports Allure results  

### ui-tests.yml
- Runs Selenium in headless mode  
- Runs TestNG group `ui`  
- Exports Allure reports  

---

## 7. Allure Reporting

Generated automatically on each pipeline run.

To open locally:

```
allure serve allure-results
```

Provides:
- Screenshots  
- Steps  
- Request/Response logs  
- Trend charts  

---

## 8. Real-World Challenge: Google CAPTCHA

Google blocks automated headless browsers.  
This test intentionally fails to demonstrate:

- Knowledge of anti-bot systems  
- Debugging skills  
- Awareness of non-deterministic AUTs  
- Reason for using controlled demo environments instead  

The stable UI test (LoginTest) validates actual UI automation skills.

---

## 9. Running Tests Locally

### Run all
```
mvn clean test
```

### API only
```
mvn clean test -Dgroups=api
```

### UI only
```
mvn clean test -Dgroups=ui
```

---

## 10. Future Improvements

- Parallel execution  
- Docker support  
- Authentication scenarios  
- Configurable environments  
- Data factories  

---

## 11. Author  
**Leandro Rojas**  
QA Manual & Automation Engineer  
UI & API Automation – Java  
GitHub: https://github.com/LeandroCRC

---

## 12. Notes  
This project was built to simulate real-company automation work and is intended for recruiter review and portfolio presentation.
