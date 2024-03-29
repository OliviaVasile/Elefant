package tests;

import com.opencsv.CSVReader;
import models.productModel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.FilterPage;
import pageObject.ProductPage;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ProductPageTest extends BaseTest {

    @DataProvider(name = "csvDp")
    public Iterator<Object[]> csvDpCollection ( ) throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\product.csv");
        Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        for (int i = 0; i < csvData.size(); i++) {
            dp.add(new Object[]{new productModel(csvData.get(i)[0]
            )});
        }
        return dp.iterator();
    }

//validate that the product title from products list is the same with the product title from product page
//    @Test(dataProvider = "csvDp")
//    public void testViewProduct (productModel pm )  {
//        FilterPage fp = new FilterPage(driver);
//        ProductPage pp = new ProductPage(driver);
//        fp.openFilterPage(hostname);
//        fp.filterResults();
//        fp.pickFirstProduct();

//        Assert.assertEquals( pm.getProduct(), fp.titleFirstProduct(),pp.productTitle() );

//    }

}
