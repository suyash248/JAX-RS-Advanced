package com.soni.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.soni.model.MyDate;

@Path(value="date")
public class DateResource {

		@GET
		@Path(value="text")
		@Produces(value=MediaType.TEXT_PLAIN)
		public MyDate getDateAsText(){
			return MyDate.today();
		}
		
		@GET
		@Path(value="json")
		@Produces(value=MediaType.APPLICATION_JSON)
		public MyDate getDateAsJson(){
			return MyDate.today();
		}
}
