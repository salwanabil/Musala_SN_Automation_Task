package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends PageBase{
    @FindBy(xpath="//a[@id='wt-cli-accept-all-btn']")
    public WebElement acceptCookieBtn;

    @FindBy(xpath = "//button/span[contains(text(),'Contact us')]")
    //@FindBy(xpath = "//a[@href='#contact_form_pop']")
    public WebElement linkContactUs;

    @FindBy(xpath = "//ul[@id='menu-main-nav-1']//a[@class='main-link'][normalize-space()='Company']")
    public WebElement linkCompany;

    public MainPage(WebDriver driver) {
        super(driver);
        jse = (JavascriptExecutor) driver;
    }
    /// This func : accepts website cookies
    public void acceptCookies(){
        waitUntilElementVisiable(acceptCookieBtn);
        clickButton(acceptCookieBtn);
    }
    /// This func : clicks on Contact Us Link
    public void clickContactUsLink()
    {
        waitUntilElementVisiable(linkContactUs);
        scrollToBottom();
        clickButton(linkContactUs);
    }

    public void navigateToCompanyLink()
    {
        waitUntilElementVisiable(linkCompany);
        clickButton(linkCompany);
    }
}
