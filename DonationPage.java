package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommanActions;

import java.time.Duration;
import java.util.List;

public class DonationPage {
    private static final Logger logger = LogManager.getLogger(DonationPage.class);
    
    private WebDriver driver;
    private WebDriverWait wait;
    private CommanActions commanActions;

    @FindBy(xpath = "//*[@id='app_root']/div/div[1]/div/div[2]/div/p")
    private WebElement heroText;

    @FindBy(xpath = "//*[@id='svg_header_image']")
    private WebElement backgroundImage;

    @FindBy(xpath = "//*[@id='app_root']/div/div[2]/div[3]/div[2]/button[1]")
    private WebElement oneTimeOption;

    @FindBy(xpath = "//*[@id='app_root']/div/div[2]/div[3]/div[2]/button[2]")
    private WebElement monthlyOption;

    @FindBy(xpath = "//*[@id='app_root']/div/div[2]/div[3]/div[3]/div[1]/div[1]")
    private WebElement amount50Button;

    @FindBy(xpath = "//*[@id='app_root']/div/div[2]/div[3]/div[2]/div[1]/div[4]")
    private WebElement amount100Button;

    public DonationPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.commanActions = new CommanActions(driver);
        PageFactory.initElements(driver, this);
        logger.debug("DonationPage initialized");
    }

    public void openPage(String url){
        logger.info("Opening page: {}", url);
        driver.get(url);
        try {
            wait.until(ExpectedConditions.visibilityOf(heroText));
            logger.info("Page loaded successfully - Hero text visible");
        } catch (Exception e) {
            logger.error("Page load failed - Hero text not visible: {}", e.getMessage(), e);
            throw e;
        }
    }

    public String getHeroText(){
        try {
            String text = heroText.getText();
            logger.debug("Retrieved hero text: {}", text);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get hero text: {}", e.getMessage(), e);
            return "";
        }
    }

    public boolean isBackgroundImageDisplayed(){
        logger.info("Verifying background image display");
        return commanActions.isElementDisplayed(backgroundImage);
    }

    public void selectOneTimeDonation(){
        logger.info("Selecting one-time donation option");
        commanActions.clickElement(oneTimeOption);
    }

    public void selectMonthlyDonation(){
        logger.info("Selecting monthly donation option");
        commanActions.clickElement(monthlyOption);
    }

    public void selectAmount(String amount){
        logger.info("Selecting amount: {}", amount);
        WebElement amountBtn;
        if ("50".equals(amount)) {
            amountBtn = amount50Button;
        } else if ("100".equals(amount)) {
            amountBtn = amount100Button;
        } else {
            logger.warn("Unsupported amount: {}. Using default (50)", amount);
            amountBtn = amount50Button;
        }
        commanActions.clickElement(amountBtn);
    }
}
