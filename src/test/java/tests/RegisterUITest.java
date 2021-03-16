package tests;

import com.opencsv.CSVReader;
import models.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.RegisterPage;
import utils.SeleniumUtils;

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

public class RegisterUITest extends BaseUITest {

    @DataProvider(name = "csvDp")
    public Iterator<Object[]> csvDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\registerNeg.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {

            dp.add(new Object[]{new RegisterModel(
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
            ResultSet results = statement.executeQuery("SELECT * FROM automation.register;");
            while (results.next()) {
                RegisterModel rm = new RegisterModel();
                rm.setPrenume(sanitizeNullDbString(results.getString("prenume")));
                rm.setNume(sanitizeNullDbString(results.getString("nume")));
                rm.setJudet(sanitizeNullDbString(results.getString("judet")));
                rm.setOras(sanitizeNullDbString(results.getString("oras")));
                rm.setAdresa(sanitizeNullDbString(results.getString("adresa")));
                rm.setTelefon(sanitizeNullDbString(results.getString("telefon")));
                rm.setEmail(sanitizeNullDbString(results.getString("email")));
                rm.setParola(sanitizeNullDbString(results.getString("parola")));
                rm.setConfParola(sanitizeNullDbString(results.getString("parola2")));

                dp.add(new Object[]{rm});


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


    private void printData (RegisterModel rm) {
        System.out.println(rm.getPrenume());
        System.out.println(rm.getNume());
        System.out.println(rm.getJudet());
        System.out.println(rm.getOras());
        System.out.println(rm.getAdresa());
        System.out.println(rm.getTelefon());
        System.out.println(rm.getEmail());
        System.out.println(rm.getParola());
        System.out.println(rm.getConfParola());
        System.out.println(rm.getEroareNume());
        System.out.println(rm.getEroarePrenume());
        System.out.println(rm.getEroareJudet());
        System.out.println(rm.getEroareOras());
        System.out.println(rm.getEroareAdresa());
        System.out.println(rm.getEroareTelefon());
        System.out.println(rm.getEroareEmail());
        System.out.println(rm.getEroareParola());
        System.out.println(rm.getEroareConfParola());
    }

    private void registerActions (RegisterModel rm){
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

    @Test(dataProvider = "csvDp")
    public void csvTest(RegisterModel rm) {
        printData(rm);
        registerActions(rm);
    }
    @Test (dataProvider = "sqlDp")
    public void sqlTest(RegisterModel rm) {

        printData(rm);
        registerActions(rm);

    }

}
