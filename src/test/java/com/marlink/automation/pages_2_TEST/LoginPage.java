package com.marlink.automation.pages_2_TEST;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By linkSignIn = By.className("authorization-link");
    private final By inputEmail = By.id("email");
    private final By inputPassword = By.id("password");
    private final By buttonSignIn = By.xpath("//button[@class='action login primary']");
    private final By buttonDropDownAccount = By.cssSelector(".action.switch");
    private final By buttonLogOut = By.xpath("//div[@class='customer-menu']//a[contains(text(),'Sign Out')]");
    private final By buttonForgotPassword = By.xpath("//a[@class='action remind']/span");
    private final By buttonResetPassword = By.xpath("//button[@class='action submit primary']");
    private final By inputEmailReset = By.id("email_address");

    private final By labelEmailError = By.id("email-error");
    private final By labelPasswordError = By.id("password-error");
    private final By labelGlobalError = By.cssSelector("div.message-error div");
    private final By labelForgotPasswordSuccess = By.xpath("//div[@class='message-success success message']");
    private final By labelMyAccountTitle = By.cssSelector("h1.page-title span");

    public void goToSignInPage() {
        waitClickable(linkSignIn);
        click(linkSignIn);
    }

    public void fillLoginForm(String email, String password) {
        waitVisible(inputEmail);
        type(inputEmail, email);
        waitVisible(inputPassword);
        type(inputPassword, password);
    }

    public void clickSignInButton() {
        waitClickable(buttonSignIn);
        click(buttonSignIn);
    }

    public void resetPassword(String email) {
        waitClickable(buttonForgotPassword);
        click(buttonForgotPassword);
        waitVisible(inputEmailReset);
        type(inputEmailReset, email);
        click(buttonResetPassword);
    }

    public String getEmailFieldError() { return getText(labelEmailError); }

    public String getPasswordFieldError() { return getText(labelPasswordError); }

    public String getGlobalErrorMessage() { return waitVisible(labelGlobalError).getText(); }

    public String getForgotPasswordSuccessMessage() { return waitVisible(labelForgotPasswordSuccess).getText(); }

    public String getPageTitle() {
        waitForElementToUpdate(labelMyAccountTitle);
        return waitVisible(labelMyAccountTitle).getText();
    }
}