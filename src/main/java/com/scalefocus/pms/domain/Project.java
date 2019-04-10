// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.domain;

import com.scalefocus.pms.constants.ValidationConstants;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECTS")
public class Project {

    /**
     * This model class represents Projects table from the Database.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJECTS_SEQ")
    @SequenceGenerator(name = "PROJECTS_SEQ", allocationSize = 1)
    @Column(name = "PROJECT_ID")
    private Long id;

    @Column(name = "PROJECT_NAME")
    private String projectName;

    @Column(name = "PROJECT_NUMBER")
    private String projectNumber;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "MANAGER_ID")
    private User manager;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToMany(mappedBy = "project", targetEntity = Phase.class, cascade = CascadeType.REMOVE)
    private List<Phase> phases;

    @Column(name = "IS_PUBLIC")
    private Boolean accessStatus;

    @Column(name = "BUDGET")
    private Double budget;

    @Column(name = "START_DATE")
    private Timestamp startDate;

    @Column(name = "END_DATE")
    private Timestamp endDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Documentation> documentations;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Meeting> meetings;

    public Project() {
    }

    /**
     * @param projectName - name of the project.
     * @param client      - name of the client.
     * @param budget      - the budget for creating the project.
     * @param startDate   - the start date of the project development.
     * @param endDate     - the end date of the project development.
     * @param description - the description of the project.
     */
    public Project(String projectName, Client client, Double budget, Date startDate,
                   Date endDate, String description) {
        this.projectName = projectName;
        this.client = client;
        this.budget = budget;
        this.startDate = new Timestamp(startDate.getTime());
        this.endDate = new Timestamp(endDate.getTime());
        this.description = description;
        this.documentations = new ArrayList<>();
        this.meetings = new ArrayList<>();
        this.phases = new ArrayList<>();
    }

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

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public String getStartDateFormatted() {
        return formatDate(this.startDate);
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public String getEndDateFormatted() {
        return formatDate(this.endDate);
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public List<Documentation> getDocumentations() {
        return documentations;
    }

    public void setDocumentations(List<Documentation> documentations) {
        this.documentations = documentations;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public void addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
    }

    public void addPhase(Phase phase) {
        this.phases.add(phase);
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    private String formatDate(Timestamp timestamp) {
        return timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern(ValidationConstants.DATE_PATTERN));
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Boolean getAccessStatus() {
        return accessStatus;
    }

    public String getStatusFormatted() {
        if (accessStatus) {
            return "Public";
        } else {
            return "Private";
        }
    }

    public void setAccessStatus(Boolean accessStatus) {
        this.accessStatus = accessStatus;
    }
}