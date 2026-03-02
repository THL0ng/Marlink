package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.UpdateQualityProductInCartWithoutLogin_ContinuePage;
import org.testng.annotations.Test;

public class UpdateQualityProductInCartWithoutLogin_Continue extends BaseTest {

    @Test
    public void TC_01_UpdateQualityProductInCart() throws InterruptedException {
        UpdateQualityProductInCartWithoutLogin_ContinuePage up = new UpdateQualityProductInCartWithoutLogin_ContinuePage(driver);

        up.clickToProductsCategory();
        up.clickToBikeQuad();
        up.selectProductAndClickAddTocartButton();
        up.inputqualityForm();
        up.clickUpdateButton();
        up.clickContinueButton();
        up.clickcartCountButton();
        up.clickEditButton();
        up.inputQualityFormDetailPage();
        up.clickupdateCartButtonDetailPage();
        up.checkUpdateProductSuccessfully();
        up.clickcartCountButton();
        up.compareQuality();

    }
}
