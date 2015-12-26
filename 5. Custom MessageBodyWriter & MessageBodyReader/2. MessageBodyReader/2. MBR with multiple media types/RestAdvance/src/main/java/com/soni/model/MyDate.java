package com.soni.model;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class MyDate {
	private Integer day;
	private Integer month;
	private Integer year;
	
	public static MyDate today() {
		Calendar cal = Calendar.getInstance();
		return new MyDate(cal.get(Calendar.DATE), cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
	}
	
	/*@Override
	public String toString() {
		String str = "day="+this.day+"\nmonth="+this.month+"\nyear="+this.month;
		return str;
	}*/
}
