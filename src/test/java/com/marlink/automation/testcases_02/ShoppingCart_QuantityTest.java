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


}
