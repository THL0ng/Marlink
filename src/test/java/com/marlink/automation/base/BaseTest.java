
package com.marlink.automation.base;

import io.sentry.Attachment;
import io.sentry.Sentry;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import static com.marlink.automation.utils.Helper.handleBasicAuth;

public class BaseTest {
    protected static WebDriver driver;
    protected String url = "https://eshop247.officience.com/en/";

    @BeforeSuite
    public void initSentry() {
        Sentry.init(options -> {
            options.setDsn("https://c969d8e5718380a991a09a3284abb1ac@o4510509518815232.ingest.us.sentry.io/4510509532119040");
            options.setTracesSampleRate(1.0);
            options.setDebug(true);
        });
        System.out.println(">>> Sentry initialized!");
    }

    @BeforeClass
    public void openBrowsers() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-geolocation");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);
        handleBasicAuth("Login_02");

        try {
            BasePage basePage = new BasePage(driver);
            basePage.waitClickable(By.xpath("//button[@class='action allow primary']")).click();
        } catch (Exception e) {
        }

    }

    public static WebDriver getDriver() {
        return driver;
    }

    @AfterClass
    public void closeBrowsers() {
        if (driver != null) {
            //driver.quit();
        }
    }

    @AfterSuite
    public void shutdownSentry() {
        Sentry.close();
    }

    // ===== Listener gửi lỗi + screenshot lên Sentry =====
    public static class TestListener implements ITestListener {

        @Override
        public void onTestFailure(ITestResult result) {
            try {
                // 1. Chụp screenshot
                File src = ((TakesScreenshot) BaseTest.getDriver())
                        .getScreenshotAs(OutputType.FILE);
                byte[] screenshotBytes = Files.readAllBytes(src.toPath());

                // 2. Gửi lỗi + attach screenshot lên Sentry
                Sentry.withScope(scope -> {
                    scope.setTag("testName", result.getName());
                    scope.setExtra("className", result.getTestClass().getName());

                    scope.addAttachment(new Attachment(
                            screenshotBytes,
                            "failure.png",
                            "image/png"
                    ));

                    Throwable error = result.getThrowable();
                    if (error != null) {
                        Sentry.captureException(error);
                    } else {
                        Sentry.captureMessage("Test failed but no throwable");
                    }
                });

                System.out.println(">>> Error + screenshot sent to Sentry");

            } catch (Exception e) {
                // Nếu lỗi trong lúc chụp ảnh thì vẫn gửi lỗi đó lên Sentry
                Sentry.captureException(e);
            }
        }

        // các override khác (onTestSuccess, onTestStart, ...) nếu cần
    }
}
