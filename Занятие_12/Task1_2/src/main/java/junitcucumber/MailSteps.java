package junitcucumber;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MailSteps {
    private static final String MAIN_URL = "http://mail.ru";
    private static final String LOGIN = "test_at2019";
    private static final String PASSWORD = "1qaz1654";
    private MailPage mailPage;
    private WebDriver webDriver;

    public MailSteps() {
        System.setProperty( "webdriver.chrome.driver", "E:\\AT\\chromedriver\\chromedriver.exe" );
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        mailPage = new MailPage( webDriver );
    }

    @Given("^I am on main application page$")
    public void loadMainPage() {
        webDriver.get( MAIN_URL );
    }

    @When("^I login as correct user$")
    public void loginAsCorrectUser() {
        mailPage.enterLoginAndPass( LOGIN, PASSWORD );
        mailPage.clickEnterButton();
    }

    @When("^I select mail$")
    public void selectMail() {
        mailPage.selectMail();
    }

    @When("^I move mail to spam$")
    public void moveMailToSpam() {
        mailPage.moveMailToSpam();
    }

    @When("^I go to spam$")
    public void goToSpam() {
        mailPage.goToSpam();
    }

    @When("I move mail to inbox")
    public void moveMailToInbox() {
        mailPage.moveMailToInbox();
    }

    @When("I go to inbox")
    public void goToInbox() {
        mailPage.goToInbox();
    }

    @When("I click button new mail")
    public void clickButtonNewMail() {
        mailPage.clickButtonNewMail();
    }

    @When("I select \"([^\"]*)\"")
    public void selectGroup(String group) {
        mailPage.selectGroup( group );
    }

    @When("I field theme \"([^\"]*)\"")
    public void fieldTheme(String theme) {
        mailPage.fieldTheme( theme );
    }

    @When("I field text mail \"([^\"]*)\"")
    public void fieldTextMail(String textMail) {
        mailPage.fieldTextMail( textMail );
    }

    @When("I click send button")
    public void clickSendButton() {
        mailPage.clickSendButton();
    }

    @When("I put flag on mail by index \"([^\"]*)\"")
    public void puFlagOnMailByIndex(int id) {
        mailPage.putFlagOnMailByIndex( id );
    }

    @When("I take off flag on all mail")
    public void takeOffFlagAllMail() {
        mailPage.takeOffFlagAllMail();
    }

    @Then("I see mail in spam")
    public void seeMailSpam() {
        Assert.assertFalse( mailPage.isEmptyListMailList() );
    }

    @Then("I see mail in inbox")
    public void seeMailInbox() {
        Assert.assertFalse( mailPage.isEmptyListMailList() );
    }

    @Then("I see text \"([^\"]*)\"")
    public void seeText(String text) {
        Assert.assertEquals( text, mailPage.getTextSendMail() );
    }

    @Then("I see flag on mail")
    public void seeFlagOnMail() {
        Assert.assertFalse( mailPage.getIsEmptyListMailMarkedFlag() );
    }

    @Then("I not see flag on mail")
    public void notSeeFlagOnMail() {
        Assert.assertTrue( mailPage.getIsEmptyListMailMarkedFlag() );
    }

    @After
    public void afterClass() {
        webDriver.quit();
    }
}
