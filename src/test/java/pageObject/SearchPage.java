package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;


//div[@class='std']
//*[@id="addsearch-results-input"]

public class SearchPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.ID, using = "addsearch-results-input")
    WebElement searchboxInput;

    @FindBy(how = How.XPATH, using = "//label[@class='addsearch-results-input-icon']")
    WebElement magnGlass;

    By noResult = By.id("notfoundtext");
    By tabelRezultate = By.xpath("//div[@class='main-container col1-layout']");

    public SearchPage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver , 15);
        PageFactory.initElements(this.driver , this);
    }

    public void openSearchPage (String hostname) {
        System.out.println("Open the next url:" + hostname);
        driver.get(hostname);

    }

    public void search (String keyword , String getResult) {
        searchboxInput.clear();
        searchboxInput.sendKeys(keyword);
        SeleniumUtils.jsExecute(driver , By.xpath("//label[@class='addsearch-results-input-icon']"));
        SeleniumUtils.waitForGenericElement(driver , tabelRezultate , 10);

    }

    public String getResult ( ) {
        return SeleniumUtils.waitForGenericElement(driver , noResult , 10).getText();

    }

    public boolean verifyMissingMessage ( ) {
        SeleniumUtils.verifyElementAbsent(driver , noResult);
        return true;
    }

    public void pickElement ( ) {
        SeleniumUtils.clickOn(driver , tabelRezultate);
    }

}

