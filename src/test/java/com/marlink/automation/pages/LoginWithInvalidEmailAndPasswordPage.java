package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.marlink.automation.utils.RandomData.email;
import static com.marlink.automation.utils.RandomData.password;

public class LoginWithInvalidEmailAndPasswordPage extends BasePage {

    public LoginWithInvalidEmailAndPasswordPage(WebDriver driver) {
        super(driver);
    }

    private final By signInLink = By.className("authorization-link");
    private final By signInButtonLoginInvalid = By.xpath("//button[@class='action login primary']");
    private final By passwordLoginInvalid = By.id("password-error");
    private final By emailLoginInvalid = By.id("email-error");
    private final By emailFormLoginInvalid = By.id("email");
    private final By passwordFormLoginInvalid = By.id("password");
    private final By messageError = By.xpath("//div[@class='messages']/div[@class='message-error']/div");



    public void clickSignInCaseInvalid (){
        waitClickable(signInLink);
        click(signInLink);
    }

    public void clickSgnInButtonLoginInvalid(){
        waitClickable(signInButtonLoginInvalid);
        click(signInButtonLoginInvalid);
    }

    public String inputBlankEmailForm(){
        waitVisible(emailLoginInvalid);
        return getText(emailLoginInvalid);
    }

    public String inputBlankPasswordForm(){
        waitVisible(passwordLoginInvalid);
        return getText(passwordLoginInvalid);
    }

    public List<String> warningText() {
        return List.of(
                "This is a required field.",
                "Ce champ est obligatoire.",
                "Campo obligatorio.",

                "Please select an option.",
                "Merci de choisir une option.",
                "Por favor selecciona una opción.",
                "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later."

        );
    }

    public void inputEmailForm(){
        waitClickable(emailFormLoginInvalid);
        type(emailFormLoginInvalid,email);
    }

    public void inputPasswordForm(){
        waitClickable(passwordFormLoginInvalid);
        type(passwordFormLoginInvalid,password);
    }

    public String checkMessageErrorDisplay(){
        waitVisible(messageError);
        return getText(messageError) ;
    }



}
