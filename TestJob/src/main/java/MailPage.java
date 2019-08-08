import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MailPage {

    private WebDriver driver;
    private static int LINK_PRESENSE_TIMEOUT = 60;

    public MailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements( driver, this );
        for (String handle : driver.getWindowHandles()
        ) {
            driver.switchTo().window( handle );
        }
    }

    @FindBy(name = "login")
    private WebElement loginField;

    public List<WebElement> getListLetters(){
        return driver.findElements(By.xpath(".//div[contains(@data-key, 'current_folder')and @data-id]"));
    }
}
