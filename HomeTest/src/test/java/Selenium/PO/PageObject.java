package Selenium.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageObject {
    private WebDriver chromeDriver;

    PageObject(WebDriver chromeDriver){
        this.chromeDriver=chromeDriver;
    }

    public WebElement findELement(By by){
        return chromeDriver.findElement(by);
    }

    public List<WebElement> findELements(By by){
        return chromeDriver.findElements(by);
    }
}
