package PageObjects;

import PageClasses.HomePage;

public class HomePageObjects extends HomePage {

    public static String roundtrip = "//span[text()='Round Trip']";
    public static String from = "//div[@class='orgn u-ib u-v-align-bottom u-text-left']//input[@placeholder='Enter city or airport']";
    public static String to = "//div[@class='dstn u-ib u-v-align-bottom u-text-left']//input[@placeholder='Enter city or airport']";
    public static String departure = "input[placeholder='Depart']";
    public static String months_table = "//div[@class='rd-date']/div[1]";
    public static String nextbutton = "//button[@class='ixi-icon-arrow rd-next']";
    public static String return_field = "input[placeholder='Return']";
    public static String src_city_list = "div[class='orgn u-ib u-v-align-bottom u-text-left'] div[class='autocompleter-scroll-cntr'] div[class*='result-row flight-airport u-box-result']";
    public static String to_city_list = "div[class='dstn u-ib u-v-align-bottom u-text-left'] div[class*='result-row flight-airport u-box-result']";
    public static String searchbutton = "//button[normalize-space()='Search']";

}
