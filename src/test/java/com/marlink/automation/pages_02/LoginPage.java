package com.marlink.automation.pages_02;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

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

    // Locators cho Validation & Errors
    private final By emailError = By.id("email-error");
    private final By passwordError = By.id("password-error");
    private final By globalErrorMessage = By.cssSelector("div.message-error div");
    private final By myAccountTitle = By.cssSelector("h1.page-title span");

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

    public String getActualPageTitle() {
        waitForElementToUpdate(myAccountTitle);
        return waitVisible(myAccountTitle).getText();
    }

}