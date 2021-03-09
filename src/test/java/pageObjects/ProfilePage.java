package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;

public class ProfilePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id="logout-submit")
    WebElement logOutButton;

    By user = By.xpath("/html/body/div[2]/div/div[4]/div/div[1]/section/div[2]/div[2]/a");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(this.driver, this);
    }

    public void logOut(){
        wait.until(ExpectedConditions.elementToBeClickable(logOutButton)).click();
    }

    public String getUser(){
        return SeleniumUtils.waitForGenericElement(driver,user, 15).getText();
    }
}
