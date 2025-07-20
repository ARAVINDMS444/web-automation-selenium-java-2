package utils;

import baseTest.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
  private ActionsUtil actionsUtil;

  @Override
  public void onTestStart(ITestResult iTestResult) {}

  @Override
  public void onTestSuccess(ITestResult iTestResult) {
    System.out.println("TEST PASSED: " + iTestResult.getMethod().getMethodName());
  }

  @Override
  public void onTestFailure(ITestResult iTestResult) {
    System.out.println("TEST FAILED: " + iTestResult.getMethod().getMethodName());

    Object currentClass = iTestResult.getInstance();
    WebDriver driver = ((BaseTest) currentClass).getDriver();

    try {
      actionsUtil = new ActionsUtil(driver);
      actionsUtil.takePageScreenshot(
          System.getProperty("user.dir")
              + "/test-artifacts/screenshots/"
              + iTestResult.getMethod().getMethodName()
              + ".png");
    } catch (Exception e) {
      System.out.println("Screenshot capture failed: " + e.getMessage());
    } finally {
      if (driver != null) {
        driver.quit();
      }
    }
  }

  @Override
  public void onTestSkipped(ITestResult iTestResult) {}

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {}

  @Override
  public void onStart(ITestContext iTestContext) {}

  @Override
  public void onFinish(ITestContext iTestContext) {}
}
