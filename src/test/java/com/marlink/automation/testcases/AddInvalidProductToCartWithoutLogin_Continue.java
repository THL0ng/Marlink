package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.AddInvalidProductToCartWithoutLogin_ContinuePage;
import org.testng.annotations.Test;

public class AddInvalidProductToCartWithoutLogin_Continue extends BaseTest {
    @Test
    public void TC_01_AddInvalidQualityProduct() throws InterruptedException {
        AddInvalidProductToCartWithoutLogin_ContinuePage addInvalid = new AddInvalidProductToCartWithoutLogin_ContinuePage(driver);

        addInvalid.clickToProductsCategory();
        addInvalid.clickCarSsvLink();
        addInvalid.clickAddToCartProduct();
        addInvalid.inputqualityForm();
        addInvalid.clickUpdateButton();
        addInvalid.CheckActualMess();

        addInvalid.updateQualityForm();
        addInvalid.clickUpdateButton();
        addInvalid.clickContinueButton();
        addInvalid.clickcartCountButton();
        addInvalid.compareQuality();



    }
}
