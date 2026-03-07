package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsingButtonToIncreaseTheQualityOfProduct_ContinuePage extends BasePage {
    public UsingButtonToIncreaseTheQualityOfProduct_ContinuePage(WebDriver driver) {
        super(driver);
    }

    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By carSSV = By.cssSelector("a[href='https://eshop247.officience.com/en/products/car-ssv.html']");
    private final By addToCartProduct = By.xpath("//strong[normalize-space()='Item reference: APR2940']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By IncreaseQTYButton = By.xpath("//span[@data-amcart='qty-plus']");
    private final By updateButton = By.xpath("//span[@class='amcart-refresh']");
    private final By ContinueButton = By.xpath("//button[@class='button am-btn-left']");
    private final By cartCountButton = By.cssSelector(".action.showcart");
    private final By qualityCartCount = By.xpath("//label[text()='Qty']/following-sibling::input");



    public void clickToProductsCategory() {
        waitClickable(productsCategory);
        click(productsCategory);
    }

    public void clickToCarSSVCategory() {
        waitClickable(carSSV);
        click(carSSV);
    }

    public void selectProductAndClickAddTocartButton() {
        waitClickable(addToCartProduct);
        click(addToCartProduct);
    }

    public void clickIncreaseQTYByButton() {
        for (int i = 0; i < 9; i++) {
            waitVisible(IncreaseQTYButton);
            driver.findElement(IncreaseQTYButton).click();
        }

    }

    public void clickUpdateButton() {
        waitClickable(updateButton);
        click(updateButton);
    }

    public void clickContinueButton() {
        waitClickable(ContinueButton);
        jsClick(ContinueButton);
    }

    public void clickcartCountButton() throws InterruptedException {
        Thread.sleep(3000);
        jsClick(cartCountButton);
    }


    public String showActualQualityProduct() {
        waitVisible(qualityCartCount);
        return getText(qualityCartCount);
    }


    public void compareQuality() throws InterruptedException {
        Thread.sleep(1000);
        String actualQuality = showActualQualityProduct();
        String expectedQuality = "10";

        if (actualQuality.equals(expectedQuality)) {
            System.out.println("Số lượng chất lượng trong giỏ hàng và trong form khớp nhau.");
        } else {
            System.out.println("Số lượng chất lượng không khớp!");
            System.out.println("Giá trị mong đợi: " + expectedQuality);
            System.out.println("Giá trị thực tế: " + actualQuality);
        }

    }






}
