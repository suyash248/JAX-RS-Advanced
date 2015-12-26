package com.soni.provider;

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
@Consumes(value={"text/csv", MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
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
		
		if( MediaType.TEXT_PLAIN.equals(mediaType.toString()) ) {
			while( (line=buf.readLine()) != null ){
				String lineArray[] = line.split("-");
				myDate.setDay(Integer.parseInt(lineArray[0]));
				myDate.setMonth(Integer.parseInt(lineArray[1]));
				myDate.setYear(Integer.parseInt(lineArray[2]));
			}
		} 
		
		else if( MediaType.APPLICATION_JSON.equals(mediaType.toString()) ) {
			while( (line=buf.readLine()) != null ){
				if(line.contains(":")){				// Check if a line contains ":". Then it's valid json pair.
					line = line.replaceAll("\"", "");		// Replace all quotes with empty string.
					String lineArray[] = line.split(":");
					String fieldName = lineArray[0].trim();
					String fieldValue = lineArray[1].trim();
					if(fieldValue.endsWith(",")) {		// Except last line, All the lines end with a comma.
						fieldValue = fieldValue.substring(0, fieldValue.length()-1).trim();
					}
					try {
						Field field = myDate.getClass().getDeclaredField(fieldName);
						field.setAccessible(true);
						field.set(myDate, new Integer(fieldValue));
					} catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		}
		
		else {		// text/csv
			while( (line=buf.readLine()) != null ){
				String lineArray[] = line.split(",");
				myDate.setDay(new Integer(lineArray[0].trim()));
				myDate.setMonth(new Integer(lineArray[1].trim()));
				myDate.setYear(new Integer(lineArray[2].trim()));
			}
		}
		return (T) myDate;
	}

}
