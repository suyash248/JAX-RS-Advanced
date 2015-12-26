package com.soni.provider.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class SupportedByFilter implements ContainerResponseFilter, ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("REQUEST : ----------------------------- SupportedByFilter executed ----------------------------- \n");
		requestContext.getHeaders().add("X-Suppored By", "Suyash");
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		System.out.println("RESPONSE : ----------------------------- SupportedByFilter executed ----------------------------- \n");
		responseContext.getHeaders().add("X-Suppored By", "Suyash");
	}

}
