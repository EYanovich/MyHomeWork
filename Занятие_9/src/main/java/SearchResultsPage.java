import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultsPage {

    @FindBy(xpath = "(.//div[contains(@aria-label, 'Оценка')])[1]")
    private WebElement hotelScore;

    @FindBy(xpath = "(.//div[@id=\"filter_review\"]//span)[1]")
    private WebElement hotelMaxRating;

    @FindBy(xpath = ".//a[@data-id = 'pri-2']")
    private WebElement lowestPrice;

    @FindBy(xpath = ".//a[@data-id = 'pri-5']")
    private WebElement maxPriceCheckBox;

    @FindBy(xpath = ".//a[@data-id = 'pri-4']")
    private WebElement maxPrice;

    @FindBy(xpath = "//div[@id=\"sort_by\"]//a[@data-category=\"price\"]")
    private WebElement sortPrice;

    private static SearchResultsPage searchResultsPage;
    private WebDriver driver;

    private SearchResultsPage(WebDriver driver) {
        PageFactory.initElements( driver, this );
        this.driver = driver;
    }

    public static SearchResultsPage newSearchResults(WebDriver driver) {
        if (searchResultsPage == null) {
            searchResultsPage = new SearchResultsPage( driver );
        } else {
            PageFactory.initElements( driver, searchResultsPage );
            searchResultsPage.driver = driver;
        }
        return searchResultsPage;
    }

    public List<WebElement> getSearchResultsHotel() {
        return driver.findElements( By.xpath( ".//div[@data-hotelid]" ) );
    }

    public SearchResultsPage selectAdults(int numberOfAdults) {
        Select selectAdults = new Select( driver.findElement( By.xpath( ".//select[@id=\"group_adults\"]" ) ) );
        selectAdults.selectByIndex( numberOfAdults );
        return this;
    }

    public SearchResultsPage selectNumRoom(int numberOfRoom) {
        Select selectAdults = new Select( driver.findElement( By.xpath( ".//select[@id=\"no_rooms\"]" ) ) );
        selectAdults.selectByIndex( numberOfRoom );
        return this;
    }

    public SearchResultsPage clickLowestPrice() {
        lowestPrice.click();
        (new WebDriverWait( driver, 10 )).until( ExpectedConditions.stalenessOf( driver.findElement( By.xpath( ".//div[contains (@class, 'sr-usp-overlay')]" ) ) ) );
        return this;
    }

    public SearchResultsPage clickMaxPrice() {
        maxPriceCheckBox.click();
        (new WebDriverWait( driver, 15 )).until( ExpectedConditions.stalenessOf( driver.findElement( By.xpath( ".//div[contains (@class, 'sr-usp-overlay')]" ) ) ) );
        return this;
    }

    public SearchResultsPage clickSortPrice() {
        sortPrice.click();
        (new WebDriverWait( driver, 15 )).until( ExpectedConditions.stalenessOf( driver.findElement( By.xpath( ".//div[contains (@class, 'sr-usp-overlay')]" ) ) ) );
        return this;
    }

    public int getLowestPriceFilter() {
        return Integer.parseInt( lowestPrice.getAttribute( "data-value" ) );
    }

    public int getMaxPriceFilter() {
        return Integer.parseInt( maxPrice.getAttribute( "data-value" ) );
    }

    public int getPriceHotelMaxScore() {
        float score = 0;
        int price = 0;
        List<WebElement> allHotelSearch = driver.findElements( By.xpath( ".//div[@data-hotelid]" ) );
        for (int i = 0; i < allHotelSearch.size(); i++) {
            WebElement element = allHotelSearch.get( i ).findElement( By.xpath( ".//div[contains(@aria-label, 'Оценка')]" ) );
            if (score < Float.parseFloat( element.getText().replace( ",", "." ) )) {
                int scoreIndex = i;
                if (i == allHotelSearch.size() - 1) {
                    price = Integer.parseInt( allHotelSearch.get( i ).findElement( By.xpath( ".//div[contains(@class, \"bui-price-display\") and contains(text(), \"BYN\")]" ) ).getText().replaceAll( "[^0-9]", "" ) );
                }
            }

        }
        return price;
    }

    public int getMaxPriceHotel() {
        return Integer.parseInt( driver.findElement( By.xpath( "(.//div[contains(@class, \"bui-price-display\") and contains(text(), \"BYN\")])[1]" ) ).getText().replaceAll( "[^0-9]", "" ) );
    }

    public SearchResultsPage clickMaxRating() {
        hotelMaxRating.click();
        (new WebDriverWait( driver, 10 )).until( ExpectedConditions.stalenessOf( driver.findElement( By.xpath( ".//div[contains (@class, 'sr-usp-overlay')]" ) ) ) );
        return this;
    }

    public void clickHotel() {
        WebElement nameHotel = driver.findElement( By.xpath( "(.//a[@class = 'hotel_name_link url'])[1]" ) );
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript( "arguments[0].click();", nameHotel );
    }

}
