package com.marlink.automation.pages_02;

import com.marlink.automation.base.BasePage;
import com.marlink.automation.utils.JsonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.marlink.automation.utils.RandomData.*;

public class ShoppingCartPage extends BasePage {
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }



    // ------------------------------------------SHOPPINGCART VALIDATION TEST--------------------------------------------------------------
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
        jsClick(removeProductButton);
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



    //------------------------------------------------------SHOPPINGCART QUANTITY TEST--------------------------------------------------------------

    private static String savedRandomQtyStrobeLampProduct;
    private final By addToCartStrobeLampProduct = By.xpath("//strong[normalize-space()='Item reference: APR2940']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");
    private final By qtyMiniCartForm = By.id("am-input");
    private final By DecreaseQTYButton = By.xpath("//span[@data-amcart='qty-minus']");
    private final By IncreaseQTYButton = By.xpath("//span[@data-amcart='qty-plus']");
    private final By ContinueButton = By.xpath("//button[@class='button am-btn-left']");
    private final By updateButtonMiniCart = By.xpath("//span[@class='amcart-refresh']");
    private final By qualityStrobeLampProductInCartCount = By.xpath("//a[normalize-space()='Strobe Lamp']/ancestor::div[contains(@class,'product-item-details')]//label[normalize-space()='Qty']/following-sibling::input");

    private final By addToCartButtonInShoppingPage = By.xpath("//button[@id='product-addtocart-button']");
    private final By gpsAntennaProductDetail = By.cssSelector("img[src*='gps_antenna_motorbike_quad_car_truck.jpg']");
    private final By pricegpsAntennaProduct = By.cssSelector("td[class='col price'] span[class='price']");
    private final By qtygpsAntenna = By.xpath("//tr[contains(@class,'item-info')]//td[@data-th='Qty']//input");

    private final By priceOfSubTotal = By.xpath("//th[normalize-space()='Subtotal']/ancestor::tr//span[@class='price']");

    private final By bikeQuad = By.cssSelector("a[href='https://eshop247.officience.com/en/products/motobike-quad.html']");
    private final By survivalKitSubCategory = By.cssSelector("a[href='https://eshop247.officience.com/en/products/truck-1/survival-equipment.html");


    private final By iridiumAntennaProductDetail = By.cssSelector("img[src*='iridium_antenna_motorbike_quad.png']");

    private final By addToCartIridiumProduct = By.xpath("//strong[normalize-space()='Item reference: APR4036']/ancestor::li[contains(@class,'product-item')]//button[@title='Add to Cart']");




    private final By priceIridium = By.xpath("//tr[.//a[normalize-space()='Iridium antenna magnetic (10cm cable)']]//td[@data-th='Price']//span[@class='price']");
    private final By qtyIridium = By.xpath("//tr[.//a[normalize-space()='Iridium antenna magnetic (10cm cable)']]//td[@data-th='Qty']//input");
    private final By viewCartButton = By.cssSelector(".button.am-btn-right");

    public void selectProductAndClickAddTocartButton() {
        waitClickable(addToCartStrobeLampProduct);
        jsClick(addToCartStrobeLampProduct);
    }

    public String inputQtyStrobeLampProduct() {
        waitClickable(qtyMiniCartForm);
        clear(qtyMiniCartForm);
        this.savedRandomQtyStrobeLampProduct = String.valueOf(getRandomNumberProduct());
        type(qtyMiniCartForm, savedRandomQtyStrobeLampProduct);
        return this.savedRandomQtyStrobeLampProduct;
    }

    public String getSavedQualityStrobeLampProduct() {
        return this.savedRandomQtyStrobeLampProduct;
    }

    public void clickUpdateButtonMiniCart() {
        waitClickable(updateButtonMiniCart);
        Actionclick(updateButtonMiniCart);

    }

    public void clickDecreaseQTYByButton() {
        int currentQty = Integer.parseInt(this.savedRandomQtyStrobeLampProduct);
        int targetQty = new Random().nextInt(currentQty - 1) + 1;
        int clicksNeeded = currentQty - targetQty;

        System.out.println("Bắt đầu giảm từ: " + currentQty + " xuống còn: " + targetQty);
        System.out.println("Số lần cần Click: " + clicksNeeded);

        for (int i = 0; i < clicksNeeded; i++) {
            waitVisible(DecreaseQTYButton);
            jsClick(DecreaseQTYButton);

        }
        this.savedRandomQtyStrobeLampProduct = String.valueOf(targetQty);

        System.out.println("Cập nhật thành công! Số lượng hiện tại trong bộ nhớ: " + this.savedRandomQtyStrobeLampProduct);

    }

    public void clickIncreaseQTYByButton() {
        int currentQty = Integer.parseInt(this.savedRandomQtyStrobeLampProduct);
        int maxLimit = 100;
        int targetQty = currentQty + new Random().nextInt(maxLimit - currentQty) + 1;
        int clicksNeeded = targetQty - currentQty;

        System.out.println("Bắt đầu tăng từ: " + currentQty + " lên thành: " + targetQty);
        System.out.println("Số lần cần Click Tăng: " + clicksNeeded);
        for (int i = 0; i < clicksNeeded; i++) {
            waitVisible(IncreaseQTYButton); // Đổi sang locator nút Tăng
            jsClick(IncreaseQTYButton);

        }
        this.savedRandomQtyStrobeLampProduct = String.valueOf(targetQty);

        System.out.println("Cập nhật thành công! Số lượng mới lưu: " + this.savedRandomQtyStrobeLampProduct);
    }

    public void clickContinueButton() {
        waitClickable(ContinueButton);
        jsClick(ContinueButton);
    }

    public String showActualQualityStrobeLampProduct() {
        waitVisible(qualityStrobeLampProductInCartCount);
        waitForTextToChange(qualityStrobeLampProductInCartCount);
        return getText(qualityStrobeLampProductInCartCount);
    }

    public void clickcartCount(){
        waitForLoadingInvisible();
        jsClick(cartCountButton);
        delay(3);
    }

    public void compareQuality() {
        String expectedQuality = getSavedQualityStrobeLampProduct();
        waitForLoadingInvisible();
        waitVisible(qualityStrobeLampProductInCartCount);
        String actualQuality = showActualQualityStrobeLampProduct();

        if (actualQuality.equals(expectedQuality)) {
            System.out.println("Số lượng chất lượng trong giỏ hàng và trong form khớp nhau.");
        } else {
            System.out.println("Số lượng chất lượng không khớp!");
            System.out.println("Giá trị mong đợi: " + expectedQuality);
            System.out.println("Giá trị thực tế: " + actualQuality);
        }
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

    public void clickgpsAntennaDetail() {
        waitClickable(gpsAntennaProductDetail);
        jsClick(gpsAntennaProductDetail);
    }


    public void clickIridiumAntennaDetail() {
        waitClickable(iridiumAntennaProductDetail);
        jsClick(iridiumAntennaProductDetail);
    }




    public void clickAddToCartButtonInShoppingPage(){
        waitClickable(addToCartButtonInShoppingPage);
        Actionclick(addToCartButtonInShoppingPage);
    }

    public void checkSubTotalAfterUpdate(){
        int qty = Integer.parseInt(driver.findElement(qtyForm).getAttribute("value"));

        waitVisible(pricegpsAntennaProduct);
        double price = Double.parseDouble(driver.findElement(pricegpsAntennaProduct).getText().replaceAll("[^0-9.]", ""));


        waitVisible(priceOfSubTotal);
        double subtotal = Double.parseDouble(
                driver.findElement(priceOfSubTotal).getText().replaceAll("[^0-9.]", ""));

        double expected = qty * price;
        expected = Math.round(expected * 100.0) / 100.0;


        System.out.println("Subtotal from UI = " + subtotal);
        System.out.println("Price * Quantity = " + price + " * " + qty + " = " + expected);
        Assert.assertEquals(subtotal, expected, "Subtotal calculation incorrect");
    }

    public void clickBikeQuadCategory() {
        waitClickable(bikeQuad);
        click(bikeQuad);
    }

    public void clickSurvivalKitSubCategory() {
        waitClickable(survivalKitSubCategory);
        jsClick(survivalKitSubCategory);
    }

    public void clickAddToCartIridiumProduct() {
        waitClickable(addToCartIridiumProduct);
        click(addToCartIridiumProduct);
    }




    public void checkSubTotalMultiProductAfterUpdate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int qtySurvival = Integer.parseInt(driver.findElement(qtyIridium).getAttribute("value"));
        int qtyGpsAntenna = Integer.parseInt(driver.findElement(qtygpsAntenna).getAttribute("value"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(priceIridium));
        double priceSurvival = Double.parseDouble(
                driver.findElement(priceIridium).getText().replaceAll("[^0-9.]", "")
        );

        wait.until(ExpectedConditions.visibilityOfElementLocated(pricegpsAntennaProduct));
        double priceCable = Double.parseDouble(
                driver.findElement(pricegpsAntennaProduct).getText().replaceAll("[^0-9.]", "")
        );

        wait.until(ExpectedConditions.visibilityOfElementLocated(priceOfSubTotal));

        String rawSubtotal = driver.findElement(priceOfSubTotal).getText();
        System.out.println("Raw subtotal text = [" + rawSubtotal + "]");

        String cleanSubtotal = rawSubtotal.replaceAll("[^0-9.]", "");
        System.out.println("Clean subtotal text = [" + cleanSubtotal + "]");

        if (cleanSubtotal.isEmpty()) {
            throw new RuntimeException("Subtotal text is empty. Please re-check locator priceOfSubTotal.");
        }

        double subtotal = Double.parseDouble(cleanSubtotal);

        double expected = (qtySurvival * priceSurvival) + (qtyGpsAntenna * priceCable);
        expected = Math.round(expected * 100.0) / 100.0;

        System.out.println("Subtotal from UI = " + subtotal);
        System.out.println("Expected subtotal = (" + priceSurvival + " * " + qtySurvival +
                ") + (" + priceCable + " * " + qtyGpsAntenna + ") = " + expected);
        Assert.assertEquals(subtotal, expected, "Subtotal calculation incorrect");
    }

    public void inputqualityProduct() {
        waitClickable(qtyMiniCartForm);
        type(qtyMiniCartForm, String.valueOf(RandomQty));
    }

    public void clickViewCartButton(){
        waitClickable(viewCartButton);
        jsClick(viewCartButton);
    }
}
