package com.soni.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
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
		Response response = prepareRequestForMessageById(2L).invoke();
		System.out.println(response.readEntity(Message.class));
	}
	
	public static Invocation prepareRequestForMessageById(long messageId) {
		WebTarget baseTarget = client.target("http://localhost:8080/RestAdvance/v1");
		WebTarget messageTarget = baseTarget.path("messages");
		WebTarget singleMessageTarget = messageTarget.path("{messagesId}");
		Invocation invocation = singleMessageTarget
														.resolveTemplate("messagesId", messageId)
														.request(MediaType.APPLICATION_JSON)
														.buildGet();
		return invocation;
	}
	
}