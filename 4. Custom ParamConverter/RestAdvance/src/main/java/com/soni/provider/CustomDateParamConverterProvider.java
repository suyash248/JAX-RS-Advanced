package com.soni.provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import com.soni.model.MyDate;

@Provider	// This annotation is used to register this class as a ParamConverterProvider.
public class CustomDateParamConverterProvider implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		if(rawType == MyDate.class) {
			return new CustomDataParamConverter<T>();
		}
		return null;
	}

}
