package junitcucumber;

import junitcucumber.Utils.DbUtils;
import junitcucumber.driver.InitChromeDriver;
import junitcucumber.enums.DbEnumUsers;
import junitcucumber.page.*;
import junitcucumber.parsers.DomParserFacade;
import org.apache.log4j.Logger;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.yandex.qatools.allure.annotations.Attachment;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MailSteps {
    private static final Logger logger = Logger.getLogger( MailSteps.class );
    private InitChromeDriver initChromeDriver = InitChromeDriver.getInstance();
    private HomePage homePage;
    private SpamPage spamPage;
    private MailPage mailPage;
    private static final String EMAILS_XML = "Emails.xml";

    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document document = dBuilder.parse( EMAILS_XML );
    List<Emails> emails = new DomParserFacade().parse( document );

    public MailSteps() throws IOException, XMLStreamException, SAXException, ParserConfigurationException {
    }

    @Attachment(value = "Attachment Screenshot", type = "image/png")
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) initChromeDriver.getDriver()).getScreenshotAs( OutputType.BYTES );
    }

    @Given("^I am on main application page$")
    public void loadMainPage() {
        initChromeDriver.setDriver();
        logger.info( "Main application page is open" );
        makeScreenshot();
    }

    @When("^I login as correct user$")
    public void loginAsCorrectUser() throws SQLException {
        homePage = new LoginPage( initChromeDriver.getDriver() )
                .enterLogin( DbUtils.getValueByColumId( DbEnumUsers.login.getValue(), emails.get( 0 ).getUserId() ) )
                .enterPassword( DbUtils.getValueByColumId( DbEnumUsers.password.getValue(),
                        emails.get( 0 ).getUserId() ) ).login();
        logger.info( "Login as correct user" );
        makeScreenshot();
    }

    @When("^I select mail$")
    public void selectMail() {
        homePage.selectMail();
        logger.info( "Select mail" );
        makeScreenshot();
    }

    @When("^I move mail to spam$")
    public void moveMailToSpam() {
        homePage.moveMailToSpam();
        logger.info( "Move mail to spam" );
        makeScreenshot();
    }

    @When("^I go to spam$")
    public void goToSpam() {
        spamPage = homePage.goToSpam();
        logger.info( "Go to spam" );
        makeScreenshot();
    }

    @When("I move mail to inbox")
    public void moveMailToInbox() {
        spamPage.moveMailToInbox();
        logger.info( "Move mail to inbox" );
        makeScreenshot();
    }

    @When("I go to inbox")
    public void goToInbox() {
        homePage.goToInbox();
        logger.info( "Go to inbox" );
        makeScreenshot();
    }

    @When("I click button new mail")
    public void clickButtonNewMail() {
        mailPage = homePage.clickButtonNewMail();
        logger.info( "Click button new mail" );
        makeScreenshot();
    }

    @When("I select Group")
    public void selectGroup() throws SQLException {
        mailPage.selectGroup( DbUtils.getValueByColumId( DbEnumUsers.send_to.getValue(), emails.get( 0 ).getUserId() ) );
        logger.info( "Select Group" );
        makeScreenshot();
    }

    @When("I field theme")
    public void fieldTheme() throws SQLException {
        mailPage.fieldTheme( DbUtils.getValueByColumId( DbEnumUsers.themeText.getValue(), emails.get( 0 ).getUserId() ) );
        logger.info( "Field theme" );
        makeScreenshot();
    }

    @When("I fill in the xml \"([^\"]*)\" field theme")
    public void fieldThemeXml(int emailId) {
        mailPage.fieldTheme( emails.get( emailId ).getTheme() );
        logger.info( "Field theme xml" );
        makeScreenshot();
    }

    @When("I field text mail")
    public void fieldTextMail() throws SQLException {
        mailPage.fieldTextMail( DbUtils.getValueByColumId( DbEnumUsers.mailText.getValue(), emails.get( 0 ).getUserId() ) );
        logger.info( "Field text mail" );
        makeScreenshot();
    }

    @When("I fill in the xml \"([^\"]*)\" field text mail")
    public void fieldTextMailXml(int emailId) {
        mailPage.fieldTextMail( emails.get( emailId ).getText() );
        logger.info( "Field theme xml" );
        makeScreenshot();
    }

    @When("I click send button")
    public void clickSendButton() {
        homePage.clickSendButton();
        logger.info( "Click send button" );
        makeScreenshot();
    }

    @When("I put flag on mail by index \"([^\"]*)\"")
    public void puFlagOnMailByIndex(int id) {
        homePage.putFlagOnMailByIndex( id );
        logger.info( "Put flag on mail by index" + id );
        makeScreenshot();
    }

    @When("I take off flag on all mail")
    public void takeOffFlagAllMail() {
        homePage.takeOffFlagAllMail();
        logger.info( "Take off flag on all mail" );
        makeScreenshot();
    }

    @When("I clear recycled")
    public void clearRecycled() {
        homePage.clearRecycled();
        logger.info( "Clear recycled" );
        makeScreenshot();
    }

    @When("I click button remove")
    public void removeMail() {
        homePage.clickButtonRemove();
        logger.info( "Click button remove" );
        makeScreenshot();
    }

    @When("I go to recycled")
    public void goToRecycled() {
        homePage.goToRecycled();
        logger.info( "Go to recycled" );
        makeScreenshot();
    }

    @When("I click button clear spam")
    public void clickButtonClearSpam() {
        homePage.clickButtonClearSpam();
        logger.info( "Click button clear spam" );
        makeScreenshot();
    }

    @When("I open mail")
    public void openMail() {
        mailPage = homePage.openMail();
        logger.info( "Open mail" );
        makeScreenshot();
    }

    @When("I click button \"Ответить\"")
    public void replyMail() {
        mailPage.replyMail();
        logger.info( "Click button reply mail" );
        makeScreenshot();
    }

    @When("I field send to")
    public void fieldSendTo() throws SQLException {
        mailPage.fieldSendTo( DbUtils.getValueByColumId( DbEnumUsers.email.getValue(), emails.get( 0 ).getUserId() ) );
        logger.info( "Field send to" );
        makeScreenshot();
    }

    @When("I fill in the xml \"([^\"]*)\" field SendTo")
    public void fieldSendToXml(int emailId) {
        mailPage.fieldSendTo( emails.get( emailId ).getSendTo() );
        logger.info( "Field send to XML" );
        makeScreenshot();
    }

    @When("I click save button")
    public void clickSaveButton() {
        mailPage.clickSaveButton();
        logger.info( "Click save button" );
        makeScreenshot();
    }

    @When("I click cancel")
    public void clickCancelButton() {
        mailPage.clickCancelButton();
        logger.info( "Click cancel button" );
        makeScreenshot();
    }

    @When("I go to draft")
    public void goToDrafts() {
        homePage.goToDrafts();
        logger.info( "Go to draft" );
        makeScreenshot();
    }

    @Then("I see mail in spam")
    public void seeMailSpam() {
        Assert.assertFalse( spamPage.isEmptyListMailList() );
        logger.info( "Mail in spam" );
        makeScreenshot();
    }

    @Then("I see mail in inbox")
    public void seeMailInbox() {
        Assert.assertFalse( homePage.isEmptyListMailList() );
        logger.info( "Mail in inbox" );
        makeScreenshot();
    }

    @Then("I see text \"([^\"]*)\"")
    public void seeText(String text) {
        Assert.assertEquals( text, homePage.getTextSendMail() );
        logger.info( "Get text send mail" );
        makeScreenshot();
    }

    @Then("I see flag on mail")
    public void seeFlagOnMail() {
        Assert.assertFalse( homePage.getIsEmptyListMailMarkedFlag() );
        logger.info( "Get flag on mail" );
        makeScreenshot();
    }

    @Then("I not see flag on mail")
    public void notSeeFlagOnMail() {
        Assert.assertTrue( homePage.getIsEmptyListMailMarkedFlag() );
        logger.info( "Get flag on mail" );
        makeScreenshot();
    }

    @Then("I see home URL")
    public void seeUrl() throws SQLException {
        Assert.assertEquals( DbUtils.getValueByColumId( DbEnumUsers.homeUrl.getValue(), emails.get( 0 ).getUserId() ), homePage.getCurrentUrl() );
        logger.info( "Get home Url" );
        makeScreenshot();
    }

    @Then("I see mail in recycled")
    public void seeMailInRecycled() {
        Assert.assertFalse( homePage.getListMails().isEmpty() );
        logger.info( "Get mail in recycled" );
        makeScreenshot();
    }

    @Then("I see my email address")
    public void seeMyEmailAddress() throws SQLException {
        Assert.assertEquals( DbUtils.getValueByColumId
                ( DbEnumUsers.email.getValue(), emails.get( 0 ).getUserId() ), homePage.getCurrentMailAddress() );
        logger.info( "Get my email address" );
        makeScreenshot();
    }

    @Then("I see text in spam \"([^\"]*)\"")
    public void seeTextInSpam(String text) {
        Assert.assertEquals( text, spamPage.getTextInSpam() );
        logger.info( "Get text in spam" );
        makeScreenshot();
    }

    @Then("I see text theme \"([^\"]*)\"")
    public void seeTextTheme(String text) {
        Assert.assertEquals( text, mailPage.getThemeText().substring( 0, 3 ) );
        logger.info( "Get text in theme" );
        makeScreenshot();
    }

    @Then("I see notify message \"([^\"]*)\"")
    public void seeNotifyMessageText(String text) {
        Assert.assertEquals( text, mailPage.getNotifyMessageText().substring( 0, 22 ) );
        logger.info( "Get text notify message" );
        makeScreenshot();
    }

    @Then("I see my save mail")
    public void seeNotifyMessageText() throws SQLException {
        Assert.assertEquals( DbUtils.getValueByColumId( DbEnumUsers
                .themeText.getValue(), emails.get( 0 ).getUserId() ), mailPage.getThemeText() );
        Assert.assertEquals( DbUtils.getValueByColumId( DbEnumUsers
                .email.getValue(), emails.get( 0 ).getUserId() ), mailPage.getSendTo() );
        logger.info( "Get my save email" );
        makeScreenshot();
    }

    @When("After test")
    public void tearDown() {
        makeScreenshot();
        initChromeDriver.quitDriver();
        logger.info( "Driver quit" );
    }
}
