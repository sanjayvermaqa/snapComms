package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import tests.BaseClass;
import utilities.GeneralFunctions;
import utilities.Logging;


public class RegisterPage extends BaseClass {

    public RegisterPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(text(), 'Registration')]") public WebElement lblTitle;
    @FindBy(id = "username") public WebElement inpUsername;
    @FindBy(id = "new_password") public WebElement inpPassword;
    @FindBy(id = "password_confirm") public WebElement inpConfirmPassword;
    @FindBy(id = "email") public WebElement inpEmail;
    @FindBy(id = "email_confirm") public WebElement inpConfirmEmail;
    @FindBy(name = "submit") public WebElement btnRegister;

    /*
     * Method to validate page elements
     */
    SoftAssert softAssert = new SoftAssert();
    public void elementValidations() throws Exception {
        try {
            Logging.info("Validating elements on the page");
            softAssert.assertEquals(lblTitle.isDisplayed(), "Title field does not exists");
            softAssert.assertEquals(inpUsername.isDisplayed(), "Username field does not exists");
            softAssert.assertTrue(inpPassword.isDisplayed(), "Password field does not exists");
            softAssert.assertTrue(inpConfirmPassword.isDisplayed(), "Confirm Password field does not exists");
            softAssert.assertTrue(inpEmail.isDisplayed(), "Email field does not exists");
            softAssert.assertTrue(inpConfirmEmail.isDisplayed(), "Confirm Email field does not exists");
            softAssert.assertTrue(btnRegister.isDisplayed(), "Register button does not exists");

        } catch (Exception e) {
            throw new Exception("Unable to continue with test due to - " + e.getMessage());
        }
    }

    /*
     * Method to click on Register button
     */
    public void clickOnRegister() throws Exception{
        try{
            btnRegister.click();
            GeneralFunctions.waitForPageLoad();
        }catch (Exception e){
            throw new Exception("Unable to click on Register button due to error - "+ e.getMessage());
        }
    }
}
