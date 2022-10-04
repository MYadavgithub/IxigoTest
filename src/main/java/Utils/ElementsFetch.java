package Utils;

import PageClasses.BaseCLass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementsFetch {

    public WebElement getWebElement(String identifierType, String identifierValue){
        switch (identifierType){
            case "id":
                return BaseCLass.driver.findElement(By.id(identifierValue));
            case "classname":
                return BaseCLass.driver.findElement(By.className(identifierValue));
            case "tagname":
                return BaseCLass.driver.findElement(By.tagName(identifierValue));
            case "css":
                return BaseCLass.driver.findElement(By.cssSelector(identifierValue));
            case "xpath":
                return BaseCLass.driver.findElement(By.xpath(identifierValue));
            default:
                return null;
        }
    }

    public List<WebElement> getListWebElements(String identifierType, String identifierValue){
        switch (identifierType){
            case "id":
                return BaseCLass.driver.findElements(By.id(identifierValue));
            case "classname":
                return BaseCLass.driver.findElements(By.className(identifierValue));
            case "tagname":
                return BaseCLass.driver.findElements(By.tagName(identifierValue));
            case "css":
                return BaseCLass.driver.findElements(By.cssSelector(identifierValue));
            case "xpath":
                return BaseCLass.driver.findElements(By.xpath(identifierValue));
            default:
                return null;
        }
    }

}
