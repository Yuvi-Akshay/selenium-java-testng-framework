package com.freecrm.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.freecrm.utils.ConfigReader;
import com.freecrm.utils.DriverFactory;

public class BaseTestPublic {

    protected WebDriver driver;
    protected Properties prop;

    @BeforeMethod
    public void setUp() {
        ConfigReader configReader = new ConfigReader();
        DriverFactory driverFactory = new DriverFactory();

        prop = configReader.initProp();
        driver= driverFactory.initDriver(prop.getProperty("browser"));

        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
