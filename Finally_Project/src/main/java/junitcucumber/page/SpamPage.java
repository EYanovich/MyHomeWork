package junitcucumber.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpamPage {
    private WebDriver driver;
    private static final int LINK_PRESENSE_TIMEOUT = 60;

    public SpamPage(WebDriver webdriver) {
        PageFactory.initElements( webdriver, this );
        this.driver = webdriver;
    }

    @FindBy(xpath = ".//div[contains(@class, \"element_move\")]//div[text() = \"Входящие\"]")
    private WebElement buttonMoveToInBox;

    @FindBy(xpath = ".//div[contains(@class, \"element_move\")]")
    private WebElement buttonMoveTo;

    @FindBy(xpath = ".//div[@class = 'octopus__text']")
    private WebElement textSpam;

    public boolean isEmptyListMailList() {
        return driver.findElements( By.xpath( ".//a[@data-uidl-id]" ) ).isEmpty();
    }

    public void moveMailToInbox() {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.elementToBeClickable( buttonMoveTo ) );
        buttonMoveTo.click();
        buttonMoveToInBox.click();
    }

    public String getTextInSpam() {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.visibilityOf( textSpam ) );
        String spamText = textSpam.getText();
        spamText = spamText.replaceAll( "\n", " " );
        return spamText;
    }


}
