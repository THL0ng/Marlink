package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import com.marlink.automation.utils.JsonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.marlink.automation.utils.RandomData.*;
import static com.marlink.automation.utils.RandomData.zipCode;

public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private final By headerAccountLinkButton = By.xpath("//a[normalize-space()='Sign in or create an account']");
    private final By createAnAccountButton = By.xpath("//a[@class='action create primary']");
    private final By firstNameField = By.id("firstname");
    private final By lastNameField = By.id("lastname");
    private final By emailField = By.id("email_address");
    private final By passwordField = By.id("password");
    private final By confirmPasswordField = By.id("password-confirmation");
    private final By phoneNumberField = By.id("telephone");
    private final By streetAddressField = By.id("street_1");
    private final By cityField = By.id("city");
    private final By zipCodeField = By.id("zip");
    private final By countryField = By.id("country");
    private final By privacyPolicyCheckbox = By.id("sparsh_consent_checkbox_1");
    private final By submitCreateAnAccount = By.xpath("//button[@class='action submit primary']");
    private final By registerSuccessfully = By.xpath("//div[@data-ui-id='message-success']");
    private final By registerErrorEmail = By.cssSelector("div.messages > div.message-error > div");

    private final String EXPECTED_REGISTER_SUCCESS = JsonHelper.get("register_inform_success");
    private final String EXPECTED_REGISTER_ERR_EMAIL = JsonHelper.get("register_err_email");
    private final String EXPECTED_REGISTER_ERR_REQUIRED = JsonHelper.get("register_err_required");
    private final String EXPECTED_REGISTER_ERR_COUNTRY = JsonHelper.get("register_err_country");

    private final By firstNameRequired = By.id("firstname-error");
    private final By lastNameRequired = By.id("lastname-error");
    private final By emailAddressRequired = By.id("email_address-error");
    private final By passwordRequired = By.id("password-error");
    private final By passwordConfirmRequired = By.id("password-confirmation-error");
    private final By phoneNumberRequired = By.id("telephone-error");
    private final By streetAddressRequired = By.id("street_1-error");
    private final By cityRequired = By.id("city-error");
    private final By zipCodeRequired = By.id("zip-error");
    private final By countryRequired = By.id("country-error");
    private final By privacyPolicyRequired = By.id("sparsh_consent_checkbox-error");




    public void clickheaderAccountLinkButton(){
        waitClickable(headerAccountLinkButton);
        click(headerAccountLinkButton);
    }

    public void clickCreateAnAccountButton(){
        waitClickable(createAnAccountButton);
        click(createAnAccountButton);
    }

    public void inputFirstName(){
        waitVisible(firstNameField);
        type(firstNameField, firstName);
    }

    public void inputLastName(){
        waitVisible(lastNameField);
        type(lastNameField, lastName);
    }

    public void inputEmail(){
        waitVisible(emailField);
        type(emailField, email);
    }

    public void inputRegisteredEmail(){
        waitVisible(emailField);
        type(emailField, registeredemail);
    }

    public void inputPassword(){
        waitVisible(passwordField);
        type(passwordField, password);
    }

    public void inputConfirmPassword(){
        waitVisible(confirmPasswordField);
        type(confirmPasswordField, password);
    }

    public void inputPhoneNumber(){
        waitVisible(phoneNumberField);
        type(phoneNumberField, phoneNumber);
    }

    public void inputStreetAddress(){
        waitVisible(streetAddressField);
        type(streetAddressField, streetAddress);
    }

    public void inputCityField(){
        waitVisible(cityField);
        type(cityField, city);
    }

    public void inputZipCode(){
        waitVisible(zipCodeField);
        type(zipCodeField, zipCode);
    }

    public String selectRandomCountry() {
        waitClickable(countryField);
        click(countryField);

        List<WebElement> options = driver.findElements(
                By.xpath("//select[@id='country']/option")
        );

        if (options.size() == 0) {
            throw new RuntimeException("country dropdown không có option!");
        }

        Random random = new Random();
        int index = random.nextInt(options.size());


        WebElement chosen = options.get(index);
        String nationality = chosen.getText().trim();
        System.out.println("Selected country: " + nationality);

        chosen.click();

        return nationality;
    }

    public void clickPrivacyPolicyCheckbox(){
        waitClickable(privacyPolicyCheckbox);
        click(privacyPolicyCheckbox);
    }

    public void clickSubmitCreateAnAccountButton(){
        waitClickable(submitCreateAnAccount);
        click(submitCreateAnAccount);
    }

    public String getActualPageTitle() {
        waitVisible(registerSuccessfully);
        return waitVisible(registerSuccessfully).getText();
    }

    public String getActualErrorEmail() {
        waitVisible(registerErrorEmail);
        return waitVisible(registerErrorEmail).getText();
    }

    public String getExpectedMessages(String key) {
        Map<String, String> messages = new HashMap<>();
        messages.put("registerSuccess", EXPECTED_REGISTER_SUCCESS);
        messages.put("errEmail", EXPECTED_REGISTER_ERR_EMAIL);
        messages.put("ErrRequired", EXPECTED_REGISTER_ERR_REQUIRED);
        messages.put("errCountry", EXPECTED_REGISTER_ERR_COUNTRY);
        return messages.get(key);
    }

    public String getActualFirstNameMess(){
        waitVisible(firstNameRequired);
        return getText(firstNameRequired);
    }

    public String getActualLastNameMess(){
        waitVisible(lastNameRequired);
        return getText(lastNameRequired);
    }

    public String getActualEmailMess(){
        waitVisible(emailAddressRequired);
        return getText(emailAddressRequired);
    }

    public String getActualPasswordMess(){
        waitVisible(passwordRequired);
        return getText(passwordRequired);
    }

    public String getActualPasswordConfirmMess(){
        waitVisible(passwordConfirmRequired);
        return getText(passwordConfirmRequired);
    }

    public String getActualNumberPhoneMess(){
        waitVisible(phoneNumberRequired);
        return getText(phoneNumberRequired);
    }

    public String getActualStreetAddressMess(){
        waitVisible(streetAddressRequired);
        return getText(streetAddressRequired);
    }

    public String getActualCityMess(){
        waitVisible(cityRequired);
        return getText(cityRequired);
    }

    public String getActualZipCodeMess(){
        waitVisible(zipCodeRequired);
        return getText(zipCodeRequired);
    }

    public String getActualCountryMess(){
        waitVisible(countryRequired);
        return getText(countryRequired);
    }

    public String getActualPrivacyPolicyMess(){
        waitVisible(privacyPolicyRequired);
        return getText(privacyPolicyRequired);
    }

}
