// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "PHASES")
public class Phase {

    @Id
    @Column(name = "PHASE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHASES_SEQ")
    @SequenceGenerator(name = "PHASES_SEQ", sequenceName = "PHASES_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "PHASE_NAME")
    private String phaseName;

    @Column(name = "PHASE_START_DATE")
    private Timestamp phaseStartDate;

    @Column(name = "PHASE_END_DATE")
    private Timestamp phaseEndDate;

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @Column(name = "PHASE_BUDGET")
    private Double phaseBudget;

    public Phase() {
    }

    /**
     * Constructor for Phases with all fields
     *
     * @param phaseName - String phaseName.
     * @param startDate - Timestamp startDate.
     * @param endDate   - Timestamp endDate.
     * @param project   - Object of type Project.
     * @param budget    - Double budget.
     */
    public Phase(String phaseName, Date startDate, Date endDate,
                 Project project, Double budget) {
        this.phaseName = phaseName;
        this.phaseStartDate = new Timestamp(startDate.getTime());
        this.phaseEndDate = new Timestamp(endDate.getTime());
        this.project = project;
        this.phaseBudget = budget;
    }

    public Long getId() {
        return id;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public Timestamp getPhaseStartDate() {
        return phaseStartDate;
    }

    public void setPhaseStartDate(Timestamp phaseStartDate) {
        this.phaseStartDate = phaseStartDate;
    }

    public Timestamp getPhaseEndDate() {
        return phaseEndDate;
    }

    public void setPhaseEndDate(Timestamp phaseEndDate) {
        this.phaseEndDate = phaseEndDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Double getPhaseBudget() {
        return phaseBudget;
    }

    public void setPhaseBudget(Double phaseBudget) {
        this.phaseBudget = phaseBudget;
    }
}


