package com.marlink.automation.testcases_02;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages_02.ContactUsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactUsTest extends BaseTest {

    ContactUsPage contact;
    @BeforeMethod
    public void setupPage() {
        contact = new ContactUsPage(driver);
    }

    @Test
    public void TC_01_verify_ContactUs_MessageSentSuccessfully(){
        contact.clickContactUsTab();
        contact.inputFirstNameContactForm();
        contact.inputLastNameContactForm();
        contact.inputEmailContactForm();
        contact.inputTopicContactForm();
        contact.inputYourMessageContactForm();
        contact.clickSubmitButton();
        Assert.assertEquals(contact.getActualTextContactUsMessage(), contact.getExpectedMessages("contactSuccess"));
    }
}
