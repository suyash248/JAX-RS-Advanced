package com.soni.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.glassfish.jersey.server.JSONP;

import com.soni.model.User;

@Path("user")
public class MyResource {
	
	@GET
	@Path("details")
	@JSONP(callback="userDetailsCallback", queryParam="userDetails")
	@Produces(value={"application/javascript"})
	public User getUserDetails() {							
		return new User("suyash248", 22, "Java Developer");
	}
	
}
