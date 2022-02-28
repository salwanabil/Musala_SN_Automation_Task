package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends PageBase{

    @FindBy(id = "cf-1")
    public WebElement txtName;
    @FindBy(name = "your-email")
    public WebElement txtEmail;
    @FindBy(xpath = "//input[@id='cf-3']")
    public WebElement txtMobile;
    @FindBy(id = "cf-4")
    public WebElement txtSubject;
    @FindBy(xpath = "//textarea[@id='cf-5']")
    public WebElement txtYourMessage;
    @FindBy(xpath = "//input[@type='submit'][@value='Send']")
    public WebElement btnSend;
    @FindBy(xpath = "//span[contains(text(),'The e-mail address entered is invalid.')]")
    public static WebElement invalidEmailErrorLabel;



    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public void fillContactUsForm(String Name, String Email , String Mobile, String Message, String Subject){
        setText(txtName ,Name);
        setText(txtEmail , Email);
        setText(txtMobile , Mobile);
        setText(txtSubject , Message);
        setText(txtYourMessage , Subject);

        clickButton(btnSend);
    }

}
