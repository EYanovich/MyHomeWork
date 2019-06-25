import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResults {

    private static SearchResults searchResults;
    private WebDriver driver;

    private SearchResults(WebDriver driver) {
        this.driver = driver;
    }

    public static SearchResults newSearchResults(WebDriver driver) {
        if (searchResults == null) {
            searchResults = new SearchResults( driver );
        } else {
            searchResults.driver = driver;
        }
        return searchResults;
    }

    public List<WebElement> getSearchResultsHotel() {
        return driver.findElements( By.xpath( ".//div[@data-hotelid]" ) );
    }

    public SearchResults checkRating() {
        driver.findElement( By.xpath( ".//div[@id=\"filter_review\"]//span[contains(text(), 'Превосходно')]/.." ) ).click();
        return searchResults;
    }

    public SearchResults clickHotel(int numHotel) {
        String numHotelXpath = String.format( "(.//a[@class = 'hotel_name_link url'])[1]", numHotel );
        WebElement nameHotel = driver.findElement( By.xpath( numHotelXpath ) );
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript( "arguments[0].click();", nameHotel );
        return searchResults;
    }

    public float getHotelScore() {
        return Float.parseFloat( driver.findElement( By.xpath( "(.//div[contains(@aria-label, 'Оценка')])[1]" ) ).getText().replace( ",", "." ) );
    }
}
