import driver.InitChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ConfigProperties;

public class Tests {
    private InitChromeDriver initChromeDriver = InitChromeDriver.getInstance();
    private MailPage mailPage;

    @BeforeTest
    public void setUp(){
        initChromeDriver.setDriver();
    }

    @Test
    public void testMailTutBy() {
        mailPage = new LoginPage(initChromeDriver.getDriver())
                .signInClick().enterLogin(ConfigProperties.getProperty("user"))
                .enterPassword(ConfigProperties.getProperty("password")).login();
        Assert.assertFalse(mailPage.getListLetters().isEmpty());
    }

    @AfterTest
    public void tearDown(){
        initChromeDriver.quitDriver();
    }
}
