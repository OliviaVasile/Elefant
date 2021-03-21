package tests;

import models.searchModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.SearchPage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static utils.OtherUtils.sanitizeNullDbString;

public class SearchTest extends BaseTest {

    //    @DataProvider(name = "csvDp")
//    public Iterator<Object[]> csvDp1Collection ( ) throws IOException {
//        Collection<Object[]> dp = new ArrayList<>();
//        File f = new File("src\\test\\resources\\data\\search.csv");
//        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
//        CSVReader csvReader = new CSVReader(reader);
//        List<String[]> csvData = csvReader.readAll();
//        for (int i = 0; i < csvData.size(); i++) {
//
//            dp.add(new Object[]{new SearchModel(csvData.get(i)[0] ,
//                    csvData.get(i)[1]
//            )});
//        }
//        return dp.iterator();
//    }

    private void printNegativeData (searchModel sm) {
        System.out.println(sm.getKeyword());
        System.out.println(sm.getResult());

    }
    //validate that "Ne pare rău, dar nu au fost găsite rezultate care să corespundă termenilor dvs.
// de căutare. Vă rugăm să verificați ortografia sau să adăugați un cuvânt alternativ.
// De asemenea, puteți verifica produsele de mai jos care v-ar putea interesa." message is present on page
    private void searchNegativeActions (searchModel sm) {
        SearchPage sp = new SearchPage(driver);
        sp.openSearchPage(hostname);
        sp.search(sm.getKeyword() , sm.getResult());
        Assert.assertEquals(sm.getResult() , sp.getResult());
    }
//not present on page
    private void printPositiveData (searchModel sm) {
        System.out.println(sm.getKeyword());

    }



    private void searchPositiveActions (searchModel sm)  {
        SearchPage sp = new SearchPage(driver);
        sp.openSearchPage(hostname);
        sp.search(sm.getKeyword() , sm.getResult());
        Assert.assertTrue(sp.verifyElementAbsent());
    }

    @DataProvider(name = "sqlDp")
    public Iterator<Object[]> sqlDpCollection ( ) {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema ,
                    dbUsername , dbPassword);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM automation.search;");
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
            ResultSet results = statement.executeQuery("SELECT * FROM automation.positivesearch;");
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



    @Test(dataProvider = "sqlDp")
    public void searchNegative (searchModel sm) {
        printNegativeData(sm);
        searchNegativeActions(sm);
    }

    //validate that "Ne pare rău, dar nu au fost găsite rezultate care să corespundă termenilor dvs.
// de căutare. Vă rugăm să verificați ortografia sau să adăugați un cuvânt alternativ.
// De asemenea, puteți verifica produsele de mai jos care v-ar putea interesa." message is not present
    @Test(dataProvider = "sqlDp1")
    public void searchPositive (searchModel sm) {
        printPositiveData(sm);
        searchPositiveActions(sm);
    }

}
