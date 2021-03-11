//package tests;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import io.github.bonigarcia.wdm.config.DriverManagerType;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import utils.SeleniumUtils;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//
//public class Verify {
//
//    WebDriver driver;
//
//    @DataProvider(name = "logindp")
//    public Iterator<Object[]> loginDp() {
//
//        Collection<Object[]> dp = new ArrayList<Object[]>();
//        dp.add(new String[]{"", "", "Combinatia user / parola nu exista in baza de date!"});
//        dp.add(new String[]{"user1@user.ro", "", "Combinatia user / parola nu exista in baza de date!"});
//        dp.add(new String[]{"", "password1", "Combinatia user / parola nu exista in baza de date!"});
//
//
//        return dp.iterator();
//    }
//
//    @Test(dataProvider = "logindp")
//    public void negativeLoginTest(String username, String password, String genErrMsg) {
//        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();//this shall be moved @ BEFORE
//        driver = new ChromeDriver();
//        driver.get("https://www.price.ro/index.php?action=account");
//        WebElement usernameInput = driver.findElement(By.id("u"));
//        WebElement passwordInput = driver.findElement(By.id("p"));
//        WebElement submitButton = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/form/div/button/span"));
//        usernameInput.clear();
//        usernameInput.sendKeys(username);
//
//        passwordInput.clear();
//        passwordInput.sendKeys(password);
//        submitButton.submit();
//
////        WebElement err1 = driver.findElement(By.xpath("//*[@id=\"login-form\"]/form/div/div[2]/div"));
//        String err = SeleniumUtils.getElementMessage(driver, By.xpath("//*[@id='login-form']/form/div/div[2]/div"));
////        Assert.assertEquals(err, genErrMsg);
//
//        driver.close();
//
//    }
//
//}
