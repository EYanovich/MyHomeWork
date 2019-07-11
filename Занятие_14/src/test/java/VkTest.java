import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.simple.parser.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;


public class VkTest {
    private static final Logger logger = Logger.getLogger(VkTest.class);


    @DataProvider
    public Object[][] postMessageTestSource() {
        return new Object[][]{{"message", "Test text"}};
    }

    @Test(dataProvider = "postMessageTestSource")
    public void postMessageTest(String parameter, String value) throws IOException, URISyntaxException, ParseException {
        VkMetods vkMetods = new VkMetods();
        URIBuilder builder = vkMetods.createPostUrl( parameter, value );
        logger.info("Post URL created.");
        String execute = EntityUtils.toString( vkMetods.execute( builder ).getEntity() );
        String expectedExecute = "\"post_id\":";
        Assert.assertEquals( execute.substring( 13, 23 ), expectedExecute );
        logger.info("Response Ok.");
        Assert.assertTrue( vkMetods.checkIdInMessageList( vkMetods.getPostId( execute ) ) );
        logger.info("Message found on the wall");
    }

    @DataProvider
    public Object[][] editMessageTestSource() {
        return new Object[][]{{"message", "New test text"}};
    }

    @Test(dataProvider = "editMessageTestSource")
    public void editMessageTest(String parameter, String value) throws IOException, URISyntaxException, ParseException {
        VkMetods vkMetods = new VkMetods();
        URIBuilder builder = vkMetods.createEditUrl( parameter, value, vkMetods.getMaxIdInMessageList() );
        logger.info("Edit URL created.");
        String execute = EntityUtils.toString( vkMetods.execute( builder ).getEntity() );
        String expectedExecute = "\"post_id\":";
        Assert.assertEquals( execute.substring( 13, 23 ), expectedExecute );
        logger.info("Response Ok.");
        Assert.assertEquals( vkMetods.getTextMessageById( vkMetods.getMaxIdInMessageList() ), value );
        logger.info("Message found on the wall with new text.");
    }

    @Test()
    public void deleteMessageTest() throws IOException, URISyntaxException, ParseException {
        VkMetods vkMetods = new VkMetods();
        int maxIdMessage = Integer.parseInt( vkMetods.getMaxIdInMessageList() );
        URIBuilder builder = vkMetods.createDeleteUrl( vkMetods.getMaxIdInMessageList() );
        logger.info("Delete URL created.");
        String execute = EntityUtils.toString( vkMetods.execute( builder ).getEntity() );
        System.out.println( execute );
        String expectedExecute = "{\"response\":1}";
        Assert.assertEquals( execute, expectedExecute );
        logger.info("Response Ok.");
        Assert.assertFalse( vkMetods.checkIdInMessageList( maxIdMessage ) );
        logger.info("Message not found on the wall");
    }

}
