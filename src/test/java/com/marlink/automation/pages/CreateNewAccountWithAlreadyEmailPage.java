package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CreateNewAccountWithAlreadyEmailPage extends BasePage {
    public CreateNewAccountWithAlreadyEmailPage(WebDriver driver) {
        super(driver);
    }

    private final By messageInformError = By.cssSelector("div.messages > div.message-error > div");
    private final By emailField = By.id("email_address");

    public String checkMessageErrorDisplay() {
        waitVisible(messageInformError);
        return getText(messageInformError);
    }


    public void InputEmailAlreadyToEmailForm() {
        waitClickable(emailField);
        type(emailField, "thanhlongzz412zz@gmail.com");
    }

    public List<String> messErrorText() {
        return List.of(
                "There is already an account with this email address.",
                "Ya existe una cuenta con esta dirección de correo electrónico.",
                "Il existe déjà un compte avec cette adresse email."

        );
    }

}
