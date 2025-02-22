package com.ui.tests;

import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPojo;
import com.ui.utility.FakeAddressUtility;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewFirstAddressTest extends TestBase {
    private MyAccountPage myAccountPage;
    private AddressPojo addressPojo;
    @BeforeMethod
    public void setup() {
        myAccountPage = homePage.gotoLoginPage().doLoginWith("hitaci9893@gufutu.com", "Admin@12345");
        addressPojo = FakeAddressUtility.getFakeAddress();
    }
    @Test(description = "Add New address into Addressbook")
    public void addNewAddressTest() {
        String newAddress = myAccountPage.gotoAddAddressPage().saveAddress(addressPojo);
        Assert.assertEquals(newAddress,addressPojo.getAddressAlias().toUpperCase());
    }
}