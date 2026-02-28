package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.RemoveProductInCartWithOutLogin_ContinuePage;
import org.testng.annotations.Test;

public class RemoveProductInCartWithOutLogin_Continue extends BaseTest {
    @Test
    public void TC_01_RemoveProductInCartWithOutLogin_Continue() throws InterruptedException {
        RemoveProductInCartWithOutLogin_ContinuePage remove = new RemoveProductInCartWithOutLogin_ContinuePage(driver);

        remove.clickToProductsCategory();
        remove.clickToSmalltrack();
        remove.addPowerCable();
        remove.inputqualityFormForSmallTrack();
        remove.clickUpdateButton();
        remove.clickContinueButton();
        remove.clickcartCountButton();
        remove.clickRemoveButton();
        remove.clickOkToRemove();
        remove.checkRemoveProductSuccessfully();

    }








}
