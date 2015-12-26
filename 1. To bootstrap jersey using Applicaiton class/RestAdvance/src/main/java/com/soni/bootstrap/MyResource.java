package com.soni.bootstrap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value="hello")
public class MyResource {
	
	@GET
	@Produces(value=MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello REST";
	}
}
