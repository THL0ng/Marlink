package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Add1ProductToCartWithoutLogin_ContinuePage extends BasePage {


    public Add1ProductToCartWithoutLogin_ContinuePage(WebDriver driver) {
        super(driver);
    }

    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By iritrack = By.cssSelector("a[href='https://eshop247.officience.com/en/products/motobike-quad.html']");
    private final By addToCartProduct = By.xpath("//strong[normalize-space()='Item reference: APR4059']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By ContinueButton = By.xpath("//button[@class='button am-btn-left']");
    private final By qualityForm = By.xpath("//input[@class='amcart-input']");
    private final By updateButton = By.xpath("//span[@class='amcart-refresh']");
    private final By homePage = By.xpath("//img[@alt='ESHOP - Marlink Events']");
    private final By cartCountButton = By.cssSelector(".action.showcart");
    private final By qualityCartCount = By.xpath("//label[text()='Qty']/following-sibling::input");


    public void clickToProductsCategory() {
        waitClickable(productsCategory);
        click(productsCategory);
    }

    public void clickToIritrack() {
        waitClickable(iritrack);
        click(iritrack);
    }

    public void selectProductAndClickAddTocartButton() {
        waitClickable(addToCartProduct);
        click(addToCartProduct);
    }

    public void inputqualityForm() {
        waitClickable(qualityForm);
        type(qualityForm, "10");
    }

    public void clickUpdateButton() {
        waitClickable(updateButton);
        click(updateButton);
    }

    public void clickContinueButton() {
        waitClickable(ContinueButton);
        jsClick(ContinueButton);

    }

    public void clickBackHomePage() {
        waitVisible(homePage);
        waitClickable(homePage);
        jsClick(homePage);

    }

    public void clickcartCountButton() throws InterruptedException {
        Thread.sleep(3000);
        click(cartCountButton);
    }

    public String showActualQualityProduct() {
        waitVisible(qualityCartCount);
        return getText(qualityCartCount);
    }

    public void compareQuality() {
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
