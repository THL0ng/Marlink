package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    private static final Logger log = LogManager.getLogger(ProductPage.class);
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private final By linkProductsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By linkTruckCategory = By.cssSelector("a[href='https://eshop247.officience.com/en/products/truck-1.html']");
    private final By labelProductName = By.xpath("//a[normalize-space()='Compass']");
    private final By imgDetailProduct = By.xpath("//img[contains(@src, 'apr0968_boussole.jpg')]");
    private final By buttonAddToCart = By.xpath("//button[@id='product-addtocart-button']");
    private final By labelMessageSuccess = By.xpath("//div[contains(@class,'message-success')]");

    public void navigateToProductsCategory() {
        log.info("Navigating to Products Category.");
        waitClickable(linkProductsCategory);
        click(linkProductsCategory);
    }

    public void navigateToTruckCategory() {
        waitClickable(linkTruckCategory);
        click(linkTruckCategory);
    }

    public void clickImageProductDetail() {
        log.info("Scrolling to product and clicking on Product Image.");
        scrollIntoView(labelProductName);
        waitClickable(imgDetailProduct);
        click(imgDetailProduct);
    }

    public void clickButtonAddToCart() {
        log.info("Clicking on Add to Cart button.");
        waitClickable(buttonAddToCart);
        click(buttonAddToCart);
    }

    public String getLabelMessageSuccess() {
        log.info("Getting Success Message text.");
        return waitVisible(labelMessageSuccess).getText();
    }
}