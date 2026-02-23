
package com.marlink.automation.bases;

import io.sentry.Attachment;
import io.sentry.Sentry;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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

public class basetest {
    protected static WebDriver driver;
    protected String url = "https://eshop-pp2.tdcom.fr/";

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
    public void openBrowsers() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-geolocation");
        options.addArguments("--disable-extensions");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @AfterClass
    public void closeBrowsers() {
        if (driver != null) {
            driver.quit();
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
                File src = ((TakesScreenshot) basetest.getDriver())
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
