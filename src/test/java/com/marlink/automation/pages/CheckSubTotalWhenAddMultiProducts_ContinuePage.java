package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckSubTotalWhenAddMultiProducts_ContinuePage extends BasePage {

    public CheckSubTotalWhenAddMultiProducts_ContinuePage(WebDriver driver) {
        super(driver);
    }

    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By bikeQuad = By.cssSelector("a[href='https://eshop247.officience.com/en/products/motobike-quad.html']");
    private final By gpsMagneticAntenna = By.xpath("//strong[normalize-space()='Item reference: APR2861']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By qualityForm = By.xpath("//input[@class='amcart-input']");
    private final By updateButton = By.xpath("//span[@class='amcart-refresh']");
    private final By ContinueButton = By.xpath("//button[@class='button am-btn-left']");
    private final By smalltrack = By.xpath("//a[contains(text(),'Smalltrack')]");
    private final By powerCable1m = By.xpath("//strong[normalize-space()='Item reference: APR4080']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By cartCountButton = By.cssSelector(".action.showcart");
    private final By priceOfPowerCable = By.xpath("//a[normalize-space()='Power cable 1m SMALLTRACK']/ancestor::*[contains(@class,'product-item')]//span[@class='minicart-price']//span[@class='price']");
    private final By qualityOfPowerCable = By.xpath("//a[normalize-space()='Power cable 1m SMALLTRACK']/ancestor::*[contains(@class,'product-item')]//label[normalize-space()='Qty']/following-sibling::input");
    private final By priceOfGpsAntenna = By.xpath("//a[normalize-space()='GPS magnetic antenna (10cm cable)']/ancestor::*[contains(@class,'product-item')]//span[@class='minicart-price']//span[@class='price']");
    private final By qualityOfGpsAntenna = By.xpath("//a[normalize-space()='GPS magnetic antenna (10cm cable)']/ancestor::*[contains(@class,'product-item')]//label[normalize-space()='Qty']/following-sibling::input");
    private final By priceOfSubTotal = By.cssSelector("div.amount > span.price-wrapper > span.price");


    public void clickProductsCategory(){
        waitClickable(productsCategory);
        click(productsCategory);
    }

    public void clickBikeQuadCategory(){
        waitClickable(bikeQuad);
        click(bikeQuad);
    }

    public void clickSelectAndAddGpsAntenna(){
        waitClickable(gpsMagneticAntenna);
        click(gpsMagneticAntenna);
    }

    public void inputQualityGpsAntenna(){
        waitClickable(qualityForm);
        type(qualityForm,"20");
    }

    public void clickUpdateButton(){
        waitClickable(updateButton);
        click(updateButton);
    }

    public void clickContinueButton(){
        waitClickable(ContinueButton);
        jsClick(ContinueButton);
    }

    public void clickSmallTrackCategory(){
        waitClickable(smalltrack);
        jsClick(smalltrack);
    }

    public void clickSelectAndAddPowerCable1m(){
        waitClickable(powerCable1m);
        click(powerCable1m);
    }

    public void inputQualityPowerCable(){
        waitClickable(qualityForm);
        type(qualityForm,"30");
    }

    public void clickCartCountButton() throws InterruptedException {
        Thread.sleep(3000);
        jsClick(cartCountButton);
    }

    public void comparePriceWithSubtotal() throws InterruptedException {

        Thread.sleep(3000);
        int qtyPowerCable = Integer.parseInt(driver.findElement(qualityOfPowerCable).getAttribute("value"));
        int qtyGPSAntenna = Integer.parseInt(driver.findElement(qualityOfGpsAntenna).getAttribute("value"));


        waitVisible(priceOfPowerCable);
        double pricePowerCable = Double.parseDouble(driver.findElement(priceOfPowerCable).getText().replaceAll("[^0-9.]", ""));
        waitVisible(priceOfGpsAntenna);
        double priceGPS = Double.parseDouble(driver.findElement(priceOfGpsAntenna).getText().replaceAll("[^0-9.]", ""));


        waitVisible(priceOfSubTotal);
        double subtotal = Double.parseDouble(
                driver.findElement(priceOfSubTotal).getText().replaceAll("[^0-9.]", ""));

        double expected = (qtyPowerCable * pricePowerCable) + (qtyGPSAntenna * priceGPS);
        expected = Math.round(expected * 100.0) / 100.0;


        System.out.println("Subtotal from UI = " + subtotal);
        System.out.println("Expected subtotal = (" + pricePowerCable + " * " + qtyPowerCable +
                ") + (" + priceGPS + " * " + qtyGPSAntenna + ") = " + expected);
        Assert.assertEquals(subtotal, expected, "Subtotal calculation incorrect");

    }


























}
