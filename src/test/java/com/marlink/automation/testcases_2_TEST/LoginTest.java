package com.marlink.automation.testcases_2_TEST;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.dataproviders.LoginDataProvider;
import com.marlink.automation.pages_2_TEST.LoginPage;
import com.marlink.automation.utils.RandomData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void setupPage() {
        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider = "loginData_01", dataProviderClass = LoginDataProvider.class)
    public void TC_01_LoginWithValidEmailAndPassword(String id, String email, String password, String expectedResult) {
        loginPage.goToSignInPage();

        loginPage.fillLoginForm(email, password);
        loginPage.clickSignInButton();

        String actual = loginPage.getPageTitle();
        String expected = "My Account";

        Assert.assertEquals(actual, expected,
                String.format("\nLỗi tiêu đề trang sau Login! \nMong đợi: [%s] \nThực tế: [%s]", expected, actual));
    }

    @Test()
    public void TC02_LoginWithInvalidEmailAndPassword() {
        loginPage.goToSignInPage();
        loginPage.clickSignInButton();

        String expectedRequired = "This is a required field.";

        Assert.assertEquals(loginPage.getEmailFieldError(), expectedRequired,
                String.format("\nLỗi validate Email trống! \nThấy: [%s]", loginPage.getEmailFieldError()));

        Assert.assertEquals(loginPage.getPasswordFieldError(), expectedRequired,
                String.format("\nLỗi validate Password trống! \nThấy: [%s]", loginPage.getPasswordFieldError()));

        loginPage.fillLoginForm("invalid_user_2026@gmail.com", "wrong_password_123");
        loginPage.clickSignInButton();

        String actualGlobal = loginPage.getGlobalErrorMessage();
        String expectedGlobal = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";

        Assert.assertEquals(actualGlobal, expectedGlobal,
                String.format("\nLỗi message Global khi sai thông tin! \nMong đợi: [%s] \nThực tế: [%s]", expectedGlobal, actualGlobal));
    }

    @Test()
    public void TC03_ForgotPassword() {
        loginPage.goToSignInPage();
        String emailForReset = RandomData.registeredemail;
        loginPage.resetPassword(emailForReset);

        String actual = loginPage.getForgotPasswordSuccessMessage();
        String expected = "If there is an account associated with " + emailForReset + ", you will receive an email with a link to reset your password.";
        Assert.assertEquals(actual, expected,
                String.format("\nLỗi message Forgot Password! \nMong đợi: [%s] \nThực tế: [%s]", expected, actual));
    }
}