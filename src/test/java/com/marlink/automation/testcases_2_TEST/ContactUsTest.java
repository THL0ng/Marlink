package com.marlink.automation.testcases_2_TEST;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages_2_TEST.ContactUsPage;
import com.marlink.automation.utils.JsonHelper;
import com.marlink.automation.utils.RandomData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactUsTest extends BaseTest {

    private ContactUsPage contactPage;
    private final String ATTACHMENT_FILE = "Marlink.png";

    @BeforeMethod
    public void setupPage() {
        contactPage = new ContactUsPage(driver);
    }

    @Test()
    public void TC01_ContactUs_SubmitSuccess() {
        contactPage.clickContactUsTab();

        contactPage.fillContactForm(
                RandomData.firstName,
                RandomData.lastName,
                RandomData.email,
                RandomData.topic,
                "Automation Test Message Content"
        );

        contactPage.clickSubmit();

        String expected = JsonHelper.get("contactUs_inform_success");
        String actual = contactPage.getActualSuccessMessage();

        Assert.assertEquals(actual, expected,
                String.format("Lỗi gửi form! Mong đợi: [%s] nhưng thực tế hiển thị: [%s]", expected, actual));
    }

    @Test()
    public void TC02_ContactUs_UploadFileSuccess() {
        contactPage.clickContactUsTab();

        contactPage.fillContactForm(
                RandomData.firstName,
                RandomData.lastName,
                RandomData.email,
                "Technical Support Request",
                "Testing upload function with Robot class"
        );

        contactPage.uploadAttachment(ATTACHMENT_FILE);
        contactPage.clickSubmit();

        String expected = JsonHelper.get("contactUs_inform_success");
        String actual = contactPage.getActualSuccessMessage();

        Assert.assertEquals(actual, expected,
                String.format("Lỗi gửi form kèm file! Mong đợi: [%s] nhưng thực tế hiển thị: [%s]", expected, actual));
    }
}