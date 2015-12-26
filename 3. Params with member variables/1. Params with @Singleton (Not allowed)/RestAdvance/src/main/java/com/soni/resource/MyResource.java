package com.soni.resource;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path(value="hello/{userName}")
@Singleton
/*
 	In case of singleton resource, Resource is instantiated at the time of application initialization. So 
 	params(Path params, query params etc.) can not be injected. And an exception is thrown.
 	Here is the exception - 
 	
 	SEVERE: StandardWrapper.Throwable
	org.glassfish.jersey.server.model.ModelValidationException: Validation of the application resource model has failed during application initialization.
	[[FATAL] Parameter userName of private java.lang.String com.soni.resource.MyResource.userName cannot be injected into singleton resource.; 
	source='private java.lang.String com.soni.resource.MyResource.userName', 
	[FATAL] Parameter userId of private java.lang.String com.soni.resource.MyResource.userId cannot be injected into singleton resource.; 
	source='private java.lang.String com.soni.resource.MyResource.userId']
 */
public class MyResource {
	
	@PathParam(value="userName") private String userName;
	@QueryParam(value="userId") private String userId;
	
	@GET
	@Produces(value=MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Path param is : "+userName + "/nQuery param is : "+userId;
	}
}
