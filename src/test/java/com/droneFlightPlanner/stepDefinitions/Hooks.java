package com.droneFlightPlanner.stepDefinitions;

import com.droneFlightPlanner.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Random;

public class Hooks {

    public static boolean isHeadless = false;
    public static String browserType = "chrome";

    public static WebDriver driver; // Declare WebDriver instance in Hooks class
    public static Actions actions;
    public static Random random = new Random();

    @Before(order = 1)
    public void setUp() {
        // This will run before each test method
        driver = Driver.getDriver(); // Assign the WebDriver instance returned by Driver.get() to the driver variable
        actions = new Actions(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.manage().deleteAllCookies(); //Clear cookies after each test
//            Driver.closeDriver();
        }
    }
}
