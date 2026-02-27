package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.AddMultipleProductsToCartWithoutLogin_ContinuePage;
import org.testng.annotations.Test;

public class AddMultipleProductsToCartWithoutLogin_Continue extends BaseTest {
    @Test
    public void TC_01_Add1ProductToCartWithoutLogin_Continue() throws InterruptedException {
        AddMultipleProductsToCartWithoutLogin_ContinuePage addMultiple = new AddMultipleProductsToCartWithoutLogin_ContinuePage(driver);

        addMultiple.clickToProductsCategory();
        addMultiple.clickToIritrack();
        addMultiple.addGpsMagneticAntenna();
        addMultiple.inputqualityFormForGPS();
        addMultiple.clickUpdateButton();
        addMultiple.clickContinueButton();

        addMultiple.clickToSmalltrack();
        addMultiple.addbracketSmallTrack();
        addMultiple.inputqualityFormForBracket();
        addMultiple.clickUpdateButton();
        addMultiple.clickContinueButton();

        addMultiple.clickTOSurvivalKit();
        addMultiple.addAluminiumRescue();
        addMultiple.inputqualityFormForAlumin();
        addMultiple.clickUpdateButton();
        addMultiple.clickContinueButton();

        addMultiple.clickcartCountButton();
        addMultiple.compareQuality();

}



}
