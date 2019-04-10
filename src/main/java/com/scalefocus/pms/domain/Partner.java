// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * This model class represents Partners table from the Database.
 */
@Entity
@Table(name = "PARTNERS")
public class Partner {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTNERS_SEQ")
    @SequenceGenerator(sequenceName = "PARTNERS_SEQ", name = "PARTNERS_SEQ",  allocationSize = 1)
    @Column(name = "PARTNER_ID")
    private Long id;

    @Column(name = "PARTNER_NAME")
    private String name;

    /**
     * Constructor for Partner.
     *
     * @param name - name of the Partner.
     */
    public Partner(String name) {
        this.name = name;
    }

    public Partner() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
