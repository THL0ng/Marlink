package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.marlink.automation.utils.RandomData.email;

public class ForgotPasswordPage extends BasePage {
    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    private final By signInLink = By.className("authorization-link");
    private final By forgotPassword = By.xpath("//a[@class='action remind']/span");
    private final By emailResetForm = By.id("email_address");
    private final By resetPasswordButton = By.xpath("//button[@class='action submit primary']");
    private final By messageInformResetPassword = By.xpath("//div[@class='message-success success message']");


    public void clickSignInLink(){
        waitClickable(signInLink);
        click(signInLink);
    }

    public void clickForgotPasswordButton(){
        waitClickable(forgotPassword);
        click(forgotPassword);
    }

    public void inputEmail(){
        waitClickable(emailResetForm);
        type(emailResetForm, email);
    }

    public void clickResetPasswordButton(){
        waitClickable(resetPasswordButton);
        click(resetPasswordButton);
    }

    public List<String> checkMessResetPasswordDisplay(){
        return List.of(
                "you will receive an email with a link to reset your password.",
                "vous recevrez un email avec un lien pour réinitialiser votre mot de passe."


        );
    }

    public String getTextInformReset(){
        waitVisible(messageInformResetPassword);
        return getText(messageInformResetPassword);

    }












}
