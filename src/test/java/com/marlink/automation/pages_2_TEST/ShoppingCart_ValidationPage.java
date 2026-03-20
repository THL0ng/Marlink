package com.marlink.automation.pages_2_TEST;

import com.marlink.automation.base.BasePage;
import com.marlink.automation.utils.JsonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import static com.marlink.automation.utils.RandomData.*;

public class ShoppingCart_ValidationPage extends BasePage {
    public ShoppingCart_ValidationPage(WebDriver driver) {
        super(driver);
    }

    // --- LOCATORS (Tiêu chuẩn prefix: link/button/input/label) ---
    private final By linkProductsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By linkCarSsv = By.cssSelector("a[href='https://eshop247.officience.com/en/products/car-ssv.html']");
    private final By imgPowerExCableDetail = By.xpath("//img[contains(@src, 'apr4033_cable_alimentation.png')]");

    private final By buttonAddToCart = By.xpath("//button[@id='product-addtocart-button']");
    private final By inputQtyForm = By.xpath("//tr[contains(@class,'item-info')]//td[@data-th='Qty']//input");
    private final By buttonUpdateCart = By.xpath("//span[normalize-space()='Update Cart']");
    private final By labelQtyError = By.xpath("//div[contains(@id,'qty-error')]");

    private final By buttonCartCount = By.cssSelector(".action.showcart");
    private final By labelCounterNumberCart = By.cssSelector("div.minicart-wrapper > a.action > span.counter");

    private final By buttonRemoveProduct = By.xpath("//a[@title='Remove']");
    private final By buttonOkToRemove = By.xpath("//button[@class='action-primary action-accept']");
    private final By labelMessageRemoveSuccess = By.xpath("//p[normalize-space()='You have no items in your shopping cart.']");

    // --- SMART WAIT (Dùng chung cho cả Add/Update/Remove) ---
    public void waitForCartSync() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String oldTotal = getText(labelCounterNumberCart).trim();

        // Đợi con số trên giỏ hàng thay đổi so với trước khi thao tác
        wait.until(d -> {
            try {
                String current = d.findElement(labelCounterNumberCart).getText().trim();
                return !current.equals(oldTotal) && !current.isEmpty();
            } catch (Exception e) { return false; }
        });
    }

    // --- ACTIONS ---
    public void clickProductsCategory() { click(linkProductsCategory); }
    public void clickCarSSVCategory() { click(linkCarSsv); }
    public void clickDetailProduct() { click(imgPowerExCableDetail); }

    public void clickAddToCartButton() {
        click(buttonAddToCart);
        waitForCartSync(); // Sync ngay sau khi add
    }

    public void inputInvalidQty() {
        clear(inputQtyForm);
        type(inputQtyForm, String.valueOf(invalidNumber));
    }

    public void inputMaxLength() {
        clear(inputQtyForm);
        type(inputQtyForm, String.valueOf(maxLengthQTY));
    }

    public void inputRandomQTY() {
        clear(inputQtyForm);
        type(inputQtyForm, String.valueOf(RandomQty));
    }

    public void clickUpdateButton() {
        waitClickable(buttonUpdateCart);
        click(buttonUpdateCart);
    }

    public void clickcartCountButton() { click(buttonCartCount); }
    public void clickRemoveButton() { jsClick(buttonRemoveProduct); }

    public void clickOkToRemove() {
        Actionclick(buttonOkToRemove);
        waitForCartSync(); // Sync sau khi xóa
    }

    // --- GETTERS & ASSERTIONS ---
    public String getTextActualInvalidMessError() { return getText(labelQtyError); }
    public String getTextActualMaxLengthMessError() { return getText(labelQtyError); }
    public String getTextActualRemoveMess() { return getText(labelMessageRemoveSuccess); }

    public String getExpectedMessages(String key) {
        Map<String, String> messages = new HashMap<>();
        messages.put("err_invalidQTYShoppingCart", JsonHelper.get("shoppingCart_err_invalidQuality"));
        messages.put("err_maxLength", JsonHelper.get("shoppingCart_err_maxLength"));
        messages.put("remove_success", JsonHelper.get("shoppingCart_inform_reomvesuccess"));
        return messages.get(key);
    }
}