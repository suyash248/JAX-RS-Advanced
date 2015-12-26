package com.soni.filter;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.internal.util.Base64;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

	private static String AUTHORIZATION_HEADER = "authorization";
	private static String AUTHORIZATION_PREFIX = "Basic ";
	private static String SECURED_URL_TOKEN = "/secured";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(requestContext.getUriInfo().getPath().contains(SECURED_URL_TOKEN)) {	
			String authHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER);
			
			if(StringUtils.isNotEmpty(authHeader)) {
				String authToken = authHeader.replaceFirst(AUTHORIZATION_PREFIX, "");
				String userNamePasswordDecoded = Base64.decodeAsString(authToken);
				StringTokenizer stringTokenizer = new StringTokenizer(userNamePasswordDecoded, ":");
				String userName = stringTokenizer.nextToken();
				String password = stringTokenizer.nextToken();
				if(userName.equals("suyash") && password.equals("soni1234")){
					return;
				}
			}
			Response response = Response
																.status(Status.UNAUTHORIZED)
																.entity("User is not authorized to access the resource")
																.build();
			requestContext.abortWith(response);
		}
	}

}
