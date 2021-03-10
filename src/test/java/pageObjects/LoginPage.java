package pageObjects;

import com.sun.xml.bind.v2.model.core.ID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.OtherUtils;
import utils.SeleniumUtils;

public class LoginPage {
    private WebDriver driver;
    WebDriverWait wait;


    @FindBy(id = "u")
    WebElement usernameInput;
    @FindBy(id = "p")
    WebElement passwordInput;
    @FindBy(xpath = "/html/body/div[5]/div/div/div/div/form/div/button/span")
    WebElement Trimite;

    @FindBy(xpath ="//html/body/div[5]/div/div/div/div/form/div/div[2]/div")
    WebElement errGeneral;
    @FindBy(xpath = "/html/body/div[5]/div/div/div/div/form/div/div[2]/div")
    WebElement errPassword;
    @FindBy(xpath= "/html/body/div[5]/div/div/div/div/form/div/div[2]/div")
    WebElement errUserName;

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
//        cookiesMess.click();
        Trimite.submit();


    }
//    public void waitForLoginPage() {
//        wait.until(ExpectedConditions.elementToBeClickable(Trimite));
//    }


    public void initializeGeneralErrorElement() {
        errGeneral = SeleniumUtils.waitForGenericElement(driver, By.xpath("/html/body/div[5]/div/div/div/div/form/div/div[2]/div"), 15);
    }

    public boolean checkErr (String error , String type) {
        if (type.equalsIgnoreCase("userErr"))
            return error.equals(errUserName.getText());
        else if (type.equalsIgnoreCase("passErr"))
            return error.equals(errPassword.getText());
        else if (type.equalsIgnoreCase("generalErr"))
            return error.equals(errGeneral.getText());
        return false;
    }


//    public boolean checkErr (String error , String type) {
//        if (type.equalsIgnoreCase("userErr"))
//            return OtherUtils.checkMessagePresentOnElement(errUserName, error);
//        else if (type.equalsIgnoreCase("passErr"))
//            return OtherUtils.checkMessagePresentOnElement(errPassword, error);
//        else if (type.equalsIgnoreCase("generalErr"))
//            return OtherUtils.checkMessagePresentOnElement(errGeneral, error);
//
//            return false;
//        }


    public void openLoginPage (String hostname) {
        System.out.println("Open the next url:" + hostname + "/index.php?action=account");
        driver.get(hostname + "/index.php?action=account");


        }
    }
