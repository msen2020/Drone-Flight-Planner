## README: 
Drone Flight Planner App - Test Execution

### Overview
The Drone Flight Planner App is a web application designed to allow customers to manage their drone flight plans. 
This README file provides guidelines for testing the application, including setup instructions, test execution steps, and reporting procedures.

### Prerequisites

- **Operating System**: Windows 11 or compatible OS
- **Browser**: Google Chrome Version 125.0.6422.142 (Official Build) (64-bit) or compatible browser
- **Testing Tools**: Selenium WebDriver, TestNG, JUnit (or any preferred testing framework)
- **Development Environment**: Visual Studio Code, IntelliJ IDEA, or any preferred IDE
- **Dependencies**: Ensure all necessary libraries and dependencies for Selenium and the testing framework are installed

### Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-repo/drone-flight-planner-app.git
   ```

2. **Install Dependencies**
   Navigate to the project directory and install the required dependencies:
   ```bash
   cd drone-flight-planner-app
   npm install
   ```

3. **Set Up Testing Environment**
   Ensure you have the necessary drivers for Selenium WebDriver installed (e.g., ChromeDriver):
   ```bash
   npm install selenium-webdriver
   ```

4. **Configure Test Environment**
   - Ensure your Selenium WebDriver configuration matches your browser and OS.
   - Update any configuration files to point to the correct paths for your WebDriver.

### Test Execution

#### Running Manual Tests

1. **Open the Application**
   Launch the application in your preferred browser:
   ```bash
   npm start
   ```

2. **Test Scenarios**
   - **Create Flight Plan**
     - Click on the map to create initial and subsequent points.
     - Verify that each point is added correctly and lines are drawn between points.
   - **Switch Between Flight Plans**
     - Save the current flight plan.
     - Create a new flight plan and verify that it can be switched with the saved one.
   - **CRUD Operations**
     - Create, read, update, and delete flight plans.
     - Ensure all operations perform as expected.

3. **Document Findings**
   Record any issues or anomalies encountered during manual testing.
   Use the template provided in the Bug Report section.

#### Running Automated Tests

1. **Set Up Test Suite**
   Ensure all test cases are defined in your testing framework (TestNG, JUnit, etc.). Example setup for TestNG:
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
   <suite name="Drone Flight Planner Test Suite">
       <test name="Flight Plan Tests">
           <classes>
               <class name="com.yourcompany.tests.CreateFlightPlanTest"/>
               <class name="com.yourcompany.tests.CRUDOperationsTest"/>
           </classes>
       </test>
   </suite>
   ```

2. **Execute Tests**
   Run the test suite using your IDE or command line:
   ```bash
   mvn test
   ```

3. **Review Test Results**
   Check the test results for any failed test cases and document them accordingly.

### Reporting

1. **Bug Reporting**
   Use the provided bug report template to document any issues encountered during testing. Include:
   - Summary
   - Steps to Reproduce
   - Expected and Actual Results
   - Severity
   - Additional Information (screenshots, logs)

2. **Test Coverage Report**
   Ensure all features and functionalities of the application are covered by your test cases.
   Document any gaps in coverage and plan for additional test cases if needed.

4. **Test Summary Report**
   Provide a summary of test execution, including:
   - Number of test cases executed
   - Number of test cases passed/failed
   - Issues found and reported
   - Overall assessment of application quality

### Additional Information

- **Contact Information**: For any questions or support, please contact Mehmet Sen (QA Engineer).
- **Repository Link**: [Drone Flight Planner App GitHub Repository](https://github.com/your-repo/drone-flight-planner-app)

### Conclusion

By following the steps outlined in this README, you can effectively test the Drone Flight Planner App to ensure it meets the required quality standards. 
This includes manual and automated testing, thorough documentation of findings, and comprehensive reporting.

