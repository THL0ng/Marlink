package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginWithValidEmailAndPasswordPage extends BasePage {

    public LoginWithValidEmailAndPasswordPage(WebDriver driver) {
        super(driver);
    }

    private final By signInLink = By.className("authorization-link");
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By signInButton = By.xpath("//button[@class='action login primary']");

    public void clickSignIn (){
        waitClickable(signInLink);
        click(signInLink);
    }
    public void enterEmail (String userName){
        type(emailInput, userName);
        waitVisible(emailInput);
    }
    public void enterPassword (String password){
        type(passwordInput, password);
        waitVisible(passwordInput);
    }
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);

    }
    public void clickSignInButton (){
        waitClickable(signInButton);
        click(signInButton);

    }

}
