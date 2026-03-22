package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Random;

public class ShoppingCart_QuantityPage extends BasePage {
    public ShoppingCart_QuantityPage(WebDriver driver) {
        super(driver);
    }
    private static final Logger log = LogManager.getLogger(ShoppingCart_QuantityPage.class);

    private static String savedQty;

    // Mini Cart & Strobe Lamp (Prefix chuẩn: button, input, link)
    private final By buttonAddToCartStrobeLampProduct = By.xpath("//strong[normalize-space()='Item reference: APR2940']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By inputQtyMiniCart = By.id("am-input");
    private final By buttonDecreaseQTY = By.xpath("//span[@data-amcart='qty-minus']");
    private final By buttonIncreaseQTY = By.xpath("//span[@data-amcart='qty-plus']");
    private final By buttonContinue = By.xpath("//button[@class='button am-btn-left']");
    private final By buttonUpdateMiniCart = By.xpath("//span[@class='amcart-refresh']");
    private final By inputQtyStrobeLampInCart = By.xpath("//a[normalize-space()='Strobe Lamp']/ancestor::div[contains(@class,'product-item-details')]//label[normalize-space()='Qty']/following-sibling::input");

    // Shopping Page & GPS Antenna
    private final By buttonAddToCartInShoppingPage = By.xpath("//button[@id='product-addtocart-button']");
    private final By imgGpsAntennaDetail = By.cssSelector("img[src*='gps_antenna_motorbike_quad_car_truck.jpg']");
    private final By labelPriceGpsAntennaProduct = By.cssSelector("td[class='col price'] span[class='price']");
    private final By inputQtyGpsAntenna = By.xpath("//tr[contains(@class,'item-info')]//td[@data-th='Qty']//input");

    // Subtotal & Cart
    private final By labelPriceOfSubTotal = By.xpath("//th[normalize-space()='Subtotal']/ancestor::tr//span[@class='price']");
    private final By buttonShowCart = By.cssSelector(".action.showcart");
    private final By labelCartCounter = By.cssSelector("div.minicart-wrapper > a.action > span.counter");
    private final By buttonUpdateCartMain = By.xpath("//span[normalize-space()='Update Cart']");
    private final By buttonRemoveProduct = By.xpath("//a[@title='Remove']");
    private final By buttonConfirmRemove = By.xpath("//button[@class='action-primary action-accept']");

    // Categories & Iridium (Sửa lại linkCarSSV chuẩn 100% của anh)
    private final By linkProductsCategory = By.xpath("//span[normalize-space()='Products']");
    private final By linkCarSSVCategory = By.cssSelector("a[href='https://eshop247.officience.com/en/products/car-ssv.html']");
    private final By linkBikeQuadCategory = By.cssSelector("a[href='https://eshop247.officience.com/en/products/motobike-quad.html']");
    private final By imgIridiumAntennaProductDetail = By.cssSelector("img[src*='iridium_antenna_motorbike_quad.png']");
    private final By labelPriceIridium = By.xpath("//tr[.//a[normalize-space()='Iridium antenna magnetic (10cm cable)']]//td[@data-th='Price']//span[@class='price']");
    private final By inputQtyIridium = By.xpath("//tr[.//a[normalize-space()='Iridium antenna magnetic (10cm cable)']]//td[@data-th='Qty']//input");

    // --- 2. SMART SYNC ---
    public void waitForCartSync() {
        log.info("Waiting for Cart counter to sync...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String oldTotal = getText(labelCartCounter).trim();
        wait.until(d -> {
            try {
                String current = d.findElement(labelCartCounter).getText().trim();
                return !current.equals(oldTotal);
            } catch (Exception e) { return false; }
        });
    }

    // --- 3. ACTIONS ---
    public void clickProductsCategory() {
        log.info("Clicking Products category.");
        click(linkProductsCategory);
    }
    public void clickCarSSVCategory() {
        log.info("Clicking Car SSV category.");
        click(linkCarSSVCategory);
    }

    public void clickBikeQuadCategory()
    { log.info("Clicking Bike Quad category.");
        click(linkBikeQuadCategory);
    }

    public void addStrobeLampToCart() {
        log.info("Adding Strobe Lamp to cart.");
        jsClick(buttonAddToCartStrobeLampProduct);
        waitForCartSync();
    }

    public void inputInitialQtyMiniCart() {
        waitClickable(inputQtyMiniCart);
        clear(inputQtyMiniCart);
        int initial = new Random().nextInt(181) + 20; // 20-200
        savedQty = String.valueOf(initial);
        log.info("Inputting initial Qty to Mini Cart: {}", savedQty);
        type(inputQtyMiniCart, savedQty);
    }

    public void clickDecreaseQTYRandomly() {
        int current = Integer.parseInt(savedQty);
        if (current <= 20) return;
        int maxClick = current - 20;
        int clicks = (maxClick > 2) ? new Random().nextInt(maxClick - 1) + 2 : 2;
        log.info("Clicking Decrease QTY button {} times.", clicks);
        for (int i = 0; i < clicks; i++) { jsClick(buttonDecreaseQTY); }
        savedQty = String.valueOf(current - clicks);
    }

    public void clickIncreaseQTYRandomly() {
        int current = Integer.parseInt(savedQty);
        int clicks = new Random().nextInt(10) + 2;
        log.info("Clicking Increase QTY button {} times.", clicks);
        for (int i = 0; i < clicks; i++) { jsClick(buttonIncreaseQTY); }
        savedQty = String.valueOf(current + clicks);
    }

    public void clickUpdateMiniCart() {
        log.info("Updating Mini Cart.");
        Actionclick(buttonUpdateMiniCart);
        waitForCartSync();
    }

    public void clickContinueButton() {
        log.info("Clicking Continue button.");
        jsClick(buttonContinue); }

    public void openMiniCart() {
        log.info("Opening Mini Cart.");
        waitForLoadingInvisible();
        jsClick(buttonShowCart);
    }

    public String getActualQuantityInCart() {
        waitVisible(inputQtyStrobeLampInCart);
        return getAttribute(inputQtyStrobeLampInCart, "value");
    }

    public String getSavedQty() {
        return savedQty;
    }


    public void clickgpsAntennaDetail() {
        log.info("Opening GPS Antenna product detail.");
        jsClick(imgGpsAntennaDetail);
    }

    public void clickIridiumAntennaDetail() {
        log.info("Opening Iridium Antenna product detail.");
        jsClick(imgIridiumAntennaProductDetail);
    }

    public void clickAddToCartButtonInShoppingPage() {
        Actionclick(buttonAddToCartInShoppingPage);
        waitForCartSync();
    }

    public void inputqualityGPSProduct() {
        waitClickable(inputQtyGpsAntenna);
        clear(inputQtyGpsAntenna);
        int qty = new Random().nextInt(181) + 20;
        log.info("Inputting Qty for GPS Product: {}", qty);
        type(inputQtyGpsAntenna, String.valueOf(qty));
    }

    public void inputqualityInridiumProduct() {
        waitClickable(inputQtyIridium);
        clear(inputQtyIridium);
        int qty = new Random().nextInt(181) + 20;
        log.info("Inputting Qty for Iridium Product: {}", qty);
        type(inputQtyIridium, String.valueOf(qty));
    }

    public void clickUpdateMainCart() {
        log.info("Updating Main Cart.");
        click(buttonUpdateCartMain);
        waitForCartSync();
        waitForLoadingInvisible();
    }

    public double getActualSubTotal() {
        waitVisible(labelPriceOfSubTotal);
        log.info("Lấy tổng tiền (Subtotal) thực tế từ UI.");
        String subTotalText = getText(labelPriceOfSubTotal).replaceAll("[^0-9.]", "");
        return Double.parseDouble(subTotalText);
    }

    public double calculateExpectedSubTotalSingleProduct() {
        log.info("Tính toán Subtotal mong đợi cho 1 sản phẩm.");
        int qty = Integer.parseInt(getAttribute(inputQtyGpsAntenna, "value"));
        double price = Double.parseDouble(getText(labelPriceGpsAntennaProduct).replaceAll("[^0-9.]", ""));

        double expected = (qty * price);
        return Math.round(expected * 100.0) / 100.0; // Làm tròn 2 chữ số thập phân
    }

    public double calculateExpectedMultiProductSubTotal() {
        log.info("Đang tính toán Subtotal mong đợi cho nhiều sản phẩm (GPS + Iridium)...");

        int qtyI = Integer.parseInt(getAttribute(inputQtyIridium, "value"));
        double priceI = Double.parseDouble(getText(labelPriceIridium).replaceAll("[^0-9.]", ""));


        int qtyG = Integer.parseInt(getAttribute(inputQtyGpsAntenna, "value"));
        double priceG = Double.parseDouble(getText(labelPriceGpsAntennaProduct).replaceAll("[^0-9.]", ""));


        double expected = (qtyI * priceI) + (qtyG * priceG);
        return Math.round(expected * 100.0) / 100.0;
    }

    public void waitForLoadingInvisible() {
        By mask = By.cssSelector(".loading-mask");
        try { new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(mask));
        } catch (Exception ignored) {}
    }

    public void clickRemoveButton() {
        log.info("Removing product from cart.");
        waitClickable(buttonRemoveProduct);
        jsClick(buttonRemoveProduct);
    }

    public void clickOkToRemove() {
        log.info("Confirm removing product from cart.");
        waitClickable(buttonConfirmRemove);
        Actionclick(buttonConfirmRemove);
    }


}