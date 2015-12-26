package com.soni.provider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.ext.ParamConverter;

import com.soni.model.MyDate;

public class CustomDataParamConverter<T> implements ParamConverter<T> {

	@Override
	public T fromString(String dateString) {
		Calendar cal = Calendar.getInstance();
		if("tomorrow".equals(dateString)){
			cal.add(Calendar.DATE, 1);
		} else if("yesterday".equals(dateString)){
			cal.add(Calendar.DATE, -1);
		}
				
		MyDate myDate = new MyDate();
		myDate.setDay(cal.get(Calendar.DATE));
		myDate.setMonth(cal.get(Calendar.MONTH) + 1);	// Calendar.MONTH starts from 0.
		myDate.setYear(cal.get(Calendar.YEAR));
		return (T) myDate;
	}

	@Override
	public String toString(T myBean) {
		if(null != myBean){
			return myBean.toString();
		} else {
			return null;
		}
	}

}
