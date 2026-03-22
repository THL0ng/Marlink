package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import com.marlink.automation.utils.JsonHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger log = LogManager.getLogger(CartPage.class);

    private static String savedQtyAluminium;
    private static String savedQtyCable3M;

    // --- LOCATORS VỚI PREFIX ---
    private final By linkProductsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By linkCarSSV = By.cssSelector("a[href='https://eshop247.officience.com/en/products/car-ssv.html']");
    private final By buttonAddAluminium = By.xpath("//strong[normalize-space()='Item reference: APR0967']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By buttonAddCable3M = By.xpath("//strong[normalize-space()='Item reference: APR4030']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By inputQtyMiniCart = By.xpath("//input[@class='amcart-input']");
    private final By buttonUpdateMIniCart = By.xpath("//span[@class='amcart-refresh']");
    private final By labelErrorMsg = By.cssSelector(".message.error");
    private final By buttonContinueShopping = By.xpath("//button[@class='button am-btn-left']");
    private final By inputQtyAluminiumInCart = By.xpath("//a[normalize-space()='Aluminium Rescue Blanket']/ancestor::div[contains(@class,'product-item-details')]//label[normalize-space()='Qty']/following-sibling::input");
    private final By buttonShowCart = By.cssSelector(".action.showcart");
    private final By labelTotalItemCount = By.cssSelector("div.items-total span.count");
    private final String EXPECTED_ERR_INVALIDNUMBER = JsonHelper.get("cartPage_err_invalidNubmer");
    private final By labelPriceAluminium = By.xpath("//a[normalize-space()='Aluminium Rescue Blanket']/ancestor::*[contains(@class,'product-item')]//span[@class='minicart-price']//span[@class='price']");
    private final By labelPriceCable3M = By.xpath("//a[normalize-space()='Cable 3m for GPS antenna']/ancestor::*[contains(@class,'product-item')]//span[@class='minicart-price']//span[@class='price']");
    private final By labelSubTotal = By.cssSelector("div.amount > span.price-wrapper > span.price");

    // --- METHODS ĐÃ ĐỔI TÊN THEO PREFIX ---
    public void clickProductsCategoryLink() {
        log.info("Clicking Products category link.");
        waitClickable(linkProductsCategory);
        click(linkProductsCategory);
    }

    public void clickCarSsvLink() {
        log.info("Clicking Car SSV category link.");
        waitClickable(linkCarSSV);
        click(linkCarSSV);
    }

    public void addAluminiumToCart() {
        log.info("Adding Aluminium Rescue Blanket to cart.");
        waitClickable(buttonAddAluminium);
        click(buttonAddAluminium);
    }

    public void addCable3MToCart() {
        log.info("Adding Cable 3m to cart.");
        waitClickable(buttonAddCable3M);
        jsClick(buttonAddCable3M);
    }

    public void inputInvalidQty() {
        log.info("Inputting invalid quantity: {}", invalidNumber);
        waitClickable(inputQtyMiniCart);
        type(inputQtyMiniCart, invalidNumber);
    }

    public void clickUpdateMiniCart() {
        log.info("Clicking Update button in Mini Cart.");
        waitClickable(buttonUpdateMIniCart);
        jsClick(buttonUpdateMIniCart);
    }

    public String getExpectedMessages(String key) {
        Map<String, String> messages = new HashMap<>();
        messages.put("errInvalidNumber", EXPECTED_ERR_INVALIDNUMBER);
        return messages.get(key);
    }

    public String getActualErrorMessage() {
        waitVisible(labelErrorMsg);
        return getText(labelErrorMsg);
    }

    public String updateAluminiumQtyRandomly() {
        waitClickable(inputQtyMiniCart);
        savedQtyAluminium = String.valueOf(getRandomNumberProduct());
        log.info("Updating Aluminium Qty to: {}", savedQtyAluminium);
        clear(inputQtyMiniCart);
        type(inputQtyMiniCart, savedQtyAluminium);
        return savedQtyAluminium;
    }

    public String getSavedAluminiumQuality() {
        return savedQtyAluminium;
    }

    public String updateCable3MQtyRandomly() {
        waitClickable(inputQtyMiniCart);
        savedQtyCable3M = String.valueOf(getRandomNumberProduct());
        log.info("Updating Cable 3M Qty to: {}", savedQtyCable3M);
        clear(inputQtyMiniCart);
        type(inputQtyMiniCart, savedQtyCable3M);
        return savedQtyCable3M;
    }

    public String getSavedCable3MQuality() {
        return savedQtyCable3M;
    }

    public void clickContinueShopping() {
        log.info("Clicking Continue Shopping button.");
        waitClickable(buttonContinueShopping);
        jsClick(buttonContinueShopping);
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

    public void openMiniCart() {
        log.info("Opening Mini Cart.");
        waitClickable(buttonShowCart);
        jsClick(buttonShowCart);
        delay(3);
    }

    public String getActualAluminiumQtyInCart() {
        scrollIntoView(inputQtyAluminiumInCart);
        waitForTextToChange(inputQtyAluminiumInCart);
        return getAttribute(inputQtyAluminiumInCart,"value");
    }

    /*public void compareQuality() {
        String expectedQuality = getSavedAluminiumQuality();
        waitForLoadingInvisible();
        waitVisible(inputQtyAluminiumInCart);
        String actualQuality = getActualAluminiumQtyInCart();

        if (actualQuality.equals(expectedQuality)) {
            System.out.println("Số lượng khớp nhau.");
        } else {
            System.out.println("Số lượng không khớp! Expected: " + expectedQuality + " Actual: " + actualQuality);
        }
    }*/

    /*public void compareTotalQuantity() {
        waitForLoadingInvisible();
        delay(3);
        waitVisible(labelTotalItemCount);
        String actualText = getText(labelTotalItemCount).trim();
        int actualTotal = Integer.parseInt(actualText.replaceAll("[^0-9]", ""));

        int savedAlumini = Integer.parseInt(getSavedAluminiumQuality() != null ? getSavedAluminiumQuality() : "0");
        int savedCable = Integer.parseInt(getSavedCable3MQuality() != null ? getSavedCable3MQuality() : "0");
        int expectedTotal = savedAlumini + savedCable;

        if (actualTotal == expectedTotal) {
            System.out.println("Tổng khớp!");
        } else {
            Assert.assertEquals(actualTotal, expectedTotal, "Tổng số lượng sai!");
        }
    }*/

    /*public void comparePriceWithSubtotal() {
        int q1 = Integer.parseInt(savedQtyAluminium);
        int q2 = Integer.parseInt(savedQtyCable3M);

        double p1 = Double.parseDouble(driver.findElement(labelPriceAluminium).getText().replaceAll("[^0-9.]", ""));
        double p2 = Double.parseDouble(driver.findElement(labelPriceCable3M).getText().replaceAll("[^0-9.]", ""));
        double subtotal = Double.parseDouble(driver.findElement(labelSubTotal).getText().replaceAll("[^0-9.]", ""));

        double expected = Math.round(((q1 * p1) + (q2 * p2)) * 100.0) / 100.0;
        System.out.println("UI Subtotal: " + subtotal + " | Expected: " + expected);
    }*/


    public int getActualTotalItemsCount() {
        log.info("Lấy tổng số lượng sản phẩm thực tế trên UI.");
        waitForLoadingInvisible();
        delay(3);
        waitVisible(labelTotalItemCount);
        String text = getText(labelTotalItemCount).replaceAll("[^0-9]", "");
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }

    public double calculateExpectedTotalQty() {
        double p1 = Double.parseDouble(driver.findElement(labelPriceAluminium).getText().replaceAll("[^0-9.]", ""));
        double p2 = Double.parseDouble(driver.findElement(labelPriceCable3M).getText().replaceAll("[^0-9.]", ""));
        int q1 = Integer.parseInt(savedQtyAluminium);
        int q2 = Integer.parseInt(savedQtyCable3M);

        double expected = (q1 * p1) + (q2 * p2);
        return Math.round(expected * 100.0) / 100.0;
    }

    public double getActualSubtotal() {
        return Double.parseDouble(getText(labelSubTotal).replaceAll("[^0-9.]", ""));
    }

    public double calculateExpectedSubtotal() {
        double p1 = Double.parseDouble(getText(labelPriceAluminium).replaceAll("[^0-9.]", ""));
        double p2 = Double.parseDouble(getText(labelPriceCable3M).replaceAll("[^0-9.]", ""));
        int q1 = Integer.parseInt(savedQtyAluminium);
        int q2 = Integer.parseInt(savedQtyCable3M);

        double total = (q1 * p1) + (q2 * p2);
        return Math.round(total * 100.0) / 100.0;
    }




}