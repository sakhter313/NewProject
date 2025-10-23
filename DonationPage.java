package pages;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommanActions;

import java.time.Duration;
import java.util.List;

public class DonationPage {
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
    }

    public void openPage(String url){
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(heroText));
    }

    public String getHeroText(){
        return heroText.getText();
    }

    public boolean isBackgroundImageDisplayed(){
        return commanActions.isElementDisplayed(backgroundImage);
    }

    public void selectOneTimeDonation(){
        commanActions.clickElement(oneTimeOption);
    }

    public void selectMonthlyDonation(){
        commanActions.clickElement(monthlyOption);
    }
    public void selectAmount(String amount){
        WebElement amountBtn = driver.findElement(By.xpath("//*[@id='app_root']/div/div[2]/div[3]/div[3]/div[1]/div[1]"));
        commanActions.clickElement(amountBtn);
    }
}
