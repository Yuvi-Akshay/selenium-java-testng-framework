package com.freecrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.freecrm.base.BasePage;
import com.freecrm.utils.WaitUtils;

public class HomePage extends BasePage {

    // Locators
    @FindBy(xpath = "//span[text()='Contacts']")
    private WebElement contactLink;

    @FindBy(xpath = "//span[text()='Deals']")
    private WebElement dealsLink;

    @FindBy(xpath = "//*[@class = 'user-display']")
    private WebElement dispNameLabel;

    @FindBy(xpath = "//*[@class='ui mini basic icon button' and  ./ancestor:: *[@class='menu-item-wrapper' and .//*[text()='Contacts']]]")
    private WebElement addContactBtn;

    @FindBy(xpath = "//*[@class='ui mini basic icon button' and  ./ancestor:: *[@class='menu-item-wrapper' and .//*[text()='Deals']]]")
    private WebElement addDealsBtn;

    private WaitUtils waitUtils;

    public HomePage(WebDriver driver) {
        super(driver);
        this.waitUtils= new WaitUtils(driver);
    }

    public String getHomePageTitle() {
        return driver.getTitle();
    }

    public String getDisplayName() {

        waitUtils.waitForElementVisibility(dispNameLabel);
        return dispNameLabel.getText();
    }

    public ContactsPage goToContacts() {
        waitUtils.waitForElementClickability(contactLink);
        contactLink.click();
        return new ContactsPage(driver);
    }

    public DealsPage goToDeals() {
        waitUtils.waitForElementClickability(dealsLink);
        dealsLink.click();
        return new DealsPage(driver);
    }

    public ContactsPage clickOnAddNewContact() {
        Actions myActions = new Actions(driver);
        myActions.moveToElement(contactLink).build().perform();
        waitUtils.waitForElementClickability(addContactBtn);
        // addContactBtn.click();
        return new ContactsPage(driver);
    }

    public DealsPage clickOnAddNewDeals() {
        Actions myActions = new Actions(driver);
        myActions.moveToElement(dealsLink).build().perform();
        waitUtils.waitForElementClickability(addDealsBtn);
        addDealsBtn.click();
        return new DealsPage(driver);
    }
}
