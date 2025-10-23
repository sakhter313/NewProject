package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VisualVerificationTest extends BaseTest {
    @Test(priority = 2, description = "Verify Background image and page text display properly")
    public void testBackgroundImageAndTextDisplay(){
        Assert.assertTrue(donationPage.isBackgroundImageDisplayed());
        String heroText = donationPage.getHeroText();
        Assert.assertTrue(heroText.length() > 0, "Page text not displayed");
    }
}

