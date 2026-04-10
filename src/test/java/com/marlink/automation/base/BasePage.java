
package com.marlink.automation.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class BasePage {
    protected WebDriver driver;
    private WebDriverWait wait;
    WebDriverWait explicitWait;
    private static final Logger log = LogManager.getLogger(BasePage.class);


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForTextToChange(By locator) {
        // 1. Lấy giá trị đang hiển thị ngay lúc này (giá trị cũ)
        String oldText = getText(locator).trim();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // 2. Đợi cho đến khi text của phần tử đó KHÁC với giá trị cũ
            wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, oldText)));
        } catch (Exception e) {
            System.out.println("Giá trị không thay đổi sau 10s, vẫn là: " + oldText);
        }
    }

    public void delay(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            // Giữ trạng thái gián đoạn của thread
            Thread.currentThread().interrupt();
        }
    }

    protected void waitForElementToUpdate(By locator) {
        WebElement elementBeforeUpdate = driver.findElement(locator);
        wait.until(ExpectedConditions.stalenessOf(elementBeforeUpdate));
        waitVisible(locator);
    }

    public void waitForDoublePageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Đợi lần 1
        wait.until(d -> js.executeScript("return document.readyState").equals("complete"));
        delay(1);
        // Đợi lần 2
        wait.until(d -> js.executeScript("return document.readyState").equals("complete"));
    }

    protected void click(By locator) {
        int retries = 3; // thử click tối đa 3 lần

        for (int attempt = 1; attempt <= retries; attempt++) {
            try {
                // 1. Chờ element clickable
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

                // 2. Scroll element vào giữa màn hình (fix lỗi React/Angular bị che)
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

                // 3. Chờ overlay loader biến mất (nếu có)
                wait.until(d -> {
                    try {
                        Object result = ((JavascriptExecutor) d).executeScript(
                                "let o = document.querySelector('.loading, .loader, .spinner, .backdrop');" +
                                        "return (o == null || o.style.display == 'none' || o.style.visibility == 'hidden');"
                        );
                        return Boolean.parseBoolean(result.toString());
                    } catch (Exception e) {
                        return true; // không có overlay => OK
                    }
                });

                // 4. Thực hiện click
                element.click();
                return; // thành công => thoát hàm

            } catch (Exception e) {

                // Nếu lần cuối vẫn lỗi => throw để test fail đúng
                if (attempt == retries) {
                    throw e;
                }

                // Retry sau 500ms
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            }
        }
    }

    public void Actionclick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        new Actions(driver).click(element).perform();
    }

    protected void type(By locator, String text) {
        int retries = 3;

        for (int attempt = 1; attempt <= retries; attempt++) {
            try {
                // 1. Chờ element hiển thị (visible)
                WebElement element = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(locator)
                );

                // 2. Đảm bảo element ở trong vùng nhìn thấy (tránh bị header/footer che)
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);

                // 3. Chờ element có thể tương tác (enabled)
                if (!element.isEnabled()) {
                    wait.until(ExpectedConditions.elementToBeClickable(locator));
                }

                // 4. Clear giá trị cũ (nếu có) rồi nhập text mới
                element.clear();
                element.sendKeys(text);

                return; // thành công → thoát hàm

            } catch (Exception e) {
                // Nếu thử đủ số lần mà vẫn fail → ném lỗi ra cho test fail đúng
                if (attempt == retries) {
                    throw e;
                }

                // Đợi nhẹ 300ms rồi thử lại (tránh animation / transition)
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    public void uploadFileWithRobotBackup(String fileName) {
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + File.separator + "uploadFiles" + File.separator + fileName;

        try {
            // Nếu popup đã mở, xử lý bằng Robot
            StringSelection stringSelection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

            Robot robot = new Robot();
            robot.delay(1000);

            // Ctrl + V
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.delay(500);

            // Enter
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            robot.delay(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void jsClick(By locator) {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator)
            );

            // Scroll tránh bị che
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'});", element);

            // JS click
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);

        } catch (Exception e) {
            throw new RuntimeException("Failed to perform jsClick on: " + locator, e);
        }
    }

    protected String getText(By locator) {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator)
            );

            String text = element.getText().trim();

            // Nếu getText() rỗng, thử lấy trong attribute value (input/textarea)
            if (text.isEmpty()) {
                text = element.getAttribute("value");
                return text != null ? text.trim() : "";
            }

            return text;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get text from: " + locator, e);
        }
    }

    protected void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // 1) Document readyState === "complete"
            wait.until(webDriver ->
                    ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState")
                            .equals("complete"));

            // 2) jQuery active requests = 0 (nếu có jQuery)
            wait.until(webDriver -> {
                try {
                    Object result = ((JavascriptExecutor) webDriver)
                            .executeScript("return window.jQuery != null && jQuery.active == 0");
                    return result != null && Boolean.parseBoolean(result.toString());
                } catch (Exception e) {
                    return true; // jQuery không tồn tại → cho pass
                }
            });

            // 3) Angular stable (nếu có Angular)
            wait.until(webDriver -> {
                try {
                    Object result = ((JavascriptExecutor) webDriver).executeScript(
                            "return window.getAllAngularTestabilities && " +
                                    "window.getAllAngularTestabilities()[0].isStable()");
                    return result != null && Boolean.parseBoolean(result.toString());
                } catch (Exception e) {
                    return true; // Không có Angular → pass
                }
            });

        } catch (Exception e) {
            throw new RuntimeException("Page did not fully load!", e);
        }
    }

    protected void selectDropdown(By dropdownToggle, By optionLocator) {

        int retries = 3;

        for (int attempt = 1; attempt <= retries; attempt++) {
            try {
                // 1. Chờ dropdown toggle clickable
                WebElement toggle = wait.until(
                        ExpectedConditions.elementToBeClickable(dropdownToggle)
                );

                // 2. Scroll toggle vào view để tránh bị che
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block: 'center'});", toggle);

                toggle.click();

                // 3. Chờ option visible (dropdown mở hoàn toàn)
                WebElement option = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(optionLocator)
                );

                // 4. Scroll option vào giữa để tránh lỗi intercept
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block: 'center'});", option);

                // 5. Chờ option clickable
                wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();

                return; // success → out

            } catch (Exception e) {

                if (attempt == retries) {
                    throw new RuntimeException("Dropdown selection failed!", e);
                }

                // retry nhẹ sau 300ms (tránh animation)
                try { Thread.sleep(300); } catch (InterruptedException ignored) {}
            }
        }
    }

    public void setRandomDate(By locator, int startYear, int endYear, String format) {
        // 1. Tìm element
        WebElement dateInput = driver.findElement(locator);

        // 2. Random ngày trong khoảng startYear → endYear
        LocalDate start = LocalDate.of(startYear, 1, 1);
        LocalDate end = LocalDate.of(endYear, 12, 31);

        long startDay = start.toEpochDay();
        long endDay = end.toEpochDay();

        long randomDay = ThreadLocalRandom.current().nextLong(startDay, endDay + 1);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        // 3. Format date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String formattedDate = randomDate.format(formatter);

        // 4. Set value
        dateInput.clear();
        dateInput.sendKeys(formattedDate);

        // 5. Trigger blur (Angular/React formControl)
        dateInput.sendKeys(Keys.TAB);

        System.out.println("✅ Random date set: " + formattedDate);
    }

    public void enterPhoneNumber(By phoneLocator, String number) {
        WebElement phoneInput = wait.until(
                ExpectedConditions.elementToBeClickable(phoneLocator)
        );

        phoneInput.clear();
        phoneInput.sendKeys(number);

        // Giúp Angular/React update FormControl value
        phoneInput.sendKeys(Keys.TAB);

        System.out.println("📱 Phone entered: " + number);
    }

    public void clickCheckbox(By checkboxLocator) {
        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(checkboxLocator)
        );

        // Scroll vào giữa để tránh bị che
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                checkbox
        );

        // Click bằng JS để tránh bị Intercepted khi Angular overlay
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);

        System.out.println("✔ Checkbox clicked: " + checkboxLocator.toString());
    }

    public void click_RandomCompanyName(By elementWithText){
        String text = getText(elementWithText);
        By dynamicLocator = By.xpath("//*[normalize-space()='" + text + "']");
        click(dynamicLocator);
    }

    public void clear(By by) {
        waitVisible(by).clear();
    }

    public WebElement scrollIntoView(By locator) {
        WebElement el = waitVisible(locator);
        new Actions(driver).scrollToElement(el).perform();
        return el;
    }

    public String getAttribute(By locator, String attributeName) {
        return waitVisible(locator).getAttribute(attributeName);
    }



}
