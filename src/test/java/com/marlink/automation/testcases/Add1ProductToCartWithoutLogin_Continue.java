package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.Add1ProductToCartWithoutLogin_ContinuePage;
import org.testng.annotations.Test;

public class Add1ProductToCartWithoutLogin_Continue extends BaseTest {
    @Test
    public void TC_01_Add1ProductToCartWithoutLogin_Continue() throws InterruptedException {
        Add1ProductToCartWithoutLogin_ContinuePage add1Product = new Add1ProductToCartWithoutLogin_ContinuePage(driver);


        add1Product.clickToProductsCategory();
        add1Product.clickToIritrack();
        add1Product.selectProductAndClickAddTocartButton();
        add1Product.inputqualityForm();
        add1Product.clickUpdateButton();
        add1Product.clickContinueButton();
        add1Product.clickBackHomePage();
        add1Product.clickcartCountButton();
        add1Product.compareQuality();




    }
}
