package com.soni.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class LoggingFilter implements ContainerResponseFilter, ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("\n---------------------------------------------------------REQUEST HEADERS-------------------------------------------------------------\n");
		System.out.println("HEADERS : "+requestContext.getHeaders());
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		System.out.println("\n---------------------------------------------------------RESPONSE HEADERS-------------------------------------------------------------\n");
		System.out.println("HEADERS : "+responseContext.getHeaders());
	}

}
