package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class PageBase{
    protected static WebDriver driver;
    public Select select;
    public Actions action;
    public static String currentWindowID = null;
    public static JavascriptExecutor jse;

    // create constructor
    public PageBase(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    // Wait for Element to be visiable.
    public static boolean waitUntilElementVisiable(WebElement element)
    {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(element));
            // element found, return true
            return true;
        }catch (Exception ex){
            // element not found, return false
            return false;
        }
    }

    // Method to Click Buttons
    protected static void clickButton(WebElement button) {
        button.click();
    }

    // Method to send Keys
    protected static void setText(WebElement textElement, String value) {
        waitUntilElementVisiable(textElement);
        textElement.clear();
        textElement.sendKeys(value);
    }

    // Method to Press Enter
    public void KeyPressEnter(WebElement webElement) {
        webElement.sendKeys(Keys.ENTER);
    }

    // Method to scroll down
    public void scrollToBottom(){
        jse.executeScript("scrollBy(0,1500)");
    }

    // Method to handle windows
    public void WindowHandling(WebDriver driver){
        currentWindowID = driver.getWindowHandle();
        for (String windowID : driver.getWindowHandles()){
            driver.switchTo().window(windowID);
        }
    }

    // Method to switch between opened tabs
    public void SwitchBetweenTaps(WebDriver driver , Integer tabIndex){
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabIndex));
    }

    // Method to confirmCookies
    public void cookieHandle(WebDriver driver) {

        WebElement elementCookie = driver.findElement(By.xpath("//*[@id='wt-cli-accept-all-btn']"));
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", elementCookie);
    }

    // Method to select from Drop down
    public void statusDropDownList(WebDriver driver, WebElement selectWebElement,String selectItem){
        Select dropdwn = new Select(selectWebElement);
        dropdwn.selectByVisibleText(selectItem);
    }
}
