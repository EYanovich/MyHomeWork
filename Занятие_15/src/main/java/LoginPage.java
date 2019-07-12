import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passField;

    private static LoginPage loginPage;
    private WebDriver driver;

    private LoginPage(WebDriver driver) {
        PageFactory.initElements( driver, this );
        this.driver = driver;
    }

    public static LoginPage newLoginPage(WebDriver driver) {
        if (loginPage == null) {
            loginPage = new LoginPage( driver );
        } else {
            loginPage.driver = driver;
            PageFactory.initElements( driver, loginPage );
        }
        return loginPage;
    }

    public void login() {
        ConfigProperties configProperties = new ConfigProperties();
        loginField.sendKeys( configProperties.getProperty( "login" ) );
        passField.sendKeys( configProperties.getProperty( "pass" ) );
        passField.submit();
        (new WebDriverWait( driver, 40 )).until( ExpectedConditions.presenceOfAllElementsLocatedBy( By.xpath( ".//a[@href=\"/spam/\"]" ) ) );
    }

}
