package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    WebDriverWait wait;

    public HomePage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver , 15);
        PageFactory.initElements(this.driver , this);
    }

    public String generalPrice ( ) {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='total'] span[class='price']")));
        String genPret = driver.findElement(By.cssSelector("div[class='total'] span[class='price']")).getText().split(" ")[0];
        ;
        System.out.println("totalul general cosului este " + genPret);
        return genPret;

    }

    public String updatedQty ( ) {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart_count")));
        String totalQty = driver.findElement(By.className("cart_count")).getText();
        System.out.println("numarul total de produse in cos este: " + totalQty);
        return totalQty;

    }


}
