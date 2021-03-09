package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.SeleniumUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class BaseUITest {
    WebDriver driver;
    String hostname;

    @BeforeClass
    public void setUp(){String browserType = null;
        //Method 1 -D cmd line parameters
        System.out.println(System.getProperty("browser"));
        browserType = System.getProperty("browser");

        try {
            //read from properties file
            InputStream input = new FileInputStream("src\\test\\java\\utils\\framework.properties");
            Properties prop = new Properties();
            prop.load(input);

//            read default value from config
            if (browserType == null)
                browserType = prop.getProperty("browser");

            System.out.println("Run test with browser:" + browserType);
            driver = SeleniumUtils.getDriver(browserType);

            hostname = prop.getProperty("hostname");
            System.out.println("Use the next hostname:" + hostname);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @AfterClass
    public void CleanUp(){
        System.out.println("Close driver at end of class test");
        driver.quit();
    }
}
