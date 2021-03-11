package pageObjects;

import com.sun.xml.bind.v2.model.core.ID;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.OtherUtils;
import utils.SeleniumUtils;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class LoginPage {
    private WebDriver driver;
    WebDriverWait wait;

//@FindBy ( xpath = "//*[@id=\"cookie-monster\"]/div/div[2]/a")
//WebElement cookieMess;

    @FindBy(id = "email")
    WebElement usernameInput;
    @FindBy(id = "pass")
    WebElement passwordInput;
    @FindBy(id = "send2")
    WebElement Autentificare;

    @FindBy(xpath = "//*[@id=\"top\"]/body/div[3]/div[3]/div[1]/div[3]/div/div[2]/div[2]/ul/li/ul/li")
    WebElement errGeneral;
    @FindBy(id = "advice-required-entry-pass")
    WebElement errPassword;
    @FindBy(id = "advice-required-entry-email")
    WebElement errUserName;

    public LoginPage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver , 45);
        PageFactory.initElements(this.driver , this);
    }

    public void login (String username , String password) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        Autentificare.submit();


    }
//    public void waitForLoginPage() {
//        wait.until(ExpectedConditions.elementToBeClickable(Trimite));
//    }


    public void initializeGeneralErrorElement ( ) {
        errGeneral = SeleniumUtils.waitForGenericElement(driver , By.xpath("/html/body/div[5]/div/div/div/div/form/div/div[2]/div") , 15);
    }

//    public boolean checkErr (String error , String type) {
//        if (type.equalsIgnoreCase("userErr"))
//            return error.equals(errUserName.getText());
//        else if (type.equalsIgnoreCase("passErr"))
//            return error.equals(errPassword.getText());
//        else if (type.equalsIgnoreCase("generalErr"))
//            return error.equals(errGeneral.getText());
//        return false;
//    }


    public boolean checkErr (String error , String type) {
        if (type.equalsIgnoreCase("userErr"))
            return OtherUtils.checkMessagePresentOnElement(errUserName , error);
        else if (type.equalsIgnoreCase("passErr"))
            return OtherUtils.checkMessagePresentOnElement(errPassword , error);
        else if (type.equalsIgnoreCase("generalErr"))
            return OtherUtils.checkMessagePresentOnElement(errGeneral , error);

        return false;
    }


    public void openLoginPage (String hostname) {
        System.out.println("Open the next url:" + hostname + "/customer/account/login/");
        driver.get(hostname + "/customer/account/login/");
//                cookieMess.click();


    }
//

}
