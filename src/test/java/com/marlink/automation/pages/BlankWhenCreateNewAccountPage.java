package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class BlankWhenCreateNewAccountPage extends BasePage {

    public BlankWhenCreateNewAccountPage(WebDriver driver) {
        super(driver);
    }

    private final By createAnAccountButton = By.xpath("//a[@class='action create primary']");
    private final By submitCreateAnAccount = By.xpath("//button[@class='action submit primary']");
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


    public void clickCreateAnAccountButton(){
        waitClickable(createAnAccountButton);
        click(createAnAccountButton);
    }

    public void clickSubmitCreateAnAccountButton(){
        waitClickable(submitCreateAnAccount);
        click(submitCreateAnAccount);
    }

    public String submitBlankFirstNameForm(){
        waitVisible(firstNameRequired);
        return getText(firstNameRequired);
    }

    public String submitBlankLastNameForm(){
        waitVisible(lastNameRequired);
        return getText(lastNameRequired);
    }

    public String submitBlankEmailForm(){
        waitVisible(emailAddressRequired);
        return getText(emailAddressRequired);
    }

    public String submitBlankPasswordForm(){
        waitVisible(passwordRequired);
        return getText(passwordRequired);
    }

    public String submitBlankPasswordConfirmForm(){
        waitVisible(passwordConfirmRequired);
        return getText(passwordConfirmRequired);
    }

    public String submitBlankPhoneNumberForm(){
        waitVisible(phoneNumberRequired);
        return getText(phoneNumberRequired);
    }

    public String submitBlankStreetAddressForm(){
        waitVisible(streetAddressRequired);
        return getText(streetAddressRequired);
    }

    public String submitBlankCityForm(){
        waitVisible(cityRequired);
        return getText(cityRequired);
    }

    public String submitBlankZipCodeForm(){
        waitVisible(zipCodeRequired);
        return getText(zipCodeRequired);
    }

    public String submitBlankCountryForm(){
        waitVisible(countryRequired);
        return getText(countryRequired);
    }

    public String submitBlankPrivacyPolicyForm(){
        waitVisible(privacyPolicyRequired);
        return getText(privacyPolicyRequired);
    }

    public List<String> getRequiredWarningTexts() {
        return List.of(
                "This is a required field.",
                "Ce champ est obligatoire.",
                "Campo obligatorio.",

                "Please select an option.",
                "Merci de choisir une option.",
                "Por favor selecciona una opción."
        );
    }












}
