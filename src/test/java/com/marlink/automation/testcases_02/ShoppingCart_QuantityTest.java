package com.marlink.automation.testcases_02;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages_02.ShoppingCartPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShoppingCart_QuantityTest extends BaseTest {
    ShoppingCartPage shoppingQTY;

    @BeforeMethod
    public void setupPage() {
        shoppingQTY = new ShoppingCartPage(driver);
    }

    @Test
    public void TC_01_DecreaseQTYOfProduct(){
        shoppingQTY.clickProductsCategory();
        shoppingQTY.clickCarSSVCategory();
        shoppingQTY.selectProductAndClickAddTocartButton();
        shoppingQTY.inputQtyStrobeLampProduct();
        shoppingQTY.clickDecreaseQTYByButton();
        shoppingQTY.clickUpdateButtonMiniCart();
        shoppingQTY.clickContinueButton();
        shoppingQTY.clickcartCount();
        shoppingQTY.compareQuality();
    }

    @Test
    public void TC_02_IncreaseQTYOfProduct(){
        shoppingQTY.clickcartCount();
        shoppingQTY.selectProductAndClickAddTocartButton();
        shoppingQTY.inputQtyStrobeLampProduct();
        shoppingQTY.clickIncreaseQTYByButton();
        shoppingQTY.clickUpdateButtonMiniCart();
        shoppingQTY.clickContinueButton();
        shoppingQTY.clickcartCount();
        shoppingQTY.compareQuality();
    }

    @Test
    public void TC_03_CheckSubTotal1Product(){
        shoppingQTY.clickRemoveButton();
        shoppingQTY.clickOkToRemove();
        shoppingQTY.clickgpsAntennaDetail();
        shoppingQTY.clickAddToCartButtonInShoppingPage();
        shoppingQTY.inputRandomQTY();
        shoppingQTY.clickUpdateButton();
        shoppingQTY.clickcartCountButton();
        shoppingQTY.checkSubTotalAfterUpdate();
    }


    @Test
    public void TC_04_CheckSubTotalWithMultiProduct(){
        shoppingQTY.clickcartCount();
        shoppingQTY.clickProductsCategory();
        shoppingQTY.clickBikeQuadCategory();
        shoppingQTY.clickIridiumAntennaDetail();

        shoppingQTY.clickAddToCartButtonInShoppingPage();
        shoppingQTY.inputRandomQTY();
        shoppingQTY.clickUpdateButton();
        shoppingQTY.clickcartCountButton();
        shoppingQTY.checkSubTotalMultiProductAfterUpdate();


    }

}
