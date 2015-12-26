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
@Priority(Priorities.AUTHENTICATION)
public class PoweredByFilter implements ContainerResponseFilter, ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("REQUEST : ----------------------------- PoweredByFilter executed ------------------------------\n");
		requestContext.getHeaders().add("X-Powered By", "Soni");
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		System.out.println("RESPONSE : ----------------------------- PoweredByFilter executed ----------------------------- \n");
		responseContext.getHeaders().add("X-Powered By", "Soni");
	}

}
