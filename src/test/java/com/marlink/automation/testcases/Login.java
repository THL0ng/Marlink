package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.dataproviders.LoginDataProvider;
import com.marlink.automation.pages.LoginPage;
import org.testng.annotations.Test;


public class Login extends BaseTest {
    @Test(
            dataProvider = "loginData",
            dataProviderClass = LoginDataProvider.class
    )

    public void TC_01_LoginWith_Valid_EmailAndPassword(String id, String email, String password, String expectedResult) {
        LoginPage login = new LoginPage(driver);
        login.clickSignIn();
        login.login(email, password);
        login.clickSignInButton();
        System.out.println(id + " | " + email + " | " + expectedResult);
    }
}