package com.ui.pages;

import com.ui.pojo.AddressPojo;
import com.ui.utility.BrowserUtility;
import jdk.jshell.spi.ExecutionControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddressPage extends BrowserUtility {
    private static final By COMPANY_TEXTBOX_LOCATOR = By.id("company");
    private static final By ADDRESS1_TEXTBOX_LOCATOR = By.id("address1");
    private static final By ADDRESS2_TEXTBOX_LOCATOR = By.id("address2");
    private static final By CITY_TEXTBOX_LOCATOR = By.id("city");
    private static final By POSTCODE_TEXTBOX_LOCATOR = By.id("postcode");
    private static final By HOMEPHONE_TEXTBOX_LOCATOR = By.id("phone");
    private static final By MOBILEPHONE_TEXTBOX_LOCATOR = By.id("phone");
    private static final By ADDITIONAL_INFORMATION_TEXTAREA_LOCATOR = By.id("other");
    private static final By ADDRESS_ALIAS_TEXTBOX_LOCATOR = By.id("alias");
    private static final By STATE_DROPDOWN_LOCATOR = By.id("id_state");
    private static  final By SAVE_ADDRESS_BUTTON_LOCATOR = By.id("submitAddress");
    private static final By ADDRESS_HEADING = By.tagName("h3");
    public AddressPage(WebDriver driver){
        super(driver);
    }

    public String saveAddress(){
        enterText(COMPANY_TEXTBOX_LOCATOR,"ABC");
        enterText(ADDRESS1_TEXTBOX_LOCATOR,"Address Line 1");
        enterText(ADDRESS2_TEXTBOX_LOCATOR,"Address Line 2");
        enterText(CITY_TEXTBOX_LOCATOR,"PUNE");
        enterText(POSTCODE_TEXTBOX_LOCATOR,"23456");
        enterText(HOMEPHONE_TEXTBOX_LOCATOR,"020-45789654");
        enterText(MOBILEPHONE_TEXTBOX_LOCATOR,"4578965412");
        enterText(ADDITIONAL_INFORMATION_TEXTAREA_LOCATOR,"Random ABV 12345");
        clearText(ADDRESS_ALIAS_TEXTBOX_LOCATOR);
        enterText(ADDRESS_ALIAS_TEXTBOX_LOCATOR,"Home Address");
        selectFromDropDown(STATE_DROPDOWN_LOCATOR,"Delaware");
        clickOn(SAVE_ADDRESS_BUTTON_LOCATOR);
        String newAddressType = getVisibleText(ADDRESS_HEADING);
        return newAddressType;

    }

    public String saveAddress(AddressPojo addressPojo){
        enterText(COMPANY_TEXTBOX_LOCATOR,addressPojo.getCompany());
        enterText(ADDRESS1_TEXTBOX_LOCATOR,addressPojo.getAddressLine1());
        enterText(ADDRESS2_TEXTBOX_LOCATOR,addressPojo.getAddressLine2());
        enterText(CITY_TEXTBOX_LOCATOR,addressPojo.getCity());
        enterText(POSTCODE_TEXTBOX_LOCATOR,addressPojo.getPostCode());
        enterText(HOMEPHONE_TEXTBOX_LOCATOR,addressPojo.getHomePhoneNumber());
        enterText(MOBILEPHONE_TEXTBOX_LOCATOR,addressPojo.getMobileNumber());
        enterText(ADDITIONAL_INFORMATION_TEXTAREA_LOCATOR,addressPojo.getOtherInformation());
        clearText(ADDRESS_ALIAS_TEXTBOX_LOCATOR);
        enterText(ADDRESS_ALIAS_TEXTBOX_LOCATOR,addressPojo.getAddressAlias());
        selectFromDropDown(STATE_DROPDOWN_LOCATOR,addressPojo.getState());
        clickOn(SAVE_ADDRESS_BUTTON_LOCATOR);
        String newAddressType = getVisibleText(ADDRESS_HEADING);
        return newAddressType;
    }
}