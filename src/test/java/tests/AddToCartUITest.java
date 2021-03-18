package tests;

import com.opencsv.CSVReader;
import models.CartModel;
import models.LoginModel;
import models.SearchModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.CartPage;
import pageObject.ProductPage;
import pageObject.SearchPage;

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

public class AddToCartUITest extends BaseUITest{

    private void printData (CartModel cm) {

        System.out.println(cm.getMesaj());

    }
    private void printQData (CartModel cm) {
        System.out.println(cm.getQty());
        System.out.println(cm.getMesaj());

    }

    private void addCartActions (CartModel cm) {
        CartPage cp = new CartPage(driver);
        ProductPage pp = new ProductPage(driver);
        pp.openProductPage(hostname);
        pp.addToCart(cm.getMesaj());
        Assert.assertTrue(cp.verifyElementPresent());
        Assert.assertEquals(cm.getMesaj() , cp.getResult());
    }

    private void addCartQActions (CartModel cm) {
        CartPage cp = new CartPage(driver);
        ProductPage pp = new ProductPage(driver);

        pp.addQToCart(cm.getQty() , cm.getMesaj());
        Assert.assertTrue(cp.verifyElementPresent());
        Assert.assertEquals(cm.getMesaj() , cp.getResult());
        Assert.assertEquals(cm.getQty() , cp.numberOfProducts());
//        Assert.assertEquals(pp.productTitle(), cp.productTitle());
    }

    private void addCartPlusActions (CartModel cm) {
        CartPage cp = new CartPage(driver);
        ProductPage pp = new ProductPage(driver);

        pp.addPlusCart();
        Assert.assertTrue(cp.verifyElementPresent());
        Assert.assertEquals(cm.getMesaj() , cp.getResult());
        Assert.assertEquals(java.util.Optional.of(Integer.valueOf(cm.getQty()) + 1) , java.util.Optional.of(cp.numberOfProductsAfterPlus()));
//        Assert.assertEquals(pp.productTitle(), cp.productTitle());
    }

        @DataProvider(name = "csvDp")
    public Iterator<Object[]> csvDp1Collection ( ) throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\adCart.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {

            dp.add(new Object[]{new CartModel(csvData.get(i)[0] ,
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
                CartModel cm = new CartModel();
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

    @Test (dataProvider = "sqlDp")
    public void addToCart (CartModel cm){
        printData(cm);
        addCartActions(cm);

    }

    @Test (dataProvider = "sqlDp")
    public void addQToCart (CartModel cm){
        printQData(cm);
        addCartQActions(cm);

    }

    @Test (dataProvider = "sqlDp")
    public void addPlusToCart (CartModel cm){

        addCartPlusActions(cm);

    }
}

