package com.soni.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("messages")
public class MyResource {
	
	@GET
	@Path("secured/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {							
		return "Hello, This is secured resorce.";
	}
	
	@GET
	@Path("hi")
	@Produces(MediaType.TEXT_PLAIN)
	public String hi() {						
		return "Hi, This is public resource.";
	}
}
