package BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.logging.*;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeSuite
    public void suppressSeleniumLogs() {
        LogManager.getLogManager().reset();
        Logger seleniumLogger = Logger.getLogger("org.openqa.selenium");
        seleniumLogger.setLevel(Level.SEVERE);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.SEVERE);
        seleniumLogger.addHandler(handler);
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        WebDriver localDriver;
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                //chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--incognito");
                localDriver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                WebDriverManager.edgedriver().setup();
                edgeOptions.addArguments("--remote-allow-origins=*");
                localDriver = new EdgeDriver(edgeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                localDriver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.set(localDriver);
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        getDriver().quit();
        driver.remove();
    }
}