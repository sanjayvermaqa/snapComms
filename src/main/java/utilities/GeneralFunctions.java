package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.HomePage;
import tests.BaseClass;

import static java.util.concurrent.TimeUnit.SECONDS;
import static utilities.Constants.*;

public class GeneralFunctions extends BaseClass {
    public static synchronized WebDriver InitialiseDriver(String browser) throws Exception {
        try {
            HashMap<String, Object> chromePrefs = null;
            ChromeOptions chromeOptions = null;

            if ("firefox".equals(browser)) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if ("internetExplorer".equals(browser)) {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            } else if ("chrome_headless".equals(browser)) {
                WebDriverManager.chromedriver().setup();
                chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "Downloads");
                chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("prefs", chromePrefs);
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
            } else {
                WebDriverManager.chromedriver().setup();
                chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "Downloads");
                chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("prefs", chromePrefs);
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return driver;
    }

    public static void loginUser () throws Exception {
        try{
            navigateToHomePage();
            HomePage homePage = new HomePage(driver);
            Logging.info("Login user with credentials as : - " +
                    "\n Login = " + fName + lName +
                    "\n Password = " + password);
            homePage.inpLoginName.sendKeys(loginName);
            homePage.inpPassword.sendKeys(password);
//            homePage.inpLoginName.sendKeys("abc2");
//            homePage.inpPassword.sendKeys("Xyz.123456");
            homePage.btnLogin.click();
        }catch (Exception e){
            throw new Exception("User login failed due to - " + e.getMessage());
        }
    }

    public static void logoutUser() throws Exception {
        try {
            navigateToHomePage();
            HomePage homePage = new HomePage(driver);
            Logging.info("Performing user logout");
            homePage.lnkLogout.click();
        }catch (Exception e){
            throw new Exception("User logout failed due to - " + e.getMessage());
        }
    }
    public static void waitForPageLoad(){
        driver.manage().timeouts().implicitlyWait(SECONDS.toSeconds(10), TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
//        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        ExpectedCondition<Boolean> expectedCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };

    }

    public static void pageRefresh(){
        Logging.info("Performing page refresh");
        driver.navigate().refresh();
        waitForPageLoad();
    }
    public static void navigateToHomePage(){
        Logging.info("Navigate to Home page");
        driver.navigate().to(Constants.url);
    }

    public static void navigateOnList(String option){
        switch (option){
            case "prev":
                Logging.info("Navigate to prev page");
                driver.findElement(By.linkText("«")).click();
                waitForPageLoad();
            case "number":
                Logging.info("Navigate to page number 3");
                driver.findElement(By.xpath("//my-pager//input[@type = 'text']")).sendKeys("3" + Keys.ENTER);
                waitForPageLoad();
            default:
                Logging.info("Navigate to next page");
                driver.findElement(By.linkText("»")).click();
                waitForPageLoad();
                break;
        }
    }
    public static void mouseHoverAction(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}
