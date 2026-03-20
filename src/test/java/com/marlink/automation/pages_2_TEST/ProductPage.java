package com.marlink.automation.pages_2_TEST;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private final By linkProductsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By linkTruckCategory = By.cssSelector("a[href='https://eshop247.officience.com/en/products/truck-1.html']");
    private final By labelProductName = By.xpath("//a[normalize-space()='Compass']");
    private final By imgDetailProduct = By.xpath("//img[contains(@src, 'apr0968_boussole.jpg')]");
    private final By buttonAddToCart = By.xpath("//button[@id='product-addtocart-button']");
    private final By labelMessInform = By.xpath("//div[contains(@class,'message-success')]");

    public void clickProductsCategory() {
        waitClickable(linkProductsCategory);
        click(linkProductsCategory);
    }

    public void clickTruckCategory() {
        waitClickable(linkTruckCategory);
        click(linkTruckCategory);
    }

    public void viewProductDetail() {
        scrollIntoView(labelProductName);
        waitClickable(imgDetailProduct);
        click(imgDetailProduct);
    }

    public void clickAddToCartButton() {
        waitClickable(buttonAddToCart);
        click(buttonAddToCart);
    }

    public String getActualMessage() {
        return waitVisible(labelMessInform).getText();
    }
}