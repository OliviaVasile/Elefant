//package pageObjects;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class RegisterPage {
//    private WebDriver driver;
//    WebDriverWait wait;
//
//
//    @FindBy(id = "firstname")
//    WebElement fnInput;
//    @FindBy(id = "lastname")
//    WebElement lnInput;
//    @FindBy(id = "region")
//    WebElement judet;
//    @FindBy(id = "city")
//    WebElement oras;
//    @FindBy(id = "street1")
//    WebElement strada;
//    @FindBy(id = "telephoneCustom")
//    WebElement telefon;
//    @FindBy(id = "email")
//    WebElement email;
//    @FindBy(id = "password")
//    WebElement parola;
//    @FindBy(id = "confirmation")
//    WebElement confparola;
//
//    @FindBy(xpath = "/html/body/div/div[2]/div[2]/form/div[3]/div/div[2]")
//    WebElement fnErrMsg;
//    @FindBy(xpath = "/html/body/div/div[2]/div[2]/form/div[4]/div/div[2]")
//    WebElement lnErrMsg;
//    @FindBy(xpath = "//*[@id=\"registration_form\"]/div[5]/div[1]/div[2]")
//    WebElement emailErrMsg;
//    @FindBy(xpath = "//*[@id=\"registration_form\"]/div[6]/div/div[2]")
//    WebElement userNameErrMsg;
//    @FindBy(xpath = "//*[@id=\"registration_form\"]/div[7]/div/div[2]")
//    WebElement passwordErrMsg;
//    @FindBy(xpath = "//*[@id=\"registration_form\"]/div[8]/div/div[2]")
//    WebElement password2ErrMsg;
//
//
//    public RegisterPage (WebDriver driver) {
//        this.driver = driver;
//        wait = new WebDriverWait(driver , 15);
//        PageFactory.initElements(this.driver , this);
//    }
//
//    public void register (String fn , String ln , String email , String userName , String password , String password2) {
//
////      registerButton.click();
//        System.out.println("fn");
//        fnInput.clear();
//        fnInput.sendKeys(fn);
//        System.out.println("ln");
//        lnInput.clear();
//        lnInput.sendKeys(ln);
//        System.out.println("email");
//        emailInput.clear();
//        emailInput.sendKeys(email);
//        userNameInput.clear();
//        userNameInput.sendKeys(userName);
//        passwordInput.clear();
//        passwordInput.sendKeys(password);
//        password2Input.clear();
//        password2Input.sendKeys(password2);
//        submitButton.submit();
//
//    }
//
//    public boolean checkErr (String error , String type) {
//        if (type.equalsIgnoreCase("errFN"))
//            return error.equals(fnErrMsg.getText());
//        else if (type.equalsIgnoreCase("errLN"))
//            return error.equals(lnErrMsg.getText());
//        else if (type.equalsIgnoreCase("errEmail"))
//            return error.equals(emailErrMsg.getText());
//        else if (type.equalsIgnoreCase("errUN"))
//            return error.equals(userNameErrMsg.getText());
//        else if (type.equalsIgnoreCase("errPass"))
//            return error.equals(passwordErrMsg.getText());
//        else if (type.equalsIgnoreCase("errPass2"))
//            return error.equals(password2ErrMsg.getText());
//        return false;
//    }
//
//    public void openRegisterPage (String hostname) {
//        System.out.println("Open the next url:" + hostname + "stubs/auth.html#registration_panel");
//        driver.get(hostname + "stubs/auth.html#registration_panel");
////        WebElement registration = driver.findElement(By.id("register-tab"));
//        WebDriverWait wait = new WebDriverWait(driver , 15);
//        for (int i = 0; i < 5; i++) {
//            WebElement registration = wait.until(
//                    ExpectedConditions.presenceOfElementLocated(By.id("register-tab")));
//            registration.click();
//
//
//        }
//
//    }
//}
//
//
