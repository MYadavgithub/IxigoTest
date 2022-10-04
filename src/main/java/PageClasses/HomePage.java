package PageClasses;

import Utils.ElementsFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static PageObjects.HomePageObjects.*;

public class HomePage extends BaseCLass {

    ElementsFetch elementsFetch = new ElementsFetch();

    public static String departure_date;
    public static String return_date;
    public String Day;
    public String MonthandYear;

    public void serachflights(String source_city, String destination_city) throws InterruptedException, AWTException {
        SoftAssert softAssert = new SoftAssert();
        elementsFetch.getWebElement("xpath", roundtrip).click();
        elementsFetch.getWebElement("xpath", from).clear();
        elementsFetch.getWebElement("xpath", from).click();

        List<WebElement> fromcityList = elementsFetch.getListWebElements("css", src_city_list);
        for (int i = 0; i < fromcityList.size(); i++) {
            WebElement city = fromcityList.get(i);
            String fullname = city.findElement(By.cssSelector("div[class='city']")).getText();
            if (fullname.contains(source_city)) {
                city.findElement(By.cssSelector("div[class='city']")).click();
                break;
            }
        }
        elementsFetch.getWebElement("xpath", to).clear();
        elementsFetch.getWebElement("xpath", to).click();
        List<WebElement> tocitylist = elementsFetch.getListWebElements("css", to_city_list);
        for (int i = 0; i < tocitylist.size(); i++) {
            WebElement city = tocitylist.get(i);
            String fullname = city.findElement(By.cssSelector("div[class='city']")).getText();
            if (fullname.contains(destination_city)) {
                city.findElement(By.cssSelector("div[class='city']")).click();
                break;
            }
        }

        getDates();
        GetMonthandYear(departure_date);
        selectDepartureDate(Day,MonthandYear);
        GetMonthandYear(return_date);
        selectReturnDate(Day,MonthandYear);
        elementsFetch.getWebElement("xpath",searchbutton).click();
        softAssert.assertAll();

    }

    public void selectDepartureDate(String day, String MonthYear) throws InterruptedException, AWTException {
        elementsFetch.getWebElement("css", departure).click();
        WebElement currentmoneth = elementsFetch.getWebElement("xpath", months_table).findElement(By.xpath("div[@class='rd-month-label']"));
        if (currentmoneth.getText().equalsIgnoreCase(MonthYear)) {
            List<WebElement> rows = elementsFetch.getListWebElements("xpath", "//div[@class='rd-date']/div[1]/table/tbody/tr");
            for (int i = 0; i < rows.size(); i++) {
                List<WebElement> dates = rows.get(i).findElements(By.xpath("td"));
                for (int j = 0; j < dates.size(); j++) {
                    if (dates.get(j).getAttribute("data-date").substring(0,2).contains(day)) {
                        dates.get(j).findElement(By.xpath("div[@class='day has-info']")).click();
                        break;
                    }
                }
            }
        } else {
            elementsFetch.getWebElement("xpath", nextbutton).click();
            List<WebElement> rows = elementsFetch.getListWebElements("xpath", "//div[@class='rd-date']/div[1]/table/tbody/tr");
            outerloop:
            for (int i = 0; i < rows.size(); i++) {
                List<WebElement> dates = rows.get(i).findElements(By.xpath("td"));
                for (int j = 0; j < dates.size(); j++) {
                    if (dates.get(j).getAttribute("data-date").substring(0,2).contains(day)) {
                        WebElement date = dates.get(j).findElement(By.xpath("div[@class='day has-info']"));
                        date.getText();
                        WebDriverWait wait = new WebDriverWait(driver, 10);
                        wait.until(ExpectedConditions.elementToBeClickable(date));
                        date.click();
                        Actions actions = new Actions(driver);
                        Robot robot = new Robot();
                        robot.mouseMove(50,50);
                        actions.click().build().perform();
                        break outerloop;
                    }
                }
            }
        }
    }
    public void selectReturnDate(String day, String MonthYear) throws AWTException {
        elementsFetch.getWebElement("css", return_field).click();
        WebElement currentmoneth = elementsFetch.getWebElement("xpath", "//div[@class='rd-container flight-ret-cal extra-bottom rd-container-attachment arrow-animation']/div[2]/div[1]/div[@class='rd-month-label']");
        if (currentmoneth.getText().equalsIgnoreCase(MonthYear)) {
            List<WebElement> rows = elementsFetch.getListWebElements("xpath", "//div[@class='rd-container flight-ret-cal extra-bottom rd-container-attachment arrow-animation']/div[@class='rd-date']/div[1]/table/tbody/tr");
            outerloop:
            for (int i = 0; i < rows.size(); i++) {
                List<WebElement> dates = rows.get(i).findElements(By.xpath("td"));
                for (int j = 0; j < dates.size(); j++) {
                    if (dates.get(j).getAttribute("data-date").substring(0,2).contains(day)) {
                        WebElement date = dates.get(j).findElement(By.xpath("div[@class='day has-info']"));
                        date.getText();
                        WebDriverWait wait = new WebDriverWait(driver, 10);
                        wait.until(ExpectedConditions.elementToBeClickable(date));
                        date.click();
                        Actions actions = new Actions(driver);
                        Robot robot = new Robot();
                        robot.mouseMove(50,50);
                        actions.click().build().perform();
                        break outerloop;
                    }
                }
            }
        } else {
            elementsFetch.getWebElement("xpath", nextbutton).click();
            List<WebElement> rows = elementsFetch.getListWebElements("xpath", "//div[@class='rd-date']/div[1]/table/tbody/tr");
            for (int i = 0; i < rows.size(); i++) {
                List<WebElement> dates = rows.get(i).findElements(By.xpath("td"));
                for (int j = 0; j < dates.size(); j++) {
                   if (dates.get(j).getAttribute("data-date").substring(0,2).contains(day)) {
                       dates.get(j).findElement(By.xpath("div[@class='day has-info']")).click();
                        break;
                    }
                }
            }
        }
    }

    public void getDates() {
        TimeZone.setDefault(TimeZone.getTimeZone("IST"));
        Calendar a = Calendar.getInstance();
        a.add(Calendar.DATE, 30);
        Date date1 = a.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
        departure_date = formatter.format(date1);
        //System.out.println(strDate);
        Calendar b = Calendar.getInstance();
        b.add(Calendar.DATE, 40);
        Date date2 = b.getTime();
        SimpleDateFormat formatter_return = new SimpleDateFormat("dd-MMMM-yyyy");
        return_date = formatter_return.format(date2);
    }

    public void GetMonthandYear(String date) {
        String[] strings = date.split("-");
        Day = strings[0];
        MonthandYear = strings[1] + " " + strings[2];
    }



}
