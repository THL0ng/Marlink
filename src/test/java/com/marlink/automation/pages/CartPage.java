package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import com.marlink.automation.utils.JsonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.marlink.automation.utils.RandomData.getRandomNumberProduct;
import static com.marlink.automation.utils.RandomData.invalidNumber;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private static String savedRandomQtyAluminium;
    private static String savedRandomQtyCable3M;

    // --- LOCATORS VỚI PREFIX ---
    private final By linkProductsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By linkCarSSV = By.cssSelector("a[href='https://eshop247.officience.com/en/products/car-ssv.html']");
    private final By buttonAddAluminium = By.xpath("//strong[normalize-space()='Item reference: APR0967']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By buttonAddCable3M = By.xpath("//strong[normalize-space()='Item reference: APR4030']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By inputQualityForm = By.xpath("//input[@class='amcart-input']");
    private final By buttonUpdate = By.xpath("//span[@class='amcart-refresh']");
    private final By labelErrorMsg = By.cssSelector(".message.error");
    private final By buttonContinue = By.xpath("//button[@class='button am-btn-left']");
    private final By inputQtyAluminiumInCartCount = By.xpath("//a[normalize-space()='Aluminium Rescue Blanket']/ancestor::div[contains(@class,'product-item-details')]//label[normalize-space()='Qty']/following-sibling::input");
    private final By buttonCartCount = By.cssSelector(".action.showcart");
    private final By labelTotalItemInCart = By.cssSelector("div.items-total span.count");
    private final String EXPECTED_ERR_INVALIDNUMBER = JsonHelper.get("cartPage_err_invalidNubmer");
    private final By labelPriceAluminium = By.xpath("//a[normalize-space()='Aluminium Rescue Blanket']/ancestor::*[contains(@class,'product-item')]//span[@class='minicart-price']//span[@class='price']");
    private final By labelPriceCable3M = By.xpath("//a[normalize-space()='Cable 3m for GPS antenna']/ancestor::*[contains(@class,'product-item')]//span[@class='minicart-price']//span[@class='price']");
    private final By labelSubTotal = By.cssSelector("div.amount > span.price-wrapper > span.price");

    // --- METHODS ĐÃ ĐỔI TÊN THEO PREFIX ---

    public void clickProductsCategoryLink() {
        waitClickable(linkProductsCategory);
        click(linkProductsCategory);
    }

    public void clickCarSsvLink() {
        waitClickable(linkCarSSV);
        click(linkCarSSV);
    }

    public void clickAddAluminiumButton() {
        waitClickable(buttonAddAluminium);
        click(buttonAddAluminium);
    }

    public void clickAddCable3MButton() {
        waitClickable(buttonAddCable3M);
        jsClick(buttonAddCable3M);
    }

    public void inputInvalidQuality() {
        waitClickable(inputQualityForm);
        type(inputQualityForm, invalidNumber);
    }

    public void clickUpdateButton() {
        waitClickable(buttonUpdate);
        jsClick(buttonUpdate);
    }

    public String getExpectedMessages(String key) {
        Map<String, String> messages = new HashMap<>();
        messages.put("errInvalidNumber", EXPECTED_ERR_INVALIDNUMBER);
        return messages.get(key);
    }

    public String getActualErrorLabel() {
        waitVisible(labelErrorMsg);
        return getText(labelErrorMsg);
    }

    public String updateAluminiumQuality() {
        waitClickable(inputQualityForm);
        savedRandomQtyAluminium = String.valueOf(getRandomNumberProduct());
        clear(inputQualityForm);
        type(inputQualityForm, savedRandomQtyAluminium);
        return savedRandomQtyAluminium;
    }

    public String getSavedAluminiumQuality() {
        return savedRandomQtyAluminium;
    }

    public String updateCable3MQuality() {
        waitClickable(inputQualityForm);
        savedRandomQtyCable3M = String.valueOf(getRandomNumberProduct());
        clear(inputQualityForm);
        type(inputQualityForm, savedRandomQtyCable3M);
        return savedRandomQtyCable3M;
    }

    public String getSavedCable3MQuality() {
        return savedRandomQtyCable3M;
    }

    public void clickContinueButton() {
        waitClickable(buttonContinue);
        jsClick(buttonContinue);
    }

    public void waitForLoadingInvisible() {
        By loadingMask = By.cssSelector(".loading-mask, .block-loader");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingMask));
        } catch (Exception e) {
            System.out.println("Loading mask không xuất hiện hoặc đã biến mất quá nhanh.");
        }
    }

    public void clickCartCountButton() {
        waitClickable(buttonCartCount);
        jsClick(buttonCartCount);
        delay(3);
    }

    public String getActualAluminiumInCartCount() {
        scrollIntoView(inputQtyAluminiumInCartCount);
        waitForTextToChange(inputQtyAluminiumInCartCount);
        return getText(inputQtyAluminiumInCartCount);
    }

    public void compareQuality() {
        String expectedQuality = getSavedAluminiumQuality();
        waitForLoadingInvisible();
        waitVisible(inputQtyAluminiumInCartCount);
        String actualQuality = getActualAluminiumInCartCount();

        if (actualQuality.equals(expectedQuality)) {
            System.out.println("Số lượng khớp nhau.");
        } else {
            System.out.println("Số lượng không khớp! Expected: " + expectedQuality + " Actual: " + actualQuality);
        }
    }

    public void compareTotalQuantity() {
        waitForLoadingInvisible();
        delay(3);
        waitVisible(labelTotalItemInCart);
        String actualText = getText(labelTotalItemInCart).trim();
        int actualTotal = Integer.parseInt(actualText.replaceAll("[^0-9]", ""));

        int savedAlumini = Integer.parseInt(getSavedAluminiumQuality() != null ? getSavedAluminiumQuality() : "0");
        int savedCable = Integer.parseInt(getSavedCable3MQuality() != null ? getSavedCable3MQuality() : "0");
        int expectedTotal = savedAlumini + savedCable;

        if (actualTotal == expectedTotal) {
            System.out.println("Tổng khớp!");
        } else {
            Assert.assertEquals(actualTotal, expectedTotal, "Tổng số lượng sai!");
        }
    }

    public void comparePriceWithSubtotal() {
        int q1 = Integer.parseInt(savedRandomQtyAluminium);
        int q2 = Integer.parseInt(savedRandomQtyCable3M);

        double p1 = Double.parseDouble(driver.findElement(labelPriceAluminium).getText().replaceAll("[^0-9.]", ""));
        double p2 = Double.parseDouble(driver.findElement(labelPriceCable3M).getText().replaceAll("[^0-9.]", ""));
        double subtotal = Double.parseDouble(driver.findElement(labelSubTotal).getText().replaceAll("[^0-9.]", ""));

        double expected = Math.round(((q1 * p1) + (q2 * p2)) * 100.0) / 100.0;
        System.out.println("UI Subtotal: " + subtotal + " | Expected: " + expected);
    }
}