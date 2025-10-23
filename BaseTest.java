package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pages.DonationPage;
import utils.CommanActions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected DonationPage donationPage;
    protected CommanActions commanActions;
    protected Properties configProps;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browseParam){
        configProps = loadProperties();
        String baseUrl = configProps.getProperty("base.url");

        String browser = (browseParam != null && !browseParam.isEmpty()) ? browseParam : configProps.getProperty("browser");
         if (browser.equalsIgnoreCase("chrome")){
             WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver();
         }
         else if (browser.equalsIgnoreCase("edge")){
             WebDriverManager.edgedriver().setup();
             driver = new EdgeDriver();
         }
         else {
             throw new IllegalArgumentException("Unsupported browser: " + browser);
         }
         driver.manage().window().maximize();
         donationPage = new DonationPage(driver);
         commanActions = new CommanActions(driver);
         donationPage.openPage(baseUrl);
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

    private Properties loadProperties(){
        Properties props = new Properties();
        try{
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            props.load(fis);
            fis.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props;
    }
}
