package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    CartPage cartPage;

    @BeforeMethod
    public void setupPage() {
        cartPage = new CartPage(driver);
    }

    @Test
    public void TC_01_AddInvalidQualityProduct_WithoutLogin() {
        cartPage.clickProductsCategoryLink();
        cartPage.clickCarSsvLink();

        cartPage.clickAddAluminiumButton();

        cartPage.inputInvalidQuality();
        cartPage.clickUpdateButton();

        Assert.assertEquals(cartPage.getActualErrorLabel(), cartPage.getExpectedMessages("errInvalidNumber"));
    }

    @Test
    public void TC_02_Add1QualityProduct_WithoutLogin() {
        cartPage.updateAluminiumQuality();
        cartPage.clickUpdateButton();
        cartPage.clickContinueButton();

        cartPage.waitForLoadingInvisible();
        cartPage.clickCartCountButton();

        cartPage.compareQuality();

        cartPage.clickCartCountButton();
    }

    @Test
    public void TC_03_AddMultipleProducts_WithoutLogin() {
        cartPage.clickAddCable3MButton();

        cartPage.updateCable3MQuality();

        cartPage.clickUpdateButton();
        cartPage.clickContinueButton();

        cartPage.clickCartCountButton();
        cartPage.compareTotalQuantity();
    }

    @Test
    public void TC_04_CheckSubTotalWhenAddMultiProducts() {
        cartPage.comparePriceWithSubtotal();
    }
}