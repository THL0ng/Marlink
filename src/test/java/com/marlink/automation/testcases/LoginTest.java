package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.dataproviders.LoginDataProvider;
import com.marlink.automation.pages.LoginPage;
import com.marlink.automation.utils.JsonHelper;
import com.marlink.automation.utils.DataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private static final Logger log = LogManager.getLogger(LoginTest.class);

    @BeforeMethod
    public void setupPage() {
        loginPage = new LoginPage(getDriver());
    }

    @Test(dataProvider = "loginData_01", dataProviderClass = LoginDataProvider.class)
    public void TC_01_LoginWithValidEmailAndPassword(String id, String email, String password, String expectedResult) {
        log.info("=== START TC_01 | ID: {} | Email: {} ===", id, email);
        loginPage.navigateToSignInPage();
        loginPage.fillLoginForm(email, password);
        loginPage.clickButtonSignIn();
        String actual = loginPage.getPageTitle();
        Assert.assertEquals(actual, JsonHelper.get("login_title_success"), "Lỗi: Tiêu đề trang sau khi đăng nhập không đúng!");
        loginPage.clickDropdownAccount();
        loginPage.clickButtonLogout();
        log.info("=== PASSED TC_01 ===");
    }

    @Test()
    public void TC_02_LoginWithInvalidEmailAndPassword() {
        log.info("=== START TC_02: Validation Messages ===");
        loginPage.navigateToSignInPage();
        loginPage.clickButtonSignIn();

        Assert.assertEquals(loginPage.getEmailFieldError(), JsonHelper.get("login_err_required"),"Lỗi: Message validate cho trường Email trống không hiển thị đúng!");
        Assert.assertEquals(loginPage.getPasswordFieldError(), JsonHelper.get("login_err_required"),"Lỗi: Message validate cho trường Password trống không hiển thị đúng!");
        log.info("Step 2: Testing with invalid account...");
        loginPage.fillLoginForm(DataHelper.invalidEmail, DataHelper.invalidPassword);
        loginPage.clickButtonSignIn();
        Assert.assertEquals(loginPage.getGlobalErrorMessage(),JsonHelper.get("login_err_global"),"Lỗi: Thông báo lỗi đăng nhập sai (Global message) không khớp!");
        log.info("=== PASSED TC_02 ===");
    }

    //@Test()
    public void TC_03_ForgotPassword() {
        log.info("=== START TC_03: Forgot Password ===");
        loginPage.navigateToSignInPage();
        String emailForReset = DataHelper.registeredemail;
        log.info("Resetting password for: {}", emailForReset);
        loginPage.resetPassword(emailForReset);
        String expectedMessage = JsonHelper.get("login_err_forgotPassword").replace("{{email}}", emailForReset);
        Assert.assertEquals(loginPage.getForgotPasswordSuccessMessage(), expectedMessage,"Lỗi: Thông báo xác nhận gửi email reset password không hiển thị đúng!");
        log.info("=== PASSED TC_03 ===");
    }
}