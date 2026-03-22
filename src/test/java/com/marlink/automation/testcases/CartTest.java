package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.CartPage;
import com.marlink.automation.utils.JsonHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    CartPage cartPage;
    private static final Logger log = LogManager.getLogger(CartTest.class);

    @BeforeMethod
    public void setupPage() {
        cartPage = new CartPage(driver);
    }

    @Test
    public void TC_01_AddInvalidQualityProduct_WithoutLogin() {
        log.info("=== START TC_01: Add Invalid Quantity Product ===");
        cartPage.clickProductsCategoryLink();
        cartPage.clickCarSsvLink();
        cartPage.addAluminiumToCart();

        cartPage.inputInvalidQty();
        cartPage.clickUpdateMiniCart();

        String actualErr = cartPage.getActualErrorMessage();
        String expectedErr = JsonHelper.get("cartPage_err_invalidNubmer");
        log.info("Verify Error Message - Expected: [{}], Actual: [{}]", expectedErr, actualErr);

        Assert.assertEquals(actualErr, expectedErr, "Lỗi: Thông báo sai số lượng không đúng!");
        log.info("=== PASSED TC_01 ===");

    }

    @Test
    public void TC_02_Add1QualityProduct_WithoutLogin() {
        log.info("=== START TC_02: Add Single Product with Random Qty ===");
        cartPage.updateAluminiumQtyRandomly();
        cartPage.clickUpdateMiniCart();
        cartPage.clickContinueShopping();

        cartPage.waitForLoadingInvisible();
        cartPage.openMiniCart();
        String actualQty = cartPage.getActualAluminiumQtyInCart();
        String expectedQty = cartPage.getSavedAluminiumQuality();
        log.info("Verify Qty - Expected: {}, Actual: {}", expectedQty, actualQty);

        Assert.assertEquals(actualQty, expectedQty, "Lỗi: Số lượng sản phẩm Aluminium không khớp!");
        log.info("=== PASSED TC_02 ===");
        cartPage.openMiniCart();
    }

    @Test
    public void TC_03_AddMultipleProducts_WithoutLogin() {
        log.info("=== START TC_03: Add Multiple Products and Check Total Qty ===");
        cartPage.addCable3MToCart();
        cartPage.updateCable3MQtyRandomly();
        cartPage.clickUpdateMiniCart();
        cartPage.clickContinueShopping();
        cartPage.openMiniCart();
        cartPage.calculateExpectedTotalQty();
        int actualTotal = cartPage.getActualTotalItemsCount();
        int expectedTotal = cartPage.getActualTotalItemsCount();
        log.info("Verify Total Qty - Expected: {}, Actual: {}", expectedTotal, actualTotal);
        Assert.assertEquals(actualTotal, expectedTotal, "Lỗi: Tổng số lượng sản phẩm trong giỏ hàng sai!");
        log.info("=== PASSED TC_03 ===");

    }

    @Test
    public void TC_04_CheckSubTotalWhenAddMultiProducts() {
        log.info("=== START TC_04: Check Subtotal Price for Multi Products ===");
        double actualSubtotal = cartPage.getActualSubtotal();
        double expectedSubtotal = cartPage.calculateExpectedSubtotal();
        log.info("Verify Subtotal - Expected: {}, Actual: {}", expectedSubtotal, actualSubtotal);
        Assert.assertEquals(actualSubtotal, expectedSubtotal, "Lỗi: Tổng tiền Subtotal không chính xác!");
        log.info("=== PASSED TC_04 ===");
    }


}