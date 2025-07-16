package SmokeTests;

import BaseTest.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.*;

@Listeners(utils.Listeners.class)

public class SeleniumActions extends BaseTest {

    WebDriverWait wait;

    public void openUrl(String url) {
        getDriver().get(url);
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void testAlerts() {
        openUrl("https://practice-automation.com/popups/");
        WebElement element = getDriver().findElement(By.xpath("//b[normalize-space()='Prompt Popup']"));
        element.click();
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = getDriver().switchTo().alert();
        alert.sendKeys("Test");
        Assert.assertEquals(alert.getText(), "Hi there, what's your name?");
        alert.dismiss();

        element.click();
        wait.until(ExpectedConditions.alertIsPresent());
        getDriver().switchTo().alert().accept();
    }

    @Test(priority = 2)
    public void testExplicitWaits() {
        openUrl("https://practice-automation.com/javascript-delays/");
        WebElement startBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start")));
        startBtn.click();
        WebElement successText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Liftoff!']")));
        Assert.assertEquals(successText.getText(), "Liftoff!");
    }

    @Test(priority = 3)
    public void testDropdown() {
        openUrl("https://practice-automation.com/form-fields/");
        Select select = new Select(getDriver().findElement(By.id("automation")));
        select.selectByVisibleText("Yes");
        select.selectByValue("no");
        select.selectByIndex(1);
    }

    @Test(priority = 4)
    public void testDragAndDrop() {
        openUrl("https://practice-automation.com/gestures/");
        WebElement text = getDriver().findElement(By.xpath("//p[contains(text(),'Drag and drop')]"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", text);
        WebElement drag = getDriver().findElement(By.id("dragMe"));
        WebElement drop = getDriver().findElement(By.id("div2"));
        new Actions(getDriver()).dragAndDrop(drag, drop).build().perform();
    }

    @Test(priority = 5)
    public void testWindowHandling() {
        openUrl("https://practice-automation.com/window-operations/");
        String parentWindow = getDriver().getWindowHandle();
        getDriver().findElement(By.xpath("//button[contains(@onclick,'newTab()')]")).click();

        Set<String> allWindows = getDriver().getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                getDriver().switchTo().window(window);
                WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[normalize-space()='Start learning']")));
                Assert.assertEquals(text.getText(), "Start learning");
                getDriver().close();
            }
        }
        getDriver().switchTo().window(parentWindow);
    }

    @Test(priority = 6)
    public void testMouseHover() {
        openUrl("https://practice-automation.com/hover/");
        WebElement element = getDriver().findElement(By.id("mouse_over"));
        new Actions(getDriver()).moveToElement(element).perform();
    }

    @Test(priority = 7)
    public void testFileDownload() {
        openUrl("https://practice-automation.com/file-download/");
        WebElement downloadLink = getDriver().findElement(By.xpath("//a[contains(@class, 'download-on-click')]"));
        downloadLink.click();
    }

    @Test(priority = 8)
    public void testFrameHandling() {
        openUrl("https://practice-automation.com/iframes/");
        WebElement iframe = getDriver().findElement(By.tagName("iframe"));
        getDriver().switchTo().frame(iframe);
        String text = getDriver().findElement(By.tagName("h1")).getText();
        Assert.assertTrue(text.contains("Playwright enables reliable"));
        getDriver().switchTo().defaultContent();
    }

    @Test(priority = 9)
    public void testSliders() {
        openUrl("https://practice-automation.com/slider/");
        WebElement slider = getDriver().findElement(By.id("slideMe"));
        new Actions(getDriver()).dragAndDropBy(slider, 200, 0).build().perform();
        WebElement label = getDriver().findElement(By.xpath("//span[text()='65']"));
        Assert.assertEquals(label.getText(), "65");
    }

    @Test(priority = 10)
    public void testFileUpload() {
        openUrl("https://practice-automation.com/file-upload/");
        getDriver().findElement(By.id("file-upload")).sendKeys("/Users/aravindms/Downloads/test.pdf");
        getDriver().findElement(By.id("upload-btn")).click();
        WebElement successText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Thank you for your message. It has been sent.']")));
        Assert.assertEquals(successText.getText(), "Thank you for your message. It has been sent.");
    }

    @Test(priority = 11)
    public void testAccordions() {
        openUrl("https://practice-automation.com/accordions/");
        getDriver().findElement(By.xpath("//summary[contains(@class,'accordion-item__title')]")).click();
        WebElement accordionText = getDriver().findElement(By.xpath("//p[normalize-space()='This is an accordion item.']"));
        Assert.assertEquals(accordionText.getText(), "This is an accordion item.");
    }

    @Test(priority = 12)
    public void testModals() {
        openUrl("https://practice-automation.com/modals/");
        getDriver().findElement(By.id("formModal")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("g1051-name"))).sendKeys("Test");
        getDriver().findElement(By.xpath("//div[@id='popmake-674']//button[@aria-label='Close']")).click();
    }

    @Test(priority = 13)
    public void testCalendar() {
        openUrl("https://practice-automation.com/calendars/");
        getDriver().findElement(By.id("g1065-2-1-selectorenteradate")).click();
        getDriver().findElement(By.xpath("//button[normalize-space()='Next Month']")).click();
        getDriver().findElement(By.xpath("//button[normalize-space()='25']")).click();
        getDriver().findElement(By.xpath("(//button[@type='submit'])[1]")).click();
    }

    @Test(priority = 14)
    public void testTables() throws InterruptedException {
        openUrl("https://practice-automation.com/tables/");
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,500)");
        Thread.sleep(3000);
        getDriver().findElement(By.xpath("//span[normalize-space()='Population (million)']")).click();
        List<WebElement> population = getDriver().findElements(By.xpath("(//table)[2]/tbody/tr/td[3]"));
        List<Float> numbers = new ArrayList<>();
        for (WebElement el : population) {
            numbers.add(Float.parseFloat(el.getText()));
        }
        for (int i = 0; i < numbers.size() - 1; i++) {
            Assert.assertTrue(numbers.get(i) <= numbers.get(i + 1));
        }
    }
}