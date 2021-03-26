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

public class LogoutTest extends BaseTest {

    //verify url after logout ! "https://www.delimano.ro/customer/account/";
    // validate that backing after logout does not redirect the user into account
    @Test(dataProvider = "csvDp1")
    public void csvLogoutTest (loginModel lm) {

        logoutActions(lm);
    }

    @Test(dataProvider = "sqlDp1")
    public void sqlLogoutTest (loginModel lm) {

        logoutActions(lm);
    }

    @DataProvider(name = "sqlDp1")
    public Iterator<Object[]> sqlDp1Collection ( ) {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema ,
                    dbUsername , dbPassword);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM automation.loginpositive;");
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
                    csvData.get(i)[4]

            )});

        }
        return dp1.iterator();
    }


    public void logoutActions (loginModel lm) {
        LoginPage lp = new LoginPage(driver);
        lp.openLoginPage(hostname);
        lp.login(lm.getAccount().getUsername() , lm.getAccount().getPassword());
        ProfilePage profilePage = new ProfilePage(driver);
        System.out.println("Logged in user :" + profilePage.getUser());
        profilePage.logOut1();
        Assert.assertTrue(profilePage.logOut1());
        profilePage.backAfterLogOut();
        Assert.assertFalse(profilePage.backAfterLogOut());
    }


}

