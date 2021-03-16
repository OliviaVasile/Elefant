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

public class ProfilePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.CLASS_NAME, using = "your-account")
    WebElement contulTau;

    @FindBy(how = How.XPATH, using = "//div[@class='links']//li[contains(@class,'last')]")
    WebElement logOutButton;

    By user = By.id("customer_email");

    public ProfilePage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver , 15);
        PageFactory.initElements(this.driver , this);
    }

    public String getUser ( ) {
        return SeleniumUtils.waitForGenericElement(driver , user , 15).getText();
    }

    public void logOut ( ) {
        contulTau.click();
//    wait.until(ExpectedConditions.elementToBeClickable(logOutButton)).click();
        logOutButton.click();
    }

    public boolean logOut1 ( ) {

        logOut();
        if (driver.getCurrentUrl().equals("https://www.delimano.ro/customer/account/")) {
            return false;
        }
        System.out.println("Ai iesit din cont");
        return true;
    }

    public boolean backAfterLogOut ( ) {


        driver.navigate().back();
        if (driver.getCurrentUrl().equals("https://www.delimano.ro/customer/account/login/")) {
            return true;
        }
        return false;
    }

}
