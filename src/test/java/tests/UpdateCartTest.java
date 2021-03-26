package tests;

import models.cartModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static utils.OtherUtils.sanitizeNullDbString;

public class UpdateCartTest extends BaseTest {

    @Test(dataProvider = "sqlDp")
//validate that Continua to checkout button is not displayed after deleting the cart
    public void ContinuaButton (cartModel cm)  {
        CartPage cp = new CartPage(driver);
        SearchPage sp = new SearchPage(driver);
        ProductPage pp = new ProductPage(driver);
        sp.openSearchPage(hostname);
        sp.search(cm.getKeyword(), cm.getResult());
        sp.pickElement();
        pp.addToCart();
        cp.deleteCart();
        Assert.assertFalse(cp.continuaAfterDeleteCart());

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
                cm.setKeyword(sanitizeNullDbString(results.getString("keyword")));


                dp.add(new Object[]{cm});
            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dp.iterator();
    }



}
