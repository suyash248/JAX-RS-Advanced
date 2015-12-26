package com.soni.resource;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value="hello")
@Singleton		
// By default a new instance is created of a resource each time a request to that resource is made. 
// But if we annotate a resource with @Singleton, Same instance of that resource is shared each time a request is made to that resource.
// Singleton resources are instantiated at application initiaization time.
public class MyResource {
	
	private int count;
	
	@GET
	@Produces(value=MediaType.TEXT_PLAIN)
	public String sayHello() {
		count++;
		return "Hello REST, This method is called "+count+" time(s)";
	}
}
