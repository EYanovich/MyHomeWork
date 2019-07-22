package junitcucumber.page;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;

public class VkPage {
    private static final Logger logger = Logger.getLogger(VkPage.class);
    private URIBuilder builder;

    public URIBuilder createPostUrl(String parameter, String value) throws IOException, URISyntaxException {
        URIBuilder builder = createBasicUrl( "post" );
        builder.setParameter( parameter, value );
        return builder;
    }

    private URIBuilder createBasicUrl(String methodName) throws IOException, URISyntaxException {
        String urlName = String.format( "https://api.vk.com/method/wall.%s?", methodName );
        builder = new URIBuilder( urlName );
        builder.setParameter( "access_token", "44ceda47c64697cab07f978513c108fb056a86130578e11cba68e55597f82ebb2be7e54eac344a7c57430" )
                .setParameter( "owner_id", "8045198" )
                .setParameter( "v", "5.101" );
        logger.info("Basik URL created.");
        return builder;
    }

    public URIBuilder createGetByIdUrl(String methodName,String postId) throws IOException, URISyntaxException {
        String urlName = String.format( "https://api.vk.com/method/wall.%s?", methodName );
        String posts = String.format( "8045198_%s", postId );
        builder = new URIBuilder( urlName );
        builder.setParameter( "access_token", "44ceda47c64697cab07f978513c108fb056a86130578e11cba68e55597f82ebb2be7e54eac344a7c57430" )
                .setParameter( "posts", posts )
                .setParameter( "v", "5.101" );
        logger.info("Post URL created.");
        return builder;
    }

    public String getExecute() throws IOException, URISyntaxException {
        return EntityUtils.toString( execute( builder ).getEntity());
    }

    public HttpResponse execute(URIBuilder builder) throws IOException, URISyntaxException {
        HttpGet request = new HttpGet( builder.build() );
        HttpClient client = HttpClientBuilder.create().build();
        return client.execute( request );
    }

    public boolean checkIdInMessageList(int idMsg) throws IOException, URISyntaxException, ParseException {
        URIBuilder builderGet = createBasicUrl( "get" );
        HttpResponse response = execute( builderGet );
        String execute = EntityUtils.toString( response.getEntity() ).substring( 12 );
        execute = execute.substring( 0, execute.length() - 1 );
        Object obj = new JSONParser().parse( execute );
        JSONObject jo = (JSONObject) obj;
        JSONArray id = (JSONArray) jo.get( "items" );
        Iterator idItr = id.iterator();
        boolean checkIdMessage = false;
        while (idItr.hasNext()) {
            JSONObject test = (JSONObject) idItr.next();
            if (Integer.parseInt( test.get( "id" ).toString() ) == idMsg) {
                checkIdMessage = true;
            }
        }
        return checkIdMessage;
    }

    public int getPostId(String execute) throws ParseException {
        Object obj = new JSONParser().parse( execute );
        JSONObject jo = (JSONObject) obj;
        JSONObject jso = (JSONObject) jo.get( "response" );
        return Integer.parseInt( jso.get("post_id").toString() );
    }

    public String getMaxIdInMessageList() throws IOException, URISyntaxException, ParseException {
        URIBuilder builderGet = createBasicUrl( "get" );
        HttpResponse response = execute( builderGet );
        String execute = EntityUtils.toString( response.getEntity() ).substring( 12 );
        execute = execute.substring( 0, execute.length() - 1 );
        Object obj = new JSONParser().parse( execute );
        JSONObject jo = (JSONObject) obj;
        JSONArray id = (JSONArray) jo.get( "items" );
        Iterator idItr = id.iterator();
        int maxMessageId = 0;
        while (idItr.hasNext()) {
            JSONObject test = (JSONObject) idItr.next();
            if (Integer.parseInt( test.get( "id" ).toString() ) > maxMessageId) {
                maxMessageId = Integer.parseInt( test.get( "id" ).toString() );
            }
        }
        return Integer.toString( maxMessageId );
    }

    public URIBuilder createEditUrl(String parameter, String value, String postId) throws IOException, URISyntaxException {
        URIBuilder builder = createBasicUrl( "edit" );
        builder.setParameter( parameter, value );
        builder.setParameter( "post_id", postId );
        return builder;
    }

    public String getTextMessageById(String postId) throws IOException, URISyntaxException, ParseException {
        URIBuilder builderGet = createGetByIdUrl( "getById", postId );
        HttpResponse response = execute( builderGet );
        String execute = EntityUtils.toString( response.getEntity() );
        Object obj = new JSONParser().parse( execute );
        JSONObject jo = (JSONObject) obj;
        JSONArray jso = (JSONArray) jo.get( "response" );
        Iterator idItr = jso.iterator();
        String textMessage = null;
        while (idItr.hasNext()) {
            JSONObject test = (JSONObject) idItr.next();
            textMessage = test.get( "text" ).toString();
        }
        return textMessage;
    }

    public URIBuilder createDeleteUrl(String postId) throws IOException, URISyntaxException {
        URIBuilder builder = createBasicUrl( "delete" );
        builder.setParameter( "post_id", postId );
        return builder;
    }
}
