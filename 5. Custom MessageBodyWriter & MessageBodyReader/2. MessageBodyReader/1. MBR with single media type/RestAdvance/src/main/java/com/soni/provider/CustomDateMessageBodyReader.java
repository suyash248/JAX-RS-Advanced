package com.soni.provider;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.soni.model.MyDate;

@Provider
@Consumes(value=MediaType.TEXT_PLAIN)
public class CustomDateMessageBodyReader<T> implements MessageBodyReader<T> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		if(type==MyDate.class)
			return true;
		else 
			return false;
	}

	@Override
	public T readFrom(Class<T> type, 
			Type genericType, 
			Annotation[] annotations, 
			MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, 
			InputStream in) throws IOException, WebApplicationException {
		MyDate myDate = new MyDate();
		BufferedReader buf = new BufferedReader(new InputStreamReader(in));
		String line;
		
		/* Format - 1
			18-12-2015
		*/
		/*while( (line=buf.readLine()) != null ){
			String lineArray[] = line.split("-");
			myDate.setDay(Integer.parseInt(lineArray[0]));
			myDate.setMonth(Integer.parseInt(lineArray[1]));
			myDate.setYear(Integer.parseInt(lineArray[2]));
		}*/
		
		/* Format - 2
			day=18
			month=12
			year=2015
		*/
		while( (line=buf.readLine()) != null ){
			String lineArray[] = line.split("=");
			String fieldName = lineArray[0];
			String fieldValue = lineArray[1];
			try {
				Field field = myDate.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				field.set(myDate, new Integer(fieldValue));
			} catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return (T) myDate;
	}

}
