package com.marlink.automation.testcases_2_TEST;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages_2_TEST.RegisterPage;
import com.marlink.automation.utils.RandomData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {
    private RegisterPage registerPage;

    @BeforeMethod
    public void setupPage() {
        registerPage = new RegisterPage(driver);
    }

    @Test()
    public void TC01_CreateNewAccountSuccessfully() {
        registerPage.clickHeaderAccountLink();
        registerPage.clickCreateAnAccountButton();

        registerPage.fillRegisterForm(
                RandomData.firstName,
                RandomData.lastName,
                RandomData.email,
                RandomData.password,
                RandomData.phoneNumber,
                RandomData.streetAddress,
                RandomData.city,
                RandomData.zipCode
        );

        registerPage.selectRandomCountry();
        registerPage.clickPrivacyPolicy();
        registerPage.clickSubmit();

        String actual = registerPage.getSuccessMessage();
        String expected = "You must confirm your account. Please check your email for the confirmation link or click here for a new link.";

        Assert.assertEquals(actual, expected,
                String.format("Lỗi message thành công! Mong đợi: [%s] nhưng thực tế: [%s]", expected, actual));
    }

    @Test()
    public void TC02_CreateNewAccountWithRegisteredEmail() {
        registerPage.clickHeaderAccountLink();
        registerPage.clickCreateAnAccountButton();

        registerPage.fillRegisterForm(
                RandomData.firstName,
                RandomData.lastName,
                RandomData.registeredemail,
                RandomData.password,
                RandomData.phoneNumber,
                RandomData.streetAddress,
                RandomData.city,
                RandomData.zipCode
        );

        registerPage.selectRandomCountry();
        registerPage.clickPrivacyPolicy();
        registerPage.clickSubmit();

        String actual = registerPage.getEmailErrorMessage();
        String expected = "There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account.";

        Assert.assertEquals(actual, expected,
                String.format("Lỗi message trùng Email! Mong đợi: [%s] nhưng thực tế: [%s]", expected, actual));
    }

    @Test()
    public void TC03_BlankWhenCreateNewAccountPage() {
        registerPage.clickHeaderAccountLink();
        registerPage.clickCreateAnAccountButton();
        registerPage.clickSubmit();

        String errRequired = "This is a required field.";
        String errCountry = "Please select an option.";

        Assert.assertEquals(registerPage.getFirstNameError(), errRequired, String.format("Lỗi validate FirstName! Thấy: [%s]", registerPage.getFirstNameError()));
        Assert.assertEquals(registerPage.getLastNameError(), errRequired, String.format("Lỗi validate LastName! Thấy: [%s]", registerPage.getLastNameError()));
        Assert.assertEquals(registerPage.getEmailFieldError(), errRequired, String.format("Lỗi validate Email! Thấy: [%s]", registerPage.getEmailFieldError()));
        Assert.assertEquals(registerPage.getPasswordError(), errRequired, String.format("Lỗi validate Password! Thấy: [%s]", registerPage.getPasswordError()));
        Assert.assertEquals(registerPage.getConfirmPasswordError(), errRequired, String.format("Lỗi validate Confirm Password! Thấy: [%s]", registerPage.getConfirmPasswordError()));
        Assert.assertEquals(registerPage.getPhoneNumberError(), errRequired, String.format("Lỗi validate Phone! Thấy: [%s]", registerPage.getPhoneNumberError()));
        Assert.assertEquals(registerPage.getStreetAddressError(), errRequired, String.format("Lỗi validate Street! Thấy: [%s]", registerPage.getStreetAddressError()));
        Assert.assertEquals(registerPage.getCityError(), errRequired, String.format("Lỗi validate City! Thấy: [%s]", registerPage.getCityError()));
        Assert.assertEquals(registerPage.getZipCodeError(), errRequired, String.format("Lỗi validate ZipCode! Thấy: [%s]", registerPage.getZipCodeError()));
        Assert.assertEquals(registerPage.getCountryError(), errCountry, String.format("Lỗi validate Country! Thấy: [%s]", registerPage.getCountryError()));
        Assert.assertEquals(registerPage.getPrivacyPolicyError(), errRequired, String.format("Lỗi validate Privacy Policy! Thấy: [%s]", registerPage.getPrivacyPolicyError()));
    }
}