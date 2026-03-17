package com.marlink.automation.testcases_02;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages_02.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {
    RegisterPage register;

    @BeforeMethod
    public void setupPage() {
        register = new RegisterPage(driver);
    }

    @Test
    public void TC_01_CreateNewAccountSuccessfully() {
        register.clickheaderAccountLinkButton();
        register.clickCreateAnAccountButton();
        register.inputFirstName();
        register.inputLastName();
        register.inputPhoneNumber();
        register.inputStreetAddress();
        register.inputCityField();
        register.inputZipCode();
        register.selectRandomCountry();
        register.inputEmail();
        register.inputPassword();
        register.inputConfirmPassword();
        register.clickPrivacyPolicyCheckbox();
        register.clickSubmitCreateAnAccountButton();
        Assert.assertEquals(register.getActualPageTitle(), register.getExpectedMessages("registerSuccess"));
    }

    @Test
    public void TC_02_CreateNewAccountWithRegisteredEmailwelcome() {
        register.clickCreateAnAccountButton();
        register.inputFirstName();
        register.inputLastName();
        register.inputPhoneNumber();
        register.inputStreetAddress();
        register.inputCityField();
        register.inputZipCode();
        register.selectRandomCountry();
        register.inputRegisteredEmail();
        register.inputPassword();
        register.inputConfirmPassword();
        register.clickPrivacyPolicyCheckbox();
        register.clickSubmitCreateAnAccountButton();
        Assert.assertEquals(register.getActualErrorEmail(), register.getExpectedMessages("errEmail"));
    }

    @Test
    public void TC_03_BlankWhenCreateNewAccountPage() {
        register.clickheaderAccountLinkButton();
        register.clickCreateAnAccountButton();
        register.clickSubmitCreateAnAccountButton();

        Assert.assertEquals(register.getActualFirstNameMess(), register.getExpectedMessages("ErrRequired"));
        Assert.assertEquals(register.getActualLastNameMess(), register.getExpectedMessages("ErrRequired"));
        Assert.assertEquals(register.getActualEmailMess(), register.getExpectedMessages("ErrRequired"));
        Assert.assertEquals(register.getActualPasswordMess(), register.getExpectedMessages("ErrRequired"));
        Assert.assertEquals(register.getActualPasswordConfirmMess(), register.getExpectedMessages("ErrRequired"));
        Assert.assertEquals(register.getActualNumberPhoneMess(), register.getExpectedMessages("ErrRequired"));
        Assert.assertEquals(register.getActualStreetAddressMess(), register.getExpectedMessages("ErrRequired"));
        Assert.assertEquals(register.getActualCityMess(), register.getExpectedMessages("ErrRequired"));
        Assert.assertEquals(register.getActualZipCodeMess(), register.getExpectedMessages("ErrRequired"));
        Assert.assertEquals(register.getActualCountryMess(), register.getExpectedMessages("errCountry"));
        Assert.assertEquals(register.getActualPrivacyPolicyMess(), register.getExpectedMessages("ErrRequired"));
    }

}
