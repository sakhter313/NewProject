package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PageLoadTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(PageLoadTest.class);
    
    @Test(priority = 1, description = "Confirm donate home page opens correctly")
    public void testPageOpenCorrectly(){
        logger.info("Starting PageLoadTest: testPageOpenCorrectly");
        String expectedUrl = configProps.getProperty("base.url");
        String actualUrl = driver.getCurrentUrl();
        logger.debug("Expected URL: {}, Actual URL: {}", expectedUrl, actualUrl);
        
        Assert.assertEquals(actualUrl, expectedUrl, "Page did not load correctly");
        logger.info("URL assertion passed");
        
        String heroText = donationPage.getHeroText();
        logger.debug("Hero text: {}", heroText);
        Assert.assertTrue(heroText.contains("Donate today and help us"), "Hero text not displayed properly");
        logger.info("Hero text assertion passed");
        
        logger.info("PageLoadTest: testPageOpenCorrectly completed successfully");
    }
}
