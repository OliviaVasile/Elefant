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
import utils.SeleniumUtils;

public class LoginPage {
    private WebDriver driver;
    WebDriverWait wait;
    @FindBy (xpath = "/html/body/div[3]/div/div[2]/a[2]/b")
    WebElement cookiesMess;
    @FindBy(xpath = "/html/body/div[2]/div/div[3]/div/main/article/div/div/div/form/p[1]/input")
    WebElement usernameInput;
    @FindBy(xpath = "/html/body/div[2]/div/div[3]/div/main/article/div/div/div/form/p[2]/span/input")
    WebElement passwordInput;
    @FindBy(xpath = "/html/body/div[2]/div/div[3]/div/main/article/div/div/div/form/p[3]/button")
    WebElement Autentificare;

    @FindBy(xpath = "/html/body/div[2]/div/div[3]/div/main/article/div/div/div/div/ul/li")
    WebElement errGeneral;
    @FindBy(xpath = "/html/body/div[2]/div/div[3]/div/main/article/div/div/div/div/ul/li")
    WebElement errPassword;
    @FindBy(xpath = "/html/body/div[2]/div/div[3]/div/main/article/div/div/div/div/ul/li")
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
        cookiesMess.click();
        Autentificare.click();

    }



    public void initializeGeneralErrorElement() {
        errGeneral = SeleniumUtils.waitForGenericElement(driver, By.xpath("/html/body/div[2]/div/div[3]/div/main/article/div/div/div/div/ul/li"), 30);
        errPassword = SeleniumUtils.waitForGenericElement(driver, By.xpath("/html/body/div[2]/div/div[3]/div/main/article/div/div/div/div/ul/li"), 30);
        errUserName = SeleniumUtils.waitForGenericElement(driver, By.xpath("/html/body/div[2]/div/div[3]/div/main/article/div/div/div/div/ul/li"), 30);
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

    public void openLoginPage (String hostname) {
        System.out.println("Open the next url:" + hostname + "/contul-meu/");
        driver.get(hostname + "/contul-meu/");
        WebDriverWait wait = new WebDriverWait(driver , 15);
//        for (int i = 0; i < 5; i++) {
//            WebElement cookiesMess = wait.until(
//                    ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div/div[2]/a[2]/b")));
//            cookiesMess.click();
//        }
    }
}