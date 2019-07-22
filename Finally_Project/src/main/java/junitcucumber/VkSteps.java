package junitcucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junitcucumber.page.VkPage;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import java.io.IOException;
import java.net.URISyntaxException;

public class VkSteps {
    private static final Logger logger = Logger.getLogger(VkSteps.class);
    private VkPage vkPage = new VkPage();
    private int maxIdMessage;
    private int postMessageId;

    @Given("^I created Post URL \"([^\"]*)\", \"([^\"]*)\"$")
    public void createdPostUrl(String parameter, String value) throws IOException, URISyntaxException {
        vkPage.createPostUrl( parameter, value );
        logger.info("Post URL created.");
    }

    @Given("^I created edit URL \"([^\"]*)\", \"([^\"]*)\"$")
    public void createdEditUrl(String parameter, String value) throws IOException, URISyntaxException, ParseException {
        vkPage.createEditUrl( parameter, value, vkPage.getMaxIdInMessageList() );
        logger.info("Edit URL created.");
    }

    @Given("^I created delete URL$")
    public void createdDeleteUrl() throws IOException, URISyntaxException, ParseException, InterruptedException {
        Thread.sleep( 1000 );
        maxIdMessage = Integer.parseInt(vkPage.getMaxIdInMessageList());
        vkPage.createDeleteUrl( vkPage.getMaxIdInMessageList() );
        logger.info("Delete URL created.");
    }

    @Then("I see Response Ok")
    public void seeResponse() throws IOException, URISyntaxException {
        String expectedExecute = "\"post_id\":";
        Assert.assertEquals( vkPage.getExecute().substring( 13, 23 ), expectedExecute );
        logger.info("Response Ok.");
    }

    @Then("I see Message on the wall")
    public void seeMessageOnWall() throws IOException, URISyntaxException, ParseException {
        postMessageId = vkPage.getPostId( vkPage.getExecute() );
        Assert.assertTrue( vkPage.checkIdInMessageList(postMessageId) );
        logger.info("Message found on the wall");
    }

    @Then("I see Message with new text \"([^\"]*)\"")
    public void seeMessageOnWallNewText(String expectedExecute) throws IOException, URISyntaxException, ParseException {
        Assert.assertEquals( vkPage.getTextMessageById( vkPage.getMaxIdInMessageList() ), expectedExecute );
        logger.info("Message found on the wall with new text.");
    }

    @Then("I see delete Response Ok")
    public void seeMessageOnWallNewText() throws IOException, URISyntaxException, ParseException {
        String expectedExecute = "{\"response\":1}";
        Assert.assertEquals( vkPage.getExecute(), expectedExecute );
        logger.info("Response Ok.");
    }

    @Then("I not see Message on the wall")
    public void notSeeMessageOnWallNewText() throws IOException, URISyntaxException, ParseException {
        Assert.assertFalse( vkPage.checkIdInMessageList( maxIdMessage ) );
        logger.info("Message not found on the wall");
    }
}
