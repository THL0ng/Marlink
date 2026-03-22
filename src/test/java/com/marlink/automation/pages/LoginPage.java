package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final Logger log = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
    }



    private final By linkSignIn = By.className("authorization-link");
    private final By inputEmail = By.id("email");
    private final By inputPassword = By.id("password");
    private final By buttonSignIn = By.xpath("//button[@class='action login primary']");
    private final By buttonForgotPassword = By.xpath("//a[@class='action remind']/span");
    private final By buttonResetPassword = By.xpath("//button[@class='action submit primary']");
    private final By inputEmailReset = By.id("email_address");
    private final By dropdownAccount = By.cssSelector(".action.switch");
    private final By buttonLogout = By.xpath("//div[@class='customer-menu']//a[contains(text(),'Sign Out')]");

    private final By labelEmailError = By.id("email-error");
    private final By labelPasswordError = By.id("password-error");
    private final By labelGlobalError = By.cssSelector("div.message-error div");
    private final By labelForgotPasswordSuccess = By.xpath("//div[@class='message-success success message']");
    private final By labelMyAccountTitle = By.cssSelector("h1.page-title span");

    public void navigateToSignInPage() {
        log.info("Clicking on Sign In link.");
        waitClickable(linkSignIn);
        click(linkSignIn);
    }

    public void fillLoginForm(String email, String password) {
        log.info("Typing Email: {} and Password.", email);
        waitVisible(inputEmail);
        type(inputEmail, email);
        waitVisible(inputPassword);
        type(inputPassword, password);
    }

    public void clickButtonSignIn() {
        log.info("Clicking on Sign In button.");
        waitClickable(buttonSignIn);
        click(buttonSignIn);
    }

    public void clickDropdownAccount() {
        log.info("Opening Account dropdown.");
        waitClickable(dropdownAccount);
        click(dropdownAccount);
    }

    public void clickButtonLogout() {
        log.info("Clicking on Logout button.");
        waitClickable(buttonLogout);
        click(buttonLogout);
    }

    public void resetPassword(String email) {
        log.info("Executing Reset Password for email: {}", email);
        waitClickable(buttonForgotPassword);
        click(buttonForgotPassword);
        waitVisible(inputEmailReset);
        type(inputEmailReset, email);
        click(buttonResetPassword);
    }

    public String getEmailFieldError() {
        log.info("Getting Email field error message.");
        return getText(labelEmailError); }

    public String getPasswordFieldError() {
        log.info("Getting Password field error message.");
        return getText(labelPasswordError);
    }
    public String getGlobalErrorMessage() {
        log.info("Getting Global error message.");
        return waitVisible(labelGlobalError).getText();
    }
    public String getForgotPasswordSuccessMessage() {
        log.info("Getting Forgot Password success message.");
        return waitVisible(labelForgotPasswordSuccess).getText();
    }

    public String getPageTitle() {
        log.info("Getting Page Title.");
        waitForElementToUpdate(labelMyAccountTitle);
        return waitVisible(labelMyAccountTitle).getText();
    }
}