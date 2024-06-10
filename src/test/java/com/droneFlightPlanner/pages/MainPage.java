package com.droneFlightPlanner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends CommonPage {

    @FindBy(xpath = "//button[@class= 'md-icon-button md-button md-ink-ripple']")
    public WebElement plusIcon;

    @FindBy(xpath = "//md-content[@class= 'dfp-editor-metadata']")
    public WebElement chooseAPointText;

    @FindBy(xpath = "//p[@ng-show=\"editor.state==='created'\"]")
    public WebElement flightPlanCreatedText;

    @FindBy(xpath = "//p[@ng-show=\"editor.state==='go-on'\"]")
    public WebElement yourDataSavedText;

    @FindBy(xpath = "//h4[text() = '1']")
    public WebElement firstCreatedFlightPoint;

    @FindBy(xpath = "//h4[text() = '2']")
    public WebElement secondCreatedFlightPoint;

    @FindBy(xpath = "//p[@class= 'dfp-item-updated-at']")
    public WebElement createdFlightPlanCoordinates;

    @FindBy(xpath = "(//p[@class= 'dfp-item-updated-at'])[2]")
    public WebElement createdSecondFlightPlan;

    @FindBy(xpath = "//input[@ng-model= 'editor.plan.description']")
    public WebElement flightDescriptionBox;

    @FindBy(xpath = "//input[@ng-model= 'editor.plan.description']")
    public WebElement flightDescriptionText;

    @FindBy(xpath = "//button[@class= 'md-no-style md-button md-ink-ripple']")
    public List<WebElement> createdFlightPlan;

    @FindBy(xpath = "//button[@class= 'md-no-style md-button md-ink-ripple']")
    public List<WebElement> secondCreatedFlightPlan;


}
