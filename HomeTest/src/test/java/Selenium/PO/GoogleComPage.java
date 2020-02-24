package Selenium.PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleComPage extends PageObject{

    GoogleComPage(WebDriver chromeDriver) {
        super(chromeDriver);
    }

    public void googleSearchBy(String text){
        WebElement input = findELement(Locators.googleSearchInput);
        input.click();
        input.sendKeys(text);
        findELement(Locators.googleSubmitSearchButton).click();
    }
}
