package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Random;

public class UpdateQualityProductInShippingCart_Page extends BasePage {
    public UpdateQualityProductInShippingCart_Page(WebDriver driver) {
        super(driver);
    }


    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By bikeQuad = By.cssSelector("a[href='https://eshop247.officience.com/en/products/motobike-quad.html']");
    private final By productName = By.xpath("//a[normalize-space()='Strobe Lamp']");
    private final By productDetail = By.xpath("//img[contains(@src, 'strobe_lamp_2.png')]");
    private final By addToCartButton = By.xpath("//button[@id='product-addtocart-button']");
    private final By increaseButton = By.xpath("//i[@class='porto-icon-up-dir']");
    private final By updateCartButton = By.xpath("//span[normalize-space()='Update Cart']");
    private final By priceProduct = By.cssSelector("td[class='col price'] span[class='price']");
    private final By qtyProduct = By.xpath("//tr[contains(@class,'item-info')]//td[@data-th='Qty']//input");
    private final By subTotalProduct = By.cssSelector("td[class='col subtotal'] span[class='price']");


    public void clickProductsCategory() {
        waitClickable(productsCategory);
        click(productsCategory);
    }

    public void clickBikeQuadCategory() {
        waitClickable(bikeQuad);
        click(bikeQuad);
    }

    public void scrollToProduct() {
        WebElement product = waitVisible(productName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", product);
    }

    public void clickDetailProduct() {
        waitClickable(productDetail);
        click(productDetail);
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


    public void checkSubTotalAfterUpdate() throws InterruptedException {

        int qty = Integer.parseInt(driver.findElement(qtyProduct).getAttribute("value"));

        waitVisible(priceProduct);
        double price = Double.parseDouble(driver.findElement(priceProduct).getText().replaceAll("[^0-9.]", ""));


        waitVisible(subTotalProduct);
        Thread.sleep(3000);
        double subtotal = Double.parseDouble(
                driver.findElement(subTotalProduct).getText().replaceAll("[^0-9.]", ""));

        double expected = qty * price;
        expected = Math.round(expected * 100.0) / 100.0;


        System.out.println("Subtotal from UI = " + subtotal);
        System.out.println("Price * Quantity = " + price + " * " + qty + " = " + expected);
        Assert.assertEquals(subtotal, expected, "Subtotal calculation incorrect");
    }

}
