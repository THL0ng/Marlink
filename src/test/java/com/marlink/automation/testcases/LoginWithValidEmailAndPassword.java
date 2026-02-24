package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.dataproviders.LoginDataProvider;
import com.marlink.automation.pages.LoginWithValidEmailAndPasswordPage;
import org.testng.annotations.Test;


public class LoginWithValidEmailAndPassword extends BaseTest {
    @Test(dataProvider = "loginData_01", dataProviderClass = LoginDataProvider.class)

    public void TC_01_Login(String id, String email, String password, String expectedResult) {
        LoginWithValidEmailAndPasswordPage login = new LoginWithValidEmailAndPasswordPage(driver);
        login.clickSignIn();
        login.login(email, password);
        login.clickSignInButton();
        System.out.println(id + " | " + email + " | " + expectedResult);
    }


}