package com.marlink.automation.pages_02;

import com.marlink.automation.base.BasePage;
import com.marlink.automation.utils.JsonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By carSSV = By.cssSelector("a[href='https://eshop247.officience.com/en/products/car-ssv.html']");
    private final By addToCartaAluminiumRescueProduct = By.xpath("//strong[normalize-space()='Item reference: APR0967']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By addToCartCable3MProduct = By.xpath("//strong[normalize-space()='Item reference: APR4030']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By qualityForm = By.xpath("//input[@class='amcart-input']");
    private final By updateButton = By.xpath("//span[@class='amcart-refresh']");
    private final By messInformError = By.cssSelector(".message.error");
    private final By ContinueButton = By.xpath("//button[@class='button am-btn-left']");
    private final By qualityAluminiumRescueProductInCartCount = By.xpath("//a[normalize-space()='Aluminium Rescue Blanket']/ancestor::div[contains(@class,'product-item-details')]//label[normalize-space()='Qty']/following-sibling::input");
    private final By cartCountButton = By.cssSelector(".action.showcart");
    private final By totalItemInCart = By.cssSelector("div.items-total span.count");
    private final String EXPECTED_ERR_INVALIDNUMBER = JsonHelper.get("cartPage_err_invalidNubmer");


    public void clickToProductsCategory() {
        waitClickable(productsCategory);
        click(productsCategory);
    }

    public void clickCarSsvLink() {
        waitClickable(carSSV);
        click(carSSV);
    }

    public void clickAddToCartAluminiumRescueProduct() {
        waitClickable(addToCartaAluminiumRescueProduct);
        click(addToCartaAluminiumRescueProduct);
    }

    public void clickAddToCartCable3MProduct() {
        waitClickable(addToCartCable3MProduct);
        jsClick(addToCartCable3MProduct);
    }

    public void inputqualityProduct() {
        waitClickable(qualityForm);
        type(qualityForm, invalidNumber);
    }

    public void clickUpdateButton() {
        waitClickable(updateButton);
        jsClick(updateButton);
    }

    public String getExpectedMessages(String key) {
        Map<String, String> messages = new HashMap<>();
        messages.put("errInvalidNumber", EXPECTED_ERR_INVALIDNUMBER);
        return messages.get(key);
    }

    public String getActualError() {
        waitVisible(messInformError);
        return getText(messInformError);
    }

    public String updateQualityAluminiRescueProduct() {
        waitClickable(qualityForm);
        this.savedRandomQtyAluminium = String.valueOf(getRandomNumberProduct());
        clear(qualityForm);
        type(qualityForm, this.savedRandomQtyAluminium);
        return this.savedRandomQtyAluminium;
    }

    public String getSavedQualityAluminiRescueProduct() {
        return this.savedRandomQtyAluminium;
    }

    public String updateQualityCable3MProduct() {
        waitClickable(qualityForm);
        this.savedRandomQtyCable3M = String.valueOf(getRandomNumberProduct());
        clear(qualityForm);
        type(qualityForm, this.savedRandomQtyCable3M);
        return this.savedRandomQtyCable3M;
    }

    public String getSavedQualityCable3MProduct() {
        return this.savedRandomQtyCable3M;
    }

    public void clickContinueButton() {
        waitClickable(ContinueButton);
        jsClick(ContinueButton);

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

    public void clickcartCountButton() {
        waitClickable(cartCountButton);
        jsClick(cartCountButton);
        delay(3);
    }

    public String showActualQualityAluminiumRescueProduct() {
        scrollIntoView(qualityAluminiumRescueProductInCartCount);
        waitForTextToChange(qualityAluminiumRescueProductInCartCount);
        return getText(qualityAluminiumRescueProductInCartCount);
    }

    public void compareQuality() {
        String expectedQuality = getSavedQualityAluminiRescueProduct();
        waitForLoadingInvisible();
        waitVisible(qualityAluminiumRescueProductInCartCount);
        String actualQuality = showActualQualityAluminiumRescueProduct();

        if (actualQuality.equals(expectedQuality)) {
            System.out.println("Số lượng chất lượng trong giỏ hàng và trong form khớp nhau.");
        } else {
            System.out.println("Số lượng chất lượng không khớp!");
            System.out.println("Giá trị mong đợi: " + expectedQuality);
            System.out.println("Giá trị thực tế: " + actualQuality);
        }
    }


    public void compareTotalQuantity() {
        // 1. Đợi giỏ hàng cập nhật xong (Dùng delay hoặc loading invisible)
        waitForLoadingInvisible();
        delay(3);

        // 2. Lấy giá trị thực tế (Actual) từ Header
        waitVisible(totalItemInCart);
        String actualText = getText(totalItemInCart).trim();
        // Lọc chỉ giữ lại số (phòng trường hợp nó hiện "10 items")
        int actualTotal = Integer.parseInt(actualText.replaceAll("[^0-9]", ""));

        // 3. Lấy giá trị đã lưu (Expected) và chuyển sang kiểu số
        // Dùng toán tử ba ngôi để gán bằng 0 nếu lỡ may biến bị null
        int savedAlumini = Integer.parseInt(getSavedQualityAluminiRescueProduct() != null ? getSavedQualityAluminiRescueProduct() : "0");
        int savedCable = Integer.parseInt(getSavedQualityCable3MProduct() != null ? getSavedQualityCable3MProduct() : "0");

        int expectedTotal = savedAlumini + savedCable;

        // 4. So sánh và in Log
        System.out.println("---------- KIỂM TRA TỔNG GIỎ HÀNG ----------");
        System.out.println("Sản phẩm Alumini đã lưu: " + savedAlumini);
        System.out.println("Sản phẩm Cable 3M đã lưu: " + savedCable);
        System.out.println("=> Tổng mong đợi (Expected): " + expectedTotal);
        System.out.println("=> Thực tế trên Header (Actual): " + actualTotal);

        if (actualTotal == expectedTotal) {
            System.out.println("KẾT QUẢ: PASSED - Tổng số lượng khớp!");
        } else {
            System.out.println("KẾT QUẢ: FAILED - Tổng số lượng KHÔNG KHỚP!");
            // Dùng Assert để đánh dấu đỏ Test Case nếu sai
            Assert.assertEquals(actualTotal, expectedTotal, "Tổng số lượng trong giỏ hàng bị sai!");
        }
        System.out.println("--------------------------------------------");
    }

    public WebElement scrollIntoView(By locator) {
        WebElement el = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", el
        );
        return el;

    }
}
