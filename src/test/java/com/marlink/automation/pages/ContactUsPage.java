package com.marlink.automation.pages;

import com.marlink.automation.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends BasePage {
    public ContactUsPage(WebDriver driver) {
        super(driver);
    }
    private static final Logger log = LogManager.getLogger(ContactUsPage.class);    

    private final By linkContactUsTab = By.cssSelector("a[title='Contact Us']");
    private final By inputFirstName = By.id("first-name");
    private final By inputLastName = By.id("last-name");
    private final By inputEmail = By.id("email");
    private final By inputTopic = By.id("topic");
    private final By inputMessage = By.id("comment");
    private final By buttonSubmit = By.cssSelector("button[title='Submit']");
    private final By labelSuccessMessage = By.cssSelector("div.messages div.message-success div");
    private final By buttonChooseFile = By.id("attachment");

    public void navigateToContactUsPage() {
        log.info("Navigating to Contact Us page.");
        waitClickable(linkContactUsTab);
        click(linkContactUsTab);
    }

    public void fillContactForm(String fName, String lName, String email, String topic, String msg) {
        log.info("Filling Contact Us form for Email: {}", email);
        type(inputFirstName, fName);
        type(inputLastName, lName);
        type(inputEmail, email);
        type(inputTopic, topic);
        type(inputMessage, msg);
    }

    public void uploadAttachment(String fileName){
        String path = "uploadFiles/" + fileName;
        uploadFile(buttonChooseFile,path);
    }

    public void clickButtonSubmit() {
        log.info("Clicking on Submit button.");
        waitClickable(buttonSubmit);
        click(buttonSubmit);
    }

    public String getLabelSuccessMessage() {
        log.info("Getting Contact Us success message.");
        return waitVisible(labelSuccessMessage).getText();
    }
}