package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckTotalPriceWhenAddProductWithotLogin_ContinuePage extends BasePage {
    public CheckTotalPriceWhenAddProductWithotLogin_ContinuePage(WebDriver driver) {
        super(driver);
    }


    private final By marocMenu= By.xpath("//span[normalize-space()='Rallye du Maroc']");
    private final By truckCategory = By.xpath("//img[@alt='truck']");
    private final By mountingAccessoriesSubCategory = By.xpath("//a[contains(text(),'Mounting Accessories')]");
    private final By selectAndAddProduct = By.xpath("//strong[normalize-space()='Item reference: S28091']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By qualityForm = By.xpath("//input[@class='amcart-input']");
    private final By updateButton = By.xpath("//span[@class='amcart-refresh']");
    private final By ContinueButton = By.xpath("//button[@class='button am-btn-left']");
    private final By cartCountButton = By.cssSelector(".action.showcart");
    private final By qualityProductInCart = By.xpath("//label[text()='Qty']/following-sibling::input");
    private final By priceOfProduct = By.xpath("//span[@class='minicart-price']//span[@class='price']");
    private final By priceOfSubTotal = By.cssSelector("div.amount > span.price-wrapper > span.price");


    public void clickMarocMenu(){
        waitClickable(marocMenu);
        click(marocMenu);
    }

    public void clickTruckCategory(){
        waitClickable(truckCategory);
        click(truckCategory);
    }

    public void clickMountingAccessoriesSubCategory(){
        waitClickable(mountingAccessoriesSubCategory);
        click(mountingAccessoriesSubCategory);
    }

    public void clickSelectAndAddProduct(){
        waitClickable(selectAndAddProduct);
        click(selectAndAddProduct);
    }

    public void inputQualityForm(){
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

    public void clickCartCountButton() throws InterruptedException {
        Thread.sleep(3000);
        jsClick(cartCountButton);
    }

    public void comparePriceWithSubtotal() {

            int qty = Integer.parseInt(driver.findElement(qualityProductInCart).getAttribute("value"));


            waitVisible(priceOfProduct);
            double price = Double.parseDouble(driver.findElement(priceOfProduct).getText().replaceAll("[^0-9.]", ""));


            waitVisible(priceOfSubTotal);
            double subtotal = Double.parseDouble(
                    driver.findElement(priceOfSubTotal).getText().replaceAll("[^0-9.]", ""));

            double expected = qty * price;
            expected = Math.round(expected * 100.0) / 100.0;


            System.out.println("Subtotal from UI = " + subtotal);
            System.out.println("Price * Quantity = " + price + " * " + qty + " = " + expected);
            Assert.assertEquals(subtotal, expected, "Subtotal calculation incorrect");

        }
    }



