package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.ShoppingCart_ValidationPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShoppingCart_ValidationTest extends BaseTest {
    ShoppingCart_ValidationPage shoppingCart;

    @BeforeMethod
    public void setupPage() {
        shoppingCart = new ShoppingCart_ValidationPage(driver);
    }

    @Test
    public void TC_01_CheckInvalidQTY() {
        shoppingCart.clickProductsCategory();
        shoppingCart.clickCarSSVCategory();
        shoppingCart.clickDetailProduct();
        shoppingCart.clickAddToCartButton();

        shoppingCart.inputInvalidQty();
        shoppingCart.clickUpdateButton();

        // Page đã có sẵn hàm getTextActualInvalidMessError và getExpectedMessages
        Assert.assertEquals(shoppingCart.getTextActualInvalidMessError(),
                shoppingCart.getExpectedMessages("err_invalidQTYShoppingCart"),
                "Lỗi: Message báo Invalid QTY không khớp!");
    }

    @Test
    public void TC_02_CheckMaxLengthQTY() {
        // Chạy tiếp tục từ TC_01 (Giỏ hàng đã có sẵn sản phẩm)
        shoppingCart.inputMaxLength();
        shoppingCart.clickUpdateButton();

        Assert.assertEquals(shoppingCart.getTextActualMaxLengthMessError(),
                shoppingCart.getExpectedMessages("err_maxLength"),
                "Lỗi: Message báo lỗi Max Length QTY không khớp!");
    }

    @Test
    public void TC_03_RemoveProduct() {
        // Nhập số lượng hợp lệ để có thể update thành công trước khi xóa
        shoppingCart.inputRandomQTY();
        shoppingCart.clickUpdateButton();

        shoppingCart.clickcartCountButton();
        shoppingCart.clickRemoveButton();
        shoppingCart.clickOkToRemove();

        Assert.assertEquals(shoppingCart.getTextActualRemoveMess(),
                shoppingCart.getExpectedMessages("remove_success"),
                "Lỗi: Thông báo xóa sản phẩm thành công không khớp!");
    }
}