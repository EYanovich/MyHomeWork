import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class BookingTests {

    @BeforeMethod
    public void setUp() {
        InitChromeDriver.initDriver().setDriver();
    }


    @DataProvider
    public Object[][] bookingTestSource() {
        return new Object[][]{{"Москва", 3}};
    }

    @Test(dataProvider = "bookingTestSource")
    public void bookingTest(String cityDestination, int daysToDepartureDate) {
        WebDriver driver = InitChromeDriver.initDriver().getDriver();
        HomePage homePage = HomePage.newHomePage( driver );
        homePage.inputSearchText( cityDestination ).clickCalendar()
                .setDate( daysToDepartureDate ).clickGuestsInput()
                .addChildrenClick().clickSearchButton();
        Assert.assertFalse( SearchResults.newSearchResults( driver ).getSearchResultsHotel().isEmpty() );
    }

    @DataProvider
    public Object[][] bookingTestRatingSource() {
        return new Object[][]{{"Москва", 3, 1, 9}};
    }

    @Test(dataProvider = "bookingTestRatingSource")
    public void bookingTestRatingTest(String cityDestination, int daysToDepartureDate, int numHotel, int expectedScore) {
        WebDriver driver = InitChromeDriver.initDriver().getDriver();
        HomePage homePage = HomePage.newHomePage( driver );
        homePage.inputSearchText( cityDestination ).clickCalendar()
                .setDate( daysToDepartureDate ).clickGuestsInput()
                .addChildrenClick().clickSearchButton();
        SearchResults searchResults = SearchResults.newSearchResults( driver );
        searchResults.checkRating().clickHotel( numHotel );
        Assert.assertTrue( searchResults.getHotelScore() >= expectedScore );
    }

    @AfterMethod
    public void tearDown() {
        InitChromeDriver.initDriver().getDriver().quit();
    }

}
