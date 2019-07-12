import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MailTest {


    @BeforeMethod
    public void setUp() {
        InitChromeDriver.initDriver().setDriver();
        WebDriver driver = InitChromeDriver.initDriver().getDriver();
        LoginPage loginPage = LoginPage.newLoginPage( driver );
        loginPage.login();

    }

    @DataProvider
    public Object[][] task1_3Source() {
        return new Object[][]{{"Group", "Test", "Test"}};
    }

    @Test(dataProvider = "task1_3Source")
    public void task1_3(String group, String theme, String text) {
        WebDriver driver = InitChromeDriver.initDriver().getDriver();
        MailPage mailPage = new MailPage( driver );
        mailPage.createNewMailAndSendGroup( group, theme, text );
    }


    @AfterMethod
    public void tearDown() {
        InitChromeDriver.initDriver().getDriver().quit();
    }
}
