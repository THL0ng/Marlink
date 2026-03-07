package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ClickOnProductToAddToCart_ContinuePage extends BasePage {
    public ClickOnProductToAddToCart_ContinuePage(WebDriver driver) {
        super(driver);
    }

    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By truckCategory = By.cssSelector("a[href='https://eshop247.officience.com/en/products/truck-1.html']");
    private final By productName = By.xpath("//a[normalize-space()='Compass']");
    private final By detailProduct = By.xpath("//img[contains(@src, 'apr0968_boussole.jpg')]");
    private final By addToCartProduct = By.xpath("//button[@id='product-addtocart-button']");
    private final By messInform = By.xpath("//div[contains(@class,'message-success') and normalize-space()='You added Compass to your shopping cart.']");


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

    public String getTextMess() {
        waitVisible(messInform);
        return getText(messInform);
    }

    public boolean checkAddToCartSuccessfully() {
        return getTextMess().contains("You added Compass to your shopping cart.");

    }

    public void scrollToProduct() {
        WebElement product = waitVisible(productName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", product);
    }






}
