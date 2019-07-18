import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InitChromeDriver {
    private static InitChromeDriver initDriver;
    private WebDriver driver;

    private InitChromeDriver() {
    }

    public static InitChromeDriver getInstance() {
        if (initDriver == null) {
            initDriver = new InitChromeDriver();
        }
        return initDriver;
    }

    public void setDriver() {
        ConfigProperties configProperties = new ConfigProperties();
        System.setProperty( configProperties.getProperty( "drivername" ), configProperties.getProperty( "chromedriverpath" ) );
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait( 40, TimeUnit.SECONDS );
        driver.get( configProperties.getProperty( "url" ) );
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
