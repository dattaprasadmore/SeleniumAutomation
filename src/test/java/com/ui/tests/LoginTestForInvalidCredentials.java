package com.ui.tests;

import com.ui.pojo.User;
import com.ui.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Listeners({com.ui.listeners.TestListener.class})
public class LoginTestForInvalidCredentials extends TestBase{
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private static final String INVALID_EMAIL_ADDRESS = "INVALID_EMAIL@gufutu.com";
    private static final String INVALID_PASSWORD = "INVALID_PASSWORD";

    @Test(description = "Verify if the proper error message is shown for the user when they enter invalid credentials",
            groups={"e2e","sanity", "smoke"})
    public void LoginTest() {
        assertEquals(homePage.gotoLoginPage()
                .doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS, INVALID_PASSWORD)
                .getErrorMessage(),"Authentication failed.");
    }
}