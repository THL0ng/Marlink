package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.CheckSubTotalWhenAddMultiProducts_ContinuePage;
import org.testng.annotations.Test;

public class CheckSubTotalWhenAddMultiProducts_Continue extends BaseTest {

    @Test
    public void TC_01_CheckSubTotalWhenAddMultiProducts() throws InterruptedException {

        CheckSubTotalWhenAddMultiProducts_ContinuePage subMulti = new CheckSubTotalWhenAddMultiProducts_ContinuePage(driver);

        subMulti.clickProductsCategory();
        subMulti.clickBikeQuadCategory();
        subMulti.clickSelectAndAddGpsAntenna();
        subMulti.inputQualityGpsAntenna();
        subMulti.clickUpdateButton();
        subMulti.clickContinueButton();
        subMulti.clickSmallTrackCategory();
        subMulti.clickSelectAndAddPowerCable1m();
        subMulti.inputQualityPowerCable();
        subMulti.clickUpdateButton();
        subMulti.clickContinueButton();
        subMulti.clickCartCountButton();
        subMulti.comparePriceWithSubtotal();

    }


}
