import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomePage {

    @FindBy(xpath = ".//input[@name = 'ss']")
    private WebElement searchField;

    @FindBy(xpath = "(.//div[contains(@data-component, \"date-field-select\")])[1]")
    private WebElement calendar;

    @FindBy(xpath = ".//button[contains(@class, 'sb-searchbox__button')]")
    private WebElement SearchButton;

    private static HomePage homePage;
    private WebDriver driver;

    private HomePage(WebDriver driver) {
        PageFactory.initElements( driver, this );
        this.driver = driver;
    }

    public static HomePage newHomePage(WebDriver driver) {
        if (homePage == null) {
            homePage = new HomePage( driver );
        } else {
            homePage.driver = driver;
            PageFactory.initElements( driver, homePage);
        }
        return homePage;
    }

    public HomePage inputSearchText(String inputText) {
        searchField.sendKeys( inputText );
        return this;
    }

    public HomePage clickCalendar() {
        calendar.click();
        return this;
    }

    public HomePage setDate(int daysToArrival, int daysToDepartureDate) {
        DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        Calendar instance = Calendar.getInstance();
        instance.add( Calendar.DAY_OF_MONTH, daysToArrival );
        Date arrivalDate = instance.getTime();
        instance.add( Calendar.DAY_OF_MONTH, daysToDepartureDate );
        Date departureDate = instance.getTime();
        String arrivalDateXpath = String.format( ".//td[@data-date = '%s']", dateFormat.format( arrivalDate ) );
        String departureDateXpath = String.format( ".//td[@data-date = '%s']", dateFormat.format( departureDate ) );
        driver.findElement( By.xpath( arrivalDateXpath ) ).click();
        driver.findElement( By.xpath( departureDateXpath ) ).click();
        return this;
    }

    public void clickSearchButton() {
        SearchButton.click();
    }
}
