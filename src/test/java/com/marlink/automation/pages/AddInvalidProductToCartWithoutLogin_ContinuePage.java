package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AddInvalidProductToCartWithoutLogin_ContinuePage extends BasePage {
    public AddInvalidProductToCartWithoutLogin_ContinuePage(WebDriver driver) {
        super(driver);
    }

    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By carSSV = By.cssSelector("a[href='https://eshop247.officience.com/en/products/car-ssv.html']");
    private final By addToCartProduct = By.xpath("//strong[normalize-space()='Item reference: APR0967']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By qualityForm = By.xpath("//input[@class='amcart-input']");
    private final By updateButton = By.xpath("//span[@class='amcart-refresh']");
    private final By messInformError = By.cssSelector(".message.error");
    private final By ContinueButton = By.xpath("//button[@class='button am-btn-left']");
    private final By qualityCartCount = By.xpath("//label[text()='Qty']/following-sibling::input");
    private final By cartCountButton = By.cssSelector(".action.showcart");


    public void clickToProductsCategory() {
        waitClickable(productsCategory);
        click(productsCategory);
    }

    public void clickCarSsvLink(){
        waitClickable(carSSV);
        click(carSSV);
    }

    public void clickAddToCartProduct(){
        waitClickable(addToCartProduct);
        click(addToCartProduct);
    }

    public void inputqualityForm() {
        waitClickable(qualityForm);
        type(qualityForm, "-20");
    }

    public void updateQualityForm() {
        waitClickable(qualityForm);
        type(qualityForm, "30");
    }

    public void clickUpdateButton() {
        waitClickable(updateButton);
        click(updateButton);
    }

    public String ExpectedError(){
        waitVisible(messInformError);
        return getText(messInformError);
    }

    public void clickContinueButton() {
        waitClickable(ContinueButton);
        jsClick(ContinueButton);

    }

    public void clickcartCountButton() throws InterruptedException {
        Thread.sleep(3000);
        jsClick(cartCountButton);
    }

    public void CheckActualMess(){
        String actual = ExpectedError();
        Assert.assertEquals(actual,"Please enter a quantity greater than 0.");
    }

    public String showActualQualityProduct() {
        waitVisible(qualityCartCount);
        return getText(qualityCartCount);
    }

    public void compareQuality() {
        String actualQuality = showActualQualityProduct();
        String expectedQuality = "30";

        if (actualQuality.equals(expectedQuality)) {
            System.out.println("Số lượng chất lượng trong giỏ hàng và trong form khớp nhau.");
        } else {
            System.out.println("Số lượng chất lượng không khớp!");
            System.out.println("Giá trị mong đợi: " + expectedQuality);
            System.out.println("Giá trị thực tế: " + actualQuality);
        }

    }















}
