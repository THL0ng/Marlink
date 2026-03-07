package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.ClickOnProductToAddToCart_ContinuePage;
import org.testng.annotations.Test;

public class ClickOnProductToAddToCart_Continue extends BaseTest {
    @Test
    public void TC_01_ClickDetailProduct(){

        ClickOnProductToAddToCart_ContinuePage detailProduct = new ClickOnProductToAddToCart_ContinuePage(driver);

        detailProduct.clickProductsCategory();
        detailProduct.clickTruckCategory();
        detailProduct.scrollToProduct();
        detailProduct.clickDetailProduct();
        detailProduct.clickAddToCartButton();
        detailProduct.checkAddToCartSuccessfully();
    }
}
