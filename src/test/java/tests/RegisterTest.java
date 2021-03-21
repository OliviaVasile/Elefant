package tests;

import com.opencsv.CSVReader;
import models.registerModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.LoginPage;
import pageObject.ProfilePage;
import pageObject.RegisterPage;

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

public class RegisterTest extends BaseTest {

    @DataProvider(name = "csvDp")
    public Iterator<Object[]> csvDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\registerNeg.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {

            dp.add(new Object[]{new registerModel(
                    csvData.get(i)[0],
                    csvData.get(i)[1],
                    csvData.get(i)[2],
                    csvData.get(i)[3],
                    csvData.get(i)[4],
                    csvData.get(i)[5],
                    csvData.get(i)[6],
                    csvData.get(i)[7],
                    csvData.get(i)[8],
                    csvData.get(i)[9],
                    csvData.get(i)[10],
                    csvData.get(i)[11],
                    csvData.get(i)[12],
                    csvData.get(i)[13],
                    csvData.get(i)[14],
                    csvData.get(i)[15],
                    csvData.get(i)[16],
                    csvData.get(i)[17])});

        }
        return dp.iterator();
    }

    @DataProvider(name = "csvDp1")
    public Iterator<Object[]> csvDp1Collection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\registerPoz.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {

            dp.add(new Object[]{new registerModel(
                    csvData.get(i)[0],
                    csvData.get(i)[1],
                    csvData.get(i)[2],
                    csvData.get(i)[3],
                    csvData.get(i)[4],
                    csvData.get(i)[5],
                    csvData.get(i)[6],
                    csvData.get(i)[7],
                    csvData.get(i)[8],
                    csvData.get(i)[9],
                    csvData.get(i)[10],
                    csvData.get(i)[11],
                    csvData.get(i)[12],
                    csvData.get(i)[13],
                    csvData.get(i)[14],
                    csvData.get(i)[15],
                    csvData.get(i)[16],
                    csvData.get(i)[17])});

        }
        return dp.iterator();
    }


    @DataProvider (name="sqlDp")
    public Iterator<Object[]> sqlDpCollection() {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema,
                    dbUsername, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM automation.registernegative;");
            while (results.next()) {
                registerModel rm = new registerModel();
                rm.setPrenume(sanitizeNullDbString(results.getString("prenume")));
                rm.setNume(sanitizeNullDbString(results.getString("nume")));
                rm.setJudet(sanitizeNullDbString(results.getString("judet")));
                rm.setOras(sanitizeNullDbString(results.getString("oras")));
                rm.setAdresa(sanitizeNullDbString(results.getString("adresa")));
                rm.setTelefon(sanitizeNullDbString(results.getString("telefon")));
                rm.setEmail(sanitizeNullDbString(results.getString("email")));
                rm.setParola(sanitizeNullDbString(results.getString("parola")));
                rm.setConfParola(sanitizeNullDbString(results.getString("parola2")));
                rm.setEroarePrenume(sanitizeNullDbString(results.getString("eroareprenume")));
                rm.setEroareNume(sanitizeNullDbString(results.getString("eroarenume")));
                rm.setEroareJudet(sanitizeNullDbString(results.getString("eroarejudet")));
                rm.setEroareOras(sanitizeNullDbString(results.getString("eroareoras")));
                rm.setEroareAdresa(sanitizeNullDbString(results.getString("eroareadresa")));
                rm.setEroareTelefon(sanitizeNullDbString(results.getString("eroaretelefon")));
                rm.setEroareEmail(sanitizeNullDbString(results.getString("eroareemail")));
                rm.setEroareParola(sanitizeNullDbString(results.getString("eroareparola")));
                rm.setEroareConfParola(sanitizeNullDbString(results.getString("eroareparola2")));


                dp.add(new Object[]{rm});

            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dp.iterator();

    }

    @DataProvider (name="sqlDp1")
    public Iterator<Object[]> sqlDp1Collection() {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema,
                    dbUsername, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM automation.registerpositive;");
            while (results.next()) {
                registerModel rm = new registerModel();
                rm.setPrenume(sanitizeNullDbString(results.getString("prenume")));
                rm.setNume(sanitizeNullDbString(results.getString("nume")));
                rm.setJudet(sanitizeNullDbString(results.getString("judet")));
                rm.setOras(sanitizeNullDbString(results.getString("oras")));
                rm.setAdresa(sanitizeNullDbString(results.getString("adresa")));
                rm.setTelefon(sanitizeNullDbString(results.getString("telefon")));
                rm.setEmail(sanitizeNullDbString(results.getString("email")));
                rm.setParola(sanitizeNullDbString(results.getString("parola")));
                rm.setConfParola(sanitizeNullDbString(results.getString("confparola")));
                rm.setEroarePrenume(sanitizeNullDbString(results.getString("eroareprenume")));
                rm.setEroareNume(sanitizeNullDbString(results.getString("eroarenume")));
                rm.setEroareJudet(sanitizeNullDbString(results.getString("eroarejudet")));
                rm.setEroareOras(sanitizeNullDbString(results.getString("eroareoras")));
                rm.setEroareAdresa(sanitizeNullDbString(results.getString("eroareadresa")));
                rm.setEroareTelefon(sanitizeNullDbString(results.getString("eroaretelefon")));
                rm.setEroareEmail(sanitizeNullDbString(results.getString("eroareemail")));
                rm.setEroareParola(sanitizeNullDbString(results.getString("eroareparola")));
                rm.setEroareConfParola(sanitizeNullDbString(results.getString("eroareconfparola")));


                dp.add(new Object[]{rm});

            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dp.iterator();

    }

    private void printNegativeData (registerModel rm) {
        System.out.println(rm.getPrenume());
        System.out.println(rm.getNume());
        System.out.println(rm.getJudet());
        System.out.println(rm.getOras());
        System.out.println(rm.getAdresa());
        System.out.println(rm.getTelefon());
        System.out.println(rm.getEmail());
        System.out.println(rm.getParola());
        System.out.println(rm.getConfParola());
        System.out.println(rm.getEroarePrenume());
        System.out.println(rm.getEroareNume());
        System.out.println(rm.getEroareJudet());
        System.out.println(rm.getEroareOras());
        System.out.println(rm.getEroareAdresa());
        System.out.println(rm.getEroareTelefon());
        System.out.println(rm.getEroareEmail());
        System.out.println(rm.getEroareParola());
        System.out.println(rm.getEroareConfParola());
    }
    private void printPositiveData (registerModel rm) {
        System.out.println(rm.getPrenume());
        System.out.println(rm.getNume());
        System.out.println(rm.getEmail());

    }

    private void negativeRegisterActions (registerModel rm){
        RegisterPage rp = new RegisterPage(driver);
        rp.openRegisterPage(hostname);
        rp.register(rm.getPrenume(), rm.getNume(), rm.getJudet(), rm.getOras(),
                rm.getAdresa(), rm.getTelefon(),
                rm.getEmail(), rm.getParola() , rm.getConfParola());

        Assert.assertTrue(rp.checkErr(rm.getEroarePrenume(), "fnErr"));
        Assert.assertTrue(rp.checkErr(rm.getEroareNume(), "lnErr"));
        Assert.assertTrue(rp.checkErr(rm.getEroareJudet(), "judetErr"));
        Assert.assertTrue(rp.checkErr(rm.getEroareOras(), "orasErr"));
        Assert.assertTrue(rp.checkErr(rm.getEroareAdresa(), "adresaErr"));
        Assert.assertTrue(rp.checkErr(rm.getEroareTelefon(), "telefonErr"));
        Assert.assertTrue(rp.checkErr(rm.getEroareEmail(), "emailErr"));
        Assert.assertTrue(rp.checkErr(rm.getEroareParola(), "parolaErr"));
        Assert.assertTrue(rp.checkErr(rm.getEroareConfParola(), "confParolaErr"));

    }

    private void positiveRegisterActions (registerModel rm) {
        LoginPage lp = new LoginPage(driver);
        lp.openLoginPage(hostname);
        RegisterPage rp = new RegisterPage(driver);
        rp.openRegisterPage(hostname);
        rp.register(rm.getPrenume(), rm.getNume(), rm.getJudet(), rm.getOras(),
                rm.getAdresa(), rm.getTelefon(),
                rm.getEmail(), rm.getParola() , rm.getConfParola());

        ProfilePage profilePage = new ProfilePage(driver);
        //because captcha :(
//        System.out.println("Registered user :" + profilePage.getUser());
//        Assert.assertEquals(rm.getEmail() , profilePage.getUser());
//        profilePage.logOut();
    }

    @Test(dataProvider = "csvDp")
    public void csvNegativeRegister(registerModel rm) {
        printNegativeData(rm);
        negativeRegisterActions(rm);
    }
    @Test (dataProvider = "sqlDp")
    public void sqlNegativeRegister(registerModel rm) {

        printNegativeData(rm);
        negativeRegisterActions(rm);

    }
    @Test (dataProvider = "csvDp1")
    public void csvPositiveRegister(registerModel rm) {

        printPositiveData(rm);
        positiveRegisterActions(rm);

    }
    @Test (dataProvider = "sqlDp1")
    public void sqlPositiveRegister(registerModel rm) {

        printPositiveData(rm);
        positiveRegisterActions(rm);

    }

}
