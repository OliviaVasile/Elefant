package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//button[@title='Adauga in cos']")
    WebElement addButton;
    @FindBy(how = How.ID, using = "qty")
    WebElement cantitate;
    @FindBy(how = How.ID, using = "add1")
    WebElement plus;
    @FindBy(how = How.ID, using = "minus1")
    WebElement minus;
    @FindBy(how = How.XPATH, using = "//select[@data-label='sWarranty']")
    WebElement garantie;
    @FindBy(how = How.XPATH, using = "//span[@class='exponea-close-warranty']")
    WebElement nuMultumesc;

    By succes = By.xpath("//span[contains(text(),'Aparat pentru clătite Joy a fost adaugat in cos.')]");



    public CartPage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver , 5);
        PageFactory.initElements(this.driver , this);
    }


    public void openCart (String hostname) {
        System.out.println("Open the next url:" + hostname);
        driver.get(hostname);

    }

    public void cartPage (String qty, String mesaj) {

        ViewProduct vp = new ViewProduct(driver);
        vp.openProduct();
        cantitate.clear();
        cantitate.sendKeys(qty);
        addButton.click();
        waitForGuarantee();
        nuMultumesc.click();

    }

    public String getResult ( ) {
        return SeleniumUtils.waitForGenericElement(driver , succes , 15).getText();

    }

    public void waitForGuarantee  ( ) {
        wait.until(ExpectedConditions.elementToBeClickable(nuMultumesc));
    }
}
