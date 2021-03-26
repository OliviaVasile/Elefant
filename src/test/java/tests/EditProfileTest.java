package tests;

import com.opencsv.CSVReader;
import models.changePasswordModel;
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


public class EditProfileTest extends BaseTest {


    //validate that user is able to change password and login with new password
    @Test(dataProvider = "csvDp")
    public void csvChangePassword (changePasswordModel cpm) {
        csvEditPasswordAct(cpm);
    }

    @DataProvider(name = "csvDp")
    public Iterator<Object[]> csvDpCollection ( ) throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\changePassword.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {
            dp.add(new Object[]{new changePasswordModel(csvData.get(i)[0] ,
                    csvData.get(i)[1] ,
                    csvData.get(i)[2] ,
                    csvData.get(i)[3]


            )});
        }
        return dp.iterator();
    }


    private void csvEditPasswordAct (changePasswordModel cpm) {
        LoginPage lp = new LoginPage(driver);
        lp.openLoginPage(hostname);
        lp.login(cpm.getUsername() , cpm.getPassword());
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.changePassword(cpm.getPassword() , cpm.getNewPassword());
        Assert.assertTrue(profilePage.contModificat());
        profilePage.logOut();
        lp.openLoginPage(hostname);
        lp.login(cpm.getUsername() , cpm.getNewPassword());
        System.out.println("Logged in user :" + profilePage.getUser());
        Assert.assertEquals(cpm.getUsername() , profilePage.getUser());
        profilePage.changePassword(cpm.getNewPassword() , cpm.getPassword());
        profilePage.logOut();
    }


}
