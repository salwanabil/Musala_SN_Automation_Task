package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CompanyPage extends PageBase {
    @FindBy(xpath = "//h2[contains(text(),'Leadership')]")
    public WebElement labelLeaderShip;

    @FindBy(xpath = "//*[@class='musala musala-icon-facebook']")
    public WebElement linkFacebook;

    public CompanyPage(WebDriver driver) {
        super(driver);
    }

    public void clickFacebookLinkFromCompanyPage(){
        waitUntilElementVisiable(linkFacebook);
        clickButton(linkFacebook);
    }
}
