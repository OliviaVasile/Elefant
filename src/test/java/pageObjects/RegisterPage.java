package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;

public class RegisterPage {
    private WebDriver driver;
    WebDriverWait wait;


    @FindBy(id = "firstname")
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

        waitForPrenume();
        fn.clear();
        fn.sendKeys(Prenume);
        waitForNume();
        ln.clear();
        ln.sendKeys(Nume);
//        waitForJudet();
        judet.clear();
        judet.sendKeys(Judet);
//       waitForOras();
        oras.clear();
        oras.sendKeys(Oras);
//       waitForAdresa();
        strada.clear();
        strada.sendKeys(Adresa);
        waitForTelefon();
        telefon.clear();
        telefon.sendKeys(Telefon);
        waitForEmail();
        email.clear();
        email.sendKeys(Email);
        waitForParola();
        parola.clear();
        parola.sendKeys(Parola);
//        waitForParola2();
        confparola.clear();
        confparola.sendKeys(ConfParola);


        creazaCont.submit();

//        waitForRegisterPage();

    }

    public void waitForPrenume ( ) {
        WebDriverWait wait = new WebDriverWait(driver , 5);
        for (int i = 0; i < 5; i++) {
            WebElement prenume = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("firstname")));
            prenume.click();

        }
    }

    public void waitForNume ( ) {
        WebDriverWait wait = new WebDriverWait(driver , 5);
        for (int i = 0; i < 5; i++) {
            WebElement nume = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("lastname")));
            nume.click();
        }
    }

    public void waitForJudet ( ) {
        WebDriverWait wait = new WebDriverWait(driver , 5);
        for (int i = 0; i < 5; i++) {
            WebElement judet = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("region")));
            judet.click();
        }
    }

    public void waitForOras ( ) {
        WebDriverWait wait = new WebDriverWait(driver , 5);
        for (int i = 0; i < 5; i++) {
            WebElement oras = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("city")));
            oras.click();
        }
    }

    public void waitForAdresa ( ) {
        WebDriverWait wait = new WebDriverWait(driver , 5);
        for (int i = 0; i < 5; i++) {
            WebElement strada = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("street1")));
            strada.click();
        }
    }

    public void waitForTelefon ( ) {
        WebDriverWait wait = new WebDriverWait(driver , 5);
        for (int i = 0; i < 5; i++) {
            WebElement telefon = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("telephoneCustom")));
            telefon.click();
        }
    }

    public void waitForEmail ( ) {
        WebDriverWait wait = new WebDriverWait(driver , 5);
        for (int i = 0; i < 5; i++) {
            WebElement email = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("email")));
            email.click();
        }
    }

    public void waitForParola ( ) {
            WebDriverWait wait = new WebDriverWait(driver , 5);
            for (int i = 0; i < 5; i++) {
                WebElement parola = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.id("password")));
                parola.click();
        }
//        wait.until(ExpectedConditions.elementToBeClickable(parola));
    }

    public void waitForParola2 ( ) {
        WebDriverWait wait = new WebDriverWait(driver , 5);
        for (int i = 0; i < 5; i++) {
            WebElement parola2 = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("confirmation")));
            parola2.click();
//        wait.until(ExpectedConditions.elementToBeClickable(confparola));
    }


//    public void waitForRegisterPage ( ) {

//                wait.until(ExpectedConditions.elementToBeClickable(creazaCont));
//                wait.until(ExpectedConditions.elementToBeClickable(ln));
//                wait.until(ExpectedConditions.elementToBeClickable(fn));
//                wait.until(ExpectedConditions.elementToBeClickable(judet));
//                wait.until(ExpectedConditions.elementToBeClickable(oras));
//                wait.until(ExpectedConditions.elementToBeClickable(strada));
//                wait.until(ExpectedConditions.elementToBeClickable(telefon));
//                wait.until(ExpectedConditions.elementToBeClickable(email));
//                wait.until(ExpectedConditions.elementToBeClickable(parola));
//                wait.until(ExpectedConditions.elementToBeClickable(confparola));
//                wait.until(ExpectedConditions.elementToBeClickable(fnErrMsg));
//                wait.until(ExpectedConditions.elementToBeClickable(lnErrMsg));
//                wait.until(ExpectedConditions.elementToBeClickable(judetErrMsg));
//                wait.until(ExpectedConditions.elementToBeClickable(orasErrMsg));
//                wait.until(ExpectedConditions.elementToBeClickable(stradaErrMsg));
//                wait.until(ExpectedConditions.elementToBeClickable(telefErrMsg));
//                wait.until(ExpectedConditions.elementToBeClickable(emailErrMsg));
//                wait.until(ExpectedConditions.elementToBeClickable(parolaErrMsg));
//                wait.until(ExpectedConditions.elementToBeClickable(parola2ErrMsg));
//
//
//
  }

        public boolean checkErr (String error , String type){
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
//
//        return false;
//    }

        public void openRegisterPage (String hostname){
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



