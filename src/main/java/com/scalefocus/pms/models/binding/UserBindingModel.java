// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.models.binding;

import com.scalefocus.pms.constants.FieldConstants;

import java.util.List;
import java.util.Set;

public class UserBindingModel {
    private Long id;
    private String firstName;
    private String familyName;
    private String username;
    private String password;
    private String email;
    private String inboundEmail;
    private String outboundEmail;
    private Boolean status;
    private Set<RoleBindingModel> authorities;
    private Set<TeamBindingModel> teams;
    private List<MeetingBindingModel> meetings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleBindingModel> getAuthorities() {
        return authorities;
    }

    public void addAuthority(RoleBindingModel model) {
        this.authorities.add(model);
    }

    public void setAuthorities(Set<RoleBindingModel> authorities) {
        this.authorities = authorities;
    }

    public Set<TeamBindingModel> getTeams() {
        return teams;
    }

    public void setTeams(Set<TeamBindingModel> teams) {
        this.teams = teams;
    }

    public String getInboundEmail() {
        return inboundEmail;
    }

    public void setInboundEmail(String inboundEmail) {
        this.inboundEmail = inboundEmail;
    }

    public String getOutboundEmail() {
        return outboundEmail;
    }

    public void setOutboundEmail(String outboundEmail) {
        this.outboundEmail = outboundEmail;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<MeetingBindingModel> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<MeetingBindingModel> meetings) {
        this.meetings = meetings;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method that will return Active/Inactive based on the boolean field "status".
     *
     * @return - String Active/Inactive.
     */
    public String statusString() {
        if (this.status) {
            return FieldConstants.STATUS_ACTIVE;
        } else {
            return FieldConstants.STATUS_INACTIVE;
        }
    }
}
