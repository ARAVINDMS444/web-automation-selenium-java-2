package SmokeTests;

import BaseTest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Listeners(utils.Listeners.class)

public class SeleniumActions extends BaseTest {

    @Test(priority = 1)
    public void testAlerts() throws InterruptedException {
        driver.get("https://practice-automation.com/popups/");
        WebElement element = driver.findElement(By.xpath("//b[normalize-space()='Prompt Popup']"));
        element.click();
        Thread.sleep(2000);
        driver.switchTo().alert().sendKeys("Test");
        Thread.sleep(2000);
        String alertText = driver.switchTo().alert().getText();
        Assert.assertEquals(alertText, "Hi there, what's your name?");
        driver.switchTo().alert().dismiss();
        Thread.sleep(2000);
        element.click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
    }

    @Test(priority = 2)
    public void testExplicitWaits() {
        driver.get("https://practice-automation.com/javascript-delays/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='start']")));
        WebElement element = driver.findElement(By.xpath("//button[@id='start']"));
        element.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Liftoff!']")));
        WebElement successText = driver.findElement(By.xpath("//div[text()='Liftoff!']"));
        Assert.assertEquals(successText.getText(), "Liftoff!");
    }

    @Test(priority = 3)
    public void testDropdown() throws InterruptedException {
        driver.get("https://practice-automation.com/form-fields/");
        WebElement dropdown = driver.findElement(By.xpath("//select[@id='automation']"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Yes");
        Thread.sleep(2000);
        select.selectByValue("no");
        Thread.sleep(2000);
        select.selectByIndex(1);
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void testDragAndDrop() throws InterruptedException {
        driver.get("https://practice-automation.com/gestures/");
        WebElement element = driver.findElement(By.xpath("//p[normalize-space()='Drag and drop the image from one box to the other.']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);
        WebElement dragElement = driver.findElement(By.xpath("//img[@id='dragMe']"));
        WebElement dropElement = driver.findElement(By.xpath("//div[@id='div2']"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(dragElement, dropElement).build().perform();
    }

    @Test(priority = 4)
    public void testWindowHandling() throws InterruptedException {
        driver.get("https://practice-automation.com/window-operations/");
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//button[contains(@onclick,'newTab()')]"));
        element.click();
       String parentWindow = driver.getWindowHandle();
       Set<String> allWindows = driver.getWindowHandles();

       for (String window : allWindows) {
           if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                Thread.sleep(2000);
                String text = driver.findElement(By.xpath("//strong[normalize-space()='Start learning']")).getText();
                Assert.assertEquals(text, "Start learning");
                driver.close();
           }
           driver.switchTo().window(parentWindow);
       }
    }

    @Test(priority = 5)
    public void testMouseHover() throws InterruptedException {
        driver.get("https://practice-automation.com/hover/");
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//h3[@id='mouse_over']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    @Test(priority = 6)
    public void testFileDownload() throws InterruptedException {
        driver.get("https://practice-automation.com/file-download/");
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//a[@class='wpdm-download-link download-on-click btn btn-primary ']"));
        element.click();
        Thread.sleep(5000);
    }

    @Test(priority = 7)
    public void testFrameHandling() throws InterruptedException {
        driver.get("https://practice-automation.com/iframes/");
        Thread.sleep(2000);
        WebElement iframe = driver.findElement(By.xpath("//iframe"));
        driver.switchTo().frame(iframe);
        String text = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(text, "Playwright enables reliable end-to-end testing for modern web apps.");
        driver.switchTo().defaultContent();
    }

    @Test(priority = 8)
    public void testSliders() throws InterruptedException {
        driver.get("https://practice-automation.com/slider/");
        Thread.sleep(2000);
        WebElement slider = driver.findElement(By.xpath("//input[@id='slideMe']"));
        Actions action = new Actions(driver);
        action.dragAndDropBy(slider, 200, 0).build().perform();
        WebElement expectedText = driver.findElement(By.xpath("//span[text()='65']"));
        Assert.assertEquals(expectedText.getText(), "65");
    }

    @Test(priority = 9)
    public void testFileUpload() throws InterruptedException {
        driver.get("https://practice-automation.com/file-upload/");
        Thread.sleep(2000);
        WebElement fileUploadButton = driver.findElement(By.xpath("//input[@id='file-upload']"));
        fileUploadButton.sendKeys("/Users/aravindms/Downloads/test.pdf");
        Thread.sleep(2000);
        WebElement uploadButton = driver.findElement(By.xpath("//input[@id='upload-btn']"));
        uploadButton.click();
        WebElement successText = driver.findElement(By.xpath("//div[text()='Thank you for your message. It has been sent.']"));
        Assert.assertEquals(successText.getText(), "Thank you for your message. It has been sent.");
    }

    @Test(priority = 10)
    public void testAccordions() throws InterruptedException {
        driver.get("https://practice-automation.com/accordions/");
        Thread.sleep(2000);
        WebElement accordionText = driver.findElement(By.xpath("//summary[@class='wp-block-coblocks-accordion-item__title']"));
        accordionText.click();
        Thread.sleep(2000);
        WebElement successText = driver.findElement(By.xpath("//p[normalize-space()='This is an accordion item.']"));
        Assert.assertEquals(successText.getText(), "This is an accordion item.");
    }

    @Test(priority = 11)
    public void testModals() throws InterruptedException {
        driver.get("https://practice-automation.com/modals/");
        Thread.sleep(2000);
        WebElement formModal = driver.findElement(By.xpath("//button[@id='formModal']"));
        formModal.click();
        Thread.sleep(2000);
        WebElement nameField = driver.findElement(By.xpath("//input[@id='g1051-name']"));
        nameField.sendKeys("Test");
        WebElement formModalCloseButton = driver.findElement(By.xpath("//div[@id='popmake-674']//button[@aria-label='Close'][normalize-space()='×']"));
        formModalCloseButton.click();
        Thread.sleep(2000);
    }

    @Test(priority = 12)
    public void testCalendar() throws InterruptedException {
        driver.get("https://practice-automation.com/calendars/");
        Thread.sleep(2000);
        WebElement selectDate = driver.findElement(By.xpath("//input[@id='g1065-2-1-selectorenteradate']"));
        selectDate.click();
        Thread.sleep(2000);
        WebElement nextMonth = driver.findElement(By.xpath("//button[normalize-space()='Next Month']"));
        nextMonth.click();
        Thread.sleep(2000);
        WebElement date = driver.findElement(By.xpath("//button[normalize-space()='25']"));
        date.click();
        Thread.sleep(2000);
        WebElement submitButton = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
        submitButton.click();
    }

    @Test(priority = 13)
    public void testTables() throws InterruptedException {
        driver.get("https://practice-automation.com/tables/");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(3000);
        WebElement populationHeader = driver.findElement(By.xpath("//span[normalize-space()='Population (million)']"));
        populationHeader.click();
        List<WebElement> population = driver.findElements(By.xpath("(//table)[2]/tbody/tr/td[3]"));
        ArrayList<Float> numbers = new ArrayList<>();

        for (WebElement el : population) {
            numbers.add(Float.parseFloat(el.getText().trim()));
        }

        boolean isAscending = true;
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) > numbers.get(i + 1)) {
                isAscending = false;
                break;
            }
        }

        Assert.assertTrue(isAscending);
    }
}