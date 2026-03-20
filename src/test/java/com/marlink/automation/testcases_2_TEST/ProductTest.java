package com.marlink.automation.testcases_2_TEST;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages_2_TEST.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {
    private ProductPage productPage;

    @BeforeMethod
    public void setupPage() {
        productPage = new ProductPage(driver);
    }

    @Test()
    public void TC01_AddProductToCart() {
        productPage.clickProductsCategory();
        productPage.clickTruckCategory();
        productPage.viewProductDetail();
        productPage.clickAddToCartButton();

        String actual = productPage.getActualMessage();

        String expected = "You added Compass to your shopping cart.";

        Assert.assertEquals(actual, expected,
                String.format("Lỗi: Message hiển thị không đúng! Mong đợi: [%s] nhưng thấy: [%s]", expected, actual));
    }
}