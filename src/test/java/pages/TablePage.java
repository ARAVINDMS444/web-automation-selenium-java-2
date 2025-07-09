package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class TablePage {

    private final WebDriver driver;

    public TablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//span[contains(text(),'Last Name')])[1]")
    private WebElement lastNameHeader;

    @FindBy(xpath = "//table[@id='table1']/tbody/tr/td[1]")
    private List<WebElement> lastNameCells;

    @FindBy(xpath = "(//td[contains(text(),'$50.00')])[2]")
    private WebElement smithAmountCell;

    public void verifyTableSort() {
        driver.get("https://the-internet.herokuapp.com/tables");

        // Click to sort by last name
        lastNameHeader.click();

        // Extract last names
        List<String> lastNames = new ArrayList<>();
        for (WebElement cell : lastNameCells) {
            lastNames.add(cell.getText().trim());
        }

        // Validate Smith's amount
        String amountText = smithAmountCell.getText().trim();
        assert amountText.equals("$50.00");
    }
}