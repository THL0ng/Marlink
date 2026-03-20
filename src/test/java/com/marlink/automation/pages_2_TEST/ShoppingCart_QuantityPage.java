package com.marlink.automation.pages_2_TEST;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.Random;
import static com.marlink.automation.utils.RandomData.*;

public class ShoppingCart_QuantityPage extends BasePage {
    public ShoppingCart_QuantityPage(WebDriver driver) {
        super(driver);
    }

    // --- 1. LOCATORS: GIỮ NGUYÊN 100% TỪ BẢN GỐC ---
    private static String savedQty;

    // Mini Cart & Strobe Lamp (Prefix chuẩn: button, input, link)
    private final By buttonAddToCartStrobeLampProduct = By.xpath("//strong[normalize-space()='Item reference: APR2940']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By inputQtyMiniCartForm = By.id("am-input");
    private final By buttonDecreaseQTY = By.xpath("//span[@data-amcart='qty-minus']");
    private final By buttonIncreaseQTY = By.xpath("//span[@data-amcart='qty-plus']");
    private final By buttonContinue = By.xpath("//button[@class='button am-btn-left']");
    private final By buttonUpdateMiniCart = By.xpath("//span[@class='amcart-refresh']");
    private final By inputQualityStrobeLampProductInCartCount = By.xpath("//a[normalize-space()='Strobe Lamp']/ancestor::div[contains(@class,'product-item-details')]//label[normalize-space()='Qty']/following-sibling::input");

    // Shopping Page & GPS Antenna
    private final By buttonAddToCartInShoppingPage = By.xpath("//button[@id='product-addtocart-button']");
    private final By imgGpsAntennaProductDetail = By.cssSelector("img[src*='gps_antenna_motorbike_quad_car_truck.jpg']");
    private final By labelPriceGpsAntennaProduct = By.cssSelector("td[class='col price'] span[class='price']");
    private final By inputQtyGpsAntenna = By.xpath("//tr[contains(@class,'item-info')]//td[@data-th='Qty']//input");

    // Subtotal & Cart
    private final By labelPriceOfSubTotal = By.xpath("//th[normalize-space()='Subtotal']/ancestor::tr//span[@class='price']");
    private final By buttonCartCount = By.cssSelector(".action.showcart");
    private final By labelCounterNumberCart = By.cssSelector("div.minicart-wrapper > a.action > span.counter");
    private final By buttonUpdateCartMain = By.xpath("//span[normalize-space()='Update Cart']");
    private final By buttonRemoveProduct = By.xpath("//a[@title='Remove']");
    private final By buttonOkToRemove = By.xpath("//button[@class='action-primary action-accept']");

    // Categories & Iridium (Sửa lại linkCarSSV chuẩn 100% của anh)
    private final By linkProductsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By linkCarSSVCategory = By.cssSelector("a[href='https://eshop247.officience.com/en/products/car-ssv.html']");
    private final By linkBikeQuad = By.cssSelector("a[href='https://eshop247.officience.com/en/products/motobike-quad.html']");
    private final By imgIridiumAntennaProductDetail = By.cssSelector("img[src*='iridium_antenna_motorbike_quad.png']");
    private final By labelPriceIridium = By.xpath("//tr[.//a[normalize-space()='Iridium antenna magnetic (10cm cable)']]//td[@data-th='Price']//span[@class='price']");
    private final By inputQtyIridium = By.xpath("//tr[.//a[normalize-space()='Iridium antenna magnetic (10cm cable)']]//td[@data-th='Qty']//input");

    // --- 2. SMART SYNC ---
    public void waitForCartSync() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String oldTotal = getText(labelCounterNumberCart).trim();
        wait.until(d -> {
            try {
                String current = d.findElement(labelCounterNumberCart).getText().trim();
                return !current.equals(oldTotal);
            } catch (Exception e) { return false; }
        });
    }

    // --- 3. ACTIONS ---
    public void clickProductsCategory() { click(linkProductsCategory); }
    public void clickCarSSVCategory() { click(linkCarSSVCategory); }
    public void clickBikeQuadCategory() { click(linkBikeQuad); }

    public void selectProductAndClickAddTocartButton() {
        jsClick(buttonAddToCartStrobeLampProduct);
        waitForCartSync();
    }

    public void inputInitialQty() {
        waitClickable(inputQtyMiniCartForm);
        clear(inputQtyMiniCartForm);
        int initial = new Random().nextInt(181) + 20; // 20-200
        savedQty = String.valueOf(initial);
        type(inputQtyMiniCartForm, savedQty);
    }

    public void clickDecreaseQTYRandomly() {
        int current = Integer.parseInt(savedQty);
        if (current <= 20) return;
        int maxClick = current - 20;
        int clicks = (maxClick > 2) ? new Random().nextInt(maxClick - 1) + 2 : 2;
        for (int i = 0; i < clicks; i++) { jsClick(buttonDecreaseQTY); }
        savedQty = String.valueOf(current - clicks);
    }

    public void clickIncreaseQTYRandomly() {
        int current = Integer.parseInt(savedQty);
        int clicks = new Random().nextInt(10) + 2; // Tăng ít nhất 2 lần click
        for (int i = 0; i < clicks; i++) { jsClick(buttonIncreaseQTY); }
        savedQty = String.valueOf(current + clicks);
    }

    public void clickUpdateButtonMiniCart() {
        Actionclick(buttonUpdateMiniCart);
        waitForCartSync();
    }

    public void clickContinueButton() { jsClick(buttonContinue); }

    public void clickcartCount() {
        waitForLoadingInvisible();
        jsClick(buttonCartCount);
    }

    public void compareQuality() {
        waitVisible(inputQualityStrobeLampProductInCartCount);
        String actual = getAttribute(inputQualityStrobeLampProductInCartCount, "value");
        Assert.assertEquals(actual, savedQty, "Số lượng sau khi Random không khớp!");
    }

    public void clickgpsAntennaDetail() { jsClick(imgGpsAntennaProductDetail); }
    public void clickIridiumAntennaDetail() { jsClick(imgIridiumAntennaProductDetail); }

    public void clickAddToCartButtonInShoppingPage() {
        Actionclick(buttonAddToCartInShoppingPage);
        waitForCartSync();
    }

    public void inputqualityGPSProduct() {
        waitClickable(inputQtyGpsAntenna);
        clear(inputQtyGpsAntenna);
        // Mỗi lần gọi hàm này, một số mới từ 20-200 sẽ được tạo ra
        int qty = new Random().nextInt(181) + 20;
        type(inputQtyGpsAntenna, String.valueOf(qty));
    }


    public void inputqualityInridiumProduct() {
        waitClickable(inputQtyIridium);
        clear(inputQtyIridium);
        // Đây là một lần "bốc thăm" hoàn toàn mới, xác suất trùng là rất thấp
        int qty = new Random().nextInt(181) + 20;
        type(inputQtyIridium, String.valueOf(qty));
    }




    public void clickUpdateButton() {
        click(buttonUpdateCartMain);
        waitForCartSync();
        waitForLoadingInvisible();
    }

    public void checkSubTotalAfterUpdate() {
        int qty = Integer.parseInt(getAttribute(inputQtyGpsAntenna, "value"));
        double price = Double.parseDouble(getText(labelPriceGpsAntennaProduct).replaceAll("[^0-9.]", ""));
        double actualSub = Double.parseDouble(getText(labelPriceOfSubTotal).replaceAll("[^0-9.]", ""));
        double expected = Math.round((qty * price) * 100.0) / 100.0;
        Assert.assertEquals(actualSub, expected, "Subtotal sai!");
    }

    public void checkSubTotalMultiProductAfterUpdate() {
        int qtyS = Integer.parseInt(getAttribute(inputQtyIridium, "value"));
        int qtyG = Integer.parseInt(getAttribute(inputQtyGpsAntenna, "value"));
        double priceS = Double.parseDouble(getText(labelPriceIridium).replaceAll("[^0-9.]", ""));
        double priceG = Double.parseDouble(getText(labelPriceGpsAntennaProduct).replaceAll("[^0-9.]", ""));
        double actualSub = Double.parseDouble(getText(labelPriceOfSubTotal).replaceAll("[^0-9.]", ""));
        double expected = Math.round(((qtyS * priceS) + (qtyG * priceG)) * 100.0) / 100.0;
        Assert.assertEquals(actualSub, expected, "Tổng Subtotal sai!");
    }

    public void waitForLoadingInvisible() {
        By mask = By.cssSelector(".loading-mask");
        try { new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(mask)); } catch (Exception ignored) {}
    }

    public void clickRemoveButton() {
        waitClickable(buttonRemoveProduct);
        jsClick(buttonRemoveProduct);
    }

    public void clickOkToRemove() {
        waitClickable(buttonOkToRemove);
        Actionclick(buttonOkToRemove);
    }


}