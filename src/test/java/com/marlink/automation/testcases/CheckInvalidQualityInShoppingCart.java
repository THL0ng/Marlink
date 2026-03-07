package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.CheckInvalidQualityInShoppingCart_page;
import org.testng.annotations.Test;

public class CheckInvalidQualityInShoppingCart extends BaseTest {
    @Test
    public void TC_01_CheckInvalidQualityInShoppingCart(){
        CheckInvalidQualityInShoppingCart_page invalidQTY = new CheckInvalidQualityInShoppingCart_page(driver);

        invalidQTY.clickProductsCategory();
        invalidQTY.clickCarSSVCategory();
        invalidQTY.clickDetailProduct();
        invalidQTY.clickAddToCartButton();
        invalidQTY.inputQtyForm();
        invalidQTY.clickUpdateButton();
        invalidQTY.checkMessErrorDisplay();
    }

    }
