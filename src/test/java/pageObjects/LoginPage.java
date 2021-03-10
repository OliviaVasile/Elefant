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

//    @FindBy(xpath = "/html/body/div[2]/div[4]/div[1]/div[1]/div[2]/div/div[2]/div[2]/div[1]/div/span")
//    WebElement arrow;
//    @FindBy(xpath = "/html/body/div[2]/div[4]/div[1]/div[1]/div[2]/div/div[2]/div[2]/div[2]/div/ul/li[1]/a")
//            WebElement login;
    @FindBy(id = "email")
    WebElement usernameInput;
    @FindBy(id = "pass")
    WebElement passwordInput;
    @FindBy(how = How.ID, using = "send2")
    WebElement Autentificare;

    @FindBy(how = How.XPATH, using = "//*[@id=\"top\"]/body/div[2]/div[4]/div[1]/div[3]/div/div[2]/div[2]/ul/li/ul/li/span")
    WebElement errGeneral;
    @FindBy(id = "advice-required-entry-pass")
    WebElement errPassword;
    @FindBy(id = "advice-required-entry-email")
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
        Autentificare.submit();

    }
    public void waitForLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(Autentificare));
    }


//    public void initializeGeneralErrorElement() {
//        errGeneral = SeleniumUtils.waitForGenericElement(driver, By.xpath("/html/body/div[2]/div/div[3]/div/main/article/div/div/div/div/ul/li"), 30);
//        errPassword = SeleniumUtils.waitForGenericElement(driver, By.xpath("/html/body/div[2]/div/div[3]/div/main/article/div/div/div/div/ul/li"), 30);
//        errUserName = SeleniumUtils.waitForGenericElement(driver, By.xpath("/html/body/div[2]/div/div[3]/div/main/article/div/div/div/div/ul/li"), 30);
//    }

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
        System.out.println("Open the next url:" + hostname + "customer/account/login/");
        driver.get(hostname + "customer/account/login/");
//
//
//        WebDriverWait wait = new WebDriverWait(driver , 25);
//        for (int i = 0; i < 5; i++) {
//            WebElement arrow = wait.until(
//                    ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[4]/div[1]/div[1]/div[2]/div/div[2]/div[2]/div[1]/div/span")));
//            arrow.click();
//            login.click();

        }
    }
