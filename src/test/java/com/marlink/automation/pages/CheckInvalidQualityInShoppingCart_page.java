package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CheckInvalidQualityInShoppingCart_page extends BasePage {

    public CheckInvalidQualityInShoppingCart_page(WebDriver driver) {
        super(driver);
    }

    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By carSsv = By.cssSelector("a[href='https://eshop247.officience.com/en/products/car-ssv.html']");
    private final By powerExCableDetail = By.xpath("//img[contains(@src, 'apr4033_cable_alimentation.png')]");
    private final By addToCartButton = By.xpath("//button[@id='product-addtocart-button']");
    private final By qtyForm = By.xpath("//tr[contains(@class,'item-info')]//td[@data-th='Qty']//input");
    private final By updateCartButton = By.xpath("//span[normalize-space()='Update Cart']");
    private final By messError = By.xpath("//div[contains(@id,'qty-error')]");



    public void clickProductsCategory() {
        waitClickable(productsCategory);
        click(productsCategory);
    }

    public void clickCarSSVCategory() {
        waitClickable(carSsv);
        click(carSsv);
    }

    public void clickDetailProduct() {
        waitClickable(powerExCableDetail);
        click(powerExCableDetail);
    }

    public void clickAddToCartButton() {
        waitClickable(addToCartButton);
        click(addToCartButton);
    }

    public void inputQtyForm() {
        waitClickable(qtyForm);
        type(qtyForm,"0");
    }

    public void clickUpdateButton(){
        waitClickable(updateCartButton);
        click(updateCartButton);
    }

    public String getTextMessError(){
        return getText(messError);
    }

    public boolean checkMessErrorDisplay(){
        return getTextMessError().contains("Please enter a number greater than 0 in this field.");

    }










}
