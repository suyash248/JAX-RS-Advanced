package com.soni.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.soni.model.MyDate;

@Path(value="date")
public class DateResource {

		@GET
		@Produces(value=MediaType.TEXT_PLAIN)
		public MyDate getDate(){
			return MyDate.today();
		}
}
