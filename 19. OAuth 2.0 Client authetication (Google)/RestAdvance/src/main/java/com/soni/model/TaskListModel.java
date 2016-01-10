package com.soni.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * Model (MVC) that contains one task list with many tasks.
 */
@Data
@XmlRootElement
public class TaskListModel {
    private final String title;
    private final List<TaskModel> tasks;
}