package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.RegisterPage;
import com.marlink.automation.utils.JsonHelper;
import com.marlink.automation.utils.DataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {
    private RegisterPage registerPage;
    private static final Logger log = LogManager.getLogger(RegisterPage.class);

    @BeforeMethod
    public void setupPage() {
        registerPage = new RegisterPage(getDriver());
    }

    @Test()
    public void TC01_CreateNewAccountSuccessfully() {
        log.info("=== START TC_01: Create New Account Successfully ===");
        registerPage.clickHeaderAccountLink();
        registerPage.navigateToRegisterPage();

        registerPage.fillRegisterForm(
                DataHelper.firstName,
                DataHelper.lastName,
                DataHelper.email,
                DataHelper.password,
                DataHelper.phoneNumber,
                DataHelper.streetAddress,
                DataHelper.city,
                DataHelper.zipCode
        );

        registerPage.selectRandomCountry();
        registerPage.clickCheckboxPrivacyPolicy();
        registerPage.clickButtonSubmit();

        String actual = registerPage.getLabelSuccessMessage();
        Assert.assertEquals(actual, JsonHelper.get("register_inform_success"), "Lỗi: Đăng ký tài khoản Không thành công!");
        log.info("=== PASSED TC_01 ===");
    }

    @Test()
    public void TC02_CreateNewAccountWithRegisteredEmail() {
        log.info("=== START TC_02: Create Account with Registered Email ===");
        registerPage.clickHeaderAccountLink();
        registerPage.navigateToRegisterPage();

        registerPage.fillRegisterForm(
                DataHelper.firstName,
                DataHelper.lastName,
                DataHelper.registeredemail,
                DataHelper.password,
                DataHelper.phoneNumber,
                DataHelper.streetAddress,
                DataHelper.city,
                DataHelper.zipCode
        );

        registerPage.selectRandomCountry();
        registerPage.clickCheckboxPrivacyPolicy();
        registerPage.clickButtonSubmit();

        String actual = registerPage.getLabelEmailErrorMessage();
        Assert.assertEquals(actual, JsonHelper.get("register_err_email"), "Lỗi: Thông báo hệ thống khi đăng ký bằng Email đã tồn tại không hiển thị đúng!");
        log.info("=== PASSED TC_02 ===");
    }

    @Test()
    public void TC03_BlankWhenCreateNewAccountPage() {
        log.info("=== START TC_03: Verify Required Fields Validation ===");
        registerPage.clickHeaderAccountLink();
        registerPage.navigateToRegisterPage();
        registerPage.clickButtonSubmit();

        Assert.assertEquals(registerPage.getLabelFirstNameError(), JsonHelper.get("register_err_required"),"Lỗi: Message validate cho trường First Name trống không hiển thị đúng!");
        Assert.assertEquals(registerPage.getLabelLastNameError(), JsonHelper.get("register_err_required"),"Lỗi: Message validate cho trường Last Name trống không hiển thị đúng!");
        Assert.assertEquals(registerPage.getLabelEmailFieldError(), JsonHelper.get("register_err_required"),"Lỗi: Message validate cho trường Email Name trống không hiển thị đúng!");
        Assert.assertEquals(registerPage.getLabelPasswordError(), JsonHelper.get("register_err_required"),"Lỗi: Message validate cho trường Password trống không hiển thị đúng!");
        Assert.assertEquals(registerPage.getLabelConfirmPasswordError(), JsonHelper.get("register_err_required"),"Lỗi: Message validate cho trường ConfirmPassword trống không hiển thị đúng!");
        Assert.assertEquals(registerPage.getLabelPhoneNumberError(), JsonHelper.get("register_err_required"),"Lỗi: Message validate cho trường PhoneNumber trống không hiển thị đúng!");
        Assert.assertEquals(registerPage.getLabelStreetAddressError(), JsonHelper.get("register_err_required"),"Lỗi: Message validate cho trường StreetAddress trống không hiển thị đúng!");
        Assert.assertEquals(registerPage.getLabelCityError(), JsonHelper.get("register_err_required"),"Lỗi: Message validate cho trường City trống không hiển thị đúng!");
        Assert.assertEquals(registerPage.getLabelZipCodeError(), JsonHelper.get("register_err_required"),"Lỗi: Message validate cho trường ZipCode trống không hiển thị đúng!");
        Assert.assertEquals(registerPage.getLabelCountryError(), JsonHelper.get("register_err_country"),"Lỗi: Message validate cho trường Country trống không hiển thị đúng!");
        Assert.assertEquals(registerPage.getLabelPrivacyPolicyError(), JsonHelper.get("register_err_required"),"Lỗi: Message validate cho trường PrivacyPolicy trống không hiển thị đúng!");
        log.info("=== PASSED TC_03 ===");
    }

}