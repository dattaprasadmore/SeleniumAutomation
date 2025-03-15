# Test Automation Framework

This repository contains a Java-based Test Automation Framework designed for functional testing of web applications. The framework is built using Java 17, TestNG, and Maven, with integration for Lambda Test, data-driven testing, headless execution, and logging/reporting mechanisms.

🚀 About Me
Hi, My Name is Dattaprasad More and I have 9 years of experience in Software testing using technologies like Selenium WebDriver, RestAssured.

My major experties is in Java Programming Language.

Authors
@dattaprasadmore
EmailAddress: dattaprasad.more@gmail.com
🔗 Links
portfolio

linkedin

Prerequisites
Before running this framework, ensure the following software is installed on your System.

Java 17 - Make sure Java is installed and JAVA_HOME environment variable is set.

Maven - Ensure Maven is installed and added to the system path

Download link: https://maven.apache.org/download.cgi

Features
Data-Driven Testing: Supports OpenCSV, Gson, Jackson, and Apache POI for reading the test data from CSV, Excel files and JSON.

Fake Data Generation: Uses the Faker library to generate random test data.

Cloud Execution: Tests can be executed on Lambda Test.

Headless Execution: Speeds up test execution.

Command Line Execution: Run tests via Maven Surefire Plugin with customizable parameters.

Logging: Generates detailed logs with Log4j.

Reporting: Generates detailed test reports with Extent Reports.

Technologies Used
Java 17
TestiNG
OpenCSV
Gson
Apache POI
Faker
Log4j
LambdaTest
ExtentReport
Installation
Clone the repository:

git clone https://github.com/dattaprasadmore/SeleniumAutomation.git

cd SeleniumAutomation
Running Tests
You can execute tests via the command line using Maven Surefire Plugin. The framework supports the following parameters:

Running tests on LambdaTest:

mvn test -Dbrowser=chrome -DisLambdaTest=true -DisHeadless=false -DisRemote=false -DenvName=QA -X
Running tests on Chrome browser in HeadLess Mode:

mvn test -Dbrowser=chrome -DisLambdaTest=false -DisHeadless=true -DisRemote=false -DenvName=QA -X
Test Reports & Logs
Reports: After execution, a detailed HTML report will be generated at ./report.html
The report contains information on test cases executed pass failed and skipped, along with screenshots for failed tests.
Logs:
Logs are created during the test execution and stored in the ./logs/ directory.

Integrated with Jenkins
