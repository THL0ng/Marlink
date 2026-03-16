package com.marlink.automation.testcases_02;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.dataproviders.LoginDataProvider;
import com.marlink.automation.pages_02.LoginPage;
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
        Assert.assertEquals(loginPage.getActualPageTitle(), "My Account");
    }

    @Test
    public void TC_02_LoginWithInvalidEmailAndPassword(){
        loginPage.clickDropDown();
        loginPage.clickLogOut();
        loginPage.goToSignInPage();
        loginPage.clickSignInButton();

        Assert.assertEquals(loginPage.getEmailFieldErrorMessage(), "This is a required field.");
        Assert.assertEquals(loginPage.getPasswordFieldErrorMessage(), "This is a required field.");

        loginPage.inputInvalidEmail();
        loginPage.inputInvalidPassword();
        loginPage.clickSignInButton();
        Assert.assertEquals(loginPage.getGlobalErrorMessage(), "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.");





    }







}

