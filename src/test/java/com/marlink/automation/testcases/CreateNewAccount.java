package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.CreateNewAccountPage;
import org.testng.annotations.Test;

public class CreateNewAccount extends BaseTest {

    @Test
    public void TC_01_CreateNewAccount_Successfully(){
        CreateNewAccountPage create = new CreateNewAccountPage(driver);

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
