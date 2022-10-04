import PageClasses.BaseCLass;
import PageClasses.FlightDetailPage;
import PageClasses.HomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;

public class CheapestFlights extends BaseCLass {

    HomePage hp = new HomePage();
    FlightDetailPage fp = new FlightDetailPage();
    @Test
    public void Getcheapestflights() throws InterruptedException, AWTException {
        SoftAssert softAssert = new SoftAssert();
        hp.serachflights("DEL","BOM");
        fp.TopCheapestFlights();
        softAssert.assertTrue(driver.getTitle().contains("New Delhi - Mumbai, Economy Flights, roundtrip, 03 Nov - 13 Nov"),"flight search page is not coming correctly");
        softAssert.assertEquals(fp.linkedHashMap_srcTODest.size(),20,"20 cheapest flight details are not captured");
        softAssert.assertEquals(fp.linkedHashMap_destTOSrc.size(),20,"20 cheapest flight details are not captured");
        softAssert.assertAll();
    }
}
