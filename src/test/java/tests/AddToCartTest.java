package tests;

import com.opencsv.CSVReader;
import models.cartModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.CartPage;
import pageObject.ProductPage;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static utils.OtherUtils.sanitizeNullDbString;

public class AddToCartTest extends BaseTest {

    private void printData (cartModel cm) {

        System.out.println(cm.getMesaj());

    }

    private void printQData (cartModel cm) {
        System.out.println(cm.getQty());
        System.out.println(cm.getMesaj());

    }
//validate that "Aparat pentru clătite Joy a fost adaugat in cos." message is present
    private void addCartActions (cartModel cm) {
        CartPage cp = new CartPage(driver);
        ProductPage pp = new ProductPage(driver);
//        cp.openCart(hostname);
//        cp.deleteCart();
        pp.openProductPage(hostname);
        pp.addToCart(cm.getMesaj());
        Assert.assertTrue(cp.verifyElementPresent());
        Assert.assertEquals(cm.getMesaj() , cp.getResult());
        cp.deleteCart();
        //        Assert.assertEquals(pp.productTitle(), cp.productTitle());
//        Assert.assertTrue(cp.checkContinuaAfterDeleteCart());
    }

//validate that "Aparat pentru clătite Joy a fost adaugat in cos." message is present;
//validate that qty inserted in product page is the same with the resulted qty from cart page
    private void addCartQActions (cartModel cm) {
        CartPage cp = new CartPage(driver);
        ProductPage pp = new ProductPage(driver);
        pp.openProductPage(hostname);


        pp.addQToCart(cm.getQty() , cm.getMesaj());
        Assert.assertTrue(cp.verifyElementPresent());
        Assert.assertEquals(cm.getMesaj() , cp.getResult());
        Assert.assertEquals(cm.getQty() , cp.numberOfProducts());

        cp.deleteCart();
        //        Assert.assertEquals(pp.productTitle(), cp.productTitle());
//        Assert.assertTrue(cp.checkContinuaAfterDeleteCart());
    }

    //validate that "Aparat pentru clătite Joy a fost adaugat in cos." message is present;
//validate that by clicking once "+" the qty from cart page is inserted qty in the product page + 1
    private void addCartPlusActions (cartModel cm) {
        CartPage cp = new CartPage(driver);
        ProductPage pp = new ProductPage(driver);
        pp.openProductPage(hostname);

        pp.addPlusCart(cm.getQty() , cm.getMesaj());
        Assert.assertTrue(cp.verifyElementPresent());
        Assert.assertEquals(cm.getMesaj() , cp.getResult());
        Assert.assertEquals(java.util.Optional.of(Integer.valueOf(cm.getQty()) + 1) , java.util.Optional.of(cp.numberOfProductsAfterPlus()));
        cp.deleteCart();
//        Assert.assertEquals(pp.productTitle(), cp.productTitle());
//        Assert.assertTrue(cp.checkContinuaAfterDeleteCart());
    }

    @DataProvider(name = "csvDp")
    public Iterator<Object[]> csvDp1Collection ( ) throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\adCart.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {

            dp.add(new Object[]{new cartModel(csvData.get(i)[0] ,
                    csvData.get(i)[1])});
        }
        return dp.iterator();
    }


    @DataProvider(name = "sqlDp")
    public Iterator<Object[]> sqlDpCollection ( ) {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema ,
                    dbUsername , dbPassword);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM automation.addcart;");
            while (results.next()) {
                cartModel cm = new cartModel();
                cm.setQty(sanitizeNullDbString(results.getString("qty")));
                cm.setMesaj(sanitizeNullDbString(results.getString("succes")));

                dp.add(new Object[]{cm});
            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dp.iterator();
    }


//    @Test (dataProvider = "csvDp")
//    public void addToCartCSV (CartModel cm){
//        printData(cm);
//        addCartActions(cm);
//
//    }

    @Test(dataProvider = "sqlDp")
    public void addToCart (cartModel cm) {
        printData(cm);
        addCartActions(cm);

    }

    @Test(dataProvider = "sqlDp")
    public void addQToCart (cartModel cm) {
        printQData(cm);
        addCartQActions(cm);

    }

    @Test(dataProvider = "sqlDp")
    public void addPlusToCart (cartModel cm) {

        addCartPlusActions(cm);

    }
}

