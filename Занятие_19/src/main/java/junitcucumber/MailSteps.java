package junitcucumber;

import junitcucumber.Utils.DbUtils;
import junitcucumber.driver.InitChromeDriver;
import junitcucumber.enums.DbEnumUsers;
import junitcucumber.page.*;
import junitcucumber.parsers.DomParserFacade;
import org.junit.Assert;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MailSteps {
    private InitChromeDriver initChromeDriver = InitChromeDriver.getInstance();
    private HomePage homePage;
    private SpamPage spamPage;
    private MailPage mailPage;
    private static final String EMAILS_XML = "Emails.xml";

    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document document = dBuilder.parse( EMAILS_XML );
    List<Emails> emails = new DomParserFacade().parse(document);

    public MailSteps() throws IOException, XMLStreamException, SAXException, ParserConfigurationException {
    }


    @Given("^I am on main application page$")
    public void loadMainPage() {
        initChromeDriver.setDriver();
    }

    @When("^I login as correct user$")
    public void loginAsCorrectUser() throws SQLException {
        homePage = new LoginPage( initChromeDriver.getDriver() ).enterLogin( DbUtils.getValueByColumId( DbEnumUsers.login.getValue(), emails.get( 0 ).getUserId() ) )
                .enterPassword( DbUtils.getValueByColumId( DbEnumUsers.password.getValue(), emails.get( 0 ).getUserId() ) ).login();
    }

    @When("^I select mail$")
    public void selectMail() {
        homePage.selectMail();
    }

    @When("^I move mail to spam$")
    public void moveMailToSpam() {
        homePage.moveMailToSpam();
    }

    @When("^I go to spam$")
    public void goToSpam() {
        spamPage = homePage.goToSpam();
    }

    @When("I move mail to inbox")
    public void moveMailToInbox() {
        spamPage.moveMailToInbox();
    }

    @When("I go to inbox")
    public void goToInbox() {
        homePage.goToInbox();
    }

    @When("I click button new mail")
    public void clickButtonNewMail() {
        mailPage = homePage.clickButtonNewMail();
    }

    @When("I select Group")
    public void selectGroup() throws SQLException {
        mailPage.selectGroup( DbUtils.getValueByColumId( DbEnumUsers.send_to.getValue(), emails.get( 0 ).getUserId() ) );
    }

    @When("I field theme")
    public void fieldTheme() throws SQLException {
        mailPage.fieldTheme( DbUtils.getValueByColumId( DbEnumUsers.themeText.getValue(), emails.get( 0 ).getUserId() ) );
    }

    @When("I fill in the xml \"([^\"]*)\" field theme")
    public void fieldThemeXml(int emailId) {
        mailPage.fieldTheme( emails.get( emailId ).getTheme() );
    }

    @When("I field text mail")
    public void fieldTextMail() throws SQLException {
        mailPage.fieldTextMail( DbUtils.getValueByColumId( DbEnumUsers.mailText.getValue(), emails.get( 0 ).getUserId() ) );
    }

    @When("I fill in the xml \"([^\"]*)\" field text mail")
    public void fieldTextMailXml(int emailId) {
        mailPage.fieldTextMail( emails.get( emailId ).getText() );
    }

    @When("I click send button")
    public void clickSendButton() {
        homePage.clickSendButton();
    }

    @When("I put flag on mail by index \"([^\"]*)\"")
    public void puFlagOnMailByIndex(int id) {
        homePage.putFlagOnMailByIndex( id );
    }

    @When("I take off flag on all mail")
    public void takeOffFlagAllMail() {
        homePage.takeOffFlagAllMail();
    }

    @When("I clear recycled")
    public void clearRecycled() {
        homePage.clearRecycled();
    }

    @When("I click button remove")
    public void removeMail() {
        homePage.clickButtonRemove();
    }

    @When("I go to recycled")
    public void goToRecycled() {
        homePage.goToRecycled();
    }

    @When("I click button clear spam")
    public void clickButtonClearSpam() {
        homePage.clickButtonClearSpam();
    }

    @When("I open mail")
    public void openMail() {
        mailPage = homePage.openMail();
    }

    @When("I click button \"Ответить\"")
    public void replyMail() {
        mailPage.replyMail();
    }

    @When("I field send to")
    public void fieldSendTo() throws SQLException {
        mailPage.fieldSendTo(DbUtils.getValueByColumId( DbEnumUsers.email.getValue(), emails.get( 0 ).getUserId() ));
    }

    @When("I fill in the xml \"([^\"]*)\" field SendTo")
    public void fieldSendToXml(int emailId){
        mailPage.fieldSendTo(emails.get( emailId ).getSendTo());
    }

    @When("I click save button")
    public void clickSaveButton() {
        mailPage.clickSaveButton();
    }

    @When("I click cancel")
    public void clickCancelButton() {
        mailPage.clickCancelButton();
    }

    @When("I go to draft")
    public void goToDrafts() {
        homePage.goToDrafts();
    }

    @Then("I see mail in spam")
    public void seeMailSpam() {
        Assert.assertFalse( spamPage.isEmptyListMailList() );
    }

    @Then("I see mail in inbox")
    public void seeMailInbox() {
        Assert.assertFalse( homePage.isEmptyListMailList() );
    }

    @Then("I see text \"([^\"]*)\"")
    public void seeText(String text) {
        Assert.assertEquals( text, homePage.getTextSendMail() );
    }

    @Then("I see flag on mail")
    public void seeFlagOnMail() {
        Assert.assertFalse( homePage.getIsEmptyListMailMarkedFlag() );
    }

    @Then("I not see flag on mail")
    public void notSeeFlagOnMail() {
        Assert.assertTrue( homePage.getIsEmptyListMailMarkedFlag() );
    }

    @Then("I see home URL")
    public void seeUrl() throws SQLException {
        Assert.assertEquals( DbUtils.getValueByColumId( DbEnumUsers.homeUrl.getValue(), emails.get( 0 ).getUserId() ), homePage.getCurrentUrl() );
    }

    @Then("I see mail in recycled")
    public void seeMailInRecycled() {
        Assert.assertFalse( homePage.getListMails().isEmpty() );
    }

    @Then("I see my email address")
    public void seeMyEmailAddress() throws SQLException {
        Assert.assertEquals( DbUtils.getValueByColumId( DbEnumUsers.email.getValue(), emails.get( 0 ).getUserId() ), homePage.getCurrentMailAddress() );
    }

    @Then("I see text in spam \"([^\"]*)\"")
    public void seeTextInSpam(String text) {
        Assert.assertEquals( text, spamPage.getTextInSpam() );
    }

    @Then("I see text theme \"([^\"]*)\"")
    public void seeTextTheme(String text) {
        Assert.assertEquals( text, mailPage.getThemeText().substring( 0, 3 ) );
    }

    @Then("I see notify message \"([^\"]*)\"")
    public void seeNotifyMessageText(String text) {
        Assert.assertEquals( text, mailPage.getNotifyMessageText().substring( 0, 22 ) );
    }

    @Then("I see my save mail")
    public void seeNotifyMessageText() throws SQLException {
        Assert.assertEquals( DbUtils.getValueByColumId( DbEnumUsers.themeText.getValue(), emails.get( 0 ).getUserId() ), mailPage.getThemeText() );
        Assert.assertEquals( DbUtils.getValueByColumId( DbEnumUsers.email.getValue(), emails.get( 0 ).getUserId() ), mailPage.getSendTo() );
    }

    @After
    public void afterClass() {
        initChromeDriver.quitDriver();
    }
}
