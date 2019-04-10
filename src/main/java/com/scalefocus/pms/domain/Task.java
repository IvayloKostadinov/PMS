// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TASKS")
public class Task {

    /**
     * This model class represents Task table from the Database.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASKS_SEQ")
    @SequenceGenerator(sequenceName = "TASKS_SEQ", name = "TASKS_SEQ", allocationSize = 1)
    @Column(name = "TASK_ID")
    private Long id;

    @Column(name = "TASK_NUMBER")
    private String taskNumber;

    @ManyToOne(targetEntity = Meeting.class)
    @JoinColumn(name = "MEETING_ID")
    private Meeting meeting;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "USER_TASK_CREATOR")
    private User taskCreator;

    @Column(name = "MEETING_DATE")
    private Timestamp meetingDate;

    @Column(name = "TITLE")
    private String title;

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @Column(name = "PROJECT_PHASE")
    private String projectPhase;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "DESIGNATED", nullable = true)
    private User user;

    @ManyToOne( targetEntity = Team.class)
    @JoinColumn(name = "DESIGNATED_TEAM", nullable = true)
    private Team team;

    @Column(name = "DATE_CREATED")
    private Timestamp dateCreated;

    @Column(name = "DEADLINE")
    private Timestamp deadline;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "EXPECTED_OUTCOME")
    private String expectedOutcome;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * @param meetingDate     - the date of the task meeting.
     * @param title           - title of the task.
     * @param projectPhase    - the phase that the project is currently in.
     * @param dateCreated     - the date that the task is created on.
     * @param deadline        - that date that that task should be completed on.
     * @param status          - the current status of the task.
     * @param expectedOutcome - what is the expected outcome of the task.
     * @param description     - the description of the task.
     */
    public Task(LocalDateTime meetingDate, String title, String projectPhase,
                LocalDateTime dateCreated, LocalDateTime deadline,
                Integer status, String expectedOutcome, String description) {
        this.title = title;
        this.projectPhase = projectPhase;
        this.status = status;
        this.expectedOutcome = expectedOutcome;
        this.description = description;
        this.meetingDate = Timestamp.valueOf(meetingDate);
        this.dateCreated = Timestamp.valueOf(dateCreated);
        this.deadline = Timestamp.valueOf(deadline);
    }

    public Task() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Timestamp getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Timestamp meetingDate) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
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

    public User getTaskCreator() {
        return taskCreator;
    }

    public void setTaskCreator(User taskCreator) {
        this.taskCreator = taskCreator;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
        project.addTask(this);
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
