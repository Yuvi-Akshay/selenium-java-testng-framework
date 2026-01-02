package com.freecrm.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public WebDriver initDriver(String browser){
        WebDriver driver= null;

        // if(browser == null){
        //     browser = "chrome";
        // }

        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver= new ChromeDriver();
        }
        else  if(browser.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver= new EdgeDriver();
        }
         else  if(browser.equalsIgnoreCase("fireFox")){
            WebDriverManager.firefoxdriver().setup();
            driver= new FirefoxDriver();
        }
        else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        return driver;
        
    }
    
}
