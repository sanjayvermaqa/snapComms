package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tests.BaseClass;

public class PathHelper extends BaseClass {

    public static WebElement findByID(String value){
        WebElement element = driver.findElement(By.id(value));
        return element;
    }

    public static WebElement findByText(String value){
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '"+value+"')]"));
        return element;
    }
//
//    public static boolean elementExists(WebElement element){
//        if (driver.findElement(By.))
//    }

}
