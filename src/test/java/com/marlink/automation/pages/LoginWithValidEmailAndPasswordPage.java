package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginWithValidEmailAndPasswordPage extends BasePage {

    public LoginWithValidEmailAndPasswordPage(WebDriver driver) {
        super(driver);
    }

    private final By signInLink = By.xpath("//a[normalize-space()='Sign in or create an account']");
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By signInButton = By.xpath("//button[@class='action login primary']");
    private final By myAccount = By.xpath("//h1[@class='page-title' and .//span[text()='My Account']]");

    public void clickSignIn() {
        waitClickable(signInLink);
        click(signInLink);
    }

    public void enterEmail(String userName) {
        waitVisible(emailInput);
        type(emailInput, userName);
    }

    public void enterPassword(String password) {
        waitVisible(passwordInput);
        type(passwordInput, password);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);

    }

    public void clickSignInButton() {
        waitClickable(signInButton);
        click(signInButton);

    }

    public String getTextMessLogin() {
        return waitVisible(myAccount).getText();
    }

    public void checkLoginSuccessfully()  {
        Assert.assertTrue(getTextMessLogin().contains("My Account"));
    }





}
