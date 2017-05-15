Project Title:
===============

Java UI Automation Framework
-----------------------------

This project provides a Java UI Automation framework with sample test scenarios which automates some of the webpages like ultimate QA.

This repository has two layers 

- FRAMEWORK LAYER::
  Framework layer contains the common methods to handle WebDriver and WebElements. This layer uses Log4j for logging. This layer could be exported as a package and used independent of webpages to be automated.  
- TEST AUTOMATION LAYER::
  Test Automation layer is a simple Maven project which consumes the Framework layer. This layer is implemented with Page Object Model as design pattern. This layer is built using TestNG and Cucumber to enable Business readable Web Tests (BDD).

Prerequisites:
---------------
Eclipse IDE

Built With:
------------
- Java - Programming Language 
- Selenium WebDriver -Web Automation tool
- Cucumber - Behavior Driven Development
- TestNG - Testing Framework
- Maven - Dependency Management

Author:
---------
Rubini Dhanasekaran




