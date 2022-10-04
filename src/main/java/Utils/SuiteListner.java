package Utils;

import com.aventstack.extentreports.utils.FileUtil;
import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

import static PageClasses.BaseCLass.driver;

public class SuiteListner implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
    String filename = System.getProperty("user.dir")+ File.separator + "Screenshots" + File.separator + result.getMethod().getMethodName();
    File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

    try {
       FileUtils.getFileUtils().copyFile(f, new File(filename + ".png"));
    }
    catch (IOException ie){
        ie.printStackTrace();
    }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

}
