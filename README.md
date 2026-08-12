Author
Rajat Kumar Barik

# Playwright Java Automation Framework

A scalable, maintainable, reusable UI automation framework built using **Java, Playwright, Cucumber, TestNG and Maven**.

The framework follows the **Page Object Model (POM)** design pattern and provides - 
cross browser execution, structured logging, parallel test suite execution, configuration handling, failure screenshot capture, and Allure report integration.

---

## Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 17 | Programming language |
| Playwright | Browser automation |
| Cucumber 7 | BDD / feature-based test development |
| TestNG | Test execution and suite management |
| Maven | Build and dependency management |
| SLF4J | Logging API |
| Logback | Logging implementation |
| Allure | Test reporting |
| Git / GitHub | Source code management |
| Jenkins | CI/CD |

---

# Framework Architecture

The framework follows a layered architecture to keep test logic, UI interaction, browser management, and configuration separated.

```text
                    Feature Files
                         |
                         v
                  Step Definitions
                         |
                         v
                    Page Objects
                         |
                         v
                     BasePage
                         |
                         v
                 PlaywrightManager
                         |
                         v
                     Playwright

#1. PlaywrightManager
PlaywrightManager is responsible for managing the Playwright lifecycle.
Like 
Playwright initialization
Browser creation
Browser Context creation
Page creation

and closing the above Objects

#2. Page Object Model
The framework follows the Page Object Model. Each page in the application has a dedicated Page Object.

For Ex:
| Page              | Class            | Responsibility                           |
|-------------------|------------------|------------------------------------------|
| Login Page        | `LoginPage`      | Handles login form interactions          |

Common Playwright functionality is maintained in: BasePage

#3. Cucumber BDD
Test scenarios are written using Gherkin syntax.
For Ex: When user adds "Sauce Labs Backpack" to the cart

#4. Hooks
Cucumber hooks are used for test lifecycle management.
The hooks handle activities such as:

Browser/page setup
Browser cleanup
Failure handling
Screenshot capture

#5. ConfigReader 
It is responsible for reading configuration properties.
This allows values such as:
Application URL
Browser

#6. Test Suites
The framework supports separate test suites for different execution purposes.
smoke suite
regression suite
master suite ( for executing both smoke and regression suites along with parallel execution )

#7. Reporting
->Cucumber Report
Cucumber HTML reporting is generated during test execution.

Example output:
target/cucumber-report.html

-> Allure Report
Allure integration is included in the framework.

Allure results are generated under:
target/allure-results

#8. Test Data
Cucumber DataTables are used where structured test data is required.

Example:
When user enters following checkout information
    | Field Name | Value  |
    | First Name | Rajat  |
    | Last Name  | Kumar  |
    | Postal Code| 751001 |
