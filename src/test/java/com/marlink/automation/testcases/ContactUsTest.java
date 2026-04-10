package com.marlink.automation.testcases;

import com.marlink.automation.base.BaseTest;
import com.marlink.automation.pages.ContactUsPage;
import com.marlink.automation.utils.JsonHelper;
import com.marlink.automation.utils.DataHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactUsTest extends BaseTest {

    private ContactUsPage contactPage;
    private final String ATTACHMENT_FILE = "Marlink.png";
    private static final Logger log = LogManager.getLogger(ContactUsTest.class);

    @BeforeMethod
    public void setupPage() {
        contactPage = new ContactUsPage(driver);
    }

    @Test()
    public void TC_01_ContactUs_SubmitSuccess() {
        log.info("=== START TC_01: Contact Us - Submit Form Success ===");
        contactPage.navigateToContactUsPage();

        contactPage.fillContactForm(
                DataHelper.firstName,
                DataHelper.lastName,
                DataHelper.email,
                DataHelper.topic,
                "Automation Test Message Content"
        );
        contactPage.clickButtonSubmit();
        log.info("Verifying Success Message. Expected: [{}]", JsonHelper.get("contactUs_inform_success"));
        Assert.assertEquals(contactPage.getLabelSuccessMessage(), JsonHelper.get("contactUs_inform_success"),"Lỗi: Thông báo gửi Contact Us thành công không hiển thị hoặc nội dung không khớp!");
        log.info("=== PASSED TC_01 ===");
    }

    @Test()
    public void TC_02_ContactUs_UploadFileSuccess() {
        log.info("=== START TC_02: Contact Us - Submit Form with Attachment ===");
        contactPage.navigateToContactUsPage();

        contactPage.fillContactForm(
                DataHelper.firstName,
                DataHelper.lastName,
                DataHelper.email,
                "Technical Support Request",
                "Testing upload function with Robot class"
        );

        log.info("Step: Uploading file [{}]", ATTACHMENT_FILE);

        contactPage.uploadAttachment(ATTACHMENT_FILE);
        contactPage.clickButtonSubmit();
        log.info("Verifying Success Message. Expected: [{}]", JsonHelper.get("contactUs_inform_success"));
        Assert.assertEquals(contactPage.getLabelSuccessMessage(), JsonHelper.get("contactUs_inform_success"),"Lỗi: Thông báo gửi Contact Us thành công không hiển thị hoặc nội dung không khớp!");
        log.info("=== PASSED TC_02 ===");
    }
}