package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class FilterPage {
    WebDriver driver;
    WebDriverWait wait;


    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Electrocasnice bucătărie')]")
    WebElement electrocasnice;
//    @FindBy(how = How.ID, using ="//*[@id=\"footerJsContainer\"]")
//            WebElement container;
    @FindBy(how = How.XPATH, using = "//*[@id=\"narrow-by-list2\"]/dd/ol/li[1]/a")
    WebElement micdejun;
    @FindBy(how = How.XPATH, using = "//*[@id=\"narrow-by-list\"]/li[2]/label")
    WebElement marca;
    @FindBy(how = How.XPATH, using = "//div[@class='top-filters']//p[@class='amount'][normalize-space()='7 Articol(e)']")
    WebElement noOfItems;

    public FilterPage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver , 5);
        PageFactory.initElements(this.driver , this);

    }


    public void openFilterPage (String hostname) {
        System.out.println("Open the next url:" + hostname);
        driver.get(hostname);
    }


    public String filterResults ( ) {
//        Actions act = new Actions(driver);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Electrocasnice bucătărie')]")));

        electrocasnice.click();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"footerJsContainer\"]")));
//        act.moveToElement(container);

        wait.until(ExpectedConditions.elementToBeClickable(micdejun));
//        act.moveToElement(micdejun);

        micdejun.click();

        wait.until(ExpectedConditions.elementToBeClickable(marca));
        marca.click();
        WebElement delimano = driver.findElement(By.xpath("//article[@class='ac-small']//a[contains(text(),'Delimano')]"));
      wait.until(ExpectedConditions.elementToBeClickable(delimano));
        delimano.click();

        System.out.println("Rezultatul afisat este:" + noOfItems.getText());
        String result = driver.findElement(By.xpath("//div[@class='top-filters']//p[@class='amount'][normalize-space()='7 Articol(e)']")).getText().split(" ")[0];
        System.out.println(result);
        return result;

    }

    public String numberOfProducts ( ) {

        List<WebElement> data = driver.findElements(By.className("view-prod"));
        System.out.println("total number of products  == " + data.size());

        return String.valueOf(data.size());
    }

}


