package com.soni.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.soni.model.MyDate;

@Provider
@Produces(value={"text/csv", MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
public class CustomDateMessageBodyWriter<T> implements MessageBodyWriter<T> {
	@Override
	public boolean isWriteable(Class<?> type, 
															Type genericType, 
															Annotation[] annotations, 
															MediaType mediaType) {
		if(type==MyDate.class)
			return true;
		else 
			return false;
	}

	@Override
	public void writeTo(T t, 
										Class<?> type, 
										Type genericType,
										Annotation[] annotations, 
										MediaType mediaType,
										MultivaluedMap<String, Object> httpHeaders, 
										OutputStream out) throws IOException, WebApplicationException {
		if(type==MyDate.class) {
			if( MediaType.TEXT_PLAIN.equals(mediaType.toString()) ) {
				out.write(t.toString().getBytes());
			}
			else if( MediaType.APPLICATION_JSON.equals(mediaType.toString()) ) {	
				MyDate myDate = (MyDate) t;
				String jsonString = "{"
						+ "\"DAY\": " + myDate.getDay() + ", "
						+ "\"MONTH\": " + myDate.getMonth() + ", "
						+ "\"YEAR\": " + myDate.getYear()
						+ "}";
				out.write(jsonString.getBytes());
			}
			else {	 // text/csv
				MyDate myDate = (MyDate) t;
				String csvString = myDate.getDay() + ", " + myDate.getMonth() + ", " + myDate.getYear();
				out.write(csvString.getBytes());
			}
		}
	}
	
	// As of JAX-RS 2.0, the method has been deprecated and the value returned by the method is ignored
   // by a JAX-RS runtime. All {@code MessageBodyWriter} implementations are advised to return {@code -1}
   // from the method.
	@Override
	public long getSize(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

}
