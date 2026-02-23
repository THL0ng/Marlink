package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.BlankWhenCreateNewAccountPage;
import com.marlink.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BlankwhenCreateNewAccount extends BaseTest {

    @Test
    public void TC_01_CreateNewAccount(){
        LoginPage login = new LoginPage(driver);
        login.clickSignIn();


        BlankWhenCreateNewAccountPage blank = new BlankWhenCreateNewAccountPage(driver);
        blank.clickCreateAnAccountButton();
        blank.clickSubmitCreateAnAccountButton();

        Assert.assertTrue(blank.getRequiredWarningTexts().contains(blank.submitBlankFirstNameForm()));
        Assert.assertTrue(blank.getRequiredWarningTexts().contains(blank.submitBlankLastNameForm()));
        Assert.assertTrue(blank.getRequiredWarningTexts().contains(blank.submitBlankEmailForm()));
        Assert.assertTrue(blank.getRequiredWarningTexts().contains(blank.submitBlankPasswordForm()));
        Assert.assertTrue(blank.getRequiredWarningTexts().contains(blank.submitBlankPasswordConfirmForm()));
        Assert.assertTrue(blank.getRequiredWarningTexts().contains(blank.submitBlankPhoneNumberForm()));
        Assert.assertTrue(blank.getRequiredWarningTexts().contains(blank.submitBlankStreetAddressForm()));
        Assert.assertTrue(blank.getRequiredWarningTexts().contains(blank.submitBlankCityForm()));
        Assert.assertTrue(blank.getRequiredWarningTexts().contains(blank.submitBlankZipCodeForm()));
        Assert.assertTrue(blank.getRequiredWarningTexts().contains(blank.submitBlankCountryForm()));
        Assert.assertTrue(blank.getRequiredWarningTexts().contains(blank.submitBlankPrivacyPolicyForm()));
    }




}
