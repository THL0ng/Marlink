package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.ShoppingCart_ValidationPage;
import com.marlink.automation.utils.JsonHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShoppingCart_ValidationTest extends BaseTest {
    ShoppingCart_ValidationPage shoppingCart;
    private static final Logger log = LogManager.getLogger(ShoppingCart_ValidationTest.class);

    @BeforeMethod
    public void setupPage() {
        shoppingCart = new ShoppingCart_ValidationPage(getDriver());
    }

    @Test
    public void TC_01_CheckInvalidQTY() {
        log.info("=== START TC_01: Check Invalid QTY Validation ===");
        shoppingCart.clickProductsCategory();
        shoppingCart.clickCarSSVCategory();
        shoppingCart.clickProductDetail();
        shoppingCart.clickAddToCartButton();

        shoppingCart.inputInvalidQty();
        shoppingCart.clickUpdateButton();

        String actual = shoppingCart.getActualQtyErrorMessage();
        String expected = JsonHelper.get("shoppingCart_err_invalidQuality");
        log.info("Verifying Invalid Qty Message. Expected: [{}], Actual: [{}]", expected, actual);
        Assert.assertEquals(actual, expected, "Lỗi: Thông báo Validation cho 'Invalid Quantity' hiển thị sai hoặc không xuất hiện!");
        log.info("=== PASSED TC_01 ===");
    }

    @Test
    public void TC_02_CheckMaxLengthQTY() {
        log.info("=== START TC_02: Check Max Length QTY Validation ===");
        shoppingCart.inputMaxLengthQty();
        shoppingCart.clickUpdateButton();

        String actual = shoppingCart.getActualQtyErrorMessage();
        String expected = JsonHelper.get("shoppingCart_err_maxLength");
        log.info("Verifying Max Length Message. Expected: [{}], Actual: [{}]", expected, actual);
        Assert.assertEquals(actual,expected, "Lỗi: Câu thông báo hiển thị khi nhập quá số lượng cho phép (Max Length) bị sai!");
        log.info("=== PASSED TC_02 ===");
    }

    @Test
    public void TC_03_RemoveProduct() {
        log.info("=== START TC_03: Remove Product and Check Empty Cart ===");
        shoppingCart.inputRandomQTY();
        shoppingCart.clickUpdateButton();

        shoppingCart.openMiniCart();
        shoppingCart.clickRemoveButton();
        shoppingCart.confirmRemoveProduct();

        String actual = shoppingCart.getActualEmptyCartMessage();
        String expected = JsonHelper.get("shoppingCart_inform_reomvesuccess");
        log.info("Verifying Empty Cart Message. Expected: [{}], Actual: [{}]", expected, actual);

        Assert.assertEquals(actual, expected, "Lỗi: Nội dung thông báo giỏ hàng trống không khớp!");
        log.info("=== PASSED TC_03 ===");
    }
}