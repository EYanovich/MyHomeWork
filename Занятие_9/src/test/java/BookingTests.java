import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class BookingTests {

    @BeforeMethod
    public void setUp() {
        InitChromeDriver.initDriver().setDriver();
    }

    @DataProvider
    public Object[][] bookingTask_1Source() {
        return new Object[][]{{"Париж", 3, 7}};
    }

    @Test(dataProvider = "bookingTask_1Source")
    public void Task_1_Test(String cityDestination, int daysToArrival, int daysToDepartureDate) {
        WebDriver driver = InitChromeDriver.initDriver().getDriver();
        HomePage homePage = HomePage.newHomePage( driver );
        homePage.inputSearchText( cityDestination ).clickCalendar()
                .setDate( daysToArrival, daysToDepartureDate ).clickSearchButton();
        SearchResultsPage searchResultsPage = SearchResultsPage.newSearchResults( driver );
        searchResultsPage.clickLowestPrice();
        Assert.assertFalse( SearchResultsPage.newSearchResults( driver ).getSearchResultsHotel().isEmpty() );
        searchResultsPage.clickMaxRating();
        Assert.assertTrue( searchResultsPage.getLowestPriceFilter() >= searchResultsPage.getPriceHotelMaxScore()/daysToDepartureDate );
    }

    @DataProvider
    public Object[][] bookingTask_2Source() {
        return new Object[][]{{"Париж", 3, 7, 4, 2}};
    }

    @Test(dataProvider = "bookingTask_2Source")
    public void Task_2_Test(String cityDestination, int daysToArrival, int daysToDepartureDate, int numAdults, int numRoom) {
        WebDriver driver = InitChromeDriver.initDriver().getDriver();
        HomePage homePage = HomePage.newHomePage( driver );
        homePage.inputSearchText( cityDestination ).clickCalendar()
                .setDate( daysToArrival, daysToDepartureDate ).clickSearchButton();
        SearchResultsPage searchResultsPage = SearchResultsPage.newSearchResults( driver );
        searchResultsPage.selectAdults( numAdults ).selectNumRoom( numRoom )
                .clickMaxPrice().clickSortPrice();
        Assert.assertTrue( searchResultsPage.getMaxPriceFilter() <= searchResultsPage.getMaxPriceHotel()/daysToDepartureDate );
    }

    @DataProvider
    public Object[][] bookingTask_3Source() {
        return new Object[][]{{"Париж", 3, 7, 4, 1}};
    }

    @Test(dataProvider = "bookingTask_3Source")
    public void Task_3_Test(String cityDestination, int daysToArrival, int daysToDepartureDate, int numAdults, int numRoom) {
        WebDriver driver = InitChromeDriver.initDriver().getDriver();
        HomePage homePage = HomePage.newHomePage( driver );
        homePage.inputSearchText( cityDestination ).clickCalendar()
                .setDate( daysToArrival, daysToDepartureDate ).clickSearchButton();
        SearchResultsPage searchResultsPage = SearchResultsPage.newSearchResults( driver );
        searchResultsPage.selectAdults( numAdults ).selectNumRoom( numRoom )
                .clickMaxPrice().clickSortPrice().clickHotel();
        HotelPage hotelPage = HotelPage.newHotelPage( driver );
        hotelPage.selectRoom( numRoom ).clickBooking();
        BookingPage bookingPage = BookingPage.newbookingPage( driver );
        bookingPage.fillBookingForm();
        Assert.assertTrue( bookingPage.getAllertTextEquals() );
    }

    @AfterMethod
    public void tearDown() {
        InitChromeDriver.initDriver().getDriver().quit();
    }

}
