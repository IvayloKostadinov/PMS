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
 * This model class.
 * represents Clients.
 * table from the Database.
 */
@Entity
@Table(name = "CLIENTS")
public class Client {

    @Id
    @Column(name = "CLIENT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTS_SEQ")
    @SequenceGenerator(name = "CLIENTS_SEQ", sequenceName = "CLIENTS_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "CLIENT_NAME")
    private String clientName;


    public Client() {

    }

    /**
     * @param clientName - name of the clients.
     */
    public Client(String clientName) {
        this.clientName = clientName;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

}
