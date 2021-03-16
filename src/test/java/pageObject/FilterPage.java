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

import java.util.ArrayList;
import java.util.List;

public class FilterPage {
    private WebDriver driver;
    WebDriverWait wait;


    //span[contains(text(),'Electrocasnice bucătărie')]
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Electrocasnice bucătărie')]")
    WebElement electrocasnice;
    @FindBy(how = How.XPATH, using = "//li[@class='cat_sb 6621 position1']//ul//li//a[@class=' level1 '][normalize-space()='Aparate pentru Mic Dejun']")
    WebElement micdejun;
    @FindBy(how = How.XPATH, using = "//*[@id=\"narrow-by-list\"]/li[2]/label")
    WebElement marca;
    @FindBy(how = How.XPATH, using = "//div[@class='top-filters']//p[@class='amount'][normalize-space()='7 Articol(e)']")
    WebElement noOfItems;

    @FindBy(how = How.XPATH, using = "//li[@class='level0 nav-2 active parent']//ul[@class='level0']")
    WebElement meniu;


    public void openFilterPage (String hostname) {
        System.out.println("Open the next url:" + hostname);
        driver.get(hostname);
    }


    public String filter ( ) {
        Actions act = new Actions(driver);

        wait.until(ExpectedConditions.elementToBeClickable(electrocasnice));
        act.moveToElement(electrocasnice).build().perform();

        wait.until(ExpectedConditions.elementToBeClickable(micdejun));
        act.moveToElement(micdejun);

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


    public FilterPage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver , 10);
        PageFactory.initElements(this.driver , this);
//
    }


}


