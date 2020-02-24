package Selenium.PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YandexRuPage extends PageObject{

    YandexRuPage(WebDriver chromeDriver) {
        super(chromeDriver);
    }

    public void yandexSearchBy(String text){
        WebElement input = findELement(Locators.yandexSearchInput);
        input.click();
        input.sendKeys(text);
        findELement(Locators.yandexSearchButton).click();
    }
}
