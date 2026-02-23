package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.CreateNewAccountPage;
import com.marlink.automation.pages.LoginPage;
import org.testng.annotations.Test;

public class CreateNewAccountSuccessfully extends BaseTest {

    @Test
    public void TC_01_CreateNewAccount(){
        CreateNewAccountPage create = new CreateNewAccountPage(driver);
        LoginPage login = new LoginPage(driver);
        login.clickSignIn();


        create.clickCreateAnAccountButton();
        create.inputFirstName();
        create.inputLastName();
        create.inputPhoneNumber();
        create.inputStreetAddress();
        create.inputCityField();
        create.inputZipCode();
        create.selectRandomCountry();
        create.inputEmail();
        create.inputPassword();
        create.inputConfirmPassword();
        create.clickPrivacyPolicyCheckbox();
        create.clickSubmitCreateAnAccountButton();
    }






}
