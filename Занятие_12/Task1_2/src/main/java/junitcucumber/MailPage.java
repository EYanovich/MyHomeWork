package junitcucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class MailPage {
    private static final int LINK_PRESENSE_TIMEOUT = 60;

    private WebDriver driver;

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    @FindBy(xpath = ".//div[contains(text(), 'Спам')]")
    private WebElement buttonSpam;

    @FindBy(xpath = "(.//div[contains (@class, 'avatar')])[1]")
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

    @FindBy(xpath = "//*[@id='mailbox:submit']/input")
    private WebElement buttonEnter;

    @FindBy(xpath = ".//a[@href=\"/inbox/\"]")
    private WebElement buttonInbox;

    @FindBy(xpath = "/html/body/div[8]/div/div/div[2]/div[2]/div/div/div[2]")
    private WebElement textSendMail;

    public MailPage(WebDriver webdriver) {
        PageFactory.initElements( webdriver, this );
        this.driver = webdriver;
    }

    public void enterLoginAndPass(String login, String password) {
        loginField.clear();
        loginField.sendKeys( login );
        passwordField.clear();
        passwordField.sendKeys( password );
    }

    public void clickEnterButton() {
        buttonEnter.click();
    }

    public void selectMail() {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.elementToBeClickable( selectCheckBox ) );
        selectCheckBox.click();
    }

    public void moveMailToSpam() {
        buttonMoveToSpam.click();
    }

    public void goToSpam() {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.presenceOfElementLocated( By.xpath( ".//a[contains(@href, 'spam')]" ) ) );
        driver.findElement( By.xpath( ".//a[contains(@href, 'spam')]" ) ).click();
    }

    public boolean isEmptyListMailList() {
        return driver.findElements( By.xpath( ".//a[@data-uidl-id]" ) ).isEmpty();
    }

    public void moveMailToInbox() {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.elementToBeClickable( buttonMoveTo ) );
        buttonMoveTo.click();
        buttonMoveToInBox.click();
    }

    public void goToInbox() {
        buttonInbox.click();
    }

    public void clickButtonNewMail() {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.elementToBeClickable( buttonNewMail ) );
        buttonNewMail.click();
    }

    public void selectGroup(String group) {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.elementToBeClickable( toSendField ) );
        toSendField.sendKeys( group );
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.presenceOfElementLocated( By.xpath( ".//div[contains(@class, 'datalist_visible')]" ) ) );
        driver.findElement( By.xpath( ".//div[contains(@class, 'datalist_visible')]" ) ).click();
    }

    public void fieldTheme(String theme) {
        themeField.clear();
        themeField.sendKeys( theme );
    }

    public void fieldTextMail(String text) {
        textMailField.clear();
        textMailField.click();
        textMailField.sendKeys( text );
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
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.presenceOfElementLocated( By.xpath( ".//button[contains(@class, \"ll-fs\")]" ) ) );
        latterLiast.get( id ).findElement( By.xpath( ".//button[contains(@class, \"ll-fs\")]" ) ).click();
    }

    public boolean getIsEmptyListMailMarkedFlag() {
        return driver.findElements( By.xpath( ".//button[contains(@class, \"ll-fs\") and contains(@title, \"Снять флажок\")]" ) ).isEmpty();
    }

    public void takeOffFlagAllMail() {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.presenceOfElementLocated( By.xpath( ".//button[contains(@class, \"ll-fs\") and contains(@title, \"Снять флажок\")]" ) ) );
        ArrayList<WebElement> latterLiast = (ArrayList<WebElement>) driver.findElements( By.xpath( ".//button[contains(@class, \"ll-fs\") and contains(@title, \"Снять флажок\")]" ) );
        for (int i = 0; i < latterLiast.size(); i++) {
            latterLiast.get( i ).click();
        }
    }
}


