package Selenium.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class YandexMarketPagePF {
    private WebDriver chromeDriver;
    private WebDriverWait wait;
    private String url = "https://market.yandex.ru/";
    public YandexMarketPagePF(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        wait = new WebDriverWait(chromeDriver, 10);
        chromeDriver.get(url);
    }

    @FindBy(how= How.CSS, css = "div.PXL2nleaah")
    WebElement allCategoriesBtn; //Кнопка "Все категории"

    @FindBy(how= How.XPATH, using = "//a[contains(text(),'Мобильные телефоны')]")
    WebElement mobilePhoneLink; //Кнопка "Мобильные телефоны"

    @FindBy(how= How.XPATH, using = "//div[@class='LhMupC0dLR']/span")
    private WebElement appleCheckBox; //Чекбокс фильтра "Apple"

    @FindAll(@FindBy(how= How.XPATH, using = "//div[@class='n-snippet-cell2__brand-name']"))
    private List<WebElement> brandNames; //Имена брендов
    // //a[contains(@class,'n-pager__button-next')]

    @FindBy(how= How.XPATH, using = "//a[contains(@class,'n-pager__button-next')]")
    WebElement nextButton; //Кнопка след страницы

    @FindAll(@FindBy(how= How.XPATH, using = "//a[contains(@class,'n-pager__button-next')]"))
    private List<WebElement> nextButtonList; //Кнопка след страницы в списке

    public WebElement getAppleCheckBox() {
        return wait.until(ExpectedConditions.elementToBeClickable(appleCheckBox));
    }

    public List<WebElement> getBrandNames() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='n-snippet-cell2__brand-name']")));
        return brandNames;
    }

    public List<WebElement> getNextButtonList() {
        return nextButtonList;
    }
}
