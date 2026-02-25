package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.CreateNewAccountPage;
import com.marlink.automation.pages.CreateNewAccountWithAlreadyEmailPage;
import com.marlink.automation.pages.LoginWithValidEmailAndPasswordPage;
import org.testng.Assert;
import org.testng.annotations.Test;



public class CreateNewAccountWithAlreadyEmail extends BaseTest {

    @Test
    public void TC_01_CreateNewAccountWithAlreadyEmail(){
        CreateNewAccountPage create = new CreateNewAccountPage(driver);
        LoginWithValidEmailAndPasswordPage login = new LoginWithValidEmailAndPasswordPage(driver);
        CreateNewAccountWithAlreadyEmailPage alreadyAcc = new CreateNewAccountWithAlreadyEmailPage(driver);

        login.clickSignIn();
        create.clickCreateAnAccountButton();
        create.inputFirstName();
        create.inputLastName();
        create.inputPhoneNumber();
        create.inputStreetAddress();
        create.inputCityField();
        create.inputZipCode();
        create.selectRandomCountry();

        alreadyAcc.InputEmailAlreadyToEmailForm();

        create.inputPassword();
        create.inputConfirmPassword();
        create.clickPrivacyPolicyCheckbox();
        create.clickSubmitCreateAnAccountButton();
        Assert.assertTrue(alreadyAcc.checkMessageErrorDisplay().contains(alreadyAcc.messErrorText().get(0)));

        System.out.println("Actual message: [" + alreadyAcc.messErrorText() + "]");
        System.out.println("Expected message: [" + alreadyAcc.checkMessageErrorDisplay() + "]");





    }


}
