package pageObject;

import models.searchModel;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductPage {
    WebDriver driver;
    WebDriverWait wait;

    public static final String WARRANTY_NO = "span[class='exponea-close-warranty']";

    @FindBy(how = How.CSS, using = "div[class='product-name js-gtm-data'] h1")
    WebElement productTitle;

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

    @FindBy(how = How.XPATH, using = "//div[@class='exponea-banner23-content']")
    WebElement modalGarantie;

    @FindBy(how = How.CSS, using = ProductPage.WARRANTY_NO)
    WebElement nuMultumesc;

//    @FindBy(how = How.ID, using ="super_attribute[174]")
//    WebElement color;

    By color = By.id("super_attribute[174]");
    By notInStock = By.xpath("//div[@class='add-to-cart-phone-text out-of-stock']");


    public ProductPage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver , 15);
        PageFactory.initElements(this.driver , this);
    }

    public void openProductPage (String hostname) {
        System.out.println("Open the next url:" + hostname);
        driver.get(hostname);
    }

    public String productTitle ( ) {
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='product-name js-gtm-data'] h1")));
        String title = driver.findElement(By.cssSelector("div[class='product-name js-gtm-data'] h1")).getText();
//        String title = (String)((JavascriptExecutor)driver).executeScript("getElementsByTagName('h1')[0];");
        System.out.println("titlul produsului este" + title);
        return title;
    }

    public String priceValue ( ) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("price")));
        String result = driver.findElement(By.className("price")).getText().split(" ")[0];
        System.out.println("pretul produsului este " + result);
        return result;

    }

    public void addToCart ( ) {
        SeleniumUtils.jsExecute(driver , By.xpath("//button[@title='Adauga in cos']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[class='exponea-close-warranty']")));
        nuMultumesc.click();

    }

    public void addQToCart (String qty , String mesaj) {
        cantitate.clear();
        cantitate.sendKeys(qty);
        SeleniumUtils.jsExecute(driver , By.xpath("//button[@title='Adauga in cos']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[class='exponea-close-warranty']")));
        nuMultumesc.click();


    }


    public void addPlusCart ( ) {

        SeleniumUtils.jsExecute(driver , By.id("add1"));
        SeleniumUtils.jsExecute(driver , By.xpath("//button[@title='Adauga in cos']"));

    }

    public boolean checkNotInStock ( ) {

        try {
            driver.findElement(notInStock);
            System.out.println("Produsul nu este momentan pe stoc. ");
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean checkAddButton ( ) {

        try {
            driver.findElement(notInStock);
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }


    }

    public String firstProductTitle ( ) {

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='cart-item-row cart-mobile']//h2[@class='product-name js-gtm-data']")));

        String result = driver.findElement(By.xpath("//div[@class='cart-item-row cart-mobile']//h2[@class='product-name js-gtm-data']")).getText();
        return result;

    }


}






