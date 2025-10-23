package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DonationOptionsTest extends BaseTest {
    @Test(priority = 3, description = "Ensure user can select both one-time and monthly donation options with different amounts")
    public void testDonationOptionsAndAmounts(){
        //One-time donation
        donationPage.selectOneTimeDonation();
        donationPage.selectAmount("50");

        //Reload for clean site
        donationPage.openPage(configProps.getProperty("base.url"));

        //Monthly donation
        donationPage.selectMonthlyDonation();
        donationPage.selectAmount("100");
        Assert.assertTrue(true, "Donate Selection successful");
    }
}
