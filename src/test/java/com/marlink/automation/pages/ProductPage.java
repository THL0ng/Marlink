package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import io.qameta.allure.Step;
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
    private final By labelProductName = By.xpath("//a[normalize-space()='Strobe Lamp']");
    private final By imgDetailProduct = By.xpath("//img[contains(@src, 'strobe_lamp_2.png')]");
    private final By buttonAddToCart = By.xpath("//button[@id='product-addtocart-button']");
    private final By labelMessageSuccess = By.xpath("//div[contains(@class,'message-success')]");

    @Step("Chuyển hướng đến Category")
    public void navigateToProductsCategory() {
        log.info("Navigating to Products Category.");
        waitClickable(linkProductsCategory);
        click(linkProductsCategory);
    }

    @Step("Chuyển hướng đến Sub Category")
    public void navigateToTruckCategory() {
        waitClickable(linkTruckCategory);
        click(linkTruckCategory);
    }

    @Step("Click vào Product")
    public void clickImageProductDetail() {
        log.info("Scrolling to product and clicking on Product Image.");
        scrollIntoView(labelProductName);
        waitClickable(imgDetailProduct);
        click(imgDetailProduct);
    }
    @Step("Click vào Add To Cart Button")
    public void clickButtonAddToCart() {
        log.info("Clicking on Add to Cart button.");
        waitClickable(buttonAddToCart);
        click(buttonAddToCart);
    }
    @Step("Check Message Inform Display")
    public String getLabelMessageSuccess() {
        log.info("Getting Success Message text.");
        return waitVisible(labelMessageSuccess).getText();
    }
}