package tests;

import com.opencsv.CSVReader;
import models.CartModel;
import models.SearchModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.CartPage;
import pageObject.SearchPage;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CartUITest extends BaseUITest{

    private void printNegativeData (CartModel cm) {
        System.out.println(cm.getQty());
        System.out.println(cm.getMesaj());

    }

    private void addCartNegativeActions (CartModel cm) {
        CartPage cp = new CartPage(driver);
        cp.openCart(hostname);

        cp.cartPage(cm.getQty() , cm.getMesaj());
        Assert.assertEquals(cm.getMesaj() , cp.getResult());
    }

        @DataProvider(name = "csvDp")
    public Iterator<Object[]> csvDp1Collection ( ) throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\addCartNegative.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {

            dp.add(new Object[]{new CartModel(csvData.get(i)[0] ,
                    csvData.get(i)[1]
            )});
        }
        return dp.iterator();
    }

    @Test (dataProvider = "csvDp")
    public void addToCartNegative(CartModel cm){
        printNegativeData(cm);
        addCartNegativeActions(cm);

    }
}
