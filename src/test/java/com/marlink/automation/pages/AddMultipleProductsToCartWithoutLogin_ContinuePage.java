package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddMultipleProductsToCartWithoutLogin_ContinuePage extends BasePage {

    public AddMultipleProductsToCartWithoutLogin_ContinuePage(WebDriver driver) {
        super(driver);
    }

    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By iritrack = By.cssSelector("a[href='https://eshop247.officience.com/en/products/motobike-quad.html']");
    private final By gpsMagneticAntenna = By.xpath("//strong[normalize-space()='Item reference: APR2861']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By qualityForm = By.xpath("//input[@class='amcart-input']");
    private final By updateButton = By.xpath("//span[@class='amcart-refresh']");
    private final By ContinueButton = By.xpath("//button[@class='button am-btn-left']");
    private final By smalltrack = By.xpath("//a[contains(text(),'Smalltrack')]");
    private final By bracketSmallTrack = By.xpath("//strong[normalize-space()='Item reference: APR4072']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By survivalKit = By.xpath("//a[contains(text(),'Survival Equipment/Kit')]");
    private final By aluminiumRescue = By.xpath("//strong[normalize-space()='Item reference: APR0967']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By cartCountButton = By.cssSelector(".action.showcart");
    private final By qualityGPSProduct = By.xpath("//a[normalize-space()='GPS magnetic antenna (10cm cable)']/ancestor::div[contains(@class,'product-item-details')]//label[normalize-space()='Qty']/following-sibling::input");
    private final By qualityBracketSmallProduct = By.xpath("//a[normalize-space()='Bracket SmallTrack']/ancestor::div[contains(@class,'product-item-details')]//label[normalize-space()='Qty']/following-sibling::input");
    private final By qualityAluminRescueProduct = By.xpath("//a[normalize-space()='Aluminium Rescue Blanket']/ancestor::div[contains(@class,'product-item-details')]//label[normalize-space()='Qty']/following-sibling::input");


    public void clickToProductsCategory() {
        waitClickable(productsCategory);
        click(productsCategory);
    }

    public void clickToIritrack() {
        waitClickable(iritrack);
        click(iritrack);
    }

    public void addGpsMagneticAntenna() {
        waitClickable(gpsMagneticAntenna);
        click(gpsMagneticAntenna);
    }

    public void inputqualityFormForGPS() {
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

    public void clickToSmalltrack() {
        waitClickable(smalltrack);
        jsClick(smalltrack);
    }

    public void addbracketSmallTrack() {
        waitClickable(bracketSmallTrack);
        click(bracketSmallTrack);
    }

    public void inputqualityFormForBracket() {
        waitClickable(qualityForm);
        type(qualityForm, "20");
    }

    public void clickTOSurvivalKit() {
        waitClickable(survivalKit);
        jsClick(survivalKit);
    }

    public void addAluminiumRescue() {
        waitClickable(aluminiumRescue);
        click(aluminiumRescue);
    }

    public void inputqualityFormForAlumin() {
        waitClickable(qualityForm);
        type(qualityForm, "20");
    }

    public void clickcartCountButton() throws InterruptedException {
        Thread.sleep(3000);
        jsClick(cartCountButton);
    }

    public String showActualQualityGPSProduct() {
        waitVisible(qualityGPSProduct);
        return getText(qualityGPSProduct);
    }

    public String showActualQualityBracketProduct() {
        //scrollIntoView(qualityBracketSmallProduct);
        waitVisible(qualityBracketSmallProduct);
        return getText(qualityBracketSmallProduct);
    }

    public String showActualQualityAluminProduct() {
        //scrollIntoView(qualityAluminRescueProduct);
        waitVisible(qualityAluminRescueProduct);
        return getText(qualityAluminRescueProduct);

    }

    public void compareQuality() {
        String actualQualityGPS = showActualQualityGPSProduct();
        String expectedQualityGPS = "10";

        String actualQualityBracket = showActualQualityBracketProduct();
        String expectedQualityBracket = "20";

        String actualQualityAlumin = showActualQualityAluminProduct();
        String expectedQualityAlumin = "20";

        if (actualQualityGPS.equals(expectedQualityGPS)
                && actualQualityBracket.equals(expectedQualityBracket)
                && actualQualityAlumin.equals(expectedQualityAlumin)) {

            System.out.println("Tất cả số lượng đều khớp!");

        } else {

            System.out.println("Có ít nhất 1 sản phẩm không khớp!");

            if (!actualQualityGPS.equals(expectedQualityGPS)) {
                System.out.println("GPS sai - Expected: " + expectedQualityGPS +
                        " | Actual: " + actualQualityGPS);
            }

            if (!actualQualityBracket.equals(expectedQualityBracket)) {
                System.out.println("Bracket sai - Expected: " + expectedQualityBracket +
                        " | Actual: " + actualQualityBracket);
            }

            if (!actualQualityAlumin.equals(expectedQualityAlumin)) {
                System.out.println("Alumin sai - Expected: " + expectedQualityAlumin +
                        " | Actual: " + actualQualityAlumin);
            }
        }

    }

    public WebElement scrollIntoView(By locator) {
        WebElement el = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", el
        );
        return el;
    }






}
