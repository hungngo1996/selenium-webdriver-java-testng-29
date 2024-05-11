package selenium.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReport implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("result = " + "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("result = " + "Fail");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("result = " + "Skip");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("context = " + "before class test");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("context = " + "after class tesst");
    }
}
