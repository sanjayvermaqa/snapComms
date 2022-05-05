package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import utilities.Constants;
import utilities.GeneralFunctions;
import utilities.Logging;

public class RegisterUserTest extends BaseClass {
    HomePage homePage;
    RegisterPage registerPage;

    @BeforeTest
    public void setUp(){
        homePage = new HomePage(driver);
        homePage.mainMenu();
        homePage.footerMenu();
    }

    @Parameters({"link"})
    @Test
    public void testLink(String link){
        if (link!=null){
            WebElement webElement = null;
            //Find and validate provided link in main menu
            System.out.println("link param value as - "+link);
            //webElement = Constants.hmMainMenu.get(link);
            if (Constants.hmMainMenu.get(link)!=null) {
                webElement = Constants.hmMainMenu.get(link);
                Logging.info("Link for " + link + " found as " + webElement.getText());
            }else if (Constants.hmPlatformMenu.get(link)!=null){
                GeneralFunctions.mouseHoverAction(Constants.hmMainMenu.get("Platform"));
                webElement = Constants.hmPlatformMenu.get(link);
                //*[@id="megaMenus"]/li[1]/a
                Logging.info("Link for " + link + " found as " + webElement.getText());
            }else if (Constants.hmSolutions.get(link)!=null) {
                webElement = Constants.hmSolutions.get(link);
                GeneralFunctions.mouseHoverAction(Constants.hmMainMenu.get("Solutions"));
                Logging.info("Link for " + link + " found as " + webElement.getText());
            } else if (Constants.hmResources.get(link)!=null){
            webElement = Constants.hmResources.get(link);
            GeneralFunctions.mouseHoverAction(Constants.hmMainMenu.get("Resources"));
            Logging.info("Link for " + link + " found as " + webElement.getText());
            }else if (Constants.hmAbout.get(link)!=null){
                webElement = Constants.hmAbout.get(link);
                GeneralFunctions.mouseHoverAction(Constants.hmMainMenu.get("About"));
                Logging.info("Link for " + link + " found as " + webElement.getText());
            }
            webElement.click();
            System.out.println(link);
            System.out.println("found it");

            //Find and validate provided link in footer menu

        }else {
            System.out.println("link param value as - "+link);
        }
    }

    /*
    *Below test is to do the page validation tests
    */
    /*@BeforeTest
    public void pageValidation() throws Exception {
        try {
            homePage = new HomePage(driver);

            homePage.clickOnSignUp();

            registerPage = new RegisterPage(driver);

            int loadTime = ((Long) ((JavascriptExecutor) driver).executeScript(
                    "return performance.timing.loadEventEnd - performance.timing.navigationStart;")).intValue();
            Logging.info("Page took - " + (loadTime / 1000) + "sec to load");

            registerPage.elementValidations();

            Logging.info(String.valueOf(TestResult.TEST_PASSED));
        } catch (Exception e) {
            Logging.error(String.valueOf(TestResult.TEST_FAILED));
            throw new Exception ("Test was unable to complete due to - " + e.getMessage());
        }
    }

    @Test
    public void negativeTests() throws Exception{
        try{
            //Condition: All empty fields

            //Condition: Empty username, rest all valid values

            //Condition: Username, rest of the fields empty

            //Condition: Username, password, rest of the fields empty

            //Condition: Username, password, confirm password, rest of the fields empty

            //Condition: Username, password, confirm password, email, empty confirm email field

            //Condition: Existing Username, password, confirm password, email, confirm email field

            //Condition: Username, password, different confirm password, email, confirm email field

            //Condition: Username, password, confirm password, email, different confirm email field

            //Condition: Username, password, confirm password, invalid email, invalid confirm email field
            String string = fName + lName;
            Logging.info("Fill all the values in the registration form as : - " +
                    "\n Username = " + string +
                    "\n Password = "+ string +
                    "\n Confirm Password = "+ string +
                    "\n Email = " + string +
                    "\n Confirm Email = " + string);
            fillFields(string,string,string,string,string);

            registerPage.clickOnRegister();

            Logging.info("Check if the appropriate error msg is displayed");
            Assert.assertTrue(PathHelper.findByText("The e-mail address you entered is invalid.").isDisplayed(),"Appropriate error message not displayed");

            Logging.info("All the values input done");

        }catch (Exception e){
            throw new Exception("Test was unable to complete due to - " + e.getMessage());
        }
    }

    private void fillFields(String username, String password, String confirmPassword, String email, String confirmEmail){
        registerPage = new RegisterPage(driver);
        if (!(username == null)){ registerPage.inpUsername.sendKeys(username);}
        if (!(password == null)){ registerPage.inpPassword.sendKeys(password);}
        if (!(confirmPassword == null)){ registerPage.inpConfirmPassword.sendKeys(confirmPassword);}
        if (!(email == null)){ registerPage.inpEmail.sendKeys(email);}
        if (!(confirmEmail == null)){ registerPage.inpConfirmEmail.sendKeys(confirmEmail);}
    }*/
}
