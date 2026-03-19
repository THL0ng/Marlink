package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.ShoppingCartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShoppingCart_ValidationTest extends BaseTest {
    ShoppingCartPage shoppingCart;
    @BeforeMethod
    public void setupPage() {
        shoppingCart = new ShoppingCartPage(driver);
    }

    @Test
    public void TC_01_CheckInvalidQTY(){
        shoppingCart.clickProductsCategory();
        shoppingCart.clickCarSSVCategory();
        shoppingCart.clickDetailProduct();
        shoppingCart.clickAddToCartButton();
        shoppingCart.inputInvalidQty();
        shoppingCart.clickUpdateButton();
        Assert.assertEquals(shoppingCart.getTextActualInvalidMessError(), shoppingCart.getExpectedMessages("err_invalidQTYShoppingCart"));
    }


    @Test
    public void TC_02_CheckMaxLengthQTY(){
        shoppingCart.inputMaxLength();
        shoppingCart.clickUpdateButton();
        Assert.assertEquals(shoppingCart.getTextActualMaxLengthMessError(), shoppingCart.getExpectedMessages("err_maxLength"));
    }

    @Test
    public void TC_03_RemoveProduct(){
        shoppingCart.inputRandomQTY();
        shoppingCart.clickUpdateButton();

        shoppingCart.clickcartCountButton();
        shoppingCart.clickRemoveButton();
        shoppingCart.clickOkToRemove();
        Assert.assertEquals(shoppingCart.getTextActualRemoveMess(), shoppingCart.getExpectedMessages("remove_success"));

    }


}
