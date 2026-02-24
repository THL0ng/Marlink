package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.ForgotPasswordPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotPassword extends BaseTest {
    @Test
    public void TC_01_ForgotPassword(){

        ForgotPasswordPage forgot = new ForgotPasswordPage(driver);

        forgot.clickSignInLink();
        forgot.clickForgotPasswordButton();
        forgot.inputEmail();
        forgot.clickResetPasswordButton();
        Assert.assertTrue(forgot.checkMessResetPasswordDisplay().contains(forgot.getTextInformReset()));
    }
}
