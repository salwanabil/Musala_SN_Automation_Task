package testcases;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CompanyPage;
import pages.MainPage;

public class CompanyTest extends TestBase {

    MainPage mainObject ;
    CompanyPage companyObject;


    @Test
    public void testCase2_NavigateToCompanyPageAndClicksFacebookIconThenVerifyIt(){
        mainObject = new MainPage(driver);
        companyObject = new CompanyPage(driver);

        mainObject.acceptCookies();
        mainObject.navigateToCompanyLink();

        String actualCompanyPageTitle = driver.getCurrentUrl();
        String expectedPageTitle = "https://www.musala.com/company/";

        Assert.assertEquals(actualCompanyPageTitle,expectedPageTitle);
        mainObject.scrollToBottom();

        List<WebElement> listElem = driver.findElements(By.xpath("//h2[normalize-space()='Leadership']"));
        if (listElem.size() >0){
            System.out.println("Leadership Section Is Displayed Successfully");
        }else {
            System.out.println("Leadership Section Is Not Found");
        }

        companyObject.clickFacebookLinkFromCompanyPage();

        mainObject.SwitchBetweenTaps(driver,1);

        //Verify that company page on Facebook correct URL
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.facebook.com/MusalaSoft?fref=ts"));
        //This verifies the Musala Soft profile picture appears on the new page
        Assert.assertTrue(driver.findElement(By.xpath("//a[@aria-label='Musala Soft profile photo']")).isDisplayed());

    }
}
