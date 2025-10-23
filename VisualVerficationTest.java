package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VisualVerificationTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(VisualVerificationTest.class);
    
    @Test(priority = 2, description = "Verify Background image and page text display properly")
    public void testBackgroundImageAndTextDisplay(){
        logger.info("Starting VisualVerificationTest: testBackgroundImageAndTextDisplay");
        
        boolean imageDisplayed = donationPage.isBackgroundImageDisplayed();
        logger.debug("Background image displayed: {}", imageDisplayed);
        Assert.assertTrue(imageDisplayed, "Background image not displayed");
        logger.info("Background image assertion passed");
        
        String heroText = donationPage.getHeroText();
        logger.debug("Hero text length: {}", heroText.length());
        Assert.assertTrue(heroText.length() > 0, "Page text not displayed");
        logger.info("Hero text length assertion passed");
        
        logger.info("VisualVerificationTest: testBackgroundImageAndTextDisplay completed successfully");
    }
}
