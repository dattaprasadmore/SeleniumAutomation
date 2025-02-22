package com.ui.pages;

import com.ui.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShipmentPage extends BrowserUtility {

    private static final By ACCEPT_TERMS_AND_CONDITIONS_CHECKBOX_LOCATOR = By.id("uniform-cgv");
    private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.name("processCarrier");

    public ShipmentPage(WebDriver driver) {
        super(driver);
    }
    public void selectTermsAndConditions(){
        clickOn(ACCEPT_TERMS_AND_CONDITIONS_CHECKBOX_LOCATOR);
    }

    public PaymentPage goToPaymentPage(){
        clickOnCheckBox(ACCEPT_TERMS_AND_CONDITIONS_CHECKBOX_LOCATOR);
        clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
        return new PaymentPage(getDriver());
    }

}