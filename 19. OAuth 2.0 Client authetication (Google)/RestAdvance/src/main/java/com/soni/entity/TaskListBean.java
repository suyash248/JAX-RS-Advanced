package com.soni.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Bean that contains one Google task list.
 */
@XmlRootElement @Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskListBean {

    @XmlAttribute
    private String id;
    @XmlAttribute
    private String title;
    @XmlAttribute(name = "items")
    private List<TaskBean> tasks;
}
