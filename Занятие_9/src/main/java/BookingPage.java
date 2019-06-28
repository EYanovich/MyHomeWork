import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingPage {
    @FindBy(xpath = ".//input[@id=\"bp_travel_purpose_business\"]")
    private WebElement travelPurpose;

    @FindBy(xpath = ".//input[@id=\"firstname\"]")
    private WebElement firstNameInput;

    @FindBy(xpath = ".//input[@id=\"lastname\"]")
    private WebElement lastNameInput;

    @FindBy(xpath = ".//input[@id=\"email\"]")
    private WebElement emailInput;

    @FindBy(xpath = ".//input[@id=\"email_confirm\"]")
    private WebElement confirmEmailInput;

    @FindBy(xpath = ".//div[@data-tooltip-class=\"submit_holder_button_tooltip\"]")
    private WebElement buttonNext;

    @FindBy(xpath = ".//input[@id=\"phone\"]")
    private WebElement inoutPhone;

    @FindBy(xpath = ".//input[@id=\"cc_name\"]")
    private WebElement inoutNameCard;

    @FindBy(xpath = ".//input[@id=\"cc_number\"]")
    private WebElement inoutCardNum;

    @FindBy(xpath = ".//input[@id=\"cc_cvc\"]")
    private WebElement inoutCVCNum;

    @FindBy(xpath = "//*[@id=\"bookwrapper_cell\"]/div[4]/div[2]/button[2]")
    private WebElement buttonСomplet;

    @FindBy(xpath = ".//a[@href = '#cc_number']")
    private WebElement allertText;

    private static BookingPage bookingPage;
    private WebDriver driver;

    private BookingPage(WebDriver driver) {
        PageFactory.initElements( driver, this );
        this.driver = driver;
    }

    public static BookingPage newbookingPage(WebDriver driver) {
        if (bookingPage == null) {
            bookingPage = new BookingPage( driver );
        } else {
            PageFactory.initElements( driver, bookingPage );
            bookingPage.driver = driver;
        }
        return bookingPage;
    }

    public void fillBookingForm() {
        travelPurpose.click();
        Select selectAdults = new Select( driver.findElement( By.xpath( ".//select[@id = 'booker_title']" ) ) );
        selectAdults.selectByValue( "mr" );
        firstNameInput.sendKeys( Person.firstName.getValue() );
        lastNameInput.sendKeys( Person.lastName.getValue() );
        emailInput.sendKeys( Person.email.getValue() );
        confirmEmailInput.sendKeys( Person.email.getValue() );
        (new WebDriverWait( driver, 120 )).until( ExpectedConditions.visibilityOf( buttonNext ) );
        buttonNext.click();
        inoutPhone.sendKeys( Person.phone.getValue() );
        inoutNameCard.sendKeys( Person.cardName.getValue() );
        Select selectTypCard = new Select( driver.findElement( By.xpath( ".//select[@id=\"cc_type\"]" ) ) );
        selectTypCard.selectByValue( "Visa" );
        inoutCardNum.sendKeys( Person.cardNum.getValue() );
        Select selectCCYear = new Select( driver.findElement( By.xpath( ".//select[@id=\"ccYear\"]" ) ) );
        selectCCYear.selectByValue( "2020" );
        inoutCVCNum.sendKeys( Person.cardCVC.getValue() );
        buttonСomplet.click();
    }

    public boolean getAllertTextEquals() {
        String expectedAllertText = "Извините, во время обработки вашего запроса произошла ошибка. Для завершения бронирования, пожалуйста, свяжитесь со Службой поддержки клиентов по телефону: +44 203 1473047 .";
        return allertText.getText().equals( expectedAllertText );
    }
}
