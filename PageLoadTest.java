package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PageLoadTest extends BaseTest {
    @Test(priority = 1, description = "Confirm donate home page opens correctly")
    public void testPageOpenCorrectly(){
        String expectedUrl = configProps.getProperty("base.url");
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Page did not load correctly");
        Assert.assertTrue(donationPage.getHeroText().contains("Donate today and help us"),"Hero text not displayed properly");
    }
}
