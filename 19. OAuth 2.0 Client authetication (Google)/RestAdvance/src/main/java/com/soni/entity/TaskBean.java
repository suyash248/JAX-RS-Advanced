package com.soni.entity;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Bean that contains one Google task.
 */
@XmlRootElement @Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskBean {
    @XmlAttribute
    private String title;

    @XmlAttribute
    private Calendar completed;
}