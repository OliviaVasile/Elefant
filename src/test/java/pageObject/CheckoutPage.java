package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;



    @FindBy( how = How.XPATH, using =" //span[@class='cspl-product-qty']")
    WebElement sumarProduse;

    public String productSummary() {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='cspl-product-qty']")));

        String result = sumarProduse.getText();
        return result;
    }

}
