import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class MailTest {
    DataBaseOperations dBOperations = new DataBaseOperations();

    @BeforeMethod
    public void setUp() throws SQLException {
        InitChromeDriver.getInstance().setDriver();
        WebDriver driver = InitChromeDriver.getInstance().getDriver();
        LoginPage loginPage = LoginPage.newLoginPage( driver );
        loginPage.login();
    }

    @Test
    public void task1_1() {
        WebDriver driver = InitChromeDriver.getInstance().getDriver();
        MailPage mailPage = new MailPage( driver );
        mailPage.selectMailAndMoveToSpam();
    }

    @Test
    public void task1_2() {
        WebDriver driver = InitChromeDriver.getInstance().getDriver();
        MailPage mailPage = new MailPage( driver );
        mailPage.selectMailAndReturnMailInBox();
    }

    @Test()
    public void task1_3() throws SQLException {
        WebDriver driver = InitChromeDriver.getInstance().getDriver();
        MailPage mailPage = new MailPage( driver );
        mailPage.createNewMailAndSendGroup( dBOperations.getValueByColumId( DbEnumUsers.send_to.getValue() )
                , dBOperations.getValueByColumId( DbEnumUsers.themeText.getValue() ), dBOperations.getValueByColumId( DbEnumUsers.mailText.getValue() ) );
    }

    @DataProvider
    public Object[][] task1_4Source() {
        return new Object[][]{{0, 5, 2}};
    }

    @Test(dataProvider = "task1_4Source")
    public void task1_4(int index1, int index2, int index3) {
        WebDriver driver = InitChromeDriver.getInstance().getDriver();
        MailPage mailPage = new MailPage( driver );
        mailPage.selectMailByIndex( index1 ).selectMailByIndex( index2 ).selectMailByIndex( index3 );
    }

    @Test(dataProvider = "task1_4Source")
    public void task1_5(int index1, int index2, int index3) {
        WebDriver driver = InitChromeDriver.getInstance().getDriver();
        MailPage mailPage = new MailPage( driver );
        mailPage.selectMailByIndex( index1 ).selectMailByIndex( index2 ).selectMailByIndex( index3 );
        mailPage.unselectAllMail();
    }

    @AfterMethod
    public void tearDown() {
        InitChromeDriver.getInstance().getDriver().quit();
    }
}
