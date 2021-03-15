package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
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

    public void register (String Prenume , String Nume , String Judet , String Oras ,
                          String Adresa , String Telefon , String Email ,
                          String Parola , String ConfParola
    ) {


        fn.clear();
        fn.sendKeys(Prenume);
//        wait.until(ExpectedConditions.elementToBeClickable(ln)).click();
        ln.clear();
        ln.sendKeys(Nume);
//        wait.until(ExpectedConditions.elementToBeClickable(judet)).click();
        judet.clear();
        judet.sendKeys(Judet);
//        wait.until(ExpectedConditions.elementToBeClickable(oras)).click();
        oras.clear();
        oras.sendKeys(Oras);
//        wait.until(ExpectedConditions.elementToBeClickable(strada)).click();
        strada.clear();
        strada.sendKeys(Adresa);
//        wait.until(ExpectedConditions.elementToBeClickable(telefon)).click();
        telefon.clear();
        telefon.sendKeys(Telefon);
//        wait.until(ExpectedConditions.elementToBeClickable(email)).click();
        email.clear();
        email.sendKeys(Email);
//        wait.until(ExpectedConditions.elementToBeClickable(parola)).click();
        parola.clear();
        parola.sendKeys(Parola);
//        wait.until(ExpectedConditions.elementToBeClickable(confparola)).click();
        confparola.clear();
        confparola.sendKeys(ConfParola);


        creazaCont.submit();

//        waitForRegisterPage();

    }


    public boolean checkErr (String error , String type) {
        if (type.equalsIgnoreCase("fnErr"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-firstname") , error);
        else if (type.equalsIgnoreCase("lnErr"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-lastname") , error);
        else if (type.equalsIgnoreCase("judetErr"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-region") , error);
        else if (type.equalsIgnoreCase("orasErr"))
            return SeleniumUtils.checkElementMessage(driver , By.id("advice-required-entry-city") , error);
        else if (type.equalsIgnoreCase("adresaErr"))
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

//        WebDriverWait wait = new WebDriverWait(driver , 15);
//        for (int i = 0; i < 5; i++) {
//            WebElement registration = wait.until(
//                    ExpectedConditions.presenceOfElementLocated(By.id("register-tab")));
//            registration.click();
//

    }

}



