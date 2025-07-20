package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionsUtil {

  private WebDriver driver;
  private WebDriverWait wait;

  public ActionsUtil(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
  }

  public void waitForSeconds(int seconds) {
    try {
      Thread.sleep(seconds * 1000L);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
    new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
        .until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public void waitForElementToBeClickable(By locator, int timeoutInSeconds) {
    new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
        .until(ExpectedConditions.elementToBeClickable(locator));
  }

  public String getElementText(By locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
  }

  public String getAttribute(By locator, String attributeName) {
    return driver.findElement(locator).getAttribute(attributeName);
  }

  public boolean isElementDisplayed(By locator) {
    try {
      return driver.findElement(locator).isDisplayed();
    } catch (NoSuchElementException | StaleElementReferenceException e) {
      return false;
    }
  }

  public void assertTextContains(String actual, String expected) {
    if (!actual.contains(expected)) {
      throw new AssertionError("Expected text to contain: " + expected + ", but got: " + actual);
    }
  }

  public void assertTextEquals(String actual, String expected) {
    if (!actual.equals(expected)) {
      throw new AssertionError("Expected: " + expected + ", but got: " + actual);
    }
  }

  public void assertElementVisible(By locator, int timeoutInSeconds) {
    waitForElementToBeVisible(locator, timeoutInSeconds);
    if (!isElementDisplayed(locator)) {
      throw new AssertionError("Element not visible: " + locator.toString());
    }
  }

  public void assertElementHidden(By locator, int timeoutInSeconds) {
    new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
        .until(ExpectedConditions.invisibilityOfElementLocated(locator));
  }

  public void scrollToElement(By locator) {
    WebElement element = driver.findElement(locator);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
  }

  public void scrollByPixels(int x, int y) {
    ((JavascriptExecutor) driver)
        .executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
  }

  public void switchToWindowByTitle(String expectedTitle) {
    String originalWindow = driver.getWindowHandle();
    Set<String> allWindows = driver.getWindowHandles();

    for (String window : allWindows) {
      driver.switchTo().window(window);
      if (driver.getTitle().equals(expectedTitle)) {
        return;
      }
    }

    driver.switchTo().window(originalWindow);
    throw new RuntimeException("Window with title '" + expectedTitle + "' not found");
  }

  public void switchToNewWindow() {
    String currentWindow = driver.getWindowHandle();
    Set<String> allWindows = driver.getWindowHandles();
    for (String window : allWindows) {
      if (!window.equals(currentWindow)) {
        driver.switchTo().window(window);
        return;
      }
    }
    throw new RuntimeException("No new window found to switch");
  }

  public void closeCurrentWindowAndSwitchBack(String originalWindowHandle) {
    driver.close();
    driver.switchTo().window(originalWindowHandle);
  }

  public void switchToFrame(By locator) {
    WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    driver.switchTo().frame(frame);
  }

  public void switchToFrameByIndex(int index) {
    driver.switchTo().frame(index);
  }

  public void switchToDefaultContent() {
    driver.switchTo().defaultContent();
  }

  public void selectDropdownByVisibleText(By locator, String visibleText) {
    Select select = new Select(driver.findElement(locator));
    select.selectByVisibleText(visibleText);
  }

  public void selectDropdownByValue(By locator, String value) {
    Select select = new Select(driver.findElement(locator));
    select.selectByValue(value);
  }

  public void selectDropdownByIndex(By locator, int index) {
    Select select = new Select(driver.findElement(locator));
    select.selectByIndex(index);
  }

  public void acceptAlert() {
    wait.until(ExpectedConditions.alertIsPresent()).accept();
  }

  public void dismissAlert() {
    wait.until(ExpectedConditions.alertIsPresent()).dismiss();
  }

  public String getAlertText() {
    return wait.until(ExpectedConditions.alertIsPresent()).getText();
  }

  public void sendKeysToAlert(String text) {
    wait.until(ExpectedConditions.alertIsPresent()).sendKeys(text);
  }

  public void uploadFile(By locator, String filePath) {
    driver.findElement(locator).sendKeys(filePath);
  }

  public boolean downloadFile(String downloadPath, String fileName, int timeoutSeconds) {
    File file = new File(downloadPath + File.separator + fileName);
    int waited = 0;
    while (waited < timeoutSeconds) {
      if (file.exists()) return true;
      waitForSeconds(1);
      waited++;
    }
    return false;
  }

  public void takePageScreenshot(String pathWithFilename) {
    TakesScreenshot ts = (TakesScreenshot) driver;
    File src = ts.getScreenshotAs(OutputType.FILE);
    try {
      FileHandler.copy(src, new File(pathWithFilename));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void takeElementScreenshot(By locator, String pathWithFilename) {
    WebElement element = driver.findElement(locator);
    File src = element.getScreenshotAs(OutputType.FILE);
    try {
      FileHandler.copy(src, new File(pathWithFilename));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
