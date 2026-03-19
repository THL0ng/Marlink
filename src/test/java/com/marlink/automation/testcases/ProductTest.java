package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {
    ProductPage product ;

    @BeforeMethod
    public void setupPage() {
        product = new ProductPage(driver);
    }

    @Test
    public void TC_01_AddProductToCart(){
        product.clickProductsCategory();
        product.clickTruckCategory();
        product.scrollToProduct();
        product.clickDetailProduct();
        product.clickAddToCartButton();
        Assert.assertEquals(product.getActualMessages(), product.getExpectedMessages("addProduct"));
    }
}
