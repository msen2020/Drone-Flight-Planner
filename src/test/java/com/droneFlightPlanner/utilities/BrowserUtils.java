package com.droneFlightPlanner.utilities;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserUtils {

    public static void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickability(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = driver -> {
            assert driver != null;
            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public static void verifyElementDisplayed(WebElement element) {
        try {
            boolean isElementDisplayed = element.isDisplayed();
            System.out.println("Is Element Displayed: " + isElementDisplayed);

            Assert.assertTrue("Element is not visible: " + element, isElementDisplayed);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);
        }
    }

    public static void verifyElementClickable(WebElement element) {
        try {
            Assert.assertTrue("Element is not clickable: " + element, element.isEnabled());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);
        }
    }

    public static void wait(int secs) {
        try {
            Thread.sleep(1000L * secs);
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException |
                 InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class FindCoordinate {
        private final int x;
        private final int y;

        public FindCoordinate(String str) {
            x = Integer.parseInt(str.split(" x=")[1].split(",")[0]);
            y = Integer.parseInt(str.split(" y=")[1].split("}")[0]);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
