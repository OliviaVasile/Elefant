package tests;

import com.opencsv.CSVReader;
import models.contactModel;
import models.loginModel;
import models.registerModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.ContactPage;
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

public class ContactTest extends BaseTest {


    @Test(dataProvider = "csvNDp")
    public void contactNegativeCsv (contactModel ctm) {
        ContactPage ctp = new ContactPage(driver);
        ctp.openContactPage(hostname);
        ctp.contact(ctm.getName(),ctm.getPhoneNo(),ctm.getEmail(),ctm.getInvoice(),ctm.getDetails());
        Assert.assertTrue(ctp.checkErr(ctm.getNameErr() , "nameError"));
        Assert.assertTrue(ctp.checkErr(ctm.getPhoneErr(), "phoneError"));
        Assert.assertTrue(ctp.checkErr(ctm.getEmailErr(), "emailError"));
        Assert.assertTrue(ctp.checkErr(ctm.getInvoiceErr(), "invoiceError"));
        Assert.assertTrue(ctp.checkErr(ctm.getValidEmailErr(), "validEmailError"));
    }
    @Test(dataProvider = "sqlNDp")
    public void contactNegativeSql (contactModel ctm) {
        ContactPage ctp = new ContactPage(driver);
        ctp.openContactPage(hostname);
        ctp.contact(ctm.getName(),ctm.getPhoneNo(),ctm.getEmail(),ctm.getInvoice(),ctm.getDetails());
        Assert.assertTrue(ctp.checkErr(ctm.getNameErr() , "nameError"));
        Assert.assertTrue(ctp.checkErr(ctm.getPhoneErr(), "phoneError"));
        Assert.assertTrue(ctp.checkErr(ctm.getEmailErr(), "emailError"));
        Assert.assertTrue(ctp.checkErr(ctm.getInvoiceErr(), "invoiceError"));
        Assert.assertTrue(ctp.checkErr(ctm.getValidEmailErr(), "validEmailError"));
    }

    @Test(dataProvider = "csvDp")
    public void contactPositive (contactModel ctm) {
        ContactPage ctp = new ContactPage(driver);
        ctp.openContactPage(hostname);
        ctp.contact(ctm.getName(),ctm.getPhoneNo(),ctm.getEmail(),ctm.getInvoice(),ctm.getDetails());
        Assert.assertTrue(ctp.checkConfirmation());

    }


    @DataProvider(name = "csvNDp")
    public Iterator<Object[]> csvNDpCollection ( ) throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\contactNegativ.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {
            dp.add(new Object[]{new contactModel(csvData.get(i)[0] ,
                    csvData.get(i)[1] ,
                    csvData.get(i)[2] ,
                    csvData.get(i)[3] ,
                    csvData.get(i)[4] ,
                    csvData.get(i)[5] ,
                    csvData.get(i)[6] ,
                    csvData.get(i)[7] ,
                    csvData.get(i)[8] ,
                    csvData.get(i)[9]

            )});
        }
        return dp.iterator();
    }

    @DataProvider(name = "csvDp")
    public Iterator<Object[]> csvDpCollection ( ) throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\contactPoz.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {
            dp.add(new Object[]{new contactModel(csvData.get(i)[0] ,
                    csvData.get(i)[1] ,
                    csvData.get(i)[2] ,
                    csvData.get(i)[3] ,
                    csvData.get(i)[4] ,
                    csvData.get(i)[5] ,
                    csvData.get(i)[6] ,
                    csvData.get(i)[7] ,
                    csvData.get(i)[8] ,
                    csvData.get(i)[9]

            )});
        }
        return dp.iterator();
    }


    @DataProvider(name = "sqlNDp")
    public Iterator<Object[]> sqlNDpCollection ( ) {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema ,
                    dbUsername , dbPassword);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM automation.contactnegative;");
            while (results.next()) {
                contactModel ctm = new contactModel();
                ctm.setName(sanitizeNullDbString(results.getString("name")));
                ctm.setPhoneNo(sanitizeNullDbString(results.getString("phonenumber")));
                ctm.setEmail(sanitizeNullDbString(results.getString("email")));
                ctm.setInvoice(sanitizeNullDbString(results.getString("invoice")));
                ctm.setNameErr(sanitizeNullDbString(results.getString("nameerror")));
                ctm.setPhoneErr(sanitizeNullDbString(results.getString("phoneerror")));
                ctm.setEmailErr(sanitizeNullDbString(results.getString("emailerror")));
                ctm.setInvoiceErr(sanitizeNullDbString(results.getString("invoiceerror")));
                ctm.setValidEmailErr(sanitizeNullDbString(results.getString("valideerror")));
                ctm.setDetails(sanitizeNullDbString(results.getString("details")));


                dp.add(new Object[]{ctm});

            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dp.iterator();

    }


}
