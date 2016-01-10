package com.soni.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Bean that contain root information about {@link TaskListBean tasklists} registered for one user.
 */
@XmlRootElement @Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskRootBean {
    @XmlAttribute
    private List<TaskListBean> items;

    @XmlAttribute
    private String kind;
}
