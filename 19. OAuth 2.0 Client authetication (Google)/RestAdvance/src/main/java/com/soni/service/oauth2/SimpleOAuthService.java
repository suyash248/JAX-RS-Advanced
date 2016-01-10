package com.soni.service.oauth2;

import org.glassfish.jersey.client.oauth2.ClientIdentifier;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;

/**
 * Store of credentials and Authorization flow. This is very simple one user store for credentials.
 * In real usage we would store tokens and flows per user.
 */
public class SimpleOAuthService {
    private static String accessToken = null;
    /**
     * Contains null or actually authorization flow.
     */
    private static OAuth2CodeGrantFlow flow;
    private static ClientIdentifier clientIdentifier;

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        SimpleOAuthService.accessToken = accessToken;
    }

    public static OAuth2CodeGrantFlow getFlow() {
        return flow;
    }

    public static void setFlow(OAuth2CodeGrantFlow flow) {
        SimpleOAuthService.flow = flow;
    }

    public static ClientIdentifier getClientIdentifier() {
        return clientIdentifier;
    }

    public static void setClientIdentifier(ClientIdentifier clientIdentifier) {
        SimpleOAuthService.clientIdentifier = clientIdentifier;
    }
}