package pageObject;

import models.accountModel;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(how = How.CSS, using = "a[title='Deconectare']")
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
        logOutButton.click();
    }

    public boolean logOut1 ( ) {
//        logOut();
        contulTau.click();
//        logOutButton.click();
        try {
            SeleniumUtils.jsExecute(driver,By.className("logout"));

        } catch (NoSuchElementException exception) {return true;}

        if (driver.getCurrentUrl().equals("https://www.delimano.ro/customer/account/")) {
            return false;
        }
        System.out.println("Ai iesit din cont");
        return true;
    }

    public boolean backAfterLogOut ( ) {
        driver.navigate().back();
        return driver.getCurrentUrl().equals("https://www.delimano.ro/customer/account/");
    }

    public void changePassword (String password , String newPassword) {
        SeleniumUtils.jsExecute(driver , By.xpath("//a[normalize-space()='Schimba parola']"));
        driver.findElement(By.id("current_password")).sendKeys(password);
        driver.findElement(By.id("password")).sendKeys(newPassword);
        driver.findElement(By.id("confirmation")).sendKeys(newPassword);
        SeleniumUtils.jsExecute(driver , By.cssSelector("button[title='Salveaza'] span span"));

    }

    public boolean contModificat ( ) {

        try {
            driver.findElement(By.xpath("//span[normalize-space()='The account information has been saved.']"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }

    }

}
