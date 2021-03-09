package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class SeleniumUtils {

    public static WebDriver getDriver (String browserType) {
        WebDriver driver = null;
        Browsers browsers = getBrowserEnumFromString(browserType);

        switch (browsers) {
            case CHROME:
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                driver = new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.getInstance(DriverManagerType.EDGE).setup();
                driver = new EdgeDriver();
                break;

            default:
                System.out.println("WARNING selected browser is not supported: " + browsers.toString());
        }
        return driver;
    }

    public static Browsers getBrowserEnumFromString (String browserType) {
        for (Browsers browser : Browsers.values()) {
            if (browserType.equalsIgnoreCase(browser.toString()))
                return browser;
        }
        System.out.println("Browser not found on supported list");
        return null;
    }

    public static WebElement waitForGenericElement (WebDriver driver , By by , int timeout) {
        WebDriverWait wait = new WebDriverWait(driver , timeout);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }


}
