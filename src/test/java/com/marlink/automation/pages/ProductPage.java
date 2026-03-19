package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import com.marlink.automation.utils.JsonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By truckCategory = By.cssSelector("a[href='https://eshop247.officience.com/en/products/truck-1.html']");
    private final By productName = By.xpath("//a[normalize-space()='Compass']");
    private final By detailProduct = By.xpath("//img[contains(@src, 'apr0968_boussole.jpg')]");
    private final By addToCartProduct = By.xpath("//button[@id='product-addtocart-button']");
    private final By messInform = By.xpath("//div[contains(@class,'message-success') and normalize-space()='You added Compass to your shopping cart.']");

    private final String EXPECTED_PRODUCT_SUCCESS = JsonHelper.get("product_add_success");



    public void clickProductsCategory() {
        waitClickable(productsCategory);
        click(productsCategory);
    }

    public void clickTruckCategory() {
        waitClickable(truckCategory);
        click(truckCategory);
    }

    public void clickDetailProduct() {
        waitClickable(detailProduct);
        click(detailProduct);
    }

    public void clickAddToCartButton() {
        waitClickable(addToCartProduct);
        click(addToCartProduct);
    }

    public String getActualMessages() {
        waitVisible(messInform);
        return getText(messInform);
    }

    public String getExpectedMessages(String key) {
        Map<String, String> messages = new HashMap<>();
        messages.put("addProduct", EXPECTED_PRODUCT_SUCCESS);
        return messages.get(key);
    }

    public void scrollToProduct() {
        WebElement product = waitVisible(productName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", product);
    }





}
