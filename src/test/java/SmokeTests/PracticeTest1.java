package SmokeTests;

import BaseTest.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataProviders;

@Listeners(utils.Listeners.class)

public class PracticeTest1 extends BaseTest {


    @Test(priority = 1)
    public void verifyLoginLogoutFlow() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.verifyLoginLogoutFlow("tomsmith", "SuperSecretPassword!");
    }

    @Test(priority = 2 , dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void verifyInvalidLogin(String username, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.verifyInvalidLogin(username, password);
    }
}