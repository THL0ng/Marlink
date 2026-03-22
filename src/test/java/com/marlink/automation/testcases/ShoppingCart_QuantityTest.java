package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.ShoppingCart_QuantityPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShoppingCart_QuantityTest extends BaseTest {
    ShoppingCart_QuantityPage shoppingQTY;
    private static final Logger log = LogManager.getLogger(ShoppingCart_QuantityTest.class);

    @BeforeMethod
    public void setupPage() {
        shoppingQTY = new ShoppingCart_QuantityPage(driver);
    }

    @Test
    public void TC_01_DecreaseQTY() {
        log.info("=== START TC_01: Decrease Quantity in Mini Cart ===");
        shoppingQTY.clickProductsCategory();
        shoppingQTY.clickCarSSVCategory();
        shoppingQTY.addStrobeLampToCart();

        shoppingQTY.openMiniCart();
        shoppingQTY.inputInitialQtyMiniCart();
        shoppingQTY.clickDecreaseQTYRandomly();
        shoppingQTY.clickUpdateMiniCart();

        shoppingQTY.clickContinueButton();
        shoppingQTY.openMiniCart();
        shoppingQTY.verifyQualityInCart();

        String actual = shoppingQTY.getActualQuantityInCart();
        String expected = shoppingQTY.getSavedQty();
        log.info("TC01 - Expected: {}, Actual: {}", expected, actual);
        Assert.assertEquals(actual, expected, "Lỗi: Số lượng sau khi giảm không khớp!");
        log.info("=== PASSED TC_01 ===");

    }

    @Test
    public void TC_02_IncreaseQTY() {
        log.info("=== START TC_02: Increase Quantity in Mini Cart ===");
        shoppingQTY.openMiniCart();
        shoppingQTY.addStrobeLampToCart();
        shoppingQTY.inputInitialQtyMiniCart();
        shoppingQTY.clickIncreaseQTYRandomly();
        shoppingQTY.clickUpdateMiniCart();

        shoppingQTY.clickContinueButton();
        shoppingQTY.openMiniCart();
        shoppingQTY.verifyQualityInCart();
        String actual = shoppingQTY.getActualQuantityInCart();
        String expected = shoppingQTY.getSavedQty();
        log.info("TC02 - Expected: {}, Actual: {}", expected, actual);
        Assert.assertEquals(actual, expected, "Lỗi: Số lượng sau khi Tăng không khớp!");
        log.info("=== PASSED TC_02 ===");
    }

    @Test
    public void TC_03_CheckSubTotal1Product() {
        log.info("=== START TC_03: Check Subtotal for 1 Product ===");
        shoppingQTY.clickRemoveButton();
        shoppingQTY.clickOkToRemove();
        shoppingQTY.clickgpsAntennaDetail();

        shoppingQTY.clickAddToCartButtonInShoppingPage();
        shoppingQTY.inputqualityGPSProduct();
        shoppingQTY.clickUpdateMainCart();

        double actualSub = shoppingQTY.getActualSubTotal();
        double expectedSub = shoppingQTY.calculateExpectedSubTotalSingleProduct();
        log.info("Kiểm tra Subtotal - Mong đợi: {}, Thực tế: {}", expectedSub, actualSub);
        shoppingQTY.openMiniCart();
        shoppingQTY.calculateExpectedSubTotalSingleProduct();
        Assert.assertEquals(actualSub, expectedSub, "Lỗi: Tổng tiền Subtotal của sản phẩm không chính xác!");
        log.info("=== PASSED TC_03 ===");
    }

    @Test
    public void TC_04_CheckSubTotalWithMultiProduct() {
        log.info("=== START TC_04: Check Subtotal with Multi Product ===");
        shoppingQTY.openMiniCart();
        shoppingQTY.clickProductsCategory();
        shoppingQTY.clickBikeQuadCategory();
        shoppingQTY.clickIridiumAntennaDetail();

        shoppingQTY.clickAddToCartButtonInShoppingPage();
        shoppingQTY.inputqualityInridiumProduct();
        shoppingQTY.clickUpdateMainCart();

        double actualSub = shoppingQTY.getActualSubTotal();
        double expectedSub = shoppingQTY.calculateExpectedMultiProductSubTotal();
        log.info("Verify Multi-Subtotal -> Expected: {}, Actual: {}", expectedSub, actualSub);
        shoppingQTY.openMiniCart();
        shoppingQTY.calculateExpectedMultiProductSubTotal();
        Assert.assertEquals(actualSub, expectedSub, "Lỗi: Tổng tiền Subtotal của nhiều sản phẩm bị sai lệch!");
        log.info("=== PASSED TC_04 ===");
    }
}