package pageObject;

import models.ProductModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.ls.LSOutput;

import javax.lang.model.element.Element;
import java.util.List;

public class ViewProduct {
    WebDriver driver;
    WebDriverWait wait;

    public ViewProduct (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver , 5);
        PageFactory.initElements(this.driver , this);
    }

    public void openViewPage (String hostname) {
        System.out.println("Open the next url:" + hostname);
        driver.get(hostname);
    }


    public String openProduct ( ) {

        FilterPage fp = new FilterPage(driver);
        fp.filterResults();
        WebElement element1 = driver.findElement(By.id("product-collection-image-212609"));
        element1.click();
        String result = driver.findElement(By.xpath("//h1[contains(text(),'Aparat pentru clătite Joy')]")).getText();
        System.out.println(result);
        return result;
    }

    public String titleProduct(){
        FilterPage fp = new FilterPage(driver);
        fp.filterResults();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Prăjitor de pâine Joy')]")));

        String result = driver.findElement(By.xpath("//a[contains(text(),'Prăjitor de pâine Joy')]")).getText();
        return result;

    }

//    public String productPage ( ) {
//        FilterPage fp = new FilterPage(driver);
//        fp.filterResults();
//
//
//        WebDriverWait wait = new WebDriverWait(driver , 10);
//        List<WebElement> productLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText("Vezi produs")));
//        System.out.println(productLinks);
//
//        for (WebElement productLink : productLinks) {
//
//            driver.get(productLink.getAttribute("href"));
//
//        }
//        returnTitle();
//
//    }
//
//        public String returnTitle(){
//
//            String product = driver.findElement(By.tagName("h1")).getText();
//            System.out.println(product);
//            return product;
//        }
    }






