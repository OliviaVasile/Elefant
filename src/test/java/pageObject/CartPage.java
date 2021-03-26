package pageObject;

import org.openqa.selenium.*;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;

import java.text.ParseException;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.ID, using = "qty")
    WebElement number;
    @FindBy(how = How.XPATH, using = "//*[@id=\"updateForm\"]/div[2]/div[1]/div/div[2]/div/div[2]/div/div/div/div[1]/div/div/div[3]/ul/li/a")
    WebElement deleteCart;
    @FindBy(how = How.XPATH, using = "//input[@id='minus1']")
    WebElement minus;
    @FindBy(how = How.XPATH, using = "//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@title='Continuă']")
    WebElement continuaButton;

    By succes = By.xpath("//span[contains(text(),'Aparat pentru clătite Joy a fost adaugat in cos.')]");

    public CartPage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver , 5);
        PageFactory.initElements(this.driver , this);
    }

    public void openCart (String hostname) {
        System.out.println("Open the next url:" + hostname);
        driver.get(hostname + "/checkout/cart/");

    }

//    public String getResult ( ) {
//        return SeleniumUtils.waitForGenericElement(driver , succes , 5).getText();
//
//    }


//    public String productTitle ( ) {
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'cart-item-row cart-mobile')]//h2[contains(@class,'product-name js-gtm-data')]")));
//        String result = driver.findElement( By.cssSelector("div[class='cart-desktop'] a[title='Vizualizare rapida']")).getText();
//        System.out.println("titlul produsului in cos este" + result);
//        return result;
//
//    }

    public String numberOfProducts ( ) {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("qty")));

        String result = driver.findElement(By.id("qty")).getAttribute("value");
        System.out.println("cantitatea este " + result);
        return result;

    }

    public Integer numberOfProductsAfterPlus ( ) {

        return SeleniumUtils.turnToInteger(numberOfProducts());

    }

    public String priceValue ( ) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("price")));
        String result = driver.findElement(By.className("price")).getText().split(" ")[0];
        System.out.println("pretul produsului este " + result);
        return result;

    }

    public String totalProductPrice ( ) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[1]/div[3]/div/div[2]/div[2]/div[2]/div/form/div[2]/div[1]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div")));
        String pret = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[3]/div/div[2]/div[2]/div[2]/div/form/div[2]/div[1]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div")).getText().split(" ")[0];
        System.out.println("pretul total al produsului este " + pret);
//        System.out.println(Integer.parseInt(pret));
        return pret;
    }

//        public Double generalPriceDouble() throws ParseException {
//
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[1]/div[3]/div/div[2]/div[2]/div[4]/div[2]/div/div[2]/div/div[1]/div[2]/div/table/tfoot/tr/td[2]/strong/span")));
//            String genPret = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[3]/div/div[2]/div[2]/div[4]/div[2]/div/div[2]/div/div[1]/div[2]/div/table/tfoot/tr/td[2]/strong/span")).getText().split(" ")[0];;
//            System.out.println("pretul total al produsului este " + SeleniumUtils.turnToDouble(genPret));
//        return SeleniumUtils.turnToDouble(genPret);
//
//        }


    public void deleteCart ( ) {
        wait.until((ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"updateForm\"]/div[2]/div[1]/div/div[2]/div/div[2]/div/div/div/div[1]/div/div/div[3]/ul/li/a"))));

        deleteCart.click();


    }

    public boolean continuaAfterDeleteCart ( ) {

        try {
            driver.findElement(By.xpath("//*[@id=\"top\"]/body/div[3]/div[1]/div[3]/div/div[2]/div[2]/div[1]/ul/li[2]/button/span/span"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }

    }

//    public void spreCheckout ( ) {
//        Actions actions = new Actions(driver);
//        actions.moveToElement(continuaButton).click().build().perform();
//        SeleniumUtils.jsExecute(driver , By.xpath("//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@title='Continuă']"));

}





