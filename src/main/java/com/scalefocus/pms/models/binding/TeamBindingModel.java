// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.models.binding;

import java.util.List;
import java.util.Set;

/**
 * TeamBindingModel.
 */
public class TeamBindingModel {
    private Long id;
    private String teamName;
    private List<TaskBindingModel> tasks;
    private List<ProjectBindingModel> projects;
    private Set<UserBindingModel> users;


    public TeamBindingModel() {
    }

    /**
     * @param teamName - Team name.
     * @param tasks    - A list of all tasks.
     * @param users    - A list of all user.
     */
    public TeamBindingModel(String teamName,
                            List<TaskBindingModel> tasks, Set<UserBindingModel> users) {
        this.teamName = teamName;
        this.tasks = tasks;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<TaskBindingModel> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<TaskBindingModel> tasks) {
        this.tasks = tasks;
    }

    public Set<UserBindingModel> getUsers() {
        return this.users;
    }

    public void addUser(UserBindingModel user){
        this.users.add(user);
    }

    public void setUsers(Set<UserBindingModel> users) {
        this.users = users;
    }


    public List<ProjectBindingModel> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectBindingModel> projects) {
        this.projects = projects;
    }
}