package smokeTests;

import baseTest.BaseTest;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Helpers;

@Listeners(utils.Listeners.class)
public class PracticeTest3 extends BaseTest {

  @Test(priority = 1)
  public void verifyPriceOrderByAscending() throws InterruptedException {
    getDriver().get("https://www.saucedemo.com/v1/inventory.html");
    Select select =
        new Select(
            getDriver().findElement(By.xpath("(//select[@class='product_sort_container'])[1]")));
    select.selectByValue("lohi");
    Thread.sleep(2000);
    List<WebElement> prices = getDriver().findElements(By.xpath("//div[contains(text(),'$')]"));
    int size = prices.size();
    ArrayList<Float> pricesNumeric = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      String price = prices.get(i).getText();
      float priceInt = Float.parseFloat(price.replace("$", ""));
      pricesNumeric.add(priceInt);
    }

    Assert.assertTrue(Helpers.isAscending(pricesNumeric));
  }

  @Test(priority = 1)
  public void verifyPriceOrderByDescending() throws InterruptedException {
    getDriver().get("https://www.saucedemo.com/v1/inventory.html");
    Select select =
        new Select(
            getDriver().findElement(By.xpath("(//select[@class='product_sort_container'])[1]")));
    select.selectByValue("hilo");
    Thread.sleep(2000);
    List<WebElement> prices = getDriver().findElements(By.xpath("//div[contains(text(),'$')]"));
    int size = prices.size();
    ArrayList<Float> pricesNumeric = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      String price = prices.get(i).getText();
      float priceInt = Float.parseFloat(price.replace("$", ""));
      pricesNumeric.add(priceInt);
    }

    Assert.assertTrue(Helpers.isDescending(pricesNumeric));
  }

  @Test(priority = 1)
  public void verifyNameOrderByAscending() throws InterruptedException {
    getDriver().get("https://www.saucedemo.com/v1/inventory.html");
    Select select =
        new Select(
            getDriver().findElement(By.xpath("(//select[@class='product_sort_container'])[1]")));
    select.selectByValue("az");
    Thread.sleep(2000);
    List<WebElement> productsElements =
        getDriver().findElements(By.xpath("//div[@class='inventory_item_name']"));
    int size = productsElements.size();
    ArrayList<String> products = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      String product = productsElements.get(i).getText();
      products.add(product);
    }

    Assert.assertTrue(Helpers.isAscendingStrings(products));
  }

  @Test(priority = 1)
  public void verifyNameOrderByDescending() throws InterruptedException {
    getDriver().get("https://www.saucedemo.com/v1/inventory.html");
    Select select =
        new Select(
            getDriver().findElement(By.xpath("(//select[@class='product_sort_container'])[1]")));
    select.selectByValue("za");
    Thread.sleep(2000);
    List<WebElement> productsElements =
        getDriver().findElements(By.xpath("//div[@class='inventory_item_name']"));
    int size = productsElements.size();
    ArrayList<String> products = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      String product = productsElements.get(i).getText();
      products.add(product);
    }

    Assert.assertTrue(Helpers.isDescendingStrings(products));
  }
}
