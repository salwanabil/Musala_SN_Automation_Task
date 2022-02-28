package testcases;

/*
 * Author : Salwa Nabil
 * email : salwa_ms1985@live.com
 */

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.MainPage;
import utilities.ExcelUtils;

public class ContactUsTest extends TestBase {
    MainPage mainObject ;
    ContactUsPage contactObject;
    ExcelUtils excelUtils;



    public static String excelFilePath = System.getProperty("user.dir") + "/TestData/TestData.xlsx";
    public static String excelSheetName= "ContactUsSheet";

    @Test (dataProvider= "getContactData")
    public void fillContactUs(String name, String email , String mobile, String message, String subject){
        //ExcelUtils.setExcelFile(excelFilePath,excelSheetName);

        mainObject = new MainPage(driver);
        contactObject = new ContactUsPage(driver);

        mainObject.acceptCookies();

        mainObject.clickContactUsLink();

        contactObject.fillContactUsForm(name, email,mobile,message,subject);
    }

    ///this method used for running test that takes data from Excel file"Data Driven Framework
    @Test
    public void testCase1_ValidateErrorTextForInvalidEmailFormat(String name, String email , String mobile, String message, String subject){
        //ExcelUtils.setExcelFile(excelFilePath,excelSheetName);

        mainObject = new MainPage(driver);
        contactObject = new ContactUsPage(driver);

        mainObject.acceptCookies();

        mainObject.clickContactUsLink();

        contactObject.fillContactUsForm(name, email,mobile,message,subject);
        Assert.assertTrue(contactObject.invalidEmailErrorLabel.getText().
                equalsIgnoreCase("The e-mail address entered is invalid."));
    }

    @DataProvider
    public Object[][] getContactData() throws Exception{
        excelUtils = new ExcelUtils();
        Object[][] testObjArray = excelUtils.getExcelData(excelFilePath,excelSheetName);

        return testObjArray;
    }


}
