package tests;

import com.opencsv.CSVReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.FilterPage;
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

public class FilterUITest extends BaseUITest {

    @Test
    public void Filter ( ) {
        FilterPage fp = new FilterPage(driver);
        fp.openFilterPage(hostname);
        Assert.assertEquals(fp.filter() , fp.numberOfProducts());
    }

}
