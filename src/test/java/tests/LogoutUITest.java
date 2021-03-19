package tests;

import com.opencsv.CSVReader;
import models.AccountModel;
import models.LoginModel;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class LogoutUITest extends BaseUITest {

//    @Test(dataProvider = "csvDp1")
//    public void logout (LoginModel lm) {
//
//     logoutActions(lm);
//    }
//    @Test (dataProvider = "csvDp1")
//
//    public void backAfterLogout(LoginModel lm) {
//        LoginPage lp = new LoginPage(driver);
//        lp.openLoginPage(hostname);
//        lp.login(lm.getAccount().getUsername() , lm.getAccount().getPassword());
//        ProfilePage profilePage = new ProfilePage(driver);
//        System.out.println("Logged in user :" + profilePage.getUser());
//        Assert.assertEquals(lm.getAccount().getUsername() , profilePage.getUser());
//
//        profilePage.logOut1();
//
//
//    }

    @DataProvider(name = "csvDp1")
    public Iterator<Object[]> csvDp1Collection ( ) throws IOException {
        Collection<Object[]> dp1 = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\loginPoz.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {

            dp1.add(new Object[]{new LoginModel(csvData.get(i)[0] ,
                    csvData.get(i)[1] ,
                    csvData.get(i)[2] ,
                    csvData.get(i)[3] ,
                    csvData.get(i)[4])});

        }
        return dp1.iterator();
    }
//verify url after logout ! "https://www.delimano.ro/customer/account/";
    //validate that backing after logout does not redirect the user into acount

    public void logoutActions(LoginModel lm){
        LoginPage lp = new LoginPage(driver);
        lp.openLoginPage(hostname);
        lp.login(lm.getAccount().getUsername() , lm.getAccount().getPassword());
        ProfilePage profilePage = new ProfilePage(driver);
        System.out.println("Logged in user :" + profilePage.getUser());
        Assert.assertEquals(lm.getAccount().getUsername() , profilePage.getUser());
        profilePage.logOut1();
        Assert.assertTrue(profilePage.logOut1());
        profilePage.backAfterLogOut();
        Assert.assertTrue(profilePage.backAfterLogOut());
    }
    @Test(dataProvider = "csvDp1")
    public void csvTest (LoginModel lm) {

        logoutActions(lm);

    }


}

