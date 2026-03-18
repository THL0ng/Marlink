package com.marlink.automation.pages_02;

import com.marlink.automation.base.BasePage;
import com.marlink.automation.utils.JsonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.marlink.automation.utils.RandomData.*;

public class ShoppingCartPage extends BasePage {
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }


    private final By productsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By carSsv = By.cssSelector("a[href='https://eshop247.officience.com/en/products/car-ssv.html']");
    private final By powerExCableDetail = By.xpath("//img[contains(@src, 'apr4033_cable_alimentation.png')]");
    private final By addToCartButton = By.xpath("//button[@id='product-addtocart-button']");
    private final By qtyForm = By.xpath("//tr[contains(@class,'item-info')]//td[@data-th='Qty']//input");
    private final By updateCartButton = By.xpath("//span[normalize-space()='Update Cart']");
    private final By InvalidErrMess = By.xpath("//div[contains(@id,'qty-error')]");
    private final By maxLengthErrMess = By.xpath("//div[contains(@id,'qty-error')]");
    private final String EXPECTED_ERR_INVALIDQTY = JsonHelper.get("shoppingCart_err_invalidQuality");
    private final String EXPECTED_ERR_MAXLENGTH = JsonHelper.get("shoppingCart_err_maxLength");

    private final By cartCountButton = By.cssSelector(".action.showcart");
    private final By removeProductButton = By.xpath("//a[@title='Remove']");
    private final By okToRemoveButton = By.xpath("//button[@class='action-primary action-accept']");
    private final By messageRemoveSuccess = By.xpath("//p[normalize-space()='You have no items in your shopping cart.']");
    private final String EXPECTED_INFORM_REMOVESUCCESS = JsonHelper.get("shoppingCart_inform_reomvesuccess");

    private final By counterNumberCart = By.cssSelector("div.minicart-wrapper > a.action > span.counter");

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

    public void inputInvalidQty() {
        waitClickable(qtyForm);
        type(qtyForm,invalidNumber);
    }

    public void inputMaxLength() {
        waitClickable(qtyForm);
        type(qtyForm,maxLengthQTY);
    }


    public void inputRandomQTY() {
        waitClickable(qtyForm);
        clear(qtyForm);
        type(qtyForm, String.valueOf(RandomQty));
    }

    public void clickUpdateButton(){
        waitClickable(updateCartButton);
        click(updateCartButton);

    }

    public String getTextActualInvalidMessError(){
        return getText(InvalidErrMess);
    }

    public String getTextActualMaxLengthMessError(){
        return getText(maxLengthErrMess);
    }

    public String getTextActualRemoveMess(){
        return getText(messageRemoveSuccess);
    }

    public String getExpectedMessages(String key) {
        Map<String, String> messages = new HashMap<>();
        messages.put("err_invalidQTYShoppingCart", EXPECTED_ERR_INVALIDQTY);
        messages.put("err_maxLength", EXPECTED_ERR_MAXLENGTH);
        messages.put("remove_success", EXPECTED_INFORM_REMOVESUCCESS);
        return messages.get(key);
    }

    public void clickcartCountButton(){
        updateCartSmartWait(counterNumberCart);
        waitClickable(cartCountButton);
        click(cartCountButton);
    }

    public void clickRemoveButton(){
        waitClickable(removeProductButton);
        click(removeProductButton);
    }

    public void clickOkToRemove(){
        waitClickable(okToRemoveButton);
        Actionclick(okToRemoveButton);
    }


    public void updateCartSmartWait(By totalLocator) {
        // 1. Chụp lại con số cũ làm mốc
        String oldTotal = getText(counterNumberCart).trim();

        // 2. Bấm nút Update
        click(updateCartButton);

        // 3. Dùng FluentWait để check mỗi 500ms cho đến khi text THAY ĐỔI
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.pollingEvery(Duration.ofMillis(500))
                .ignoring(StaleElementReferenceException.class) // Quan trọng: Bỏ qua lỗi khi trang đang load
                .until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver d) {
                        try {
                            String currentTotal = d.findElement(counterNumberCart).getText().trim();
                            // Chỉ trả về true khi con số hiện tại khác số cũ và không rỗng
                            return !currentTotal.equals(oldTotal) && !currentTotal.isEmpty();
                        } catch (Exception e) {
                            return false; // Nếu đang load (lỗi) thì tiếp tục đợi
                        }
                    }
                });

        System.out.println("Cập nhật thành công! Số cũ: " + oldTotal + " -> Số mới: " + getText(totalLocator));
    }


}
