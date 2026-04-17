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
    protected static WebDriver driver;
    protected static final Logger Log = LogManager.getLogger(BaseTest.class);
    protected String url = "https://eshop247.officience.com/en/";

    public static WebDriver getDriver() {
        return driver;
    }
    @BeforeClass
    public void openBrowsers() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // XỬ LÝ BASIC AUTH TRƯỚC KHI MỞ TRANG
        String credentials = BasicAuthHelper.getBasicAuthCredential("Login_02");
        String finalUrl = url;
        if (credentials != null) {
            finalUrl = url.replace("https://", "https://" + credentials + "@");
            Log.info("Using Basic Auth URL.");
        }
        driver.get(finalUrl);
        try {
            BasePage basePage = new BasePage(driver);
            basePage.waitClickable(By.xpath("//button[@class='action allow primary']")).click();
            Log.info("Clicked Allow Cookies.");
        } catch (Exception e) {
            Log.info("Cookie banner not appearing.");
        }
    }
    @AfterClass
    public void closeBrowsers() {
        if (driver != null) {
            driver.quit();
        }
    }
}