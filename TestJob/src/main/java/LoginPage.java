import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private static int LINK_PRESENSE_TIMEOUT = 60;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "login")
    private WebElement loginField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = ".//a[contains(@href, 'logout')]")
    private WebElement logoutLink;

    @FindBy(xpath = ".//span[@class = 'uname']")
    private WebElement userName;

    @FindBy(xpath = ".//a[contains(@href, 'mail.html')]")
    private WebElement mailButton;

    @FindBy(xpath = ".//a[contains(@class, 'enter')]")
    private WebElement signInButton;

    public LoginPage signInClick() {
        (new WebDriverWait(driver, LINK_PRESENSE_TIMEOUT)).until(ExpectedConditions.visibilityOf(signInButton));
        signInButton.click();
        return this;
    }

    public LoginPage enterLogin(String login) {
        (new WebDriverWait(driver, LINK_PRESENSE_TIMEOUT)).until(ExpectedConditions.visibilityOf(loginField));
        loginField.clear();
        loginField.sendKeys(login);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public MailPage login() {
        passwordField.submit();
        userName.click();
        (new WebDriverWait(driver, LINK_PRESENSE_TIMEOUT)).until(ExpectedConditions.visibilityOf(logoutLink));
        mailButton.click();
        return new MailPage(driver);
    }

}
