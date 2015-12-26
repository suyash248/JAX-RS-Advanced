package com.soni.model;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class MyDate {
	private Integer day;
	private Integer month;
	private Integer year;
	
	public static MyDate today() {
		Calendar cal = Calendar.getInstance();
		return new MyDate(cal.get(Calendar.DATE), cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
	}
}
