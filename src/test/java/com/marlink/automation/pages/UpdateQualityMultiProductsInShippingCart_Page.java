package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class UpdateQualityMultiProductsInShippingCart_Page extends BasePage {
    public UpdateQualityMultiProductsInShippingCart_Page(WebDriver driver) {
        super(driver);
    }


    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By bikeQuad = By.cssSelector("a[href='https://eshop247.officience.com/en/products/motobike-quad.html']");
    private final By truckCategory = By.cssSelector("a[href='https://eshop247.officience.com/en/products/truck-1.html']");
    private final By survivalKit = By.xpath("//a[normalize-space()='Survival Equipment Kit']");
    private final By SurvivalKitDetail = By.xpath("//img[contains(@src, 'safety_kit.jpg')]");
    private final By antenna5mCable = By.xpath("//a[normalize-space()='Iridium Antenna magnetic 5m cable']");
    private final By antenna5mCableDetail = By.xpath("//img[contains(@src, 'iridium_antenna_truck_5m.png')]");
    private final By addToCartButton = By.xpath("//button[@id='product-addtocart-button']");
    private final By increaseButton = By.xpath("//i[@class='porto-icon-up-dir']");
    private final By updateCartButton = By.xpath("//span[normalize-space()='Update Cart']");

    private final By priceSurvivalKit = By.xpath("//tr[.//a[normalize-space()='Survival Equipment Kit']]//td[@data-th='Price']//span[@class='price']");
    private final By qtySurvivalKit = By.xpath("//tr[.//a[normalize-space()='Survival Equipment Kit']]//td[@data-th='Qty']//input");
    private final By subTotalSurvivalKit = By.xpath("//tr[.//a[normalize-space()='Survival Equipment Kit']]//td[contains(@class,'subtotal')]//span[@class='price']");

    private final By price5mCable = By.xpath("//tr[.//a[normalize-space()='Iridium Antenna magnetic 5m cable']]//td[@data-th='Price']//span[@class='price']");
    private final By qty5mCable = By.xpath("//tr[.//a[normalize-space()='Iridium Antenna magnetic 5m cable']]//td[@data-th='Qty']//input");
    private final By subTotal5mCable = By.xpath("//tr[.//a[normalize-space()='Iridium Antenna magnetic 5m cable']]//td[contains(@class,'subtotal')]//span[@class='price']");





    public void clickProductsCategory() {
        waitClickable(productsCategory);
        click(productsCategory);
    }

    public void clickBikeQuadCategory() {
        waitClickable(bikeQuad);
        click(bikeQuad);
    }

    public void scrollToSurvivalKitProduct() {
        WebElement product = waitVisible(survivalKit);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", product);
    }

    public void clickDetailSurvivalKitProduct() {
        waitClickable(SurvivalKitDetail);
        click(SurvivalKitDetail);
    }

    public void clickAddToCartButton() {
        waitClickable(addToCartButton);
        click(addToCartButton);
    }

    public void clickIncreaseButton(){
        Random random = new Random();
        int times = random.nextInt(100) + 1; // random từ 1 -> 999
        for (int i = 0; i < times; i++) {
            waitVisible(increaseButton);
            driver.findElement(increaseButton).click();
        }
        System.out.println("Clicked increase button: " + times + " times");
    }

    public void clickUpdateCartButton(){
        waitClickable(updateCartButton);
        click(updateCartButton);
    }

    public void clickTruckCategory() {
        waitClickable(truckCategory);
        click(truckCategory);
    }

    public void scrollTo5mCableProduct() {
        WebElement product = waitVisible(antenna5mCable);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", product);
    }

    public void clickDetail5mCableProduct(){
        waitClickable(antenna5mCableDetail);
        click(antenna5mCableDetail);

    }










































}
