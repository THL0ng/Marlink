package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.dataproviders.LoginDataProvider;
import com.marlink.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void setupPage() {
        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider = "loginData_01", dataProviderClass = LoginDataProvider.class)
    public void TC_01_LoginWithValidEmailAndPassword(String id, String email, String password, String expectedResult) {
        loginPage.goToSignInPage();
        loginPage.fillLoginField(email, password);
        loginPage.clickSignInButton();
        System.out.println(id + " | " + email + " | " + expectedResult);
        Assert.assertEquals(loginPage.getActualPageTitle(), loginPage.getExpectedMessages("success"));
    }

    @Test
    public void TC_02_LoginWithInvalidEmailAndPassword(){
        loginPage.clickDropDown();
        loginPage.clickLogOut();
        loginPage.goToSignInPage();
        loginPage.clickSignInButton();

        Assert.assertEquals(loginPage.getEmailFieldErrorMessage(), loginPage.getExpectedMessages("required"));
        Assert.assertEquals(loginPage.getPasswordFieldErrorMessage(), loginPage.getExpectedMessages("required"));
        loginPage.inputInvalidEmail();
        loginPage.inputInvalidPassword();
        loginPage.clickSignInButton();
        Assert.assertEquals(loginPage.getGlobalErrorMessage(), loginPage.getExpectedMessages("global"));
    }

    @Test
    public void TC_03_ForgotPassword(){
        loginPage.clickForgotPasswordButton();
        loginPage.inputEmailReset();
        loginPage.clickResetPasswordButton();
        //Assert.assertEquals(loginPage.getForgotPasswordErrorMessage(), loginPage.getExpectedMessages("forgotPassword"));
    }

}

