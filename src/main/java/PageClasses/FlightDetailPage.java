package PageClasses;

import Utils.ElementsFetch;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static PageObjects.FlightDetailsPageObjects.*;

public class FlightDetailPage extends BaseCLass {


    ElementsFetch elementsFetch = new ElementsFetch();
    public LinkedHashMap<Integer, HashMap<String,String>> linkedHashMap_srcTODest = new LinkedHashMap<>();
    public LinkedHashMap<Integer, HashMap<String,String>> linkedHashMap_destTOSrc = new LinkedHashMap<>();

    public void TopCheapestFlights() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Thread.sleep(5000);
        List<WebElement> cheapests = elementsFetch.getListWebElements("xpath", cheapestbuttons);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(cheapests));
        for (int i = 0; i < cheapests.size(); i++) {
            cheapests.get(i).click();
        }
        GetSrcTOdestinationFlights();
        GetDestinationToSrcFlights();
        OutputLogger("Top Cheapest flights from Source to Desctination :");
        for(Map.Entry<Integer,HashMap<String,String>> entry : linkedHashMap_srcTODest.entrySet()){
            OutputLogger(""+entry.getKey()+" flight details are");
            OutputLogger(entry.getValue().toString());
        }

        OutputLogger("------------------------------------------------");
        OutputLogger("Top Cheapest flights from Destination to Source :");
        for(Map.Entry<Integer,HashMap<String,String>> entry : linkedHashMap_destTOSrc.entrySet()){
            OutputLogger(""+entry.getKey()+" flight details are");
            OutputLogger(entry.getValue().toString());
        }
        softAssert.assertAll();
    }

    public void GetSrcTOdestinationFlights() throws InterruptedException {
        int count = 1;
        loop:
        for (int i = 0; i < elementsFetch.getListWebElements("xpath", source_TO_Destination).size(); i++) {
            Thread.sleep(5000);
            String flightinfo = elementsFetch.getListWebElements("xpath", source_TO_Destination).get(i).getText();
            String[] strings = flightinfo.split("\n");
            HashMap<String,String> map = new HashMap<>();
            if(strings.length>0){
                String timegroup = strings[0];
                String flightnumber = strings[1];
                String duration = strings[2];
                String type = strings[3];
                String price = strings[4];
                map.put("time-group",timegroup);
                map.put("flight number",flightnumber);
                map.put("type",type);
                map.put("duration",duration);
                map.put("price",price);
            }
            linkedHashMap_srcTODest.put(count,map);
            count++;
            if(count>20){
                break loop;
            }
        }
    }

    public void GetDestinationToSrcFlights() {
        //List<WebElement> Returnflights = elementsFetch.getListWebElements("xpath", Destination_TO_source);
        int count = 1;
        loop:
        for (int i = 0; i < elementsFetch.getListWebElements("xpath", Destination_TO_source).size(); i++) {
            String flightinfo = elementsFetch.getListWebElements("xpath", Destination_TO_source).get(i).getText();
            String[] strings = flightinfo.split("\n");
            HashMap<String,String> map = new HashMap<>();
            if(strings.length>0){
                String timegroup = strings[0];
                String flightnumber = strings[1];
                String duration = strings[2];
                String type = strings[3];
                String price = strings[4];
                map.put("time-group",timegroup);
                map.put("flight number",flightnumber);
                map.put("type",type);
                map.put("duration",duration);
                map.put("price",price);
            }
            linkedHashMap_destTOSrc.put(count,map);
            count++;
            if(count>20){
                break loop;
            }

        }

    }
}
