package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class OtherUtils {
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
}
