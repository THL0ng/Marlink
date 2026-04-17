package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    private static final Logger log = LogManager.getLogger(RegisterPage.class);

    private final By linkHeaderAccount = By.xpath("//a[normalize-space()='Sign in or create an account']");
    private final By buttonCreateAnAccount = By.xpath("//a[@class='action create primary']");
    private final By inputFirstName = By.id("firstname");
    private final By inputLastName = By.id("lastname");
    private final By inputEmail = By.id("email_address");
    private final By inputPassword = By.id("password");
    private final By inputConfirmPassword = By.id("password-confirmation");
    private final By inputPhoneNumber = By.id("telephone");
    private final By inputStreetAddress = By.id("street_1");
    private final By inputCity = By.id("city");
    private final By inputZipCode = By.id("zip");
    private final By selectCountry = By.id("country");
    private final By checkboxPrivacyPolicy = By.id("sparsh_consent_checkbox_1");
    private final By buttonSubmitCreate = By.xpath("//button[@class='action submit primary']");

    private final By labelRegisterSuccess = By.xpath("//div[@data-ui-id='message-success']");
    private final By labelRegisterErrorEmail = By.cssSelector("div.messages > div.message-error > div");
    private final By labelFirstNameError = By.id("firstname-error");
    private final By labelLastNameError = By.id("lastname-error");
    private final By labelEmailError = By.id("email_address-error");
    private final By labelPasswordError = By.id("password-error");
    private final By labelConfirmPasswordError = By.id("password-confirmation-error");
    private final By labelPhoneNumberError = By.id("telephone-error");
    private final By labelStreetAddressError = By.id("street_1-error");
    private final By labelCityError = By.id("city-error");
    private final By labelZipCodeError = By.id("zip-error");
    private final By labelCountryError = By.id("country-error");
    private final By labelPrivacyPolicyError = By.id("sparsh_consent_checkbox-error");

    public void clickHeaderAccountLink() {
        log.info("Clicking Header Link.");
        waitClickable(linkHeaderAccount);
        click(linkHeaderAccount);
    }

    public void navigateToRegisterPage() {
        log.info("Navigating to Register page via Header Link.");
        waitClickable(buttonCreateAnAccount);
        click(buttonCreateAnAccount);
    }

    public void fillRegisterForm(String fName, String lName, String email, String pwd, String phone, String street, String city, String zip) {
        log.info("Filling Register form for Email: {}", email);
        type(inputFirstName, fName);
        type(inputLastName, lName);
        type(inputEmail, email);
        type(inputPassword, pwd);
        type(inputConfirmPassword, pwd);
        type(inputPhoneNumber, phone);
        type(inputStreetAddress, street);
        type(inputCity, city);
        type(inputZipCode, zip);
    }
    public void selectRandomCountry() {
        log.info("Selecting a random country from dropdown.");

        // 1. Đợi Dropdown có thể click được và click
        waitClickable(selectCountry);

        // 2. Sử dụng class Select chuyên dụng cho thẻ <select>
        // Nó tự động handle việc đợi các option bên trong sẵn sàng
        Select countrySelect = new Select(driver.findElement(selectCountry));

        // 3. Lấy danh sách options
        List<WebElement> options = countrySelect.getOptions();

        if (options.size() <= 1) { // Thường option index 0 là "Please select..."
            log.error("Country dropdown has no actual options!");
            throw new RuntimeException("Country dropdown rỗng hoặc chỉ có dòng mặc định!");
        }

        // 4. Chọn ngẫu nhiên (bỏ qua index 0 nếu là câu hướng dẫn)
        int randomIndex = new Random().nextInt(options.size() - 1) + 1;

        log.info("Total options: {}. Selecting index: {}", options.size(), randomIndex);

        // 5. Dùng selectByIndex để an toàn hơn là click trực tiếp vào WebElement
        countrySelect.selectByIndex(randomIndex);
    }

    public void clickCheckboxPrivacyPolicy() {
        log.info("Clicking on Privacy Policy checkbox.");
        waitClickable(checkboxPrivacyPolicy);
        click(checkboxPrivacyPolicy);
    }

    public void clickButtonSubmit() {
        log.info("Clicking on Submit Create Account button.");
        waitClickable(buttonSubmitCreate);
        click(buttonSubmitCreate);
    }

    public String getLabelSuccessMessage() {
        log.info("Getting Register Success Message.");
        return waitVisible(labelRegisterSuccess).getText(); }
    public String getLabelEmailErrorMessage() {
        log.info("Getting Global Email Error Message.");
        return waitVisible(labelRegisterErrorEmail).getText(); }


    public String getLabelFirstNameError() { return getText(labelFirstNameError); }
    public String getLabelEmailFieldError() { return getText(labelEmailError); }
    public String getLabelLastNameError() { return getText(labelLastNameError); }
    public String getLabelPasswordError() { return getText(labelPasswordError); }
    public String getLabelConfirmPasswordError() { return getText(labelConfirmPasswordError); }
    public String getLabelPhoneNumberError() { return getText(labelPhoneNumberError); }
    public String getLabelStreetAddressError() { return getText(labelStreetAddressError); }
    public String getLabelCityError() { return getText(labelCityError); }
    public String getLabelZipCodeError() { return getText(labelZipCodeError); }
    public String getLabelCountryError() { return getText(labelCountryError); }
    public String getLabelPrivacyPolicyError() { return getText(labelPrivacyPolicyError); }
}