package com.marlink.automation.testcases_02;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages_02.CartPage;
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
        cartPage.clickToProductsCategory();
        cartPage.clickCarSsvLink();
        cartPage.clickAddToCartAluminiumRescueProduct();
        cartPage.inputqualityProduct();
        cartPage.clickUpdateButton();
        Assert.assertEquals(cartPage.getActualError(), cartPage.getExpectedMessages("errInvalidNumber"));

    }

    @Test
    public void TC_02_Add1QualityProduct_WithoutLogin(){
        cartPage.updateQualityAluminiRescueProduct();
        cartPage.clickUpdateButton();
        cartPage.clickContinueButton();
        cartPage.waitForLoadingInvisible();
        cartPage.clickcartCountButton();
        cartPage.compareQuality();
        cartPage.clickcartCountButton();
    }

    @Test
    public void TC_03_AddMultipleProducts_WithoutLogin(){
        cartPage.clickAddToCartCable3MProduct();
        cartPage.updateQualityCable3MProduct();
        cartPage.clickUpdateButton();
        cartPage.clickContinueButton();
        cartPage.clickcartCountButton();
        cartPage.compareTotalQuantity();
    }


}
