package com.ui.tests;

import com.ui.pojo.User;
import com.ui.pages.HomePage;
import com.ui.utility.LoggerUtility;
import com.ui.utility.exceptionUtil.ApplicationErrors;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.ui.constants.Browser.CHROME;
import static org.testng.Assert.assertEquals;
@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest extends TestBase{
    Logger logger = LoggerUtility.getLogger(this.getClass());

    /*@Test(description = "Verifies the valid user is able to login into the application", groups={"e2e","sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginTestDataProvider")*/
    public void LoginTest(User user) {
        assertEquals(homePage.gotoLoginPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName(),"Dp dp");
    }
    /*@Test(description = "Verifies the valid user is able to login into the application", groups={"e2e","sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginTestCSVDataProvider")*/
    public void LoginCSVTest(User user) {
        assertEquals(homePage.gotoLoginPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName(),"Dp dp");
    }
    @Test(description = "Verifies the valid user is able to login into the application", groups={"e2e","sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginTestExcelDataProvider",
            retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
    public void LoginExcelTest(User user) {
        assertEquals(homePage.gotoLoginPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName(),"Dp dp");
    }
}