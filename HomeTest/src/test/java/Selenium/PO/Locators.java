package Selenium.PO;

import org.openqa.selenium.By;

public class Locators {
    static By googleSearchInput = By.xpath("//input[@class='gLFyf gsfi'][@name='q']"); //googleSearchInput
    static By googleSubmitSearchButton = By.cssSelector("input.gNO89b[name='btnK']");      //googleSubmitSearchButton
    static By googleItemOpenRuFromSearch = By.cssSelector(".bkWMgd .g a[href='https://www.open.ru/']"); //https://www.open.ru from google search

    static By yandexSearchInput = By.cssSelector("#text");                             //yandexSearchInput
    static By yandexSearchButton = By.cssSelector(".search2__button button");          //yandexSearchButton
    static By yandexItemsFromSearch = By.cssSelector(".content__left li.serp-item");   //items from search
    static By yandexSearchWikiGladiolusLink = By.cssSelector(
            "li.serp-item a[href='https://ru.wikipedia.org/wiki/%D0%A8%D0%BF%D0%B0%D0%B6%D0%BD%D0%B8%D0%BA']"); //страница  https://ru.wikipedia.org/wiki/Шпажник в результатах поиска

    static By openRuExchangeTableRates = By.cssSelector(".main-page-exchange__table span.main-page-exchange__rate"); //Значения с таблицы с курсами валют open.ru
}

