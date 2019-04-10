// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.domain;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User implements UserDetails {

    /**
     * This model class represents User Entity from Users Table.
     */

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "FAMILY_NAME")
    private String familyName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "INBOUND_EMAIL")
    private String inboundEmail;

    @Column(name = "OUTBOUND_EMAIL")
    private String outboundEmail;

    @Column(name = "STATUS")
    private Boolean status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> authorities;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "USER_TEAM",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "TEAM_ID"))
    private Set<Team> teams;

    @OneToMany(mappedBy = "author")
    private List<News> news;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Meeting> meetings;

    public User() {
    }

    /**
     * Public constructor that takes only the UNIQUE fields as parameters for User
     * Class.
     *
     * @param username - user name for the User.
     * @param password - password for the User.
     * @param email    - email for the User.
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Public constructor that takes all field as parameters for User class.
     *
     * @param firstName     - first name for the User.
     * @param familyName    - family name for the User.
     * @param password      - password for the User.
     * @param username      - user name for the User.
     * @param email         - email for the User.
     * @param inboundEmail  - inbound email for the user (Internal email set by
     *                      Admin).
     * @param outboundEmail outbound email for the user (External email set by the
     *                      User).
     * @param status        - status for the User. It can be Active/Disabled.
     * @param tasks         - list of assigned tasks for the User.
     * @param authorities   - set of Roles that the User has.
     * @param projects      - list of assigned Projects for the User.
     * @param meetings      - list of created Meetings for the User
     */
    public User(String firstName, String familyName, String password, String username, String email,
                String inboundEmail, String outboundEmail, Boolean status, List<Task> tasks, Set<Role> authorities,
                List<Project> projects, List<News> news, List<Meeting> meetings) {

        this.firstName = firstName;
        this.familyName = familyName;
        this.password = password;
        this.username = username;
        this.email = email;
        this.inboundEmail = inboundEmail;
        this.outboundEmail = outboundEmail;
        this.status = status;
        this.tasks = tasks;
        this.authorities = authorities;
        this.projects = projects;
        this.news = news;
        this.meetings = meetings;

    }

    /**
     * Public constructor that takes all field except Tasks , Roles and Projects for
     * User class
     *
     * @param firstName     - first name for the User.
     * @param familyName    - family name for the User.
     * @param password      - password for the User.
     * @param username      - user name for the User.
     * @param email         - email for the User.
     * @param inboundEmail  - inbound email for the user (Internal email set by
     *                      Admin).
     * @param outboundEmail outbound email for the user (External email set by the
     *                      User).
     * @param status        - status for the User. It can be Active/Disabled.
     */

    public User(String firstName, String familyName, String password, String username, String email,
                String inboundEmail, String outboundEmail, Boolean status) {

        this.firstName = firstName;
        this.familyName = familyName;
        this.password = password;
        this.username = username;
        this.email = email;
        this.inboundEmail = inboundEmail;
        this.outboundEmail = outboundEmail;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    @Override
    public Set<Role> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setAuthority(Role role) {
        this.authorities.add(role);
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<News> getNewsList() {
        return this.news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public List<News> getNews() {
        return news;
    }
}
