
import com.applitools.eyes.exceptions.TestFailedException;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MailPage {
    private WebDriver driver;

    @FindBy(xpath = ".//a[@href=\"/spam/\"]")
    private WebElement buttonSpam;

    @FindBy(xpath = "(.//div[contains (@class, 'avatar')])[1]")
    private WebElement selectCheckBox;

    @FindBy(xpath = ".//div[contains (@class, 'avatar')]//div")
    private WebElement CheckBoxStstus;

    @FindBy(xpath = ".//div[contains (@class, 'spam')]")
    private WebElement buttonMoveToSpam;

    @FindBy(xpath = ".//div[contains(@class, \"element_move\")]")
    private WebElement buttonMoveTo;

    @FindBy(xpath = ".//div[contains(@class, \"element_move\")]//div[text() = \"Входящие\"]")
    private WebElement buttonMoveToInBox;

    @FindBy(xpath = ".//span[@title=\"Написать письмо\"]")
    private WebElement buttonNewMail;

    @FindBy(xpath = ".//div[@class=\"input--3slxg\"]//input")
    private WebElement toSendField;

    @FindBy(xpath = ".//div[@class=\"container--3QXHv\"]//input")
    private WebElement themeField;

    @FindBy(xpath = ".//div[@role='textbox']")
    private WebElement textMailField;

    @FindBy(xpath = ".//span[text() = \"Отправить\"]")
    private WebElement buttonSend;

    public MailPage(WebDriver driver) {
        PageFactory.initElements( driver, this );
        this.driver = driver;
    }

    public MailPage  createNewMailAndSendGroup(String group, String theme, String text) {
        // Initialize the eyes SDK and set your private API key.
        Eyes eyes = new Eyes( );

        // Set the API key from the env variable. Please read the "Important Note"
        // section above.
        eyes.setApiKey("JgTKaK0BeociynF9XRjPmGNqxo5nfTFam3nT9sCe8LE110");

        try {


            // Start the test by setting AUT's name, window or the page name that's being tested, viewport width and height
            eyes.open(driver, "appName","HomeWorkEyesMail.ru");

            buttonNewMail.click();
            eyes.checkWindow("New mail");
            toSendField.sendKeys( group );
            driver.findElement( By.xpath( ".//div[contains(@class, 'datalist_visible')]" ) ).click();
            eyes.checkWindow("Field to send");
            themeField.sendKeys( theme );
            eyes.checkWindow("Field theme");
            textMailField.clear();
            textMailField.click();
            textMailField.sendKeys( text );
            eyes.checkWindow("Field text");
            buttonSend.click();
            eyes.checkWindow("Send mail.");

            eyes.close();

        } catch (TestFailedException e) {
            System.out.println("\n" + e + "\n");
        } finally {

            // Close the browser.
            driver.quit();

            // If the test was aborted before eyes.close was called, ends the test as
            // aborted.
            eyes.abortIfNotClosed();

            // End main test
            System.exit(0);
        }
        return this;
    }

}
