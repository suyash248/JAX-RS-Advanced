package com.soni.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.soni.model.MyDate;

@Path(value="date/{dateString}")
public class DateResource {

		@GET
		public String getDate(@PathParam(value="dateString") MyDate myDate){
			return myDate.toString();
		}
}

/*
	PathConverters are responsibe for converting the params(e.g. HeaderParam, PathParam, QueryParam, CookieParam etc.) to java object.

	For writing our own param converter, We need to deal with 2 interfaces - 
		1. ParamConverterProvider - Provides the appropriate ParamConverter, which does the actual conversion (param to java object).
		2. ParamConverter - Contains the actual conversion (param to java object).

	In above example, There must be a ParamConverter which can convert the captured path param {dateString} and converts it to 'MyDate' object.
	If no such converter is found, Jersey will throw an exception at the time of initialization.
*/