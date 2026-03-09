package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

import static com.marlink.automation.utils.RandomData.*;


public class CreateNewAccountPage extends BasePage {

    public CreateNewAccountPage(WebDriver driver) {
        super(driver);
    }

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
    private final By registerSuccessfully = By.xpath("//h1[@class='page-title' and .//span[text()='Customer Login']]");



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

    public String getTextMessRegister() {
        return waitVisible(registerSuccessfully).getText();
    }

    public void checkRegisterSuccessfully()  {
        Assert.assertTrue(getTextMessRegister().contains("Customer Login"));
    }
















}
