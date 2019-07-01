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

    @Test
    public void task1_1() {
        WebDriver driver = InitChromeDriver.initDriver().getDriver();
        MailPage mailPage = new MailPage( driver );
        mailPage.selectMailAndMoveToSpam();
    }

    @Test
    public void task1_2() {
        WebDriver driver = InitChromeDriver.initDriver().getDriver();
        MailPage mailPage = new MailPage( driver );
        mailPage.selectMailAndReturnMailInBox();
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

    @DataProvider
    public Object[][] task1_4Source() {
        return new Object[][]{{0, 5, 2}};
    }

    @Test(dataProvider = "task1_4Source")
    public void task1_4(int index1, int index2, int index3) {
        WebDriver driver = InitChromeDriver.initDriver().getDriver();
        MailPage mailPage = new MailPage( driver );
        mailPage.selectMailByIndex( index1 ).selectMailByIndex( index2 ).selectMailByIndex( index3 );
    }

    @Test(dataProvider = "task1_4Source")
    public void task1_5(int index1, int index2, int index3) {
        WebDriver driver = InitChromeDriver.initDriver().getDriver();
        MailPage mailPage = new MailPage( driver );
        mailPage.selectMailByIndex( index1 ).selectMailByIndex( index2 ).selectMailByIndex( index3 );
        mailPage.unselectAllMail();
    }

    @AfterMethod
    public void tearDown() {
        InitChromeDriver.initDriver().getDriver().quit();
    }
}
