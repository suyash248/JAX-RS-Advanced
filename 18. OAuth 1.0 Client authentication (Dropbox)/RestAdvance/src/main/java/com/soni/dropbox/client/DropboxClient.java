package com.soni.dropbox.client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.oauth1.AccessToken;
import org.glassfish.jersey.client.oauth1.ConsumerCredentials;
import org.glassfish.jersey.client.oauth1.OAuth1AuthorizationFlow;
import org.glassfish.jersey.client.oauth1.OAuth1ClientSupport;
import org.glassfish.jersey.jackson.JacksonFeature;

import com.soni.model.AccountInfo;

/** Simple command-line application that uses Jersey OAuth client library to authenticate
 * with Twitter.
 */
public class DropboxClient {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")));
    private static final String ACCOUNTS_INFO_URI = "https://api.dropboxapi.com/1/account/info";
    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTIES_FILE_NAME = "/home/suyash/Desktop/dropboxclient.properties";
    private static final String PROPERTY_CONSUMER_KEY = "consumerKey";
    private static final String PROPERTY_CONSUMER_SECRET = "consumerSecret";
    private static final String PROPERTY_TOKEN = "token";
    private static final String PROPERTY_TOKEN_SECRET = "tokenSecret";
    
    private static final String requestTokenUrl = "https://api.dropbox.com/1/oauth/request_token";
    private static final String accessTokenUrl = "https://api.dropbox.com/1/oauth/access_token";
    private static final String authorizationUrl = "https://www.dropbox.com/1/oauth/authorize";

    /**
     * Main method that creates a {@link Client client} and initializes the OAuth support with
     * configuration needed to connect to the Dropbox and retrieve statuses.
     * <p/>
     * Execute this method to demo
     *
     * @param args Command line arguments.
     * @throws Exception Thrown when error occurs.
     */
    public static void main(final String[] args) throws Exception {
        // retrieve consumer key/secret and token/secret from the property file
        // or system properties
        new DropboxClient().loadSettings();;

        final ConsumerCredentials consumerCredentials = new ConsumerCredentials(
                PROPERTIES.getProperty(PROPERTY_CONSUMER_KEY),
                PROPERTIES.getProperty(PROPERTY_CONSUMER_SECRET));

        final Feature filterFeature;
        if ( StringUtils.isEmpty(PROPERTIES.getProperty(PROPERTY_TOKEN)) ) {

            // we do not have Access Token yet. Let's perfom the Authorization Flow first,
            // let the user approve our app and get Access Token.
            final OAuth1AuthorizationFlow authFlow = OAuth1ClientSupport.builder(consumerCredentials)
                    .authorizationFlow(requestTokenUrl, accessTokenUrl, authorizationUrl)
                    .build();
            final String authorizationUri = authFlow.start();

            System.out.println("Enter the following URI into a web browser and authorize me:");
            System.out.println(authorizationUri);
            System.out.print("Press ENTER once authorized..............");
            final String verifier;
            try {
                verifier = IN.readLine();
            } catch (final IOException ex) {
                throw new RuntimeException(ex);
            }
            final AccessToken accessToken = authFlow.finish();

            // store access token for next application execution
            PROPERTIES.setProperty(PROPERTY_TOKEN, accessToken.getToken());
            PROPERTIES.setProperty(PROPERTY_TOKEN_SECRET, accessToken.getAccessTokenSecret());

            // get the feature that will configure the client with consumer credentials and
            // received access token
            filterFeature = authFlow.getOAuth1Feature();
        } else {
            final AccessToken storedToken = new AccessToken(PROPERTIES.getProperty(PROPERTY_TOKEN),
                    PROPERTIES.getProperty(PROPERTY_TOKEN_SECRET));
            // build a new feature from the stored consumer credentials and access token
            filterFeature = OAuth1ClientSupport.builder(consumerCredentials).feature()
                    .accessToken(storedToken).build();
        }


        // create a new Jersey client and register filter feature that will add OAuth signatures and
        // JacksonFeature that will process returned JSON data.
        final Client client = ClientBuilder.newBuilder()
                .register(filterFeature)
                .register(JacksonFeature.class)
                .build();

        // make requests to protected resources
        // (no need to care about the OAuth signatures)
        final Response response = client.target(ACCOUNTS_INFO_URI).request().get();
        if (response.getStatus() != 200) {
            String errorEntity = null;
            if (response.hasEntity()) {
                errorEntity = response.readEntity(String.class);
            }
            throw new RuntimeException("Request to Twitter was not successful. Response code: "
                    + response.getStatus() + ", reason: " + response.getStatusInfo().getReasonPhrase()
                    + ", entity: " + errorEntity);
        }

        // persist the current consumer key/secret and token/secret for future use
        storeSettings();

        AccountInfo accountInfo = response.readEntity(AccountInfo.class);
        System.out.println(accountInfo);
    }

    private void loadSettings() {
    	FileInputStream fis = null;
        try {
            fis = new FileInputStream(PROPERTIES_FILE_NAME);
            PROPERTIES.load(fis);
        } catch (final IOException e) {
            // ignore
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (final IOException ex) {
                    // ignore
                }
            }
        }

        for (final String name : new String[]{PROPERTY_CONSUMER_KEY, PROPERTY_CONSUMER_SECRET,
                PROPERTY_TOKEN, PROPERTY_TOKEN_SECRET}) {
            final String value = System.getProperty(name);
            if (value != null) {
                PROPERTIES.setProperty(name, value);
            }
        }

        if (PROPERTIES.getProperty(PROPERTY_CONSUMER_KEY) == null
                || PROPERTIES.getProperty(PROPERTY_CONSUMER_SECRET) == null) {
            System.out.println("No consumerKey and/or consumerSecret found in twitterclient.properties file. "
                    + "You have to provide these as system properties. See README.html for more information.");
            System.exit(1);
        }
    }

    private static void storeSettings() {
        FileOutputStream st = null;
        try {
            st = new FileOutputStream(PROPERTIES_FILE_NAME);
            PROPERTIES.store(st, null);
        } catch (final IOException e) {
            // ignore
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (final IOException ex) {
                // ignore
            }
        }
    }

}
