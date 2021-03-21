package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeleniumUtils {


    public static WebDriver getDriver(String browserType) {

        WebDriver driver = null;
        Browsers browsers = getBrowserEnumFromString(browserType);


        switch (browsers) {

            case CHROME:
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;
//

            case FIREFOX:
                WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                FirefoxProfile profile = new FirefoxProfile();

                driver = new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.getInstance(DriverManagerType.EDGE).setup();
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("WARNING selected browser is not supported");
        }
        return driver;

    }

    public static Browsers getBrowserEnumFromString(String browserType) {
        for (Browsers browser : Browsers.values()) {
            if (browserType.equalsIgnoreCase(browser.toString()))
                return browser;
        }
        System.out.println("Browser not found on supported list");
        return null;
    }

    public static WebElement waitForGenericElement(WebDriver driver, By by, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public static boolean checkElementMessage(WebDriver driver, By by, String expectedError) {
        String message = "";
        int retry = 0;
        WebElement element;
        System.out.println("Check presence of next message:" + expectedError);
        if (expectedError != null) {
            while (retry < 3) {
                try {
                    if (expectedError.isEmpty())
                        element = driver.findElement(by);
                    else
                        element = SeleniumUtils.waitForGenericElement(driver, by, 20);
                    message = element.getText();
                    return message.equals(expectedError);
                } catch (ElementClickInterceptedException | NoSuchElementException | TimeoutException e) {
                    if (expectedError.isEmpty())
                        return true;
                    else
                        retry++;
                }
            }
            System.out.println("Element not found on page");
            return false;
        } else
            return true;
    }

    public static Integer turnToInteger(String s){

        int is = Integer.valueOf(s);

        System.out.println("cantitatea este " + is);
        return is;


    }
public static void jsExecute(WebDriver driver, By by) {
    WebElement ele = driver.findElement(by);
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("arguments[0].click()" , ele);

}

    public static void forceSendKeys(WebDriver driver, WebElement element, String text)

    {
        if (element != null)
            ((JavascriptExecutor)driver).executeScript("arguments[0].value=arguments[1]", element, text);
    }



    public static void waitForAjax(WebDriver driver, By by, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));

    }




}
