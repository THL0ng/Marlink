package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.UpdateQualityMultiProductsInShippingCart_Page;
import org.testng.annotations.Test;

public class UpdateQualityMultiProductsInShippingCart extends BaseTest {
    @Test
    public void TC_01_UpdateQualityMultiProductsInShippingCart() throws InterruptedException {

        UpdateQualityMultiProductsInShippingCart_Page upMulti = new UpdateQualityMultiProductsInShippingCart_Page(driver);

        upMulti.clickProductsCategory();
        upMulti.clickBikeQuadCategory();
        upMulti.scrollToSurvivalKitProduct();
        upMulti.clickDetailSurvivalKitProduct();
        upMulti.clickAddToCartButton();
        upMulti.clickIncreaseButtonOfSurvivalKit();
        upMulti.clickUpdateCartButton();

        upMulti.clickProductsCategory();
        upMulti.clickTruckCategory();
        upMulti.scrollTo5mCableProduct();
        upMulti.clickDetail5mCableProduct();
        upMulti.clickAddToCartButton();
        upMulti.clickIncreaseButtonOfIridiumCable();
        upMulti.clickUpdateCartButton();

        upMulti.reloadPage();
        upMulti.checkSubTotalAfterUpdate();

    }
}
