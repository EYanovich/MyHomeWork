import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomePage {
    private static HomePage homePage;
    private WebDriver driver;

    private HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public static HomePage newHomePage(WebDriver driver) {
        if (homePage == null) {
            homePage = new HomePage( driver );
        } else {
            homePage.driver = driver;
        }
        return homePage;
    }

    public HomePage inputSearchText(String inputText) {
        driver.findElement( By.xpath( ".//input[@name = 'ss']" ) ).sendKeys( inputText );
        return homePage;
    }

    public HomePage clickCalendar() {
        driver.findElement( By.xpath( "(.//div[contains(@data-component, \"date-field-select\")])[1]" ) ).click();
        return homePage;
    }

    public HomePage setDate(int daysToDepartureDate) {
        DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        Calendar instance = Calendar.getInstance();
        Date arrivalDate = instance.getTime();
        instance.add( Calendar.DAY_OF_MONTH, daysToDepartureDate );
        Date departureDate = instance.getTime();
        String arrivalDateXpath = String.format( ".//td[@data-date = '%s']", dateFormat.format( arrivalDate ) );
        String departureDateXpath = String.format( ".//td[@data-date = '%s']", dateFormat.format( departureDate ) );
        driver.findElement( By.xpath( arrivalDateXpath ) ).click();
        driver.findElement( By.xpath( departureDateXpath ) ).click();
        return homePage;
    }

    public HomePage clickGuestsInput() {
        driver.findElement( By.xpath( ".//label[@id=\"xp__guests__toggle\"]" ) ).click();
        return homePage;
    }

    public HomePage addChildrenClick() {
        driver.findElement( By.xpath( ".//label[@for=\"group_children\"]/../..//button[contains(@data-bui-ref, 'add')]" ) ).click();
        return homePage;
    }

    public void clickSearchButton() {
        driver.findElement( By.xpath( ".//button[contains(@class, 'sb-searchbox__button')]" ) ).click();
    }
}
