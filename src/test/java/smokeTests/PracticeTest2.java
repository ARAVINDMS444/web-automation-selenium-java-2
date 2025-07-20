package smokeTests;

import baseTest.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.TablePage;

@Listeners(utils.Listeners.class)
public class PracticeTest2 extends BaseTest {

  @Test(priority = 1)
  public void verifySortByAscendingOrder() throws InterruptedException {
    TablePage tablePage = new TablePage(getDriver());
    tablePage.verifyTableSort();
  }
}
