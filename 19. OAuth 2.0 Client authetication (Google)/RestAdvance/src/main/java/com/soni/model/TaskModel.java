package com.soni.model;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * Model (MVC) that contains one google task.
 */
@Data @XmlRootElement
public class TaskModel {
    private final String title;
}
