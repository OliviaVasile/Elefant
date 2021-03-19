package tests;

import models.CartModel;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.CartPage;
import pageObject.CheckoutPage;
import pageObject.FilterPage;
import pageObject.ProductPage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static utils.OtherUtils.sanitizeNullDbString;

public class CartUITest extends BaseUITest {

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

    @Test(dataProvider = "sqlDp")
//validate that Continua to checkout button is not displayed after deleting the cart
    public void ContinuaButton (CartModel cm) throws Exception {
        CartPage cp = new CartPage(driver);
        ProductPage pp = new ProductPage(driver);
        FilterPage fp = new FilterPage(driver);
        CheckoutPage ckp = new CheckoutPage();
        fp.openFilterPage(hostname);
        pp.addQToCart(cm.getQty() , cm.getMesaj());
        cp.deleteCart();
        Assert.assertTrue(cp.checkContinuaAfterDeleteCart());

    }
}
