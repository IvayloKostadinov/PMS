// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * This model class represents Documentation table from the Database.
 */
@Entity
@Table(name = "PROJECT_DOCUMENTATIONS")
public class Documentation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJECT_DOCUMENTATIONS_SEQ")
    @SequenceGenerator(name = "PROJECT_DOCUMENTATIONS_SEQ", sequenceName = "PROJECT_DOCUMENTATIONS_SEQ",
            allocationSize = 1)
    @Column(name = "DOCUMENTATION_ID")
    private Long id;

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "USER_ID")
    private User creator;

    @Column(name = "MY_CONTENT")
    private String content;

    /**
     * @param project - project for which my_projects is crated.
     * @param content - my_projects content.
     */
    public Documentation(Project project, String content) {
        this.project = project;
        this.content = content;
    }

    public Documentation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
