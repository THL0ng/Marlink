package com.marlink.automation.testcases;

import com.marlink.automation.bases.basetest;
import com.marlink.automation.dataproviders.LoginDataProvider;
import com.marlink.automation.pages.LoginPage;
import org.testng.annotations.Test;


public class SignIn_ValidLogin extends basetest {
    @Test(
            dataProvider = "loginData",
            dataProviderClass = LoginDataProvider.class
    )



    public void TC_01_LoginWith_Valid_EmailAndPassword(String id, String email, String password, String expectedResult) {
        LoginPage home = new LoginPage(driver);
        home.clickSignIn();
        home.login(email, password);
        home.clickSignInButton();
        System.out.println(id + " | " + email + " | " + expectedResult);
    }






}