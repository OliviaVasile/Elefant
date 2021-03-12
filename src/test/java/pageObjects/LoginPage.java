package pageObjects;

import com.sun.xml.bind.v2.model.core.ID;
import org.openqa.selenium.*;
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

    @FindBy(xpath = "//*[@id=\"cookie-monster\"]/div/div[2]/a")
    WebElement cookieMess;

    @FindBy(id = "email")
    WebElement usernameInput;
    @FindBy(id = "pass")
    WebElement passwordInput;
    @FindBy(id = "send2")
    WebElement Autentificare;

    @FindBy(xpath= ("//li[contains(@class, 'error-msg')]/ul/li/span"))
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
//        waitForError();

        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        Autentificare.submit();
//        fluentWait(By.id("advice-required-entry-pass"));
//        fluentWait(By.id("advice-required-entry-email"));
//        fluentWait(By.xpath("//*[@id=\"top\"]/body/div[3]/div[3]/div[1]/div[3]/div/div[2]/div[2]/ul/li/ul/li/span"));


    }

//    public WebElement fluentWait (final By locator) {
//        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//                .withTimeout(10 , TimeUnit.SECONDS)
//                .pollingEvery(5 , TimeUnit.SECONDS)
//                .ignoring(NoSuchElementException.class);
//
//        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
//            public WebElement apply (WebDriver driver) {
//                return driver.findElement(locator);
//            }
//        });
//
//        return foo;
//    }

    ;

//    public String waitForError() {
//        int retry = 0;
//        String error="";
//        while (retry < 3) {
//            try {
////                error=SeleniumUtils.waitForGenericElement(driver, By.xpath("/html/body/div[2]/div[4]/div[1]/div[3]/div/div[2]/div[2]/ul/li/ul/li"), 15).getText();
//                error=SeleniumUtils.waitForGenericElement(driver, By.id("advice-required-entry-pass"), 20).getText();
////                error=SeleniumUtils.waitForGenericElement(driver, By.id("advice-required-entry-email"), 15).getText();
//                retry = 4;
//            } catch (ElementClickInterceptedException | NoSuchElementException e) {
//                retry++;
//            }
//        }
//        return error;}

//    public void initializeGeneralErrorElement ( ) {
//        errGeneral = SeleniumUtils.waitForGenericElement(driver , By.xpath("/html/body/div[5]/div/div/div/div/form/div/div[2]/div") , 15);
//    }

    //    public void waitForLoginPage() {
//        wait.until(ExpectedConditions.elementToBeClickable(Trimite));
//    }

//    public boolean checkErr (String error , String type) {
//        if (type.equalsIgnoreCase("userErr"))
//            return SeleniumUtils.checkElementMessage(driver, By.id("advice-required-entry-email"), error);
//        else if (type.equalsIgnoreCase("passErr"))
//            return SeleniumUtils.checkElementMessage(driver, By.id("advice-required-entry-pass"), error);
//        else if (type.equalsIgnoreCase("generalErr"))
//            return SeleniumUtils.checkElementMessage(driver, By.xpath("//li[contains(@class, 'error-msg')]/ul/li/span"), error);;
//        return false;


//        String err = SeleniumUtils.getElementMessage(driver, By.xpath("//*[@id='login-form']/form/div/div[2]/div"));
//        Assert.assertEquals(err, genErrMsg);

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
//        cookieMess.click();
    }
//

}
