package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.CheckMaxLengthWhenInputQualityFormShippingCart_Page;
import org.testng.annotations.Test;

public class CheckMaxLengthWhenInputQualityFormShippingCart extends BaseTest {
    @Test
    public void TC_01_CheckMaxLengthWhenInputQualityFormShippingCart(){
        CheckMaxLengthWhenInputQualityFormShippingCart_Page checkMaxLength = new CheckMaxLengthWhenInputQualityFormShippingCart_Page(driver);

        checkMaxLength.clickProductsCategory();
        checkMaxLength.clickCarSSVCategory();
        checkMaxLength.clickDetailProduct();
        checkMaxLength.clickAddToCartButton();
        checkMaxLength.inputQtyForm();
        checkMaxLength.clickUpdateButton();
        checkMaxLength.checkMessErrorDisplay();

    }
}
