package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    WebDriver driver;
    WebDriverWait wait;


    @FindBy(how = How.XPATH, using = "//button[@title='Adauga in cos']")
    WebElement addButton;
    @FindBy(how = How.ID, using = "qty")
    WebElement cantitate;
    @FindBy(how = How.ID, using = "add1")
    WebElement plus;
    @FindBy(how = How.ID, using = "minus1")
    WebElement minus;
    @FindBy(how = How.XPATH, using = "//select[@data-label='sWarranty']")

    WebElement garantie;

    @FindBy(how = How.XPATH, using = "//div[@class='exponea-banner23-content']")
    WebElement modalGarantie;

    @FindBy(how = How.CSS, using = "span[class='exponea-close-warranty']")
    WebElement nuMultumesc;


    public ProductPage (WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver , 15);
        PageFactory.initElements(this.driver , this);
    }

    public void openProductPage (String hostname) {
        System.out.println("Open the next url:" + hostname);
        driver.get(hostname);
    }


    public String openProduct ( ) {

        FilterPage fp = new FilterPage(driver);
        fp.filterResults();
        WebElement element1 = driver.findElement(By.id("product-collection-image-212609"));
        element1.click();
        String result = driver.findElement(By.xpath("//h1[contains(text(),'Aparat pentru clătite Joy')]")).getText();
        System.out.println(result);
        return result;
    }

    public void addQToCart (String qty , String mesaj) {


        ProductPage vp = new ProductPage(driver);
        vp.openProduct();
//        driver.get("https://www.delimano.ro/aparat-pentru-clatite-joy");
        cantitate.clear();
        cantitate.sendKeys(qty);
        addButton();

        WebDriverWait wait = new WebDriverWait(driver , 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='exponea-close-warranty']")));
        nuMultumesc.click();

    }

    public void addToCart (String mesaj) {

        ProductPage vp = new ProductPage(driver);
        vp.openProduct();
//            driver.get("https://www.delimano.ro/aparat-pentru-clatite-joy");

        addButton();

        WebDriverWait wait = new WebDriverWait(driver , 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='exponea-close-warranty']")));
        nuMultumesc.click();
    }

    public void addPlusCart (String qty , String mesaj) {

        ProductPage vp = new ProductPage(driver);
        vp.openProduct();
//        driver.get("https://www.delimano.ro/aparat-pentru-clatite-joy");
//        Actions actions = new Actions(driver);
//        actions.moveToElement(plus).click().build().perform();
        cantitate.clear();
        cantitate.sendKeys(qty);
        plusButton();
        addButton();

        WebDriverWait wait = new WebDriverWait(driver , 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='exponea-close-warranty']")));
        nuMultumesc.click();
    }


    public void addButton ( ) {

        WebElement ele = driver.findElement(By.xpath("//button[@title='Adauga in cos']"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()" , ele);

    }

    public void plusButton ( ) {

        WebElement ele = driver.findElement(By.id("add1"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()" , ele);


    }

//    public  void navigateThroughLinks() throws InterruptedException {
//
//        List<WebElement> all_links_webpage = driver.findElements(By.tagName("a"));
//        System.out.println("Total no of products found: " + all_links_webpage.size());
//        int k = all_links_webpage.size();
//        System.out.println("List of products: ");
//        for (int i = 0; i < k; i++) {
//            if (all_links_webpage.get(i).getAttribute("href").contains("friteuza")) {
//                String link = all_links_webpage.get(i).getAttribute("href");
//
//            }
//        }
//    }


    public String productTitle ( ) {

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='cart-item-row cart-mobile']//h2[@class='product-name js-gtm-data']")));

        String result = driver.findElement(By.xpath("//div[@class='cart-item-row cart-mobile']//h2[@class='product-name js-gtm-data']")).getText();
        return result;

    }


}






