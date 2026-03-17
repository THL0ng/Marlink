package com.marlink.automation.pages_02;

import com.marlink.automation.base.BasePage;
import com.marlink.automation.utils.JsonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.marlink.automation.utils.RandomData.getRandomNumberProduct;
import static com.marlink.automation.utils.RandomData.invalidNumber;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private String savedRandomQty;
    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By carSSV = By.cssSelector("a[href='https://eshop247.officience.com/en/products/car-ssv.html']");
    private final By addToCartProduct = By.xpath("//strong[normalize-space()='Item reference: APR0967']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By qualityForm = By.xpath("//input[@class='amcart-input']");
    private final By updateButton = By.xpath("//span[@class='amcart-refresh']");
    private final By messInformError = By.cssSelector(".message.error");
    private final By ContinueButton = By.xpath("//button[@class='button am-btn-left']");
    private final By qualityCartCount = By.xpath("//label[text()='Qty']/following-sibling::input");
    private final By cartCountButton = By.cssSelector(".action.showcart");
    private final String EXPECTED_ERR_INVALIDNUMBER = JsonHelper.get("cartPage_err_invalidNubmer");


    public void clickToProductsCategory() {
        waitClickable(productsCategory);
        click(productsCategory);
    }

    public void clickCarSsvLink(){
        waitClickable(carSSV);
        click(carSSV);
    }

    public void clickAddToCartProduct(){
        waitClickable(addToCartProduct);
        click(addToCartProduct);
    }

    public void inputqualityProduct() {
        waitClickable(qualityForm);
        type(qualityForm, invalidNumber);
    }

    public void clickUpdateButton() {
        waitClickable(updateButton);
        click(updateButton);
    }

    public String getExpectedMessages(String key) {
        Map<String, String> messages = new HashMap<>();
        messages.put("errInvalidNumber", EXPECTED_ERR_INVALIDNUMBER);
        return messages.get(key);
    }

    public String getActualError(){
        waitVisible(messInformError);
        return getText(messInformError);
    }

    public String updateQualityProduct() {
        waitClickable(qualityForm);
        this.savedRandomQty = String.valueOf(getRandomNumberProduct());
        clear(qualityForm);
        type(qualityForm, this.savedRandomQty);
        return this.savedRandomQty;
    }

    public String getSavedQuality() {
        return this.savedRandomQty;
    }

    public void clickContinueButton() {
        waitClickable(ContinueButton);
        jsClick(ContinueButton);

    }

    public void waitForLoadingInvisible() {
        By loadingMask = By.cssSelector(".loading-mask");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingMask));
    }

    public void clickcartCountButton() {
        waitVisible(cartCountButton);
        jsClick(cartCountButton);
    }

    public String showActualQualityProduct() {
        waitForElementToUpdate(qualityCartCount);
        return getText(qualityCartCount);
    }

    public void compareQuality() {
        String expectedQuality = getSavedQuality();
        waitForLoadingInvisible();
        waitVisible(qualityCartCount);
        String actualQuality = showActualQualityProduct();

        if (actualQuality.equals(expectedQuality)) {
            System.out.println("Số lượng chất lượng trong giỏ hàng và trong form khớp nhau.");
        } else {
            System.out.println("Số lượng chất lượng không khớp!");
            System.out.println("Giá trị mong đợi: " + expectedQuality);
            System.out.println("Giá trị thực tế: " + actualQuality);
        }
    }



}
