package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsingButtonToDecreaseTheQualityOfProduct_ContinuePage extends BasePage {

    public UsingButtonToDecreaseTheQualityOfProduct_ContinuePage(WebDriver driver) {
        super(driver);
    }

    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By carSSV = By.cssSelector("a[href='https://eshop247.officience.com/en/products/car-ssv.html']");
    private final By addToCartProduct = By.xpath("//strong[normalize-space()='Item reference: APR2940']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By qualityForm = By.xpath("//input[@class='amcart-input']");
    private final By updateButton = By.xpath("//span[@class='amcart-refresh']");
    private final By DecreaseQTYButton = By.xpath("//span[@data-amcart='qty-minus']");
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

    public void inputqualityToForm() {
        waitClickable(qualityForm);
        type(qualityForm, "129");

    }

    public void clickUpdateButton() throws InterruptedException {
        waitClickable(updateButton);
        click(updateButton);
        Thread.sleep(3000);
    }

    public void clickDecreaseQTYByButton()  {
        for (int i = 0; i < 20; i++) {
            waitVisible(DecreaseQTYButton);
            jsClick(DecreaseQTYButton);

        }

    }

    public void clickUpdateButtonAfterDecrease() throws InterruptedException {
        waitClickable(updateButton);
        jsClick(updateButton);
        Thread.sleep(3000);
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
        String expectedQuality = "109";

        if (actualQuality.equals(expectedQuality)) {
            System.out.println("Số lượng chất lượng trong giỏ hàng và trong form khớp nhau.");
        } else {
            System.out.println("Số lượng chất lượng không khớp!");
            System.out.println("Giá trị mong đợi: " + expectedQuality);
            System.out.println("Giá trị thực tế: " + actualQuality);
        }

    }






}
