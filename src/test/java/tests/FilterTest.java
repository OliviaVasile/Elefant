package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.FilterPage;

public class FilterTest extends BaseTest {

    //validate that the number of displayed results is the same with the number of displayed products

    @Test
    public void Filter ( ) {
        FilterPage fp = new FilterPage(driver);
        fp.openFilterPage(hostname);
        Assert.assertEquals(fp.filterResults() , fp.numberOfProducts());
    }

}
