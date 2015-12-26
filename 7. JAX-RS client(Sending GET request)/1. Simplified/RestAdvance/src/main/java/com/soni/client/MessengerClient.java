package com.soni.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.soni.messenger.model.Message;

public class MessengerClient {
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		Response reponse = client.target("http://localhost:8080/RestAdvance/v1/messages/1").request(MediaType.APPLICATION_JSON).get();
		Message message = reponse.readEntity(Message.class);
		System.out.println(message);
	}
}

/*
OUTPUT - 
Message(
					id=1, 
					message=Java Developer, 
					created=Sat Dec 26 01:54:34 IST 2015, 
					author=suyash248, 
					comments={}, 
					links=[
						Link(
								link=http://localhost:8080/RestAdvance/v1/messages/1, 
								rel=self
						), 
						Link(
								link=http://localhost:8080/RestAdvance/v1/profiles/suyash248, 
								rel=profile
						), 
						Link(
								link=http://localhost:8080/RestAdvance/v1/messages/1/comments, 
								rel=comments
						)
					]
)

 */