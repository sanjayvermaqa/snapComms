package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import tests.BaseClass;
import utilities.GeneralFunctions;
import utilities.Logging;
import utilities.Constants;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class HomePage extends BaseClass {

    public HomePage (WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    //*[@id="main_nav"]
    @FindBy(xpath = "//*[@id='navbar_top']/div/nav/a/img") public WebElement lnkTitle;
    @FindBy(xpath = "//*[@id='main_nav']/*") public List<WebElement> navbarMain;
    @FindBy(xpath = "//div[@class='row tablet-hide']") public List<WebElement> footerList;

    //*[@id="hs_cos_wrapper_module_163302975809334"]/div[1]/div/div[1]/div[2]/div/a[1]
    //*[@id="hs_cos_wrapper_module_163302975809334"]/div[1]/div/div[1]
    @FindBy(name = "login") public WebElement inpLoginName;
    @FindBy(name = "password") public WebElement inpPassword;
    @FindBy(xpath = "//button[@type='submit'][text()='Login']") public WebElement btnLogin;
    @FindBy(xpath = "//a[@class='nav-link'][text()='Logout']") public WebElement lnkLogout;
    @FindBy(xpath = "//*[contains(text(), 'Sign in')]") public WebElement lnkSignIn;
    @FindBy(xpath = "//a[@class='signup-btn button button-wide']") public WebElement lnkSignUp;


    public void mainMenu(){
        //Create hash map to store elements from navbar main menu
        Constants.hmMainMenu = new HashMap<>();
        for (WebElement webElement : navbarMain) {
            List<WebElement> webElement2 = webElement.findElements(By.xpath("*"));
            for (WebElement element : webElement2) {
                if (element.getText()!=""){
                Constants.hmMainMenu.put(element.getText(), element);}
                /*if (element.getText().contains("Platform")) {
                    hmMainMenu.put("Platform", element);
                } else if (element.getText().contains("Solutions")) {
                    hmMainMenu.put("Solutions", element);
                } else if (element.getText().contains("Templates")) {
                    hmMainMenu.put("Templates", element);
                } else if (element.getText().contains("Pricing")) {
                    hmMainMenu.put("Pricing", element);
                } else if (element.getText().contains("Resources")) {
                    hmMainMenu.put("Resources", element);
                } else if (element.getText().contains("About")) {
                    hmMainMenu.put("About", element);
                } else if (element.getText().contains("Free")) {
                    hmMainMenu.put("FreeTrail", element);
                } else if (element.getText().contains("Login")) {
                    hmMainMenu.put("Login", element);
                } else if (element.getText().contains("Search")) {
                    hmMainMenu.put("Search", element);
                }*/
            }
        }

        //Log found values
        Iterator<Map.Entry<String, WebElement>> iterator = Constants.hmMainMenu.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry me2 = (Map.Entry) iterator.next();
            Logging.info("Main menu found - "+ me2.getKey());
        }
        megaMenu();
    };

    public void megaMenu(){
        //Create hash map to store elements from menus under each navbar item
        Constants.hmPlatformMenu = new HashMap<>();
        Constants.hmSolutions = new HashMap<>();
        Constants.hmResources = new HashMap<>();
        Constants.hmAbout = new HashMap<>();

        Iterator<Map.Entry<String, WebElement>> iterator = Constants.hmMainMenu.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry me2 = (Map.Entry) iterator.next();
            WebElement primaryElement = (WebElement) me2.getValue();
            GeneralFunctions.mouseHoverAction(primaryElement);
            List<WebElement> list = primaryElement.findElements(By.tagName("li"));
            for (WebElement element : list) {
                if (primaryElement.getSize() != null) {
                    if (primaryElement.getText().contains("Platform")) {
                        Constants.hmPlatformMenu.put(element.getText(), element);
                        Logging.info("Found this in hashmap- " + Constants.hmPlatformMenu.get(element.getText()));
                    } else if (primaryElement.getText().contains("Solutions")) {
                        Constants.hmSolutions.put(element.getText(), element);
                    } else if (primaryElement.getText().contains("Resources")) {
                        Constants.hmResources.put(element.getText(), element);
                    } else if (primaryElement.getText().contains("About")) {
                        Constants.hmAbout.put(element.getText(), element);
                    }
                    Logging.info("Mega menu links found - " + element.getText());
                }
            }
        }


       /* Iterator<Map.Entry<String, WebElement>> iterator2 = hmPlatformMenu.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry me2 = (Map.Entry) iterator2.next();
            WebElement ele = (WebElement) me2.getValue();
                    System.out.println(ele.getText());
                    System.out.println("Key3: "+me2.getKey() + " & Value: " + me2.getValue());
        }*/

    }

    public void footerMenu(){
        Constants.hmFooterMenu = new HashMap<>();
        for (WebElement element: footerList){
            if(element.getText()!="") {
                List<WebElement> linkList = element.findElements(By.tagName("a"));
                for (WebElement link: linkList){
                    Constants.hmFooterMenu.put(link.getText(),link);
                }

            }
        }
        Iterator<Map.Entry<String, WebElement>> iterator = Constants.hmMainMenu.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry me2 = (Map.Entry) iterator.next();
            WebElement primaryElement = (WebElement) me2.getValue();
            GeneralFunctions.mouseHoverAction(primaryElement);
            List<WebElement> list = primaryElement.findElements(By.tagName("li"));
            for (WebElement element: list){
                Constants.hmPlatformMenu.put(element.getText(), element);
            }
        }
    }
    /*
     * Method to validate page elements
     */
    SoftAssert softAssert = new SoftAssert();
    public void elementValidations() throws Exception {
        try {
            Logging.info("Validating elements on the page");
            softAssert.assertEquals(lnkSignUp.isEnabled(), "SIGN UP link does not exists");

        } catch (Exception e) {
            throw new Exception("Unable to continue with test due to - " + e.getMessage());
        }
    }

    /*
     * Method to click on Register button
     */
    public void clickOnSignUp() throws Exception{
        try{
            lnkSignIn.click();
            PageFactory.initElements(driver,this);
            lnkSignUp.click();
            GeneralFunctions.waitForPageLoad();
        }catch (Exception e){
            throw new Exception("Unable to click on SIGN UP link due to error - "+ e.getMessage());
        }
    }
}
