package junitcucumber.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailPage {
    private WebDriver driver;
    private static final int LINK_PRESENSE_TIMEOUT = 60;

    public MailPage(WebDriver webdriver) {
        PageFactory.initElements( webdriver, this );
        this.driver = webdriver;
    }

    @FindBy(xpath = ".//div[@class = 'letter__footer-button']/span[contains(@class, 'reply')]")
    private WebElement replyMailButton;

    @FindBy(xpath = ".//div[@class=\"container--3QXHv\"]//input")
    private WebElement themeField;

    @FindBy(xpath = ".//div[@class=\"input--3slxg\"]//input")
    private WebElement toSendField;

    @FindBy(xpath = ".//div[@role='textbox']")
    private WebElement textMailField;

    @FindBy(xpath = ".//span[text() = 'Сохранить']")
    private WebElement saveButton;

    @FindBy(xpath = ".//span[@class = 'notify__message__text']")
    private WebElement notifyMessageText;

    @FindBy(xpath = ".//span[text() = 'Отменить']")
    private WebElement cancelButton;

    @FindBy(xpath = ".//span[@class = 'text--1tHKB']/..")
    private WebElement getSendField;

    public void replyMail(){
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.visibilityOf( replyMailButton ) );
        replyMailButton.click();
    }

    public String getThemeText(){
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.visibilityOf( themeField ) );
        return themeField.getAttribute( "value" );
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

    public void fieldSendTo(String send){
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.visibilityOf( toSendField ) );
        toSendField.clear();
        toSendField.sendKeys( send );
        driver.findElement( By.xpath( ".//div[contains(@class, 'datalist_visible')]" ) ).click();
    }

    public void selectGroup(String group) {
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.elementToBeClickable( toSendField ) );
        toSendField.sendKeys( group );
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.presenceOfElementLocated( By.xpath( ".//div[contains(@class, 'datalist_visible')]" ) ) );
        driver.findElement( By.xpath( ".//div[contains(@class, 'datalist_visible')]" ) ).click();
    }

    public String getMailText(){
        return textMailField.getAttribute( "value" );
    }

    public void clickSaveButton(){
        saveButton.click();
    }

    public String getNotifyMessageText(){
        (new WebDriverWait( driver, LINK_PRESENSE_TIMEOUT )).until( ExpectedConditions.visibilityOf( notifyMessageText ) );
        return notifyMessageText.getText();
    }

    public void clickCancelButton(){
        cancelButton.click();
    }

    public String getSendTo(){
        return getSendField.getAttribute( "Title" );
    }
}
