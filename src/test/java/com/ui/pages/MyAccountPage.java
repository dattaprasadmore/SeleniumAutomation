package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.ui.utility.BrowserUtility;

public final class MyAccountPage extends BrowserUtility {
    private static final By USER_NAME_LOCATOR = By.xpath("//a[@title='View my customer account']");
    private static final By SEARCH_TEXT_BOX_LOCATOR = By.xpath("//input[@id='search_query_top']");
    private static final By ADD_NEW_ADDRESS_LOCATOR = By.xpath("//a[@title='Add my first address']");
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }
    public String getUserName(){
        return getVisibleText(USER_NAME_LOCATOR);
    }
    public SearchResultPage searchForProduct(String productName){
        enterText(SEARCH_TEXT_BOX_LOCATOR,productName);
        enterSpecialKey(SEARCH_TEXT_BOX_LOCATOR, Keys.ENTER);
        return new SearchResultPage(getDriver());
    }

    public AddressPage gotoAddAddressPage(){
        clickOn(ADD_NEW_ADDRESS_LOCATOR);
        return new AddressPage(getDriver());
    }

}