package com.soni.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path(value="hello/{userName}")
/* 
 	In case of 'Request-Per-Resource' resource, Resource is instantiated immediately 'AFTER' the request is made to that resource. So 
 	params(Path params, query params etc.) will be injected. 
 */
public class MyResource {
	/*
	 Params annotations can either be used on class level variables or on method arguments(local variables).
	 If we use them on class level variables, Those variables can be reused in several resource methods.
	 But if we use them on method arguments, Then each resource method must have it's own local variable(s) in order to bind param(s)' value(s).
	 */
	@PathParam(value="userName") private String userName;
	@QueryParam(value="userId") private String userId;
	
	@GET
	@Produces(value=MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Path param is : "+userName + "\nQuery param is : "+userId;
	}
}
