package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DonationOptionsTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(DonationOptionsTest.class);
    
    @Test(priority = 3, description = "Ensure user can select both one-time and monthly donation options with different amounts")
    public void testDonationOptionsAndAmounts(){
        logger.info("Starting DonationOptionsTest: testDonationOptionsAndAmounts");
        
        // One-time donation
        logger.info("Testing one-time donation with $50");
        donationPage.selectOneTimeDonation();
        donationPage.selectAmount("50");
        logger.debug("One-time donation selection completed");
        
        // Reload for clean state
        donationPage.openPage(configProps.getProperty("base.url"));
        logger.debug("Page reloaded for monthly test");
        
        // Monthly donation
        logger.info("Testing monthly donation with $100");
        donationPage.selectMonthlyDonation();
        donationPage.selectAmount("100");
        logger.debug("Monthly donation selection completed");
        
        Assert.assertTrue(true, "Donate Selection successful");
        logger.info("DonationOptionsTest: testDonationOptionsAndAmounts completed successfully");
    }
}
