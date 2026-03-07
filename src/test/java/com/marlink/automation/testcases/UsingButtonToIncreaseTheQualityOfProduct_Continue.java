package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.UsingButtonToIncreaseTheQualityOfProduct_ContinuePage;
import org.testng.annotations.Test;

public class UsingButtonToIncreaseTheQualityOfProduct_Continue extends BaseTest {

    @Test
    public void TC_01_IncreaseQualityByButton() throws InterruptedException {

        UsingButtonToIncreaseTheQualityOfProduct_ContinuePage increaseQTY = new UsingButtonToIncreaseTheQualityOfProduct_ContinuePage(driver);

        increaseQTY.clickToProductsCategory();
        increaseQTY.clickToCarSSVCategory();
        increaseQTY.selectProductAndClickAddTocartButton();
        increaseQTY.clickIncreaseQTYByButton();
        increaseQTY.clickUpdateButton();
        increaseQTY.clickContinueButton();
        increaseQTY.clickcartCountButton();
        increaseQTY.compareQuality();
    }


}
