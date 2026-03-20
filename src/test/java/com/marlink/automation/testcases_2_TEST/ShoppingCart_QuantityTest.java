package com.marlink.automation.testcases_2_TEST;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages_2_TEST.ShoppingCart_QuantityPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShoppingCart_QuantityTest extends BaseTest {
    ShoppingCart_QuantityPage shoppingQTY;

    @BeforeMethod
    public void setupPage() {
        shoppingQTY = new ShoppingCart_QuantityPage(driver);
    }

    @Test
    public void TC_01_DecreaseQTY() {
        shoppingQTY.clickProductsCategory();
        shoppingQTY.clickCarSSVCategory();
        shoppingQTY.selectProductAndClickAddTocartButton();

        shoppingQTY.clickcartCount();
        shoppingQTY.inputInitialQty();
        shoppingQTY.clickDecreaseQTYRandomly();
        shoppingQTY.clickUpdateButtonMiniCart();

        shoppingQTY.clickContinueButton();
        shoppingQTY.clickcartCount();
        shoppingQTY.compareQuality();
    }

    @Test
    public void TC_02_IncreaseQTY() {
        shoppingQTY.clickcartCount();
        shoppingQTY.selectProductAndClickAddTocartButton();
        shoppingQTY.inputInitialQty();
        shoppingQTY.clickIncreaseQTYRandomly();
        shoppingQTY.clickUpdateButtonMiniCart();

        shoppingQTY.clickContinueButton();
        shoppingQTY.clickcartCount();
        shoppingQTY.compareQuality();
    }

    @Test
    public void TC_03_CheckSubTotal1Product() {
        shoppingQTY.clickRemoveButton();
        shoppingQTY.clickOkToRemove();
        shoppingQTY.clickgpsAntennaDetail();

        shoppingQTY.clickAddToCartButtonInShoppingPage();
        shoppingQTY.inputqualityGPSProduct();
        shoppingQTY.clickUpdateButton();


        shoppingQTY.clickcartCount();
        shoppingQTY.checkSubTotalAfterUpdate();
    }

    @Test
    public void TC_04_CheckSubTotalWithMultiProduct() {
        shoppingQTY.clickcartCount();
        shoppingQTY.clickProductsCategory();
        shoppingQTY.clickBikeQuadCategory();
        shoppingQTY.clickIridiumAntennaDetail();

        shoppingQTY.clickAddToCartButtonInShoppingPage();
        shoppingQTY.inputqualityInridiumProduct();
        shoppingQTY.clickUpdateButton();

        shoppingQTY.clickcartCount();
        shoppingQTY.checkSubTotalMultiProductAfterUpdate();
    }
}