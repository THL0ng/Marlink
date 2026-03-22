package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.ProductPage;
import com.marlink.automation.utils.JsonHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {
    private ProductPage productPage;
    private static final Logger log = LogManager.getLogger(ProductTest.class);
    @BeforeMethod
    public void setupPage() {
        productPage = new ProductPage(driver);
    }

    @Test()
    public void TC01_AddProductToCart() {
        log.info("=== START TC_01: Verify Add Product to Cart flow ===");
        productPage.navigateToProductsCategory();
        productPage.navigateToTruckCategory();
        productPage.clickImageProductDetail();
        productPage.clickButtonAddToCart();
        String actual = productPage.getLabelMessageSuccess();
        Assert.assertEquals(actual, JsonHelper.get("product_add_success"), "Lỗi: Sản Phẩm Không Được Thêm Vào Giỏ Hàng Thành Công");
        log.info("=== PASSED TC_01 ===");
    }
}