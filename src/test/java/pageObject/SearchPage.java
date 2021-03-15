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

public class SearchPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.ID, using = "addsearch-results-input")
    WebElement searchboxInput;
    //        @FindBy(how = How.ID, using = "notfoundtext")
//    WebElement noResult;
    @FindBy(how = How.XPATH, using = "//label[@class='addsearch-results-input-icon']")
    WebElement magnGlass;

    By noResult = By.id("notfoundtext");

    public SearchPage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver , 15);
        PageFactory.initElements(this.driver , this);
    }

    public void openSearchPage (String hostname) {
        System.out.println("Open the next url:" + hostname);
        driver.get(hostname);

    }

    public String getResult ( ) {
        return SeleniumUtils.waitForGenericElement(driver , noResult , 15).getText();

    }


    public void search (String keyword , String result) {
        searchboxInput.clear();
        searchboxInput.sendKeys(keyword);
        magnGlass.click();
//        wait.until(ExpectedConditions.elementToBeClickable(noResult)).click();

    }

    public boolean verifyElementAbsent ( ){

        boolean visible = driver.findElement(By.id("notfoundtext")).isDisplayed();
        boolean result = !visible;
        System.out.println(result);
        return result;
    }
}

