import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class MailPage {
    private WebDriver driver;

    @FindBy(xpath = ".//a[@href=\"/spam/\"]")
    private WebElement buttonSpam;

    @FindBy(xpath = "(.//div[contains (@class, 'avatar')]//button//div[contains(@title, 'Выделить')])[1]")
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


    public MailPage selectMailAndMoveToSpam() {
        selectCheckBox.click();
        buttonMoveToSpam.click();
        return this;
    }

    public MailPage selectMailAndReturnMailInBox() {
        buttonSpam.click();

        (new WebDriverWait( driver, 80 )).until( ExpectedConditions.presenceOfElementLocated(
                By.xpath( ".//a[contains (@class, 'active') and contains (@href, '/spam/')]" ) ) );
        selectCheckBox.click();
        buttonMoveTo.click();
        buttonMoveToInBox.click();
        return this;
    }

    public MailPage  createNewMailAndSendGroup(String group, String theme, String text) {
        buttonNewMail.click();
        toSendField.sendKeys( group );
        driver.findElement( By.xpath( ".//div[contains(@class, 'datalist_visible')]" ) ).click();
        themeField.sendKeys( theme );
        textMailField.clear();
        textMailField.click();
        textMailField.sendKeys( text );
        buttonSend.click();
        return this;
    }

    public MailPage selectMailByIndex(int index) {
        ArrayList<WebElement> latterLiast = (ArrayList<WebElement>) driver.findElements( By.xpath( ".//a[contains(@class, 'letter-list')]" ) );
        latterLiast.get( index ).findElement( By.xpath( ".//div[contains (@class, 'avatar')]" ) ).click();
        return this;
    }

    public MailPage unselectAllMail() {
        ArrayList<WebElement> latterLiast = (ArrayList<WebElement>) driver.findElements( By.xpath( ".//a[contains(@class, 'letter-list')]" ) );
        String attrValue = "Снять выделение";
        for (int i = 0; i < latterLiast.size(); i++) {
            if (attrValue.equals( latterLiast.get( i ).findElement( By.xpath( ".//div[contains (@class, 'avatar')]//div" ) ).getAttribute( "data-title" ))) {
                latterLiast.get( i ).findElement( By.xpath( ".//div[contains (@class, 'avatar')]" ) ).click();
            }
        }
        return this;
    }


}
