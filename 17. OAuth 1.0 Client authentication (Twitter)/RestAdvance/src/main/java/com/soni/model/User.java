package com.soni.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter@Setter @NoArgsConstructor @AllArgsConstructor
public class User {
	@XmlElement(name = "name")
    private String name;
}
