package com.freecrm.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.freecrm.reports.ExtentManager;
import com.freecrm.utils.DriverFactory;
import com.freecrm.utils.ScreenshotUtil;

public class ExtentTestListener implements ITestListener {

    private ExtentReports extent = ExtentManager.getExtentReports();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        WebDriver driver = DriverFactory.getDriver();
        String screenshotPath = ScreenshotUtil.takeScreenShot(driver, testName);// your existing method in TestUtil
        extentTest.get().fail(result.getThrowable());
        extentTest.get().addScreenCaptureFromPath(screenshotPath);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
