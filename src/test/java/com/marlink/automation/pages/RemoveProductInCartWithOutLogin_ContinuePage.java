package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class RemoveProductInCartWithOutLogin_ContinuePage extends BasePage {
    public RemoveProductInCartWithOutLogin_ContinuePage(WebDriver driver) {
        super(driver);
    }


    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By smalltrack = By.cssSelector("a[href='https://eshop247.officience.com/en/products/car-ssv.html']");
    private final By powerCable = By.xpath("//span[normalize-space()='Item reference: APR4033']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By qualityForm = By.xpath("//input[@class='amcart-input']");
    private final By updateButton = By.xpath("//span[@class='amcart-refresh']");
    private final By continueButton = By.xpath("//button[@class='button am-btn-left']");
    private final By cartCountButton = By.cssSelector(".action.showcart");
    private final By removeProductButton = By.xpath("//a[@title='Remove']");
    private final By okToRemoveButton = By.xpath("//button[@class='action-primary action-accept']");
    private final By messageRemoveSuccess = By.xpath("//span[normalize-space()='You have no items in your shopping cart.']");



    public void clickToProductsCategory() {
        waitClickable(productsCategory);
        click(productsCategory);
    }

    public void clickToSmalltrack() {
        waitClickable(smalltrack);
        jsClick(smalltrack);
    }

    public void addPowerCable() {
        waitClickable(powerCable);
        click(powerCable);
    }

    public void inputqualityFormForSmallTrack() {
        waitClickable(qualityForm);
        type(qualityForm, "123");
    }

    public void clickUpdateButton() {
        waitClickable(updateButton);
        click(updateButton);
    }

    public void clickContinueButton() {
        waitClickable(continueButton);
        jsClick(continueButton);
    }

    public void clickcartCountButton() throws InterruptedException {
        Thread.sleep(3000);
        jsClick(cartCountButton);
    }

    public void clickRemoveButton(){
        waitClickable(removeProductButton);
        jsClick(removeProductButton);
    }

    public void clickOkToRemove(){
        waitClickable(okToRemoveButton);
        jsClick(okToRemoveButton);
    }

    public void checkRemoveProductSuccessfully() throws InterruptedException {
        Thread.sleep(3000);
        WebElement mess = driver.findElement(messageRemoveSuccess);
        Assert.assertTrue(mess.isDisplayed(),"You have no items in your shopping cart.");
    }

}
