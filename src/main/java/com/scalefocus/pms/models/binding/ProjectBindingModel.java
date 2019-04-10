// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.models.binding;

import com.scalefocus.pms.constants.ValidationConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProjectBindingModel {
    private Long id;

    @NotNull
    @Size(max = ValidationConstants.USERNAME_MAX_LENGTH)
    private String projectName;

    @NotNull
    private Integer projectNumber;

    private UserBindingModel manager;
    private ClientBindingModel client;
    private TeamBindingModel team;
    private List<PhaseBindingModel> phases;

    @NotNull
    private Double budget;

    @NotNull
    @DateTimeFormat(pattern = ValidationConstants.DATE_PATTERN)
    private Date startDate;

    @NotNull
    @DateTimeFormat(pattern = ValidationConstants.DATE_PATTERN)
    private Date endDate;

    @NotNull
    private String description;
    private Boolean accessStatus;
    private List<TaskBindingModel> tasks = new ArrayList<>();
    private List<DocumentationBindingModel> documentations = new ArrayList<>();
    private List<MeetingBindingModel> meetings = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(Integer projectNumber) {
        this.projectNumber = projectNumber;
    }

    public UserBindingModel getManager() {
        return manager;
    }

    public void setManager(UserBindingModel manager) {
        this.manager = manager;
    }

    public ClientBindingModel getClient() {
        return client;
    }

    public void setClient(ClientBindingModel client) {
        this.client = client;
    }

    public TeamBindingModel getTeam() {
        return team;
    }

    public void setTeam(TeamBindingModel team) {
        this.team = team;
    }

    public List<PhaseBindingModel> getPhases() {
        return phases;
    }

    public void setPhases(List<PhaseBindingModel> phases) {
        this.phases = phases;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getStartDateFormatted() {
        return formatDate(startDate);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getEndDateFormatted() {
        return formatDate(endDate);
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TaskBindingModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskBindingModel> tasks) {
        this.tasks = tasks;
    }

    public List<DocumentationBindingModel> getDocumentations() {
        return documentations;
    }

    public void setDocumentations(List<DocumentationBindingModel> documentations) {
        this.documentations = documentations;
    }

    public List<MeetingBindingModel> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<MeetingBindingModel> meetings) {
        this.meetings = meetings;
    }

    public String getStatusFormatted() {
        if (accessStatus) {
            return "Public";
        } else {
            return "Private";
        }
    }

    public Boolean getAccessStatus() {
        return accessStatus;
    }

    public void setAccessStatus(Boolean accessStatus) {
        this.accessStatus = accessStatus;
    }

    private String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(ValidationConstants.DATE_PATTERN, new Locale("en"));
        return dateFormat.format(date);
    }
}
