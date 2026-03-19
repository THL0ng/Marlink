package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import com.marlink.automation.utils.JsonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

import static com.marlink.automation.utils.RandomData.email;
import static com.marlink.automation.utils.RandomData.password;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // --- Locators ---
    private final By signInLink = By.className("authorization-link");
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By signInButton = By.xpath("//button[@class='action login primary']");
    private final By dropDownAccount = By.cssSelector(".action.switch");
    private final By logOutButton = By.xpath("//div[@class='customer-menu']//a[contains(text(),'Sign Out')]");
    private final By forgotPasswordButton = By.xpath("//a[@class='action remind']/span");
    private final By resetPasswordButton = By.xpath("//button[@class='action submit primary']");
    private final By emailResetForm = By.id("email_address");

    // Locators cho Validation & Errors
    private final By emailError = By.id("email-error");
    private final By passwordError = By.id("password-error");
    private final By globalErrorMessage = By.cssSelector("div.message-error div");
    private final By forgotPasswordErrorMessage = By.xpath("//div[@class='message-success success message']");
    private final By myAccountTitle = By.cssSelector("h1.page-title span");
    private final String EXPECTED_LOGIN_SUCCESS = JsonHelper.get("login_title_success");
    private final String EXPECTED_REQUIRED_ERROR = JsonHelper.get("login_err_required");
    private final String EXPECTED_GLOBAL_ERROR = JsonHelper.get("login_err_global");
    private final String EXPECTED_FORGOTPASSWORD_ERROR = JsonHelper.get("login_err_forgotPassword");


    // --- Actions ---
    public void goToSignInPage() {
        waitClickable(signInLink);
        click(signInLink);
    }

    public void fillLoginField(String email, String password) {
        waitVisible(emailInput);
        type(emailInput, email);
        waitVisible(passwordInput);
        type(passwordInput, password);
    }

    public void clickSignInButton() {
        waitClickable(signInButton);
        click(signInButton);

    }

    public void inputInvalidEmail(){
        waitClickable(emailInput);
        type(emailInput,email);
    }

    public void inputInvalidPassword(){
        waitClickable(passwordInput);
        type(passwordInput,password);
    }

    public void clickDropDown(){
        waitClickable(dropDownAccount);
        click(dropDownAccount);
    }

    public void clickLogOut(){
        waitClickable(logOutButton);
        click(logOutButton);
    }

    public String getEmailFieldErrorMessage() {
        return waitVisible(emailError).getText();
    }

    public String getPasswordFieldErrorMessage() {
        return waitVisible(passwordError).getText();
    }

    public String getGlobalErrorMessage() {
        return waitVisible(globalErrorMessage).getText();
    }

    public String getForgotPasswordErrorMessage(){
        return waitVisible(forgotPasswordErrorMessage).getText();
    }

    public String getExpectedMessages(String key) {
        Map<String, String> messages = new HashMap<>();
        messages.put("success", EXPECTED_LOGIN_SUCCESS);
        messages.put("required", EXPECTED_REQUIRED_ERROR);
        messages.put("global", EXPECTED_GLOBAL_ERROR);
        messages.put("forgotPassword", EXPECTED_FORGOTPASSWORD_ERROR);
        return messages.get(key);
    }

    public void clickForgotPasswordButton(){
        waitClickable(forgotPasswordButton);
        click(forgotPasswordButton);
    }

    public void inputEmailReset(){
        waitClickable(emailResetForm);
        type(emailResetForm, email);
    }

    public void clickResetPasswordButton(){
        waitClickable(resetPasswordButton);
        click(resetPasswordButton);
    }

    public String getActualPageTitle() {
        waitForElementToUpdate(myAccountTitle);
        return waitVisible(myAccountTitle).getText();
    }

}