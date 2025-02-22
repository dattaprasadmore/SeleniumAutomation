package com.ui.tests;

import com.ui.constants.Browser;
import com.ui.pages.HomePage;
import com.ui.utility.*;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Properties;

import static com.ui.constants.Browser.CHROME;

public class TestBase {
    protected HomePage homePage;
    protected static Properties prop;
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private boolean isLambdaTest;
    private boolean isHeadless = false;
    @Parameters({"browser","isLambdaTest","isHeadless","isRemote","envName"})
    @BeforeTest(description = "Load the Home page of the website")
    public void setup(
            @Optional("chrome") String browser,
            @Optional("false") boolean isLambdaTest,
            @Optional("false") boolean isHeadless,
            @Optional("false") boolean isRemote,
            @Optional("QA") String envName)
    {
        prop = PropertiesUtil.initProp(envName);
        WebDriver lambdaDriver;
        WebDriver seleniumGridDriver;
        this.isLambdaTest = isLambdaTest;
        if(isLambdaTest){
            lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser);
            homePage = new HomePage(lambdaDriver);
        }else if(isRemote){
            seleniumGridDriver = SeleniumGridUtility.initGridRemoteDriver(browser);
            homePage = new HomePage(seleniumGridDriver);
        }
        else{
            //Running the test on Local Machine
            logger.info("Load the homepage of the website");
            homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
        }
    }
    public BrowserUtility getInstance(){
        return homePage;
    }
    @AfterTest
    public void tearDown(){
        if(isLambdaTest){
            LambdaTestUtility.quitSession();
        } else{
            homePage.getDriver().quit();
        }
    }
}