package com.freecrm.base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.freecrm.pages.HomePage;
import com.freecrm.pages.LandingPage;
import com.freecrm.pages.LoginPage;
import com.freecrm.utils.ConfigReader;
import com.freecrm.utils.DriverFactory;

import io.github.cdimascio.dotenv.Dotenv;

public class BaseTest {

    protected WebDriver driver;
    protected Properties prop;
    protected HomePage homePage;

    @BeforeMethod
    public void setUp() {
        ConfigReader configReader = new ConfigReader();
        DriverFactory driverFactory = new DriverFactory();

        prop = configReader.initProp();
        String browser = prop.getProperty("browser");

        driver = driverFactory.initDriver(browser);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("url"));

        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = landingPage.clickOnLoginBtn();
        // homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

        // String userName = System.getenv("username");
        // String password = System.getenv("password");
        // System.out.println("Credentials loaded from environment");
        // homePage = loginPage.login(userName, password);

        Dotenv dotenv = Dotenv.load();
        String userName = dotenv.get("username");
        String password = dotenv.get("password");
        homePage = loginPage.login(userName, password);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
