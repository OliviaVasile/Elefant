package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;

import java.util.NoSuchElementException;

public class ContactPage {

    private WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.ID, using = "field9665")
    WebElement inputName;
    @FindBy(how = How.ID, using = "field9669")
    WebElement inputPhoneNo;
    @FindBy(how = How.ID, using = "field9673")
    WebElement inputEmail;
    @FindBy(how = How.ID, using = "field9677")
    WebElement inputInvoiceNO;
    @FindBy(how = How.ID, using = "field9681")
    WebElement related;
    @FindBy(how = How.ID, using = "field9689")
    WebElement inputDetails;
    @FindBy(how = How.ID, using = "webform_1725_submit_button")
    WebElement send;
//    By confirmation = By.xpath("//p[contains(text(),'Sesizarea a fost trimisa! Studio Moderna se angaje')]");


    public ContactPage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver , 15);
        PageFactory.initElements(this.driver , this);
    }


    public void openContactPage (String hostname) {
        System.out.println("Open the next url:" + hostname + "/formular-sesizari");
        driver.get(hostname + "/formular-sesizari");

    }


    public void contact (String name , String phoneNo , String email , String invoice , String details) {

        inputName.clear();
        inputName.sendKeys(name);
        inputPhoneNo.click();
        inputPhoneNo.sendKeys(phoneNo);
        inputEmail.clear();
        inputEmail.sendKeys(email);
        inputInvoiceNO.clear();
        inputInvoiceNO.sendKeys(invoice);
        Select dropdown = new Select(related);
        dropdown.selectByVisibleText("Eroare");
        inputDetails.clear();
        inputDetails.sendKeys(details);
        SeleniumUtils.jsExecute(driver , By.id("webform_1725_submit_button"));
    }

    public boolean checkConfirmation ( ) {

        try {
            SeleniumUtils.waitForGenericElement(driver , By.xpath("//p[contains(text(),'Sesizarea a fost trimisa! Studio Moderna se angaje')]") , 15);
            driver.findElement(By.xpath("//p[contains(text(),'Sesizarea a fost trimisa! Studio Moderna se angaje')]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public boolean checkErr (String error , String type) {
        if (type.equalsIgnoreCase("nameError"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-field9665") , error);
        else if (type.equalsIgnoreCase("phoneError"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-field9669") , error);
        else if (type.equalsIgnoreCase("emailError"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-field9673") , error);
        else if (type.equalsIgnoreCase("invoiceError"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-field9677") , error);
        else if (type.equalsIgnoreCase("validEmailError"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-validate-email-field9673") , error);
        return false;


    }

}



