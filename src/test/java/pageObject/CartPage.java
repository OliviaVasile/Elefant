package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;

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

    public String getResult ( ) {
        return SeleniumUtils.waitForGenericElement(driver , succes , 5).getText();

    }

    public boolean verifyElementPresent ( ) {
        driver.findElement(By.xpath("//span[contains(text(),'Aparat pentru clătite Joy a fost adaugat in cos.')]"));
        return true;
    }

    public String numberOfProducts ( ) {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("qty")));

        String result = driver.findElement(By.id("qty")).getAttribute("value");
        System.out.println("cantitatea este " + result);
        return result;

    }


    public Integer numberOfProductsAfterPlus ( ) {

        return SeleniumUtils.turnToInteger(numberOfProducts());

    }

    public void deleteCart ( ) {


        wait.until((ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"updateForm\"]/div[2]/div[1]/div/div[2]/div/div[2]/div/div/div/div[1]/div/div/div[3]/ul/li/a"))));
//    continue_button.click()
//    WebElement delete = driver.findElement(By.xpath("ul[class ='cart-links']/ul/li/a/span"));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(delete);
        deleteCart.click();
//   SeleniumUtils.jsExecute(driver,By.xpath("a[title='Sterge'] span"));

    }

    public boolean checkContinuaAfterDeleteCart ( ) throws Exception {

        try {
            driver.findElement(By.xpath("//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@title='Continuă']")).isDisplayed();

            return false;
        } catch (NoSuchElementException e) {
            return true;

        }
    }

    public void spreCheckout ( ) {
        Actions actions = new Actions(driver);
//        actions.moveToElement(continuaButton).click().build().perform();
//        SeleniumUtils.jsExecute(driver , By.xpath("//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@title='Continuă']"));

    }

}



