package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import models.AccountModel;
import models.LoginModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utils.ExcelReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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

public class LoginUITest extends BaseUITest {

    @DataProvider(name = "jsonDp")
    public Iterator<Object[]> jsonDpCollection ( ) throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        File f = new File("src\\test\\resources\\data\\testdata.json");
        LoginModel lm = mapper.readValue(f , LoginModel.class);
        dp.add(new Object[]{lm});
        return dp.iterator();
    }

    @Test(dataProvider = "jsonDp")
    public void jsonTest (LoginModel lm) {
        printData(lm);
        loginActions(lm);

    }


    @DataProvider(name = "xmlDp")
    public Iterator<Object[]> xmlDpCollection ( ) {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\testdata.xml");
        LoginModel lm = unMarshalLoginModel(f);
        dp.add(new Object[]{lm});
        return dp.iterator();
    }

    private LoginModel unMarshalLoginModel (File f) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(LoginModel.class);
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            return (LoginModel) jaxbUnMarshaller.unmarshal(f);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;

        }
    }

    @Test(dataProvider = "xmlDp")
    public void xmlTest (LoginModel lm) {
        printData(lm);
        loginActions(lm);
    }

    private void printData (LoginModel lm) {
        System.out.println(lm.getAccount().getUsername());
        System.out.println(lm.getAccount().getPassword());
        System.out.println(lm.getUserError());
        System.out.println(lm.getPasswordError());
        System.out.println(lm.getGeneralError());

    }

    @DataProvider(name = "csvDp")
    public Iterator<Object[]> csvDpCollection ( ) throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\testdata.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {
            AccountModel ac = new AccountModel();
            ac.setUsername(csvData.get(i)[0]);
            ac.setPassword(csvData.get(i)[1]);
            LoginModel lm = new LoginModel();
            lm.setAccount(ac);
            lm.setUserError(csvData.get(i)[2]);
            lm.setPasswordError(csvData.get(i)[3]);
            lm.setGeneralError(csvData.get(i)[4]);
            dp.add(new Object[]{lm});

        }

        return dp.iterator();

    }

    @Test(dataProvider = "csvDp")
    public void csvTest (LoginModel lm) {
        printData(lm);
        loginActions(lm);

    }

    @DataProvider(name = "xlsDp")
    public Iterator<Object[]> xlsDpCollection ( ) throws Exception {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\testdata.xlsx");
        String[][] excelData = ExcelReader.readExcelFile(f,"sheet1", true, true);
        for (int i = 0; i < excelData.length; i++) {
            AccountModel ac = new AccountModel();
            ac.setUsername(excelData[i][0]);
            ac.setPassword(excelData[i][1]);
            LoginModel lm = new LoginModel();
            lm.setAccount(ac);
            lm.setUserError(excelData[i][2]);
            lm.setPasswordError(excelData[i][3]);
            lm.setGeneralError(excelData[i][4]);
            dp.add(new Object[]{lm});


        }
        return dp.iterator();
    }
    @Test(dataProvider = "xlsDp")
    public void xlsTest (LoginModel lm) {
        printData(lm);
        loginActions(lm);

    }

    @DataProvider (name="sqlDp")
    public Iterator<Object[]> sqlDpCollection ( )  {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema,dbUsername,dbPassword);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM automation.authentication;");
            while(results.next()){
                AccountModel am = new AccountModel();
                am.setUsername(sanitizeNullDbString(results.getString("username")));
                am.setPassword(sanitizeNullDbString(results.getString("password")));
                LoginModel lm = new LoginModel();
                lm.setAccount(am);
                lm.setUserError(sanitizeNullDbString(results.getString("userError")));
                lm.setPasswordError(sanitizeNullDbString(results.getString("passwordError")));
                lm.setGeneralError(sanitizeNullDbString(results.getString("generalError")));
                dp.add(new Object[]{lm});

            }

            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return dp.iterator();}

        @Test (dataProvider = "sqlDp")
        public void sqlTest(LoginModel lm) {
            printData(lm);
            loginActions(lm);

        }



    private void loginActions (LoginModel lm){
        LoginPage lp = new LoginPage(driver);
        lp.openLoginPage(hostname);
        lp.login(lm.getAccount().getUsername(),lm.getAccount().getPassword());
        Assert.assertTrue(lp.checkErr(lm.getGeneralError(), "generalErr"));
        Assert.assertTrue(lp.checkErr(lm.getUserError(), "userErr"));
        Assert.assertTrue(lp.checkErr(lm.getPasswordError(), "passErr"));

    }
}