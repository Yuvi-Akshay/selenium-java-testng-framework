package com.freecrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.freecrm.base.BasePage;

public class LandingPage extends BasePage {


    // locators
    // private By loginBtn = By.xpath("//span[text()='Log In']");
    @FindBy(xpath = "//*[text()='Log In']")
    private WebElement loginBtn;

    @FindBy(xpath = "//a[@title='free crm home']//span[text()='free']")
    private WebElement pageHeader;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public String getLandingPageTitle() {
        return driver.getTitle();
    }

    public LoginPage clickOnLoginBtn() {
        loginBtn.click();
        return new LoginPage(driver);
    }

    public boolean isPageHeaderVisible() {
        return pageHeader.isDisplayed();
    }
}
