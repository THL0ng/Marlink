package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
    private final By priceOfProduct = By.cssSelector("span.price-excluding-tax > span.minicart-price > span.price");
    private final By priceOfSubTotal = By.cssSelector("div.amount > span.price-wrapper > span.price");






}
