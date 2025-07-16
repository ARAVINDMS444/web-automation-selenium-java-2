package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Constants;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By usernameTextbox = By.id("username");
    private final By passwordTextbox = By.id("password");
    private final By signInButton = By.xpath("//i[@class='fa fa-2x fa-sign-in']");
    private final By successMessage = By.xpath("//h4[contains(text(),'Welcome to the Secure Area.')]");
    private final By signOutButton = By.xpath("//i[@class='icon-2x icon-signout']");
    private final By loginPageHeader = By.xpath("//h2[normalize-space()='Login Page']");
    private final By toastErrorMessage = By.xpath("//div[@id='flash']");

    public void verifyLoginLogoutFlow(String username, String password) throws InterruptedException {
        driver.get(Constants.baseUrl);
        driver.findElement(usernameTextbox).sendKeys(username);
        driver.findElement(passwordTextbox).sendKeys(password);
        driver.findElement(signInButton).click();
        Thread.sleep(3000);
        driver.findElement(successMessage).isDisplayed();
        assert driver.findElement(successMessage).getText().equals("Welcome to the Secure Area. When you are done click logout below.");
        driver.findElement(signOutButton).click();
        Thread.sleep(3000);
        assert driver.findElement(loginPageHeader).isDisplayed();
        assert driver.findElement(loginPageHeader).getText().equals("Login Page");
    }

    public void verifyInvalidLogin(String username, String password) throws InterruptedException {
        driver.get(Constants.baseUrl);
        driver.findElement(usernameTextbox).sendKeys(username);
        driver.findElement(passwordTextbox).sendKeys(password);
        driver.findElement(signInButton).click();
        Thread.sleep(3000);
        driver.findElement(toastErrorMessage).isDisplayed();
        assert driver.findElement(toastErrorMessage).getText().contains("Your username is invalid!");
    }
}