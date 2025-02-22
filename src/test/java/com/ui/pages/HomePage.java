package com.ui.pages;

import com.ui.constants.Browser;
import com.ui.constants.ConfigProperties;
import com.ui.utility.LoggerUtility;
import com.ui.utility.PropertiesUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import com.ui.utility.BrowserUtility;
import org.openqa.selenium.WebDriver;

public class HomePage extends BrowserUtility {
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private static final By SIGN_IN_LINK_LOCATOR = By.xpath(".//a[contains(text(),\"Sign in\")]");

    public HomePage(Browser browserName, boolean isHeadless) {
        super(browserName, isHeadless);
        goToWebsite(PropertiesUtil.get(ConfigProperties.URL));
    }

    public HomePage(WebDriver driver){
        super(driver);
        goToWebsite(PropertiesUtil.get(ConfigProperties.URL));
    }

    public LoginPage gotoLoginPage(){
        logger.info("Trying to performing click to go to Sign in Page ");
        clickOn(SIGN_IN_LINK_LOCATOR);
        return new LoginPage(getDriver());
    }
}