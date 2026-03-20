package com.marlink.automation.pages_2_TEST;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private final By buttonHeaderAccountLink = By.xpath("//a[normalize-space()='Sign in or create an account']");
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

    // Locators cho Message
    private final By labelRegisterSuccess = By.xpath("//div[@data-ui-id='message-success']");
    private final By labelRegisterErrorEmail = By.cssSelector("div.messages > div.message-error > div");

    // Locators cho Error Required
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

    // --- Actions ---
    public void clickHeaderAccountLink() {
        waitClickable(buttonHeaderAccountLink);
        click(buttonHeaderAccountLink);
    }

    public void clickCreateAnAccountButton() {
        waitClickable(buttonCreateAnAccount);
        click(buttonCreateAnAccount);
    }

    public void fillRegisterForm(String fName, String lName, String email, String pwd, String phone, String street, String city, String zip) {
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
        click(selectCountry);
        List<WebElement> options = driver.findElements(By.xpath("//select[@id='country']/option"));
        if (options.isEmpty()) throw new RuntimeException("Country dropdown rỗng!");
        options.get(new Random().nextInt(options.size())).click();
    }

    public void clickPrivacyPolicy() {
        waitClickable(checkboxPrivacyPolicy);
        click(checkboxPrivacyPolicy);
    }

    public void clickSubmit() {
        waitClickable(buttonSubmitCreate);
        click(buttonSubmitCreate);
    }

    // --- Get Actual Texts ---
    public String getSuccessMessage() { return waitVisible(labelRegisterSuccess).getText(); }
    public String getEmailErrorMessage() { return waitVisible(labelRegisterErrorEmail).getText(); }
    public String getFirstNameError() { return getText(labelFirstNameError); }
    public String getEmailFieldError() { return getText(labelEmailError); }
    public String getLastNameError() { return getText(labelLastNameError); }
    public String getPasswordError() { return getText(labelPasswordError); }
    public String getConfirmPasswordError() { return getText(labelConfirmPasswordError); }
    public String getPhoneNumberError() { return getText(labelPhoneNumberError); }
    public String getStreetAddressError() { return getText(labelStreetAddressError); }
    public String getCityError() { return getText(labelCityError); }
    public String getZipCodeError() { return getText(labelZipCodeError); }
    public String getCountryError() { return getText(labelCountryError); }
    public String getPrivacyPolicyError() { return getText(labelPrivacyPolicyError); }
}