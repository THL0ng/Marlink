package com.marlink.automation.base;

import com.marlink.automation.utils.BasicAuthHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    // SỬA TẠI ĐÂY: Dùng ThreadLocal để mỗi luồng giữ một Driver riêng
    protected static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    protected static final Logger Log = LogManager.getLogger(BaseTest.class);
    protected String url = "https://eshop247.officience.com/en/";

    // SỬA TẠI ĐÂY: Lấy driver từ ThreadLocal
    public static WebDriver getDriver() {
        return driverThread.get();
    }

    @BeforeClass
    public void openBrowsers() throws Exception {
        Log.info(">>> Opening browser on Thread ID: " + Thread.currentThread().getId());

        ChromeOptions options = new ChromeOptions();

        // Muốn Browser Mở thì // headless 2 dòng dưới
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // Khởi tạo Driver mới và nạp vào ThreadLocal
        WebDriver localDriver = new ChromeDriver(options);
        driverThread.set(localDriver);

        getDriver().manage().window().maximize();

        // Xử lý Basic Auth qua URL
        String credentials = BasicAuthHelper.getBasicAuthCredential("Login_02");
        String finalUrl = url;
        if (credentials != null) {
            finalUrl = url.replace("https://", "https://" + credentials + "@");
            Log.info("Using Basic Auth URL on Thread: " + Thread.currentThread().getId());
        }

        getDriver().get(finalUrl);

        try {
            // Lưu ý: Truyền getDriver() vào BasePage
            BasePage basePage = new BasePage(getDriver());
            basePage.waitClickable(By.xpath("//button[@class='action allow primary']")).click();
            Log.info("Clicked Allow Cookies.");
        } catch (Exception e) {
            Log.info("Cookie banner not appearing on thread: " + Thread.currentThread().getId());
        }
    }

    @AfterClass
    public void closeBrowsers() {
        if (getDriver() != null) {
            getDriver().quit();
            // Xóa driver khỏi ThreadLocal để giải phóng bộ nhớ
            driverThread.remove();
        }
    }
}