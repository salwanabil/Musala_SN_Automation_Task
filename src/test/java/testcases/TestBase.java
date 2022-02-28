package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utilities.Utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class TestBase {
    protected static WebDriver driver;
    public static Properties envConfig;
    WebDriverWait wait;

    @BeforeClass
    public void startDriver() throws IOException {
        //if (PropertiesReader.getProperty("config.properties","browserName") == "Chrome"){}
        if (Utility.fetchPropertyByValue("browserName").toString().equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        }
        else if (Utility.fetchPropertyByValue("browserName").toString().equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else {
            throw new RuntimeException("Browser type unsupported");
        }

        //Setting implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().window().maximize();

        //Setting WebDriverWait with max timeout value of 20 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    @BeforeMethod
    public void loadBaseUrl() throws IOException {
        driver.get(Utility.fetchPropertyByValue("baseProductionURL").toString());
    }

    @AfterMethod
    public void screenshotAndDeleteCookies(ITestResult testResult) throws IOException {
        //Taking screenshot in case of failure
        if(testResult.getStatus() == ITestResult.FAILURE){
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) +  ".jpg"));
        }

        //Deleting cookies
        driver.manage().deleteAllCookies();
    }

    //
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
