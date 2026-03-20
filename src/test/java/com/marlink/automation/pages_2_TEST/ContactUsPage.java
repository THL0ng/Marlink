package com.marlink.automation.pages_2_TEST;

import com.marlink.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends BasePage {
    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    private final By linkContactUsTab = By.cssSelector("a[title='Contact Us']");
    private final By inputFirstName = By.id("first-name");
    private final By inputLastName = By.id("last-name");
    private final By inputEmail = By.id("email");
    private final By inputTopic = By.id("topic");
    private final By inputMessage = By.id("comment");
    private final By buttonSubmit = By.cssSelector("button[title='Submit']");
    private final By labelSuccessMessage = By.cssSelector("div.messages div.message-success div");
    private final By buttonChooseFile = By.id("attachment");

    public void clickContactUsTab() {
        waitClickable(linkContactUsTab);
        click(linkContactUsTab);
    }

    public void fillContactForm(String fName, String lName, String email, String topic, String msg) {
        type(inputFirstName, fName);
        type(inputLastName, lName);
        type(inputEmail, email);
        type(inputTopic, topic);
        type(inputMessage, msg);
    }

    public void uploadAttachment(String fileName) {
        waitClickable(buttonChooseFile);
        jsClick(buttonChooseFile);
        uploadFileWithRobotBackup(fileName);
    }

    public void clickSubmit() {
        waitClickable(buttonSubmit);
        click(buttonSubmit);
    }

    public String getActualSuccessMessage() {
        return getText(labelSuccessMessage);
    }
}