package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.UsingButtonToDecreaseTheQualityOfProduct_ContinuePage;
import com.marlink.automation.pages.UsingButtonToIncreaseTheQualityOfProduct_ContinuePage;
import org.testng.annotations.Test;

public class UsingButtonToDecreaseTheQualityOfProduct_Continue extends BaseTest {
    @Test
    public void TC_01_DecreaseQualityByButton() throws InterruptedException {

        UsingButtonToDecreaseTheQualityOfProduct_ContinuePage decreaseQTY = new UsingButtonToDecreaseTheQualityOfProduct_ContinuePage(driver);

        decreaseQTY.clickToProductsCategory();
        decreaseQTY.clickToCarSSVCategory();
        decreaseQTY.selectProductAndClickAddTocartButton();
        decreaseQTY.inputqualityToForm();
        decreaseQTY.clickUpdateButton();
        decreaseQTY.clickDecreaseQTYByButton();
        decreaseQTY.clickUpdateButtonAfterDecrease();
        decreaseQTY.clickContinueButton();
        decreaseQTY.clickcartCountButton();
        decreaseQTY.compareQuality();
    }

}
