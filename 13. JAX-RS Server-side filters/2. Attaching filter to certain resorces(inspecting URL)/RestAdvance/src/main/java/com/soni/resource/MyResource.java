package com.soni.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("messages")
public class MyResource {
	
	@GET
	@Path("filter/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {										// PoweredByResponseFilter is associated with this resource.
		return "Hello Filters";
	}
	
	@GET
	@Path("hi")
	@Produces(MediaType.TEXT_PLAIN)
	public String hi() {											// No filter is associated with this resource.
		return "Hi, There is no filter";
	}
}
