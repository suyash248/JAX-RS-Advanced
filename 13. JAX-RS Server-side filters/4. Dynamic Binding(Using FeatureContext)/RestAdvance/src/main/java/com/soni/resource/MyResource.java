package com.soni.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.soni.annotation.SupportedBy;

@Path("messages")
public class MyResource {
	
	@GET
	@SupportedBy		// FeatureContext registers  SupportedByResponseFilter filter with this resource method.
	@Path("hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {							
		return "Hello JAX-RS";
	}
	
	@GET
	@Path("hi")			// FeatureContext registers  PoweredByResponseFilter filter with this resource method.
	@Produces(MediaType.TEXT_PLAIN)
	public String hiPoweredBy() {						
		return "Hi JAX-RS";
	}
}
