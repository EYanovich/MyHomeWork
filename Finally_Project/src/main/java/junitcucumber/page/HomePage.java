package junitcucumber.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private static final int LINK_PRESENSE_TIMEOUT = 60;

    private WebDriver driver;

    @FindBy(xpath = ".//a[@href=\"/spam/\"]")
    private WebElement buttonSpam;

    @FindBy(xpath = ".//a[@href=\"/drafts/\"]")
    private WebElement buttonDrafts;

    @FindBy(xpath = ".//a[@href=\"/trash/\"]")
    private WebElement buttonRecycled;

    @FindBy(xpath = "(.//div[contains (@class, 'avatar')])[1]")
    private WebElement selectCheckBox;

    @FindBy(xpath = ".//div[contains (@class, 'avatar')]//div")
    private WebElement CheckBoxStstus;

    @FindBy(xpath = ".//div[contains (@class, 'spam')]")
    private WebElement buttonMoveToSpam;

    @FindBy(xpath = ".//span[@title=\"Написать письмо\"]")
    private WebElement buttonNewMail;

    @FindBy(xpath = ".//span[text() = \"Отправить\"]")
    private WebElement buttonSend;

    @FindBy(xpath = ".//a[@href=\"/inbox/\"]")
    private WebElement buttonInbox;

    @FindBy(xpath = "/html/body/div[8]/div/div/div[2]/div[2]/div/div/div[2]")
    private WebElement textSendMail;

    @FindBy(id = "PH_user-email")
    private WebElement currentMailAddress;

    @FindBy(xpath = ".//div[text() = 'Корзина']/../following-sibling::div")
    private WebElement clearRecycledButton;

    @FindBy(xpath = ".//div[@class = 'layer__submit-button']")
    private WebElement confirmСlearButton;

    @FindBy(xpath = ".//div[contains(@class, 'remove')]")
    private WebElement removeButton;

    @FindBy(xpath = ".//div[text() = 'Спам']/../following-sibling::div")
    private WebElement clearSpamButton;

    @FindBy(xpath = "(.//a[@data-id])[1]")
    private WebElement firstMail;

    public HomePage(WebDriver webdriver) {
        PageFactory.initElements( webdriver, this );
        this.driver = webdriver;
    }

    public void selectMail() {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.elementToBeClickable( selectCheckBox ) );
        selectCheckBox.click();
    }

    public void moveMailToSpam() {
        buttonMoveToSpam.click();
    }

    public SpamPage goToSpam() {
        buttonSpam.click();
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.presenceOfElementLocated( By.xpath( ".//a[@href=\"/spam/\" and contains(@class, 'active')]" ) ) );
        return new SpamPage(driver);
    }

    public boolean isEmptyListMailList() {
        return driver.findElements( By.xpath( ".//a[@data-uidl-id]" ) ).isEmpty();
    }

    public void goToInbox() {
        buttonInbox.click();
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.presenceOfElementLocated( By.xpath( ".//a[@href=\"/inbox/\" and contains(@class, 'active')]" ) ) );
    }

    public MailPage clickButtonNewMail() {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.elementToBeClickable( buttonNewMail ) );
        buttonNewMail.click();
        return new MailPage( driver );
    }

    public String getTextSendMail() {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.presenceOfElementLocated( By.xpath( "/html/body/div[8]/div/div/div[2]/div[2]/div/div/div[2]" ) ) );
        return textSendMail.getText();
    }

    public void clickSendButton() {
        buttonSend.click();
    }

    public void putFlagOnMailByIndex(int id) {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.presenceOfElementLocated( By.xpath( ".//a[contains(@class, 'letter-list')]" ) ) );
        ArrayList<WebElement> latterLiast = (ArrayList<WebElement>) driver.findElements( By.xpath( ".//a[contains(@class, 'letter-list')]" ) );
        latterLiast.get( id ).findElement( By.xpath( ".//button[contains(@class, \"ll-fs\")]/.." ) ).click();
    }

    public boolean getIsEmptyListMailMarkedFlag() {
        driver.navigate().refresh();
        int size = driver.findElements( By.xpath( ".//button[contains(@title, \"Снять флажок\")]/.." ) ).size();
        return size == 0;
    }

    public void takeOffFlagAllMail() {
        driver.navigate().refresh();
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.visibilityOfAllElements( driver.findElements( By.xpath( ".//button[contains(@title, \"Снять флажок\")]/.." ) ) ) );
        ArrayList<WebElement> latterLiast = (ArrayList<WebElement>) driver.findElements( By.xpath( ".//button[contains(@title, \"Снять флажок\")]/.." ) );
        for (int i = 0; i < latterLiast.size(); i++) {
            latterLiast.get( i ).click();
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getCurrentMailAddress() {
        return currentMailAddress.getText();
    }

    public void clearRecycled() {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.elementToBeClickable( clearRecycledButton ) );
        clearRecycledButton.click();
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.visibilityOf( confirmСlearButton ) );
        confirmСlearButton.click();
    }

    public void clickButtonRemove() {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.visibilityOf( removeButton ) );
        removeButton.click();
    }

    public void goToRecycled() {
        buttonRecycled.click();
    }

    public List<WebElement> getListMails() {
        return driver.findElements( By.xpath( ".//a[@data-id]" ) );
    }

    public void clickButtonClearSpam() {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.elementToBeClickable( clearSpamButton ) );
        clearSpamButton.click();
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.visibilityOf( confirmСlearButton ) );
        confirmСlearButton.click();
    }

    public MailPage openMail() {
        firstMail.click();
        return new MailPage( driver );
    }

    public void goToDrafts(){
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.elementToBeClickable( buttonDrafts ) );
        buttonDrafts.click();
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.presenceOfElementLocated( By.xpath( ".//a[@href=\"/drafts/\" and contains(@class, 'active')]" ) ) );
    }

}


