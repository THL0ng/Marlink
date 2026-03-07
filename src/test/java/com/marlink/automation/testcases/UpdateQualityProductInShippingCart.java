package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.UpdateQualityProductInShippingCart_Page;
import org.testng.annotations.Test;

public class UpdateQualityProductInShippingCart extends BaseTest {
    @Test
    public void TC_01_UpdateQualityProductInShippingCart() throws InterruptedException {

        UpdateQualityProductInShippingCart_Page upShippingCart = new UpdateQualityProductInShippingCart_Page(driver);

        upShippingCart.clickProductsCategory();
        upShippingCart.clickBikeQuadCategory();
        upShippingCart.scrollToProduct();
        upShippingCart.clickDetailProduct();
        upShippingCart.clickAddToCartButton();
        upShippingCart.clickIncreaseButton();
        upShippingCart.clickUpdateCartButton();
        upShippingCart.checkSubTotalAfterUpdate();

    }
}
