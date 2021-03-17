package tests;

import com.opencsv.CSVReader;
import models.LoginModel;
import models.ProductModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.FilterPage;
import pageObject.ViewProduct;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ViewProductUITest extends BaseUITest {

    @DataProvider(name = "csvDp")
    public Iterator<Object[]> csvDpCollection ( ) throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\product.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {
            dp.add(new Object[]{new ProductModel(csvData.get(i)[0]
            )});
        }
        return dp.iterator();
    }


    @Test(dataProvider = "csvDp")
    public void testViewProduct (ProductModel pm ) {

        ViewProduct vp = new ViewProduct(driver);
        vp.openViewPage(hostname);
        vp.openProduct();

        Assert.assertEquals( pm.getProduct(), vp.openProduct() );
//        Assert.assertEquals(vp.titleProduct(), vp.openProduct());

    }

}
