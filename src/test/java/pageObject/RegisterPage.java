package pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import javafx.scene.control.Tab;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;

public class RegisterPage {
    private WebDriver driver;
    WebDriverWait wait;


    @FindBy(how = How.ID, using = "firstname")
    WebElement fn;
    @FindBy(how = How.ID, using = "lastname")
    WebElement ln;
    @FindBy(how = How.ID, using = "region")
    WebElement judet;
    @FindBy(how = How.ID, using = "city")
    WebElement oras;
    @FindBy(how = How.ID, using = "street1")
    WebElement strada;
    @FindBy(how = How.ID, using = "telephoneCustom")
    WebElement telefon;
    @FindBy(how = How.ID, using = "email")
    WebElement email;
    @FindBy(how = How.ID, using = "password")
    WebElement parola;
    @FindBy(how = How.ID, using = "confirmation")
    WebElement confparola;
    @FindBy(how = How.ID, using = "createacc")
    WebElement creazaCont;

    @FindBy(how = How.ID, using = "advice-required-entry-firstname")
    WebElement fnErrMsg;
    @FindBy(how = How.ID, using = "advice-required-entry-lastname")
    WebElement lnErrMsg;
    @FindBy(how = How.ID, using = "advice-required-entry-region")
    WebElement judetErrMsg;
    @FindBy(how = How.ID, using = "advice-required-entry-city")
    WebElement orasErrMsg;
    @FindBy(how = How.ID, using = "advice-required-entry-street1")
    WebElement stradaErrMsg;
    @FindBy(how = How.ID, using = "advice-required-entry-telephoneCustom")
    WebElement telefErrMsg;
    @FindBy(how = How.ID, using = "advice-required-entry-email")
    WebElement emailErrMsg;
    @FindBy(how = How.ID, using = "advice-required-entry-password")
    WebElement parolaErrMsg;
    @FindBy(how = How.ID, using = "advice-required-entry-confirmation")
    WebElement parola2ErrMsg;


    public RegisterPage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver , 15);
        PageFactory.initElements(this.driver , this);
    }

//    public void forceSendKeys(WebElement element, String text)
//    {
//        if (element != null)
//            ((JavascriptExecutor) this.driver).executeScript("arguments[0].value=arguments[1]", element, text);
//    }


    public void register (String Prenume , String Nume , String Judet , String Oras ,
                          String Adresa , String Telefon , String Email ,
                          String Parola , String ConfParola
    ) {

        Actions act = new Actions(driver);
        act.moveToElement(fn);
        SeleniumUtils.forceSendKeys(driver,fn, Prenume);

        act.moveToElement(ln);
        SeleniumUtils.forceSendKeys(driver,ln, Nume);

        act.moveToElement(judet);
        SeleniumUtils.forceSendKeys(driver, judet, Judet);

        act.moveToElement(oras);
        SeleniumUtils.forceSendKeys(driver, oras, Oras);

        act.moveToElement(strada);
        SeleniumUtils.forceSendKeys(driver, strada, Adresa);

        act.moveToElement(telefon);
        SeleniumUtils.forceSendKeys(driver, telefon, Telefon);

        act.moveToElement(email);
        SeleniumUtils.forceSendKeys(driver, email, Email);

        act.moveToElement(parola);
        SeleniumUtils.forceSendKeys(driver, parola, Parola);

        act.moveToElement(confparola);
        SeleniumUtils.forceSendKeys(driver,confparola, ConfParola);

        SeleniumUtils.waitForGenericElement(driver,By.id("createacc"),15);
     SeleniumUtils.jsExecute(driver, By.id("createacc"));


    }

//    public boolean checkErr (String error , String type) {
//        if (type.equalsIgnoreCase("fnErr"))
//            return error.equals(fnErrMsg.getText());
//        else if (type.equalsIgnoreCase("lnErr"))
//            return error.equals(lnErrMsg.getText());
//        else if (type.equalsIgnoreCase("judetErr"))
//            return error.equals(judetErrMsg.getText());
//        else if (type.equalsIgnoreCase("orasErr"))
//            return error.equals(orasErrMsg.getText());
//        else if (type.equalsIgnoreCase("adresaErr"))
//            return error.equals(stradaErrMsg.getText());
//        else if (type.equalsIgnoreCase("telefonErr"))
//            return error.equals(telefErrMsg.getText());
//        else if (type.equalsIgnoreCase("emailErr"))
//            return error.equals(emailErrMsg.getText());
//        else if (type.equalsIgnoreCase("parolaErr"))
//            return error.equals(parolaErrMsg.getText());
//        else if (type.equalsIgnoreCase("confParolaErr"))
//            return error.equals(parola2ErrMsg.getText());
//        return false;
//    }


    public boolean checkErr (String error , String type) {
        if (type.equalsIgnoreCase("fnErr"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-firstname") , error);
        //div[@id='advice-required-entry-firstname']
        else if (type.equalsIgnoreCase("lnErr"))
            //div[@id='advice-required-entry-lastname']
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-lastname") , error);
        else if (type.equalsIgnoreCase("judetErr"))
            //div[@id='advice-required-entry-region']
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-region") , error);
        else if (type.equalsIgnoreCase("orasErr"))
            //div[@id='advice-required-entry-city']
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-city") , error);
        else if (type.equalsIgnoreCase("adresaErr"))
            //div[@id='advice-required-entry-street1']
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-street1") , error);
        else if (type.equalsIgnoreCase("telefonErr"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-telephoneCustom") , error);
        if (type.equalsIgnoreCase("emailErr"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-email") , error);
        if (type.equalsIgnoreCase("parolaErr"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-password") , error);
        if (type.equalsIgnoreCase("confParolaErr"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-confirmation") , error);


        return false;
    }


    public void openRegisterPage (String hostname) {
        System.out.println("Open the next url:" + hostname + "/customer/account/create/");
        driver.get(hostname + "/customer/account/create/");
        SeleniumUtils.waitForGenericElement(driver,By.id("createacc"),5);

    }

}



