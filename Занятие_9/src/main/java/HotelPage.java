import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HotelPage {

    @FindBy(xpath = ".//div[@class = 'hprt-reservation-cta']")
    private WebElement bookingButon;

    private static HotelPage hotelPage;
    private WebDriver driver;

    private HotelPage(WebDriver driver) {
        PageFactory.initElements( driver, this );
        this.driver = driver;
    }

    public static HotelPage newHotelPage(WebDriver driver) {
        if (hotelPage == null) {
            hotelPage = new HotelPage( driver );
            for (String handle : driver.getWindowHandles()
            ) {
                driver.switchTo().window( handle );
            }
        } else {
            PageFactory.initElements( driver, hotelPage );
            hotelPage.driver = driver;
        }
        return hotelPage;
    }

    public HotelPage selectRoom(int numberOfRoom) {
        Select selectAdults = new Select( driver.findElement( By.xpath( "(.//select[contains(@class, 'hprt-nos-select')])[1]" ) ) );
        selectAdults.selectByIndex( numberOfRoom );
        return this;
    }

    public void clickBooking() {
        (new WebDriverWait( driver, 120 )).until( ExpectedConditions.visibilityOf( bookingButon ) );
        bookingButon.click();
    }

}
