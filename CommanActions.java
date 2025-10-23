package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CommanActions {

    private WebDriver driver;
    private WebDriverWait wait;

    public CommanActions(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public boolean isElementDisplayed(WebElement element){
        try{
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        }
        catch (Exception e){
            return false;
        }
    }
    public boolean verifyText(WebElement element, String expectedText){
        return element.getText().trim().equalsIgnoreCase(expectedText);
    }

    public boolean checkAllLinksFunctionality(List<WebElement> links){
        for(WebElement link: links){
            String url = link.getAttribute("href");
            if(url !=null && !url.startsWith("javascript") && !url.startsWith("#")){
                String originalWindow = driver.getWindowHandle();
                clickElement(link);
            }
        }
        return true;
    }

}
