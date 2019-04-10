// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.models.binding;

import com.scalefocus.pms.constants.ValidationConstants;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

public class PhaseBindingModel {
    private Long id;
    private String phaseName;
    @DateTimeFormat(pattern = ValidationConstants.DATE_PATTERN)
    private Date phaseStartDate;
    @DateTimeFormat(pattern = ValidationConstants.DATE_PATTERN)
    private Date phaseEndDate;
    private ProjectBindingModel project;
    private Double phaseBudget;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public Date getPhaseStartDate() {
        return phaseStartDate;
    }

    public String getPhaseStartDateFormatted() {
        return formatDate(phaseStartDate);
    }

    /**
     * @param phaseStartDate - The start date of the phase.
     */
    public void setPhaseStartDate(Date phaseStartDate) {
        if (phaseStartDate == null) {
            this.phaseStartDate = Date.from(Instant.now());
        } else {
            this.phaseStartDate = phaseStartDate;
        }
    }

    public Date getPhaseEndDate() {
        return phaseEndDate;
    }

    public String getPhaseEndDateFormatted() {
        return formatDate(phaseEndDate);
    }

    /**
     * @param phaseEndDate - The end date of the phase.
     */
    public void setPhaseEndDate(Date phaseEndDate) {
        if (phaseEndDate == null) {
            this.phaseEndDate = Date.from(Instant.now());
        } else {
            this.phaseEndDate = phaseEndDate;
        }
    }

    public ProjectBindingModel getProject() {
        return project;
    }

    public void setProject(ProjectBindingModel project) {
        this.project = project;
    }

    public Double getPhaseBudget() {
        return phaseBudget;
    }

    public void setPhaseBudget(Double phaseBudget) {
        this.phaseBudget = phaseBudget;
    }

    private String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(ValidationConstants.DATE_PATTERN, new Locale("en"));
        return dateFormat.format(date);
    }
}
