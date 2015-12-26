package com.soni.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.soni.model.MyDate;

@Path(value="date")
public class DateResource {

		@POST
		@Path(value="text")
		@Consumes(value=MediaType.TEXT_PLAIN)
		@Produces(value=MediaType.TEXT_PLAIN)
		public String getDateAsText(MyDate myDate) {
			return myDate.toString();
		}
		
		@POST
		@Path(value="json")
		@Consumes(value=MediaType.APPLICATION_JSON)
		@Produces(value=MediaType.APPLICATION_JSON)
		public String getDateAsJson(MyDate myDate) {
			return myDate.toString();
		}
}
