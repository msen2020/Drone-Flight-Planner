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
        BrowserUtils.wait(3);
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
        System.out.println("Sample Coordinates: " + endX + ", " + endY);

        BrowserUtils.moveToElement(endX, endY);
        BrowserUtils.moveToElement(endX, endY - 100);
        BrowserUtils.moveToElement(endX - 100, endY - 100);
        BrowserUtils.moveToElement(endX - 100, endY);
        BrowserUtils.moveToElement(endX - 10, endY);
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
                .moveToElement(map, endX, endY - 100).click()
                .build().perform();
    }

    @Then("the user verifies that the text Your data are saved along the way, no need to worry... is visible")
    public void theUserVerifiesThatTheTextYourDataAreSavedAlongTheWayNoNeedToWorryIsVisible() {
        BrowserUtils.waitForVisibility(mainPage().yourDataSavedText);
        BrowserUtils.verifyElementDisplayed(mainPage().yourDataSavedText);
    }

    @Then("the user verifies that created second flight point number appears on the flight plan")
    public void theUserVerifiesThatCreatedSecondFlightPointNumberAppearsOnTheFlightPlan() {
        WebElement flightPointElement = mainPage().secondCreatedFlightPoint;
        // Verify the element is displayed
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
                .moveToElement(map, 120, endY - 150).click()
                .moveToElement(map, endX - 150, endY - 150).click()
                .moveToElement(map, endX - 150, endY).click()
                .moveToElement(map, endX - 10, endY).click()
                .build().perform();
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
        BrowserUtils.verifyElementDisplayed(mainPage().createdFlightPlan.get(0));
        BrowserUtils.verifyElementDisplayed(mainPage().secondCreatedFlightPlan.get(1));

        // Verify that there are multiple flight plans
        if (mainPage().flightPlans.size() > 1) {
            System.out.println("Multiple flight plans are displayed.");
        } else if (mainPage().flightPlans.size() == 1) {
            System.out.println("Only one flight plan is displayed.");
        } else {
            System.out.println("No flight plans are displayed.");
        }
    }

    @And("the user switches between flight plans")
    public void theUserSwitchesBetweenFlightPlans() {
        mainPage().secondCreatedFlightPlan.get(1).click();
        mainPage().createdFlightPlan.get(0).click();
    }

    @And("the user reloads the page")
    public void theUserReloadsThePage() {
        driver.navigate().refresh();
    }

    @Test
    public void name() {
        String str = "{_add={}, _divideBy={}, _floor={}, _multiplyBy={}, _round={}, _subtract={}, add={}, clone={}, contains={}, distanceTo={}, divideBy={}, equals={}, floor={}, multiplyBy={}, round={}, subtract={}, toString={}, x=1056, y=690}";

        BrowserUtils.FindCoordinate findCoordinate = new BrowserUtils.FindCoordinate(str);
        System.out.println("findCoordinate.getX() = " + findCoordinate.getX());
        System.out.println("findCoordinate.getY() = " + findCoordinate.getY());
    }

    Map<String, Integer> getCoordinate(WebElement element) {
        String str = element.getDomProperty("_leaflet_pos");
        Map<String, Integer> map = new HashMap<>();
        int x = Integer.parseInt(str.split(" x=")[1].split(",")[0]);
        int y = Integer.parseInt(str.split(" y=")[1].split("}")[0]);
        map.put("x", x);
        map.put("y", y);
        return map;
    }

    @Then("the user verifies that the given coordinates are displayed on the map")
    public void theUserVerifiesThatTheGivenCoordinatesAreDisplayedOnTheMap() {
//        String leafletPos = mainPage().points.get(0).getDomProperty("_leaflet_pos");
        Map<String, Integer> coordinate0 = getCoordinate(mainPage().points.get(0));
        int x0 = coordinate0.get("x");
        int y0 = coordinate0.get("y");
        System.out.println("coordinate0: " + x0 + ", " + y0);
        // second point
        Map<String, Integer> coordinate1 = getCoordinate(mainPage().points.get(1));
        int x1 = coordinate1.get("x");
        int y1 = coordinate1.get("y");
        System.out.println("coordinate1: " + x1 + ", " + y1);

        // third point
        Map<String, Integer> coordinate2 = getCoordinate(mainPage().points.get(2));
        int x2 = coordinate2.get("x");
        int y2 = coordinate2.get("y");
        System.out.println("coordinate2: " + x2 + ", " + y2);
        // fourth point
        Map<String, Integer> coordinate3 = getCoordinate(mainPage().points.get(3));
        int x3 = coordinate3.get("x");
        int y3 = coordinate3.get("y");
        System.out.println("coordinate3: " + x3 + ", " + y3);
        Assert.assertEquals(x0, x1);
        Assert.assertEquals(y0, y1 + 100);
        Assert.assertEquals(x1, x2 + 100);
        Assert.assertEquals(y2, y3 - 100);
    }
}

