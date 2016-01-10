package com.soni.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * Model (MVC) that contains all task lists.
 */
@Data
@XmlRootElement
public class AllTaskListsModel {
    private final List<TaskListModel> taskListModels;
}