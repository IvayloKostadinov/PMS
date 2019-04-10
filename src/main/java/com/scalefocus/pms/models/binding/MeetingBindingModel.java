// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.models.binding;

import com.scalefocus.pms.constants.ValidationConstants;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetingBindingModel {

    private Long id;
    private String meetingTheme;
    @DateTimeFormat(pattern = ValidationConstants.DATE_TIME_PATTERN)
    private Date startDate;
    @DateTimeFormat(pattern = ValidationConstants.DATE_TIME_PATTERN)
    private Date endTime;
    private UserBindingModel createdBy;
    private String meetingType;
    private ProjectBindingModel project;
    private String notes;
    public List<TaskBindingModel> getTasks() {
        return tasks;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public void setTasks(List<TaskBindingModel> tasks) {
        this.tasks = tasks;
    }

    private List<TaskBindingModel> tasks = new ArrayList<>();


    public String getMeetingTheme() {
        return meetingTheme;
    }

    public void setMeetingTheme(String meetingTheme) {
        this.meetingTheme = meetingTheme;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public UserBindingModel getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserBindingModel createdBy) {
        this.createdBy = createdBy;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }


    public ProjectBindingModel getProject() {
        return project;
    }

    public void setProject(ProjectBindingModel project) {
        this.project = project;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}