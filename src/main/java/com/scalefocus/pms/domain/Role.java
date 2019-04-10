// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Role implements GrantedAuthority {
    /**
     * This model class represents Role Entity from Roles Table.
     */

    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLES_SEQ")
    @SequenceGenerator(name = "ROLES_SEQ", sequenceName = "ROLES_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "AUTHORITY")
    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Role() {

    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

}
