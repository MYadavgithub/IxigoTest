package PageClasses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static Utils.Constants.browser;
import static Utils.Constants.url;

public class BaseCLass {
    public static WebDriver driver;

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;

    @BeforeTest
    public void initialisingHtmlReporting() throws IOException {
        htmlReporter = new ExtentHtmlReporter(
                System.getProperty("user.dir") + File.separator + "reports" + File.separator + "AutomationReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Ixigo Automation Report");
        htmlReporter.config().setReportName("Ixigo Automation Results");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("SDET", "Mayank Yadav");
           //delete all screenshots captured in previous run
            File directory = new File(System.getProperty("user.dir")+File.separator+"Screenshots");
            FileUtils.cleanDirectory(directory);

    }


    public void initialisation(String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            String chromedriverpath = System.getProperty("user.dir")+ File.separator + "Drivers" + File.separator + "chromedriver.exe";
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--disable-notifications");
            System.setProperty("webdriver.chrome.driver",chromedriverpath);
            driver = new ChromeDriver(opt);
        }
        else if(browserName.equalsIgnoreCase("mozilla")){
            String chromedriverpath = System.getProperty("user.dir")+ File.separator + "Drivers" + File.separator + "geckodriver";
            System.setProperty("webdriver.gecko.driver",chromedriverpath);
            driver = new FirefoxDriver();
        }
    }

    @BeforeMethod()
    public void beforeMethod(Method testMethod){
        initialisation(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        driver.switchTo().defaultContent();
        logger = extent.createTest(testMethod.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if (result.getStatus() == ITestResult.SUCCESS) {
            String methodName = result.getMethod().getMethodName();
            String logText = " Test Case : "  + methodName+ " Passed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS, m);
        } else if (result.getStatus() == ITestResult.FAILURE) {
            String methodName = result.getMethod().getMethodName();
            String logText = " Test Case : "  + methodName+ " Failed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            logger.log(Status.FAIL, m);
        } else if (result.getStatus() == ITestResult.SKIP) {
            String methodName = result.getMethod().getMethodName();
            String logText = " Test Case : "  + methodName+ " Skipped";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
            logger.log(Status.SKIP, m);
        }

        driver.quit();
    }

    @AfterTest
    public void AfterTest(){
        extent.flush();
    }

    public static void OutputLogger(String logMessage){
        System.out.println(logMessage);
        Reporter.log(logMessage);
        logger.info(logMessage);
    }




}
