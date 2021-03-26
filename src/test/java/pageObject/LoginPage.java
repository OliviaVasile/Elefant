package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;

public class LoginPage {
    private WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.ID, using = "email")
    WebElement usernameInput;
    @FindBy(how = How.ID, using = "pass")
    WebElement passwordInput;
    @FindBy(how = How.ID, using = "send2")
    WebElement autentificare;

    public LoginPage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver , 15);
        PageFactory.initElements(this.driver , this);
    }

    public void login (String username , String password) {

        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        autentificare.submit();
    }

    public boolean checkErr (String error , String type) {
        if (type.equalsIgnoreCase("userErr"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-email") , error);
        else if (type.equalsIgnoreCase("passErr"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-pass") , error);
        else if (type.equalsIgnoreCase("generalErr"))
            return SeleniumUtils.checkElementMessage(driver , By.xpath("//li[contains(@class, 'error-msg')]/ul/li/span") , error);
        return false;
    }

    public void openLoginPage (String hostname) {
        System.out.println("Open the next url:" + hostname + "/customer/account/login/");
        driver.get(hostname + "/customer/account/login/");

    }

}
