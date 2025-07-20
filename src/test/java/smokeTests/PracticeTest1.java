package smokeTests;

import baseTest.BaseTest;
import java.io.IOException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.*;

@Listeners(utils.Listeners.class)
public class PracticeTest1 extends BaseTest {

  @Test(priority = 1)
  public void verifyLoginLogoutFlow() throws InterruptedException {
    LoginPage loginPage = new LoginPage(getDriver());
    loginPage.verifyLoginLogoutFlow(TestData.username, TestData.password);
  }

  @Test(priority = 2, dataProvider = "LoginData", dataProviderClass = DataProviders.class)
  public void verifyInvalidLogin1(String username, String password) throws InterruptedException {
    LoginPage loginPage = new LoginPage(getDriver());
    loginPage.verifyInvalidLogin(username, password);
  }

  @Test(priority = 3)
  public void verifyInvalidLogin2() throws InterruptedException, IOException {
    LoginPage loginPage = new LoginPage(getDriver());
    String username =
        ExcelReader.getCellValue(Constants.TEST_DATA_PATH, Constants.LOGIN_SHEET, 1, 0);
    String password =
        ExcelReader.getCellValue(Constants.TEST_DATA_PATH, Constants.LOGIN_SHEET, 1, 1);
    loginPage.verifyInvalidLogin(username, password);
  }

  @Test(priority = 4)
  public void verifyInvalidLogin3() throws InterruptedException, IOException {
    LoginPage loginPage = new LoginPage(getDriver());
    String username = ConfigReader.get("username");
    String password = ConfigReader.get("password");
    loginPage.verifyInvalidLogin(username, password);
  }
}
