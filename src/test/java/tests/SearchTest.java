package tests;

import models.searchModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.ProductPage;
import pageObject.SearchPage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static utils.OtherUtils.sanitizeNullDbString;

public class SearchTest extends BaseTest {

    //validate that user receives the message with no results
    @Test(dataProvider = "sqlDp")
    public void searchNegative (searchModel sm) {
        printNegativeData(sm);
        searchNegativeActions(sm);
    }

    //validate that message with no results is not present on page;
    //validate the product title contains the search keyword
    @Test(dataProvider = "sqlDp1")
    public void searchPositive (searchModel sm) {
        printPositiveData(sm);
        searchPositiveActions(sm);
    }

    @DataProvider(name = "sqlDp")
    public Iterator<Object[]> sqlDpCollection ( ) {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema ,
                    dbUsername , dbPassword);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM automation.searchnegative;");
            while (results.next()) {
                searchModel sm = new searchModel();
                sm.setKeyword(sanitizeNullDbString(results.getString("keyword")));
                sm.setResult(sanitizeNullDbString(results.getString("result")));
                dp.add(new Object[]{sm});
            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dp.iterator();
    }

    @DataProvider(name = "sqlDp1")
    public Iterator<Object[]> sqlDp1Collection ( ) {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema ,
                    dbUsername , dbPassword);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM automation.searchpositive;");
            while (results.next()) {
                searchModel sm = new searchModel();
                sm.setKeyword(sanitizeNullDbString(results.getString("keyword")));
//                sm.setResult(sanitizeNullDbString(results.getString("result")));
                sm.setProductTitle(sanitizeNullDbString(results.getString("producttitle")));
                dp.add(new Object[]{sm});
            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dp.iterator();
    }


    private void printNegativeData (searchModel sm) {
        System.out.println(sm.getKeyword());
        System.out.println(sm.getResult());

    }

    private void printPositiveData (searchModel sm) {
        System.out.println(sm.getKeyword());
    }


    private void searchNegativeActions (searchModel sm) {
        SearchPage sp = new SearchPage(driver);
        sp.openSearchPage(hostname);
        sp.search(sm.getKeyword() , sm.getResult());
        Assert.assertEquals(sp.getResult() , sm.getResult() );
    }


    private void searchPositiveActions (searchModel sm) {
        SearchPage sp = new SearchPage(driver);
        ProductPage pp = new ProductPage(driver);
        sp.openSearchPage(hostname);
        sp.search(sm.getKeyword() , sm.getResult());
        Assert.assertTrue(sp.verifyMissingMessage());
        sp.pickElement();
        Assert.assertEquals(pp.productTitle() , sm.getProductTitle());
    }


}
