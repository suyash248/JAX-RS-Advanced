package com.soni.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.soni.model.MyDate;

@Path(value="date")
public class DateResource {
		// You can have any media type as long as there are appropriate MessageBodyWriter & MessageBodyReader 
		// are available for handling those media types.
		@POST
		@Consumes(value={"text/csv", MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
		@Produces(value={"text/csv", MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
		public MyDate getDate2(MyDate myDate) {
			return myDate;
		}
	
}
