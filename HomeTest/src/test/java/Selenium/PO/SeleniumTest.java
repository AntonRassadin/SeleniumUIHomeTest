package Selenium.PO;

import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SeleniumTest extends WebDriverSettings{

    @Test
    public void gladiolusTest(){
        System.out.println("Run gladiolus test");
        chromeDriver.get(Links.yandex);
        YandexRuPage page = new YandexRuPage(chromeDriver);
        page.yandexSearchBy("Гладиолус");
        List<WebElement> itemsList = page.findELements(Locators.yandexSearchWikiGladiolusLink);   //ссылки https://ru.wikipedia.org/wiki/Шпажник в результатах поиска
        boolean check = false;
        if(itemsList.size()>0){
            check = true;
         }

        Assert.assertTrue("Ссылки на википедию нет", check);
    }

    @Test
    public void googleOpenRuTest(){
        System.out.println("Run googleOpenRuTest test");
        chromeDriver.get(Links.google);
        GoogleComPage page = new GoogleComPage(chromeDriver);
        page.googleSearchBy("Открытие");
        WebElement link = page.findELement(Locators.googleItemOpenRuFromSearch); //поиск ссылки https://www.open.ru/ в результатах поиска
        link.click();
        List<Double> exchangeRates = page.findELements(Locators.openRuExchangeTableRates).stream()
                .map(x->Double.parseDouble(x.getText().replace(",",".")))
                        .collect(Collectors.toList());
        Assert.assertTrue(exchangeRates.get(1) > exchangeRates.get(0));
        Assert.assertTrue(exchangeRates.get(3) > exchangeRates.get(2));
    }

    @Test
    public void pageFactoryYMarket() throws InterruptedException {
        YandexMarketPagePF page = PageFactory.initElements(chromeDriver, YandexMarketPagePF.class); // Запустить Chrome Открыть яндексмаркет
        page.allCategoriesBtn.click(); //Нажимаем кнопку категории
        page.mobilePhoneLink.click();  //Переходим по ссылке Мобильные телефоны
        page.getAppleCheckBox().click();
        Thread.sleep(4000);
        AtomicInteger a = new AtomicInteger(1);
        for (int i=0; i<10; i++){
            List<WebElement> nextButtonList = page.getNextButtonList();
            List<WebElement> brandsList = page.getBrandNames();
            brandsList.forEach(x -> Assert.assertEquals("APPLE", x.getText()));

            if(nextButtonList.size()>0) {
                nextButtonList.get(0).click();
                Thread.sleep(2000);
            }else
                break;
        }
    }
}
