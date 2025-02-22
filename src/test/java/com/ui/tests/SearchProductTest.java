package com.ui.tests;

import com.ui.pages.HomePage;
import com.ui.pages.MyAccountPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchProductTest extends TestBase{
    private MyAccountPage myAccountPage;
    //private static String  SEARCH_TERM = "printed summer dress";
    private static String  SEARCH_TERM = "Blouse";

    @BeforeMethod(description = "Valid user logs into the application")
    public void setup(){
        myAccountPage = homePage.gotoLoginPage().doLoginWith("hitaci9893@gufutu.com","Admin@12345");
    }

    @Test(description = "Verify if the logged in user is able to search for a product and products are displayed.",
          groups={"smoke", "sanity"})
    public void verifyProductSearchTest(){
        //myAccountPage.searchForProduct(SEARCH_TERM).getAllDressesName(SEARCH_TERM);
        boolean actualResult = myAccountPage.searchForProduct(SEARCH_TERM).isSearchTermPresentInProductList(SEARCH_TERM);
        Assert.assertEquals(actualResult ,true);
    }
}