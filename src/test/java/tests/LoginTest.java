package tests;

import com.opencsv.CSVReader;
import models.accountModel;
import models.loginModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.LoginPage;
import pageObject.ProfilePage;

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

public class LoginTest extends BaseTest {


    private void printData (loginModel lm) {
        System.out.println(lm.getAccount().getUsername());
        System.out.println(lm.getAccount().getPassword());
        System.out.println(lm.getUserError());
        System.out.println(lm.getPasswordError());
        System.out.println(lm.getGeneralError());
    }
    private void printPositiveData (loginModel lm) {
        System.out.println(lm.getAccount().getUsername());
        System.out.println(lm.getAccount().getPassword());
    }

    private void loginActions (loginModel lm) {
        LoginPage lp = new LoginPage(driver);
        lp.openLoginPage(hostname);
        lp.login(lm.getAccount().getUsername() , lm.getAccount().getPassword());
        Assert.assertTrue(lp.checkErr(lm.getGeneralError() , "generalErr"));
        Assert.assertTrue(lp.checkErr(lm.getUserError() , "userErr"));
        Assert.assertTrue(lp.checkErr(lm.getPasswordError() , "passErr"));

    }
    private void loginPositiveActions (loginModel lm) {
        LoginPage lp = new LoginPage(driver);
        lp.openLoginPage(hostname);
        lp.login(lm.getAccount().getUsername() , lm.getAccount().getPassword());
        ProfilePage profilePage = new ProfilePage(driver);
        System.out.println("Logged in user :" + profilePage.getUser());
        Assert.assertEquals(lm.getAccount().getUsername() , profilePage.getUser());
        profilePage.logOut();
    }

    @DataProvider(name = "csvDp")
    public Iterator<Object[]> csvDpCollection ( ) throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\loginNeg.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {
            dp.add(new Object[]{new loginModel(csvData.get(i)[0] ,
                    csvData.get(i)[1] ,
                    csvData.get(i)[2] ,
                    csvData.get(i)[3] ,
                    csvData.get(i)[4])});
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
            ResultSet results = statement.executeQuery("SELECT * FROM automation.authentication;");
            while (results.next()) {
                accountModel am = new accountModel();
                am.setUsername(sanitizeNullDbString(results.getString("username")));
                am.setPassword(sanitizeNullDbString(results.getString("password")));
                loginModel lm = new loginModel();
                lm.setAccount(am);
                lm.setUserError(sanitizeNullDbString(results.getString("userError")));
                lm.setPasswordError(sanitizeNullDbString(results.getString("passwordError")));
                lm.setGeneralError(sanitizeNullDbString(results.getString("generalError")));
                dp.add(new Object[]{lm});


//                dp.add(new Object[]{new LoginModel(
//                        results.sanitizeNullDbString(results.getString("username")),
//                        results.sanitizeNullDbString(results.getString("password")),
//                        results.sanitizeNullDbString(results.getString("userError")),
//                        results.sanitizeNullDbString(results.getString("passwordError")),
//                        results.sanitizeNullDbString(results.getString("generalError")),

            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dp.iterator();

    }

    @DataProvider(name = "csvDp1")
    public Iterator<Object[]> csvDp1Collection ( ) throws IOException {
        Collection<Object[]> dp1 = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\loginPoz.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {
            dp1.add(new Object[]{new loginModel(csvData.get(i)[0] ,
                    csvData.get(i)[1] ,
                    csvData.get(i)[2] ,
                    csvData.get(i)[3] ,
                    csvData.get(i)[4])});
        }
        return dp1.iterator();
    }
    @DataProvider(name = "sqlDp1")
    public Iterator<Object[]> sqlDp1Collection ( ) {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema ,
                    dbUsername , dbPassword);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM automation.positiveauthentication;");
            while (results.next()) {
                accountModel am = new accountModel();
                am.setUsername(sanitizeNullDbString(results.getString("username")));
                am.setPassword(sanitizeNullDbString(results.getString("password")));
                loginModel lm = new loginModel();
                lm.setAccount(am);
                lm.setUserError(sanitizeNullDbString(results.getString("userError")));
                lm.setPasswordError(sanitizeNullDbString(results.getString("passwordError")));
                lm.setGeneralError(sanitizeNullDbString(results.getString("generalError")));
                dp.add(new Object[]{lm});

            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dp.iterator();

    }

    @Test(dataProvider = "csvDp1")
    public void csvLoginPositive (loginModel lm) {
        printPositiveData(lm);
        loginPositiveActions(lm);
    }
    @Test(dataProvider = "sqlDp1")
    public void sqlLoginPositive (loginModel lm) {
        printPositiveData(lm);
        loginPositiveActions(lm);
    }

    @Test(dataProvider = "csvDp")
    public void csvLoginNegative (loginModel lm) {
        printData(lm);
        loginActions(lm);
    }
    @Test(dataProvider = "sqlDp")
    public void sqlLoginNegative (loginModel lm) {
        printData(lm);
        loginActions(lm);
    }




}
