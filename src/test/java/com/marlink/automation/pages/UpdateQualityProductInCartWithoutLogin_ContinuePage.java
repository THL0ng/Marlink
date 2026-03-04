package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class UpdateQualityProductInCartWithoutLogin_ContinuePage extends BasePage {
    public UpdateQualityProductInCartWithoutLogin_ContinuePage(WebDriver driver) {
        super(driver);
    }

    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By bikeQuad = By.cssSelector("a[href='https://eshop247.officience.com/en/products/motobike-quad.html']");
    private final By addToCartIridiumProduct = By.xpath("//strong[normalize-space()='Item reference: APR4036']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By qualityForm = By.xpath("//input[@class='amcart-input']");
    private final By updateButton = By.xpath("//span[@class='amcart-refresh']");
    private final By ContinueButton = By.xpath("//button[@class='button am-btn-left']");
    private final By cartCountButton = By.cssSelector(".action.showcart");
    private final By editButton = By.xpath("//a[@title='Edit item']");
    private final By updateFormDetailPage = By.id("qty");
    private final By updateCartButtonDetailPage = By.xpath("//button[@id='product-updatecart-button']");
    private final By messageUpdateSuccess = By.xpath("//div[contains(@class,'message-success')]//div");
    private final By qualityCartCount = By.xpath("//label[text()='Qty']/following-sibling::input");


    public void clickToProductsCategory() {
        waitClickable(productsCategory);
        click(productsCategory);
    }

    public void clickToBikeQuad() {
        waitClickable(bikeQuad);
        click(bikeQuad);
    }

    public void selectProductAndClickAddTocartButton() {
        waitClickable(addToCartIridiumProduct);
        click(addToCartIridiumProduct);
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

    public void clickcartCountButton() throws InterruptedException {
        Thread.sleep(3000);
        jsClick(cartCountButton);
    }

    public void checkUpdateProductSuccessfully() throws InterruptedException {
        Thread.sleep(3000);
        WebElement mess = driver.findElement(messageUpdateSuccess);
        Assert.assertTrue(mess.isDisplayed(),"Iridium antenna magnetic (10cm cable) was updated in your shopping cart.");
    }

    public void clickEditButton() {
        waitClickable(editButton);
        jsClick(editButton);
    }

    public void inputQualityFormDetailPage() {
        waitClickable(updateFormDetailPage);
        type(updateFormDetailPage,"100");
    }


    public void clickupdateCartButtonDetailPage() {
        waitClickable(updateCartButtonDetailPage);
        click(updateCartButtonDetailPage);
    }

    public String showActualQualityProduct() {
        waitVisible(qualityCartCount);
        return getText(qualityCartCount);
    }

    public void compareQuality() {
        String actualQuality = showActualQualityProduct();
        String expectedQuality = "100";

        if (actualQuality.equals(expectedQuality)) {
            System.out.println("Số lượng chất lượng trong giỏ hàng và trong form khớp nhau.");
        } else {
            System.out.println("Số lượng chất lượng không khớp!");
            System.out.println("Giá trị mong đợi: " + expectedQuality);
            System.out.println("Giá trị thực tế: " + actualQuality);
        }

    }


}
