package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class OtherUtils {
     WebDriver driver;

    public static String sanitizeNullDbString(String dbResult){
        if(dbResult == null){
            return "" ;

        }
        return dbResult;
    }
    public static boolean checkMessagePresentOnElement(WebElement element, String expectedError){
        try {
            String acctualMsg = element.getText();
            return expectedError.equals(acctualMsg);
        }
        catch (NoSuchElementException e){
            if (expectedError.isEmpty())
                return true;
        }
        return false;
    }

//    public WebElement fluentWait(final By locator){
//        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//                .withTimeout(30, TimeUnit.SECONDS)
//                .pollingEvery(5, TimeUnit.SECONDS)
//                .ignoring(NoSuchElementException.class);
//
//        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
//            public WebElement apply(WebDriver driver) {
//                return driver.findElement(locator);
//            }
//        });
//
//        return foo;
//    };
}
