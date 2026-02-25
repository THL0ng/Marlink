package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddToCartWithoutLogin extends BasePage {


    public AddToCartWithoutLogin(WebDriver driver) {
        super(driver);
    }

    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By iritrack = By.xpath("//a[normalize-space()='Bike / Quad']/following-sibling::ul//a[@title='Iritrack']");
    private final By addToCartProduct = By.xpath("//strong[normalize-space()='Item reference: APR4059']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");




}
