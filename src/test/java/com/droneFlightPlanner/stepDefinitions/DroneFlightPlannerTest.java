package com.droneFlightPlanner.stepDefinitions;

import com.droneFlightPlanner.pages.CommonPage;
import com.droneFlightPlanner.utilities.BrowserUtils;
import com.droneFlightPlanner.utilities.ConfigurationReader;
import com.droneFlightPlanner.utilities.Driver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.droneFlightPlanner.stepDefinitions.Hooks.*;


public class DroneFlightPlannerTest extends CommonPage {

    @Given("the user lands on the Main Page")
    public void theUserLandsOnTheMainPage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @Then("the user verifies that the url of the page is {string}")
    public void theUserVerifiesIfTheRelevantPageOpens(String url) {
        BrowserUtils.waitForPageToLoad(25);
        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals("The current URL doesn't match the expected URL", url, currentUrl);
        System.out.println("The Current URL is: " + currentUrl);
    }

    @Then("the user verifies that the titles are visible on the Main Page")
    public void theUserVerifiesThatTheTitlesAreVisibleOnTheMainPage(DataTable titles) {
        BrowserUtils.waitForPageToLoad(15);
        List<String> titleList = titles.asList();
        List<String> foundTitles = new ArrayList<>();

        for (String title : titleList) {
            WebElement element;
            if (title.equals("This is") || title.equals("Create the")) {
                element = driver.findElement(By.xpath("//*[normalize-space(text())='This is']"));
                Assert.assertTrue("The title" + "This is " + " not visible: " + title, element.isDisplayed());
            } else {
                element = driver.findElement(By.xpath("//*[text()='" + title.trim() + "']"));
                Assert.assertTrue("Title not visible: " + title, element.isDisplayed());
                foundTitles.add(title.trim());
            }
            BrowserUtils.verifyElementDisplayed(element);
        }

        // Print out the found titles once after verification
        System.out.println("Found Titles:");
        for (String foundTitle : foundTitles) {
            System.out.println(foundTitle);
        }
    }

    @Then("the user verifies that the Plus Icon is clickable")
    public void theUserVerifiesThatThePlusIconIsClickable() {
        BrowserUtils.verifyElementClickable(mainPage().plusIcon);
    }

    @And("the user clicks on the Plus Icon to set the initial point")
    public void theUserClicksOnThePlusIconToSetTheInitialPoint() {
        BrowserUtils.waitForPageToLoad(15);
        BrowserUtils.wait(3);
        mainPage().plusIcon.click();
    }

    @Then("the user verifies that the text Choose a point near your take off position to start creating your flight plan. is visible")
    public void theUserVerifiesThatTheTextChooseAPointNearYourTakeOffPositionToStartCreatingYourFlightPlanIsVisible() {
        BrowserUtils.verifyElementDisplayed(mainPage().chooseAPointText);
    }

    @And("the user clicks a point on the map")
    public void theUserClicksAPointOnTheMap() {
        BrowserUtils.waitForPageToLoad(15);
        WebElement map = driver.findElement(By.tagName("dfp-editor"));

        // Get map dimensions
        int mapWidth = map.getSize().getWidth();
        int mapHeight = map.getSize().getHeight();

        // Set sample coordinates to click map dimensions
        int endX = mapWidth / 6;
        int endY = mapHeight / 3;

        // Creating click gestures
        actions.moveToElement(map, endX, endY).click()
                .release().perform();
    }

    @Then("the user verifies that the text Good! your Flight Plan has been created. You can now add more checkpoints. is visible")
    public void theUserVerifiesThatTheTextGoodYourFlightPlanHasBeenCreatedYouCanNowAddMoreCheckpointsIsVisible() {
        BrowserUtils.waitForVisibility(mainPage().flightPlanCreatedText);
        BrowserUtils.verifyElementDisplayed(mainPage().flightPlanCreatedText);
    }

    @Then("the user verifies that the Flight Plan Coordinates appears under the Flight Plans title")
    public void theUserVerifiesThatTheFlightPlanCoordinatesAppearsUnderTheFlightPlansTitle() {
        BrowserUtils.verifyElementDisplayed(mainPage().createdFlightPlanCoordinates);
    }

    @Then("the user verifies that created first flight point number appears on the flight plan")
    public void theUserVerifiesThatCreatedFirstFlightPointNumberAppearsOnTheFlightPlan() {
        WebElement flightPointElement = mainPage().firstCreatedFlightPoint;
        // Ensure the element is displayed
        BrowserUtils.verifyElementDisplayed(flightPointElement);

        // Fetch the text content of the element
        String flightPointText = flightPointElement.getText();

        // Extract the number from the text
        String expectedNumber = "1";

        // Verify the extracted number is "1"
        Assert.assertTrue("Expected number '1' is not displayed in the flight point element.", flightPointText.contains(expectedNumber));

    }

    @And("the user inputs a Flight Description")
    public void theUserInputsAFlightDescription() {
        int randomNum = random.nextInt(100);
        char randomLetter = (char) (random.nextInt(26) + 'A');
        BrowserUtils.waitForClickability(mainPage().flightDescriptionBox);
        mainPage().flightDescriptionBox.click();
        mainPage().flightDescriptionBox.sendKeys("My Flight Plan " + randomNum + " " + randomLetter);
    }

    @Then("the user verifies that the Flight Description is displayed")
    public void theUserVerifiesThatTheFlightDescriptionIsDisplayed() {
        BrowserUtils.waitForVisibility(mainPage().flightDescriptionText);
        BrowserUtils.verifyElementDisplayed(mainPage().flightDescriptionText);
    }

    @And("the user clicks on the created Flight Plan")
    public void theUserClicksOnTheCreatedFlightPlan() {
        BrowserUtils.waitForClickability(mainPage().createdFlightPlanCoordinates);
        mainPage().createdFlightPlanCoordinates.click();
    }

    @And("the user clicks multiple points on the map")
    public void theUserClicksMultiplePointsOnTheMap() {
        BrowserUtils.waitForPageToLoad(15);
        WebElement map = driver.findElement(By.tagName("dfp-editor"));

        // Get map dimensions
        int mapWidth = map.getSize().getWidth();
        int mapHeight = map.getSize().getHeight();

        // Set sample coordinates to click map dimensions
        int endX = mapWidth / 6;
        int endY = mapHeight / 3;

        // Creating click gestures
        actions.moveToElement(map, endX, endY).click()
                .release().perform();

//        Second click
        actions.moveToElement(map, endX, endY - 100).click()
                .release().perform();
//        third click
        actions.moveToElement(map, endX - 100, endY - 100).click()
                .release().perform();

//        fourth click
        actions.moveToElement(map, endX - 100, endY).click()
                .release().perform();

//        fifth click
        actions.moveToElement(map, endX - 10, endY).click()
                .release().perform();
    }

    @Test
    public void name() {
        String str = "{_add={}, _divideBy={}, _floor={}, _multiplyBy={}, _round={}, _subtract={}, add={}, clone={}, contains={}, distanceTo={}, divideBy={}, equals={}, floor={}, multiplyBy={}, round={}, subtract={}, toString={}, x=1056, y=690}";

        // way 1
        BrowserUtils.FindCoordinate findCoordinate = new BrowserUtils.FindCoordinate(str);
        System.out.println("findCoordinate.getX() = " + findCoordinate.getX());
        System.out.println("findCoordinate.getY() = " + findCoordinate.getY());

        // way 2
        Map<String, Integer> coordinate = getCoordinate(str);
        System.out.println("coordinate.get(\"x\") = " + coordinate.get("x"));
        System.out.println("coordinate.get(\"y\") = " + coordinate.get("y"));

    }

    Map<String, Integer> getCoordinate(String str) {
        Map<String, Integer> map = new HashMap<>();
        int x = Integer.parseInt(str.split(" x=")[1].split(",")[0]);
        int y = Integer.parseInt(str.split(" y=")[1].split("}")[0]);
        map.put("x", x);
        map.put("y", y);
        return map;
    }

    @And("the user clicks to another point on the map")
    public void theUserClicksToAnotherPointOnTheMap() {
        BrowserUtils.waitForPageToLoad(15);
        WebElement map = driver.findElement(By.tagName("dfp-editor"));

        // Get map dimensions
        int mapWidth = map.getSize().getWidth();
        int mapHeight = map.getSize().getHeight();

        // Set sample coordinates to click map dimensions
        int endX = mapWidth / 6;
        int endY = mapHeight / 3;

        // Creating click gestures
        actions.moveToElement(map, endX, endY).click()
                .release().perform();
        //        Second click
        actions.moveToElement(map, endX, endY - 100).click()
                .release().perform();
    }

    @Then("the user verifies that the text Your data are saved along the way, no need to worry... is visible")
    public void theUserVerifiesThatTheTextYourDataAreSavedAlongTheWayNoNeedToWorryIsVisible() {
        BrowserUtils.waitForVisibility(mainPage().yourDataSavedText);
        BrowserUtils.verifyElementDisplayed(mainPage().yourDataSavedText);
    }

    @Then("the user verifies that created second flight point number appears on the flight plan")
    public void theUserVerifiesThatCreatedSecondFlightPointNumberAppearsOnTheFlightPlan() {
        WebElement flightPointElement = mainPage().secondCreatedFlightPoint;
        // Ensure the element is displayed
        BrowserUtils.verifyElementDisplayed(flightPointElement);

        // Fetch the text content of the element
        String flightPointText = flightPointElement.getText();

        // Extract the number from the text
        String expectedNumber = "2";

        // Verify the extracted number is "2"
        Assert.assertTrue("Expected number '2' is not displayed in the flight point element.", flightPointText.contains(expectedNumber));
    }

    @And("the user adds another flight Plan")
    public void theUserAddsAnotherFlightPlan() {
        BrowserUtils.waitForPageToLoad(15);
        WebElement map = driver.findElement(By.tagName("dfp-editor"));

        // Get map dimensions
        int mapWidth = map.getSize().getWidth();
        int mapHeight = map.getSize().getHeight();

        // Set sample coordinates to click map dimensions
        int endX = mapWidth / 6;
        int endY = mapHeight / 3;

        // Creating click gestures
        actions.moveToElement(map, endX, endY).click()
                .release().perform();

        actions.moveToElement(map, 120, endY - 150).click()
                .release().perform();

        actions.moveToElement(map, endX - 150, endY - 150).click()
                .release().perform();

        actions.moveToElement(map, endX - 150, endY).click()
                .release().perform();

        actions.moveToElement(map, endX - 10, endY).click()
                .release().perform();
    }

    @And("the user clicks on the created second Flight Plan")
    public void theUserClicksOnTheCreatedSecondFlightPlan() {
        mainPage().createdSecondFlightPlan.click();
    }

    @And("the user inputs second Flight Description")
    public void theUserInputsSecondFlightDescription() {
        int randomNum = random.nextInt(100);
        char randomLetter = (char) (random.nextInt(26) + 'A');
        BrowserUtils.waitForClickability(mainPage().flightDescriptionBox);
        mainPage().flightDescriptionBox.click();
        mainPage().flightDescriptionBox.sendKeys("My Flight Plan " + randomNum + " " + randomLetter);
    }

    @Then("the user verifies that there are multiple Created Flight Plan displayed")
    public void theUserVerifiesThatThereAreMultipleCreatedFlightPlanDisplayed() {
        // Locate all flight plan elements
        List<WebElement> flightPlans = driver.findElements(By.cssSelector("md-list-item.dfp-item"));

        BrowserUtils.verifyElementDisplayed(mainPage().createdFlightPlan.get(0));
        BrowserUtils.verifyElementDisplayed(mainPage().secondCreatedFlightPlan.get(1));

        // Verify that there are multiple flight plans
        if (flightPlans.size() > 1) {
            System.out.println("Multiple flight plans are displayed.");
        } else if (flightPlans.size() == 1) {
            System.out.println("Only one flight plan is displayed.");
        } else {
            System.out.println("No flight plans are displayed.");
        }
    }
}

