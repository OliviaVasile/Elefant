package reports;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListenerCustom extends TestListenerAdapter {

    public void onTestStart(ITestResult result) {
        String testMethodName = result.getMethod().getMethodName();
        String testDescription = result.getMethod().getDescription();
        System.out.println();
        System.out.println("START TEST: " + testMethodName);
        if (testDescription != null) {
            if (!testDescription.isEmpty()) {
                System.out.println("Description: " + testDescription);
            }
        }
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed:" + result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed:" + result.getMethod().getMethodName());
    }
}

