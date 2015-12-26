package com.soni.model;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement
@Getter@Setter @NoArgsConstructor @AllArgsConstructor
public class User {
	private String userName;
	private Integer age;
	private String profession;
}
