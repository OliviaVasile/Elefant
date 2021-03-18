package pageObject;

import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public String getResult ( ) {
        return SeleniumUtils.waitForGenericElement(driver , succes , 5).getText();

    }

    public boolean verifyElementPresent ( ){
        driver.findElement(By.xpath("//span[contains(text(),'Aparat pentru clătite Joy a fost adaugat in cos.')]"));
        return true;
    }

    public String numberOfProducts(){

        String result = driver.findElement(By.xpath("//input[@id='qty']")).getAttribute("value");
        System.out.println("cantitatea este " + result);
        return result;

    }
//int iTest = Integer.valueOf(strTest);

    public Integer numberOfProductsAfterPlus(){

        String result = driver.findElement(By.xpath("//input[@id='qty']")).getAttribute("value");
        int iresult = Integer.valueOf(result);
        System.out.println("cantitatea este " + iresult);
        return iresult;

    }

    public String productTitle(){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='cart-desktop']//a[@title='Vizualizare rapida'][contains(text(),'Aparat pentru clătite Joy')]")));

        String result = driver.findElement(By.xpath("//div[@class='cart-desktop']//a[@title='Vizualizare rapida'][contains(text(),'Aparat pentru clătite Joy')]")).getText();
        return result ;

    }

}


