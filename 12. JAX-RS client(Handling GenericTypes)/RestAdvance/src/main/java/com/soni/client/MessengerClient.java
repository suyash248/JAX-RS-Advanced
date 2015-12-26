package com.soni.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.soni.messenger.model.Message;

public class MessengerClient {
	
	public static Client client;
	
	// Client should be created only once for an application and must be reused when required.
	static {
		if(client==null) {
			client = ClientBuilder.newClient();
		}
	}
	
	public static void main(String[] args) {
		WebTarget baseTarget = client.target("http://localhost:8080/RestAdvance/v1");
		WebTarget messageTarget = baseTarget.path("messages");
		List<Message> messages = messageTarget
														.queryParam("author", "abhi25")
														.request(MediaType.APPLICATION_JSON)
														.get(new GenericType<List<Message>>() { });
		System.out.println(messages);
	}
	
	
}