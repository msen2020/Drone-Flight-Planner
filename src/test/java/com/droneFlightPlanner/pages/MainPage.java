package com.droneFlightPlanner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends CommonPage {

    @FindBy(xpath = "//button[@class= 'md-icon-button md-button md-ink-ripple']")
    public WebElement plusIcon;

    @FindBy(xpath = "//md-content[@class= 'dfp-editor-metadata']")
    public WebElement chooseAPointText;

    @FindBy(xpath = "//p[@ng-show=\"editor.state==='created'\"]")
    public WebElement flightPlanCreatedText;

    @FindBy(xpath = "//p[@class= 'dfp-item-updated-at']")
    public WebElement createdFlightPlan;

    @FindBy(xpath = "//p[@class= 'dfp-item-updated-at']")
    public WebElement flightDescriptionBox;
}
