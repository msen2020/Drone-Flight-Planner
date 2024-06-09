package com.droneFlightPlanner.pages;

import com.droneFlightPlanner.utilities.Driver;
import org.openqa.selenium.support.PageFactory;


public abstract class CommonPage {

    public CommonPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    private MainPage mainPage;

    public MainPage mainPage() {
        if (mainPage == null) {
            mainPage = new MainPage();
        }
        return mainPage;
    }
}
