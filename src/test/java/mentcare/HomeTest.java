package mentcare;

import mentcare.pageObjects.HomePO;
import mentcare.pageObjects.ReportPO;
import org.junit.Assert;
import org.junit.Test;

public class HomeTest extends SystemTest{
    @Test
    public void checkReport(){
        HomePO home = new HomePO(driver);
        home.loadPage();
        ReportPO rep = new ReportPO(home.clickReport());
        Assert.assertEquals("report", rep.getTitle().toLowerCase());
    }
}
