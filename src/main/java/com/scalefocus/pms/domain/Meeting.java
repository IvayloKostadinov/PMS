// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * This model class represents Meeting table from the Database.
 */
@Entity
@Table(name = "MEETINGS")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEETINGS_SEQ")
    @SequenceGenerator(sequenceName = "MEETINGS_SEQ", name = "MEETINGS_SEQ", allocationSize = 1)
    @Column(name = "MEETING_ID")
    private Long id;

    @Column(name = "AGENDA")
    private String agenda;

    @Column(name = "MEETING_DATE")
    private Timestamp date;

    @Column(name = "MEETING_END")
    private Timestamp endTime;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "CREATED_BY")
    private User user;

    @ManyToOne(targetEntity = Project.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @Column(name = "MEETING_TYPE")
    private String meetingType;

    @Lob
    @Column(name = "NOTES")
    private String notes;

    @OneToMany(mappedBy = "meeting",cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Meeting() {
    }

    /**
     * @param agenda      - plan for the current meeting.
     * @param date        - date when the meeting.
     * @param meetingType - the type of the meeting.
     * @param notes       - notes for the current meeting.
     * @param endTime     - time when the meeting ends.
     * @param creator     - user who created the meeting.
     */
    public Meeting(String agenda, LocalDateTime date, String meetingType, String notes,
                   LocalDateTime endTime, User creator) {
        this.agenda = agenda;
        this.date = Timestamp.valueOf(date);
        this.meetingType = meetingType;
        this.notes = notes;
        this.tasks = new ArrayList<>();
        this.endTime = Timestamp.valueOf(endTime);
        this.user = creator;
    }

    public Long getId() {
        return id;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project projectNumber) {
        this.project = projectNumber;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }
}