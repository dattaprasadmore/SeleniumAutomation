package com.ui.tests;

import static com.ui.constants.Size.*;
import com.ui.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({com.ui.listeners.TestListener.class})
public class ProductCheckoutTest extends TestBase{
    private static final String SEARCH_TERM = "Printed Summer dress";
    private SearchResultPage searchResultPage;
    @BeforeMethod(description = "Load the Home page of the website")
    public void setup() {
        searchResultPage = homePage.gotoLoginPage().doLoginWith("hitaci9893@gufutu.com", "Admin@12345")
                .searchForProduct(SEARCH_TERM);
    }
    @Test(description = "Verify if the logged in User is able to bye a dress", groups ={"e2e","smoke","sanity"})
    public void checkoutTest() {
        String result = searchResultPage.clickOnTheProductAtIndex(1).changeSize(L).AddProductToCart().proceedToCheckout()
                .goToConfirmAddressPage().goToShipmentPage().goToPaymentPage().makePaymentByWire();
        Assert.assertTrue(result.contains("Your order on My Shop is complete."));
    }
}