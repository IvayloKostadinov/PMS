// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.models.binding;

import com.scalefocus.pms.constants.ValidationConstants;
import com.scalefocus.pms.domain.Role;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class RegisterBindingModel {

    @NotNull
    @Size(min = ValidationConstants.USERNAME_MIN_LENGTH, max = ValidationConstants.USERNAME_MAX_LENGTH)
    private String username;

    @NotNull
    @Email
    @Size(min = ValidationConstants.EMAIL_MIN_LENGTH, max = ValidationConstants.EMAIL_MAX_LENGTH)
    private String email;

    @NotNull
    @Min(ValidationConstants.PASSWORD_MIN_LENGTH)
    private String password;

    @NotNull
    @Min(ValidationConstants.PASSWORD_MIN_LENGTH)
    private String confirmPassword;

    @NotNull
    @Size(max = ValidationConstants.NAME_MAX_LENGTH)
    private String firstName;

    @NotNull
    @Size(max = ValidationConstants.NAME_MAX_LENGTH)
    private String familyName;

    @NotNull
    private Boolean status;

    @NotNull
    private Set<Role> authorities;

    public RegisterBindingModel() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> roles) {
        this.authorities = roles;
    }

    public void setAuthorities(Role role) {
        this.authorities.add(role);
    }

}
