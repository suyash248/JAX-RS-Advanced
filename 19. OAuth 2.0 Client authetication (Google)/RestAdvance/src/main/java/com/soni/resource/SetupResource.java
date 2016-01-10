package com.soni.resource;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.client.oauth2.ClientIdentifier;
import com.soni.service.oauth2.SimpleOAuthService;

/**
 * Resource initializes Client ID (ID of application issued by Google).
 */
@Path("setup")
public class SetupResource {
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces("text/html")
    public Response setup(@QueryParam("clientId") String consumerKey,
                          @QueryParam("clientSecret") String consumerSecret) {

        SimpleOAuthService.setClientIdentifier(new ClientIdentifier(consumerKey, consumerSecret));
        final URI uri = UriBuilder.fromUri(uriInfo.getBaseUri()).path("tasks").build();

        return Response.seeOther(uri).build();
    }
}
