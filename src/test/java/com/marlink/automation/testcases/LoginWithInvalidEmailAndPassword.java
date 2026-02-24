package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.LoginWithInvalidEmailAndPasswordPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginWithInvalidEmailAndPassword extends BaseTest {


    @Test
    public void TC_01_BlankTo_EmailAndPasswordForm(){
        LoginWithInvalidEmailAndPasswordPage loginInvalid = new LoginWithInvalidEmailAndPasswordPage(driver);

        loginInvalid.clickSignInCaseInvalid();
        loginInvalid.clickSgnInButtonLoginInvalid();

        Assert.assertTrue(loginInvalid.warningText().contains(loginInvalid.inputBlankEmailForm()));
        Assert.assertTrue(loginInvalid.warningText().contains(loginInvalid.inputBlankPasswordForm()));

        loginInvalid.inputEmailForm();
        loginInvalid.inputPasswordForm();
        loginInvalid.clickSgnInButtonLoginInvalid();
        Assert.assertTrue(loginInvalid.warningText().contains(loginInvalid.checkMessageErrorDisplay()));
    }




}
