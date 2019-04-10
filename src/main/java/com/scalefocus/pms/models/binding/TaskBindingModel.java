// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.models.binding;

import com.scalefocus.pms.constants.ValidationConstants;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class TaskBindingModel {
    private Long id;
    private String taskNumber;
    private MeetingBindingModel meeting;
    private UserBindingModel taskCreator;
    @DateTimeFormat(pattern = ValidationConstants.DATE_PATTERN)
    private Date meetingDate;
    @NotNull
    @Size(min = 3, max = 50)
    private String title;
    private String projectPhase;
    private UserBindingModel user;
    private TeamBindingModel team;
    @DateTimeFormat(pattern = ValidationConstants.DATE_PATTERN)
    @NotNull
    private Date dateCreated;
    @DateTimeFormat(pattern = ValidationConstants.DATE_PATTERN)
    @NotNull
    private Date deadline;
    @NotNull
    private Integer status;
    @NotNull
    @Size(min = 3, max = 50)
    private String expectedOutcome;
    @NotNull
    @Size(min = 3, max = 100)
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public MeetingBindingModel getMeeting() {
        return meeting;
    }

    public void setMeeting(MeetingBindingModel meeting) {
        this.meeting = meeting;
    }

    public UserBindingModel getTaskCreator() {
        return taskCreator;
    }

    public void setTaskCreator(UserBindingModel taskCreator) {
        this.taskCreator = taskCreator;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProjectPhase() {
        return projectPhase;
    }

    public void setProjectPhase(String projectPhase) {
        this.projectPhase = projectPhase;
    }

    public UserBindingModel getUser() {
        return user;
    }

    public void setUser(UserBindingModel user) {
        this.user = user;
    }

    public TeamBindingModel getTeam() {
        return team;
    }

    public void setTeam(TeamBindingModel team) {
        this.team = team;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getDateCreatedFormatted() {
        return formatDate(dateCreated);
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getDeadlineFormatted() {
        return formatDate(deadline);
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExpectedOutcome() {
        return expectedOutcome;
    }

    public void setExpectedOutcome(String expectedOutcome) {
        this.expectedOutcome = expectedOutcome;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(ValidationConstants.DATE_PATTERN, new Locale("en"));
        return dateFormat.format(date);
    }
}
