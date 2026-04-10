package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static com.marlink.automation.utils.DataHelper.*;


public class ShoppingCart_ValidationPage extends BasePage {
    public ShoppingCart_ValidationPage(WebDriver driver) {
        super(driver);
    }
    private static final Logger log = LogManager.getLogger(ShoppingCart_ValidationPage.class);

    // --- LOCATORS (Tiêu chuẩn prefix: link/button/input/label) ---
    private final By linkProductsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By linkCarSsv = By.cssSelector("a[href='https://eshop247.officience.com/en/products/car-ssv.html']");
    private final By imgPowerExCableDetail = By.cssSelector("img[src*='gps_antenna_motorbike_quad_car_truck.jpg");

    private final By buttonAddToCart = By.xpath("//button[@id='product-addtocart-button']");
    private final By inputQtyForm = By.xpath("//tr[contains(@class,'item-info')]//td[@data-th='Qty']//input");
    private final By buttonUpdateCart = By.xpath("//span[normalize-space()='Update Cart']");
    private final By labelQtyError = By.xpath("//div[contains(@id,'qty-error')]");

    private final By buttonShowCart = By.cssSelector(".action.showcart");
    private final By labelCartCounter = By.cssSelector("div.minicart-wrapper > a.action > span.counter");

    private final By buttonRemoveProduct = By.xpath("//a[@title='Remove']");
    private final By buttonConfirmRemove = By.xpath("//button[@class='action-primary action-accept']");
    private final By labelEmptyCartMessage = By.xpath("//p[normalize-space()='You have no items in your shopping cart.']");

    // --- SMART WAIT (Dùng chung cho cả Add/Update/Remove) ---
    public void waitForCartSync() {
        log.info("Waiting for Cart counter to sync...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String oldTotal = getText(labelCartCounter).trim();

        // Đợi con số trên giỏ hàng thay đổi so với trước khi thao tác
        wait.until(d -> {
            try {
                String current = d.findElement(labelCartCounter).getText().trim();
                return !current.equals(oldTotal) && !current.isEmpty();
            } catch (Exception e) { return false; }
        });
    }

    // --- ACTIONS ---
    public void clickProductsCategory() {
        log.info("Clicking Products category.");
        click(linkProductsCategory);
    }

    public void clickCarSSVCategory() {
        log.info("Clicking Car SSV category.");
        click(linkCarSsv);
    }

    public void clickProductDetail() {
        log.info("Clicking Product Detail (Power Ex Cable).");
        click(imgPowerExCableDetail);
    }

    public void clickAddToCartButton() {
        log.info("Clicking Add to Cart button.");
        click(buttonAddToCart);
        waitForCartSync(); // Sync ngay sau khi add
    }

    public void inputInvalidQty() {
        log.info("Inputting invalid Qty: {}", invalidNumber);
        clear(inputQtyForm);
        type(inputQtyForm, String.valueOf(invalidNumber));
    }

    public void inputMaxLengthQty() {
        log.info("Inputting max length Qty: {}", maxLengthQTY);
        clear(inputQtyForm);
        type(inputQtyForm, String.valueOf(maxLengthQTY));
    }

    public void inputRandomQTY() {
        log.info("Inputting random Qty: {}", RandomQty);
        clear(inputQtyForm);
        type(inputQtyForm, String.valueOf(RandomQty));
    }

    public void clickUpdateButton() {
        log.info("Clicking Update Cart button.");
        waitClickable(buttonUpdateCart);
        click(buttonUpdateCart);
    }

    public void openMiniCart() {
        log.info("Opening Mini Cart.");
        click(buttonShowCart); 
    }
    
    public void clickRemoveButton() {
        log.info("Clicking Remove product button.");
        jsClick(buttonRemoveProduct);
    }

    public void confirmRemoveProduct() {
        log.info("Confirming product removal.");
        Actionclick(buttonConfirmRemove);
        waitForCartSync(); 
    }

    // --- GETTERS & ASSERTIONS ---
    public String getActualQtyErrorMessage() { return getText(labelQtyError); }
    public String getActualEmptyCartMessage() { return getText(labelEmptyCartMessage); }

}