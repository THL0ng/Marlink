package com.marlink.automation.pages_02;

import com.marlink.automation.base.BasePage;
import com.marlink.automation.utils.JsonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

import static com.marlink.automation.utils.RandomData.*;

public class ContactUsPage extends BasePage {
    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    private final By contactUsTab = By.cssSelector("a[title='Contact Us']");
    private final By firstNameContactForm = By.id("first-name");
    private final By lastNameCountactForm = By.id("last-name");
    private final By emailContactForm = By.id("email");
    private final By topicContactForm = By.id("topic");
    private final By yourMessageContactForm = By.id("comment");
    private final By submitButton = By.cssSelector("button[title='Submit']");
    private final By textContactUsMessage = By.cssSelector("div.messages > div.message-success > div");
    private final String EXPECTED_CONTACT_SUCCESS = JsonHelper.get("contactUs_inform_success");
    private final By chooseFileButton = By.id("attachment");


    public void clickContactUsTab(){
        waitClickable(contactUsTab);
        click(contactUsTab);
    }

    public void inputFirstNameContactForm(){
        waitClickable(firstNameContactForm);
        type(firstNameContactForm,firstName);
    }

    public void inputLastNameContactForm(){
        waitClickable(lastNameCountactForm);
        type(lastNameCountactForm,lastName);
    }

    public void inputEmailContactForm(){
        waitClickable(emailContactForm);
        type(emailContactForm,email);
    }

    public void inputTopicContactForm(){
        waitClickable(topicContactForm);
        type(topicContactForm,topic);
    }

    public void inputYourMessageContactForm(){
        waitClickable(yourMessageContactForm);
        type(yourMessageContactForm,randomText);
    }

    public void clickSubmitButton(){
        waitClickable(submitButton);
        click(submitButton);
    }

    public String getActualTextContactUsMessage(){
        return waitVisible(textContactUsMessage).getText();
    }

    public String getExpectedMessages(String key) {
        Map<String, String> messages = new HashMap<>();
        messages.put("contactSuccess", EXPECTED_CONTACT_SUCCESS);
        return messages.get(key);
    }

    public void clickChooseFileButton(){
        waitClickable(chooseFileButton);
        jsClick(chooseFileButton);
    }

    public void selectAndUploadFile(){
        uploadFileWithRobotBackup("MARLINK.png");
    }



}
