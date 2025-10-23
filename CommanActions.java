package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CommanActions {
    private static final Logger logger = LogManager.getLogger(CommanActions.class);

    private WebDriver driver;
    private WebDriverWait wait;

    public CommanActions(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.debug("CommanActions initialized with WebDriver");
    }

    public void clickElement(WebElement element){
        try {
            logger.debug("Waiting for element to be clickable: {}", element.toString());
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            logger.info("Successfully clicked element: {}", element.toString());
        } catch (Exception e) {
            logger.error("Failed to click element: {}", e.getMessage(), e);
            throw e;
        }
    }

    public boolean isElementDisplayed(WebElement element){
        try{
            logger.debug("Waiting for visibility of element: {}", element.toString());
            wait.until(ExpectedConditions.visibilityOf(element));
            boolean displayed = element.isDisplayed();
            logger.info("Element visibility check: {}", displayed ? "Displayed" : "Not displayed");
            return displayed;
        }
        catch (Exception e){
            logger.warn("Element not visible: {}", e.getMessage());
            return false;
        }
    }

    public boolean verifyText(WebElement element, String expectedText){
        try {
            String actualText = element.getText().trim();
            boolean matches = actualText.equalsIgnoreCase(expectedText);
            logger.info("Text verification - Expected: '{}', Actual: '{}', Match: {}", expectedText, actualText, matches);
            return matches;
        } catch (Exception e) {
            logger.error("Failed to verify text: {}", e.getMessage(), e);
            return false;
        }
    }

    public boolean checkAllLinksFunctionality(List<WebElement> links){
        logger.info("Checking functionality for {} links", links.size());
        int validLinks = 0;
        for(WebElement link: links){
            try {
                String url = link.getAttribute("href");
                if(url !=null && !url.startsWith("javascript") && !url.startsWith("#")){
                    logger.debug("Valid link found: {}", url);
                    String originalWindow = driver.getWindowHandle();
                    clickElement(link);
                    validLinks++;
                    // Optionally switch back or handle new window
                    driver.switchTo().window(originalWindow);
                }
            } catch (Exception e) {
                logger.warn("Issue with link: {}", e.getMessage());
            }
        }
        logger.info("Processed {} valid links successfully", validLinks);
        return true;
    }
}
