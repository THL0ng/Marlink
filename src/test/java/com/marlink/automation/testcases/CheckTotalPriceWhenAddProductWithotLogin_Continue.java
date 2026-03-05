package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.CheckTotalPriceWhenAddProductWithotLogin_ContinuePage;
import org.testng.annotations.Test;

public class CheckTotalPriceWhenAddProductWithotLogin_Continue extends BaseTest {

    @Test
    public void TC_01_CheckPriceWithTotal() throws InterruptedException {
        CheckTotalPriceWhenAddProductWithotLogin_ContinuePage priceSubTotal = new CheckTotalPriceWhenAddProductWithotLogin_ContinuePage(driver);

        priceSubTotal.clickMarocMenu();
        priceSubTotal.clickTruckCategory();
        priceSubTotal.clickMountingAccessoriesSubCategory();
        priceSubTotal.clickSelectAndAddProduct();
        priceSubTotal.inputQualityForm();
        priceSubTotal.clickUpdateButton();
        priceSubTotal.clickContinueButton();
        priceSubTotal.clickCartCountButton();
        priceSubTotal.comparePriceWithSubtotal();

    }


}
