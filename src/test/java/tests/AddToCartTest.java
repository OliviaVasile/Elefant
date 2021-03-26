package tests;

import models.cartModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.CartPage;
import pageObject.ProductPage;
import pageObject.SearchPage;
import utils.SeleniumUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static utils.OtherUtils.sanitizeNullDbString;

public class AddToCartTest extends BaseTest {

    //validate that just clicking on "adauga in cos"=> the price and qty in cart are properly updated
    //validate that after deleting the cart Continue button is not displayed
    @Test(dataProvider = "sqlDp")
    public void addToCart (cartModel cm) {
        printData(cm);
        addCartActions(cm);

    }

    // qty inserted on product page is the same with number of products in the cart
    // qty inserted on product page * price from the product page = final price
    //after deleting the cart Continue button is not displayed
    @Test(dataProvider = "sqlQtyDp")
    public void addQtyToCart (cartModel cm) {
        printQtyData(cm);
        addCartQtyActions(cm);

    }

    //validate that if the product not in stock add button is not displayed
    @Test(dataProvider = "sqlNotInStockDp")
    public void addCartNotInStock (cartModel cm) {

        addCartNotInStockActions(cm);

    }

    //validate that using "+" for qty will properly increment the qty & price in cart
    @Test(dataProvider = "sqlPlusDp")
    public void addCartPlus (cartModel cm) {

        addCartPlusActions(cm);

    }

    private void printData (cartModel cm) {

        System.out.println(cm.getMesaj());

    }

    private void printQtyData (cartModel cm) {
        System.out.println(cm.getQty());
        System.out.println(cm.getMesaj());
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
                cm.setQty(sanitizeNullDbString(results.getString("qty")));
                cm.setMesaj(sanitizeNullDbString(results.getString("mesaj")));
                cm.setPret(sanitizeNullDbString(results.getString("pret")));

                dp.add(new Object[]{cm});
            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dp.iterator();
    }

    @DataProvider(name = "sqlQtyDp")
    public Iterator<Object[]> sqlQtyDpCollection ( ) {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema ,
                    dbUsername , dbPassword);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM automation.addcartqty;");
            while (results.next()) {
                cartModel cm = new cartModel();
                cm.setKeyword(sanitizeNullDbString(results.getString("keyword")));
                cm.setQty(sanitizeNullDbString(results.getString("qty")));
                cm.setMesaj(sanitizeNullDbString(results.getString("mesaj")));
                cm.setPret(sanitizeNullDbString(results.getString("pret")));
                cm.setPretTotalProdus(sanitizeNullDbString(results.getString("prettotalprodus")));

                dp.add(new Object[]{cm});
            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dp.iterator();
    }

    @DataProvider(name = "sqlNotInStockDp")
    public Iterator<Object[]> sqlNotInStockDpCollection ( ) {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema ,
                    dbUsername , dbPassword);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM automation.addcartnotinstock;");
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


    @DataProvider(name = "sqlPlusDp")
    public Iterator<Object[]> sqlPlusDpCollection ( ) {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema ,
                    dbUsername , dbPassword);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM automation.addcartplus;");
            while (results.next()) {
                cartModel cm = new cartModel();
                cm.setKeyword(sanitizeNullDbString(results.getString("keyword")));
                cm.setQty(sanitizeNullDbString(results.getString("qty")));
                cm.setPretTotalProdus(sanitizeNullDbString(results.getString("prettotalprodus")));
                cm.setTotalGeneral(sanitizeNullDbString(results.getString("totalprice")));
                cm.setTotalProduseInCos(sanitizeNullDbString(results.getString("updatedqty")));
                dp.add(new Object[]{cm});
            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dp.iterator();
    }

    private void addCartActions (cartModel cm) {
        CartPage cp = new CartPage(driver);
        SearchPage sp = new SearchPage(driver);
        ProductPage pp = new ProductPage(driver);
        sp.openSearchPage(hostname);
        sp.search(cm.getKeyword() , cm.getResult());
        sp.pickElement();
        pp.addToCart();
//        Assert.assertEquals(pp.productTitle() , cp.productTitle());
        Assert.assertEquals(cm.getQty() , cp.numberOfProducts());
        Assert.assertEquals(cm.getPret() , pp.priceValue() , cp.priceValue());
        cp.deleteCart();
        Assert.assertFalse(cp.continuaAfterDeleteCart());
    }


    private void addCartQtyActions (cartModel cm) {
        CartPage cp = new CartPage(driver);
        SearchPage sp = new SearchPage(driver);
        ProductPage pp = new ProductPage(driver);
        sp.openSearchPage(hostname);
        sp.search(cm.getKeyword() , cm.getResult());
        sp.pickElement();
        pp.addQToCart(cm.getQty() , cm.getMesaj());
        Assert.assertEquals((cm.getQty()) , (cp.numberOfProducts()));
        Assert.assertEquals((java.util.Optional.of(SeleniumUtils.turnToInteger(cm.getQty()))) , (java.util.Optional.of(cp.numberOfProductsAfterPlus())));
        Assert.assertEquals((cm.getPretTotalProdus()) , (cp.totalProductPrice()));
//        Assert.assertEquals(java.util.Optional.of((Integer.parseInt(cm.getQty()) * (Integer.parseInt(cm.getPret())))) ,(java.util.Optional.of(cp.numberOfProductsAfterPlus())));
        cp.deleteCart();
        Assert.assertFalse(cp.continuaAfterDeleteCart());
    }


    private void addCartNotInStockActions (cartModel cm) {

        SearchPage sp = new SearchPage(driver);
        ProductPage pp = new ProductPage(driver);
        sp.openSearchPage(hostname);
        sp.search(cm.getKeyword() , cm.getResult());
        sp.pickElement();
        Assert.assertFalse(pp.checkAddButton());
        Assert.assertTrue(pp.checkNotInStock());
    }


    private void addCartPlusActions (cartModel cm) {
        CartPage cp = new CartPage(driver);
        SearchPage sp = new SearchPage(driver);
        ProductPage pp = new ProductPage(driver);
        sp.openSearchPage(hostname);
        sp.search(cm.getKeyword() , cm.getResult());
        sp.pickElement();
        pp.addPlusCart();
        Assert.assertEquals(java.util.Optional.of((Integer.parseInt(cm.getQty()) + 1)) , (java.util.Optional.of(cp.numberOfProductsAfterPlus())));
        cp.deleteCart();

    }

//    private void addMoreThanOneProductsActions (cartModel cm) throws Exception {
//        CartPage cp = new CartPage(driver);
//        SearchPage sp = new SearchPage(driver);
//        ProductPage pp = new ProductPage(driver);
//        HomePage hp = new HomePage(driver);
//        sp.openSearchPage(hostname);
//        sp.search(cm.getKeyword(), cm.getResult());
//        sp.pickElement();
//        pp.addPlusCart();
//        driver.get(hostname);
//        Assert.assertEquals(cm.getTotalGeneral(),hp.generalPrice());
//        Assert.assertEquals(cm.getTotalProduseInCos(), hp.updatedQty());

//    }


//    @Test(dataProvider = "sqlPlusDp")
//    public void addCartMoreThanOneProduct (cartModel cm) throws Exception {
//        addMoreThanOneProductsActions(cm);
//    }

}

